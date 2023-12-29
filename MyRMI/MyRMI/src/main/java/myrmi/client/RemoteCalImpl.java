package myrmi.client;

// MyRemoteImpl.java

public class RemoteCalImpl implements RemoteCal {
   public RemoteCalImpl(){
   }

    public int add(int a, int b) {
        return a+b;
    }

    public int minus(int a, int b) {
        return a-b;
    }

    public int mul(int a, int b) {
        return a*b;
    }

    public int prod(int a, int b) {
       if(b != 0)
            return a/b;
       else return 0;
    }
    public int selectOp(String op, int a, int b) {
        if (op.equals("add")) {
            return add(a, b);
        } else if (op.equals("minus")) {
            return minus(a, b);
        } else if (op.equals("mul")) {
            return mul(a, b);
        } else if (op.equals("prod")) {
            return prod(a, b);
        } else {
            System.out.println("illegal operation");
            return 0;
        }
    }

}
