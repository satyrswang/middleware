package entry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import impl.UserManagerImpl;
import stub.UserManagerInterface;

public class Entry {

	public static void main(String[] args) {

		try {
			UserManagerImpl userManager = new UserManagerImpl();

			UserManagerInterface userManagerInterface = (UserManagerInterface) UnicastRemoteObject
					.exportObject(userManager, 0);

			Registry registry = LocateRegistry.createRegistry(2015);
			registry.rebind("userManager", userManagerInterface);

			System.out.println("In service...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
