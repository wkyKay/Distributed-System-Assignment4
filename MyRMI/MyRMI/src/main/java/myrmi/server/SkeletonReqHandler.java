package myrmi.server;

import myrmi.Remote;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class SkeletonReqHandler extends Thread {
    private Socket socket;
    private Remote obj;
    private int objectKey;

    public SkeletonReqHandler(Socket socket, Remote remoteObj, int objectKey) {
        this.socket = socket;
        this.obj = remoteObj;
        this.objectKey = objectKey;
    }

    /*TODO: implement method here
     * You need to:
     * 1. handle requests from stub, receive invocation arguments, deserialization
     * 2. get result by calling the real object, and handle different cases (non-void method, void method, method throws exception, exception in invocation process)
     * Hint: you can use an int to represent the cases: -1 invocation error, 0 exception thrown, 1 void method, 2 non-void method
     *
     *  */
    @Override
    public void run() {
        try{
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            int objectKey = in.readInt();
            String methodName = in.readUTF();
            Class<?>[] argTypes = (Class<?>[]) in.readObject();
            Object[] args = (Object[]) in.readObject();

            Method method = obj.getClass().getMethod(methodName, argTypes);
            System.out.println(methodName + " invoked");
            Object result = method.invoke(obj, args);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(result);

            in.close();
            out.close();
            socket.close();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log the exception
            }
        }
    }

}
