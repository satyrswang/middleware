package stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

import bean.Account;

public interface UserManagerInterface extends Remote {

	public String getTestString() throws RemoteException;

	public Account getAdminAccount() throws RemoteException;
}
