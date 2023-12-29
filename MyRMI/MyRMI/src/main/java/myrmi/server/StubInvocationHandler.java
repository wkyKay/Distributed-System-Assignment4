package myrmi.server;

import com.sun.corba.se.spi.ior.ObjectKey;
import myrmi.exception.RemoteException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

public class StubInvocationHandler implements InvocationHandler, Serializable {
    private String host;
    private int port;
    private int objectKey;

    public StubInvocationHandler(String host, int port, int objectKey) {
        this.host = host;
        this.port = port;
        this.objectKey = objectKey;
        System.out.printf("Stub created to %s:%d, object key = %d\n", host, port, objectKey);
    }

    public StubInvocationHandler(RemoteObjectRef ref) {
        this(ref.getHost(), ref.getPort(), ref.getObjectKey());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws RemoteException {
        /*TODO: implement stub proxy invocation handler here
         *  You need to do:
         * 1. connect to remote skeleton, send method and arguments
         * 2. get result back and return to caller transparently
         * */
        try (
                Socket socket = new Socket(host, port);
        ) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            sendInvocationDetails(out, method, args);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return receiveResult(in);
        } catch (IOException | ClassNotFoundException e) {
            throw new RemoteException();
        }
    }

    private void sendInvocationDetails(ObjectOutputStream out, Method method, Object[] args) throws IOException {
        out.writeInt(objectKey);
        out.writeUTF(method.getName());
        out.writeObject(method.getParameterTypes());
        out.writeObject(args);
    }

    private Object receiveResult(ObjectInputStream in) throws IOException, ClassNotFoundException {
        return in.readObject();
    }


}
