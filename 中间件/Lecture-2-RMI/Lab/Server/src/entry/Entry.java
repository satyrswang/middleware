package entry;

import impl.UserManagerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import stub.UserManagerInterface;

public class Entry {

	public static void main(String[] args) throws AlreadyBoundException,
			RemoteException {

		UserManagerImpl userManager = new UserManagerImpl();

		UserManagerInterface userManagerInterface = (UserManagerInterface) UnicastRemoteObject
				.exportObject(userManager, 0);

		Registry registry = LocateRegistry.createRegistry(2015);
		registry.rebind("userManager", userManagerInterface);

		System.out.println("In service...");
	}
}
