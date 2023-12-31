package myrmi.server;

import myrmi.Remote;
import myrmi.client.RemoteCal;
import myrmi.demo.Payment;
import myrmi.registry.Registry;

import java.lang.reflect.Proxy;

public class Util {


    public static Remote createStub(RemoteObjectRef ref) {
        //TODO: finish here, instantiate an StubInvocationHandler for ref and then return a stub
        StubInvocationHandler invocationHandler = new StubInvocationHandler(ref.getHost(), ref.getPort(), ref.getObjectKey());
        if(ref.getInterfaceName().equals("myrmi.client.RemoteCal")){
            return (Remote) Proxy.newProxyInstance(RemoteCal.class.getClassLoader(), new Class<?>[]{RemoteCal.class}, invocationHandler);
        }else if(ref.getInterfaceName().equals("myrmi.registry.Registry")){
            return (Registry) Proxy.newProxyInstance(Registry.class.getClassLoader(), new Class<?>[]{Registry.class}, invocationHandler);
        }else if (ref.getInterfaceName().equals("myrmi.demo.Payment")){
            return (Payment) Proxy.newProxyInstance(Payment.class.getClassLoader(), new Class<?>[]{Payment.class}, invocationHandler);
        }else
            return null;

    }


}
