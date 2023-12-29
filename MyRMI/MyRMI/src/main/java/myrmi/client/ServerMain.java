package myrmi.client;

import myrmi.Remote;
import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;
import myrmi.server.UnicastRemoteObject;

public class ServerMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            Remote remoteObj = UnicastRemoteObject.exportObject(new RemoteCalImpl(),"127.0.0.1",9999 );
            registry.bind("myrmi.client.RemoteCal", remoteObj);
            System.out.println("Remote object registered.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
