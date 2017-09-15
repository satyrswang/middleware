package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bean.Account;
import stub.UserManagerInterface;

public class Main {

	public static void main(String[] args) {

		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 2015);
			UserManagerInterface userManagerInterface = (UserManagerInterface) registry.lookup("userManager");

			System.out.println(userManagerInterface.getTestString());

			Account ac = userManagerInterface.getAdminAccount();
			System.out.println("Account Info: " + ac.getUsername() + " / " + ac.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}注册，接口，接口获得account，