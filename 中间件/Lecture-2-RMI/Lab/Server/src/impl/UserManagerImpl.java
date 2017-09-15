package impl;

import java.rmi.RemoteException;

import stub.UserManagerInterface;
import bean.Account;

public class UserManagerImpl implements UserManagerInterface {

	public UserManagerImpl() throws RemoteException {
	}

	public String getUsername() throws RemoteException {
		return "Hello World";
	}

	public Account getAdminAccount() throws RemoteException {
		Account account = new Account();
		account.setUsername("admin");
		account.setPassword("admin");

		return account;
	}
}