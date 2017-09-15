package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

public class JTALab {

	public static void main(String[] args) {

		XADataSource xads1 = getMysqlXADS("jdbc:mysql://10.60.42.66/[database_name1]", 3306, "[username1]",
				"[password1]");
		XADataSource xads2 = getMysqlXADS("jdbc:mysql://10.60.42.66/[database_name2]", 3306, "[username2]",
				"[password2]");

		XAConnection xaconn1 = null, xaconn2 = null;
		Connection conn1 = null, conn2 = null;
		Statement stmt1 = null, stmt2 = null;
		XAResource xares1 = null, xares2 = null;

		MysqlXid xid1 = new MysqlXid(new byte[] { 0x01 }, new byte[] { 0x57 }, 0);
		MysqlXid xid2 = new MysqlXid(new byte[] { 0x01 }, new byte[] { 0x58 }, 0);

		try {
			xaconn1 = xads1.getXAConnection();
			conn1 = xaconn1.getConnection();
			stmt1 = conn1.createStatement();
			xares1 = xaconn1.getXAResource();

			xaconn2 = xads2.getXAConnection();
			conn2 = xaconn2.getConnection();
			stmt2 = conn2.createStatement();
			xares2 = xaconn2.getXAResource();

			conn1.setAutoCommit(false);
			conn2.setAutoCommit(false);

			xares1.start(xid1, XAResource.TMNOFLAGS);
			stmt1.execute("update t_account1 set balance = balance + 100 where id = 1");
			xares1.end(xid1, XAResource.TMSUCCESS);

			xares2.start(xid2, XAResource.TMNOFLAGS);
			stmt2.execute("update t_account2 set balance = balance - 100 where id = 1");
			xares2.end(xid2, XAResource.TMSUCCESS);

			int ret1 = xares1.prepare(xid1);
			int ret2 = xares2.prepare(xid2);

			if (ret1 == XAResource.XA_OK && ret2 == XAResource.XA_OK) {
				xares1.commit(xid1, false);
				xares2.commit(xid2, false);
				System.out.println("OK!");
			} else {
				xares1.rollback(xid1);
				xares2.rollback(xid2);
			}
		} catch (SQLException e) {
			try {
				xares1.rollback(xid1);
				xares2.rollback(xid2);
			} catch (XAException xae) {
				xae.printStackTrace();
			}
		} catch (XAException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt1.close();
				stmt2.close();
				conn1.close();
				conn2.close();
				xaconn1.close();
				xaconn2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static XADataSource getMysqlXADS(String url, int port, String user, String pass) {
		MysqlXADataSource xads = new MysqlXADataSource();
		xads.setUrl(url);
		xads.setPort(port);
		xads.setUser(user);
		xads.setPassword(pass);

		return xads;
	}
}
