package myrmi.registry;

import myrmi.exception.RemoteException;
import myrmi.registry.LocateRegistry;

public class Registry1 {
    public static void main(String args[]) {
        try {
            LocateRegistry.createRegistry("127.0.2.1");
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("RMI Registry is running...");

    }
}
