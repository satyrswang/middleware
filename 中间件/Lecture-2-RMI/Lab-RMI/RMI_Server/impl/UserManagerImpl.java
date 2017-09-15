package impl;

import java.rmi.RemoteException;

import bean.Account;
import stub.UserManagerInterface;

public class UserManagerImpl implements UserManagerInterface {

	public UserManagerImpl() throws RemoteException {
	}

	public String getTestString() throws RemoteException {
		return "Test String";
	}

	public Account getAdminAccount() throws RemoteException {
		Account account = new Account();
		account.setUsername("admin");
		account.setPassword("admin");

		return account;
	}
}