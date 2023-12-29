package myrmi.server;

import myrmi.Remote;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Skeleton extends Thread {
    static final int BACKLOG = 5;
    private Remote remoteObj;

    private String host;
    private int port;
    private int objectKey;

    public int getPort() {
        return port;
    }

    public Skeleton(Remote remoteObj, RemoteObjectRef ref) {
        this(remoteObj, ref.getHost(), ref.getPort(), ref.getObjectKey());
    }

    public Skeleton(Remote remoteObj, String host, int port, int objectKey) {
        super();
        this.remoteObj = remoteObj;
        this.host = host;
        this.port = port;
        this.objectKey = objectKey;
        this.setDaemon(false);
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port, BACKLOG, java.net.InetAddress.getByName(host));
            System.out.println(serverSocket);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Handle each request using a handler thread
                new Thread(() -> {
                    SkeletonReqHandler reqHandler = new SkeletonReqHandler(clientSocket, remoteObj, objectKey);
                    reqHandler.start();
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
