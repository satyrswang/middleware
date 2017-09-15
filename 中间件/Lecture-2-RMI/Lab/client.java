package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import stub.UserManagerInterface;
import bean.Account;

public class Main {

	public static void main(String[] args) {

		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 2015);

			UserManagerInterface userManagerInterface = (UserManagerInterface) registry
					.lookup("userManager");

			System.out.println(userManagerInterface.getUsername());

			Account ac = userManagerInterface.getAdminAccount();
			System.out.println("Account Info: " + ac.getUsername() + " / "
					+ ac.getPassword());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}