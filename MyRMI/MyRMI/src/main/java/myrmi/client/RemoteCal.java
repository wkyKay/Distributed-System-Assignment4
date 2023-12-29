package myrmi.client;

import myrmi.Remote;

public interface RemoteCal extends Remote {

    int selectOp(String op, int a, int b);

}