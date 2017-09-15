package stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

import bean.Account;

public interface UserManagerInterface extends Remote {

	public String getUsername() throws RemoteException;

	public Account getAdminAccount() throws RemoteException;
}
