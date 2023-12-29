package myrmi.client;

import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;

import java.util.Random;

public class ClientMain {
    public static void main(String[] args) {
        try {
            String []Ops = new String[]{"add","minus","mul","prod"};
            Random random = new Random();
            int selected = random.nextInt(4);
            int c1 = random.nextInt(20);
            int c2 = random.nextInt(20);
            Registry registry = LocateRegistry.getRegistry();
            RemoteCal r = (RemoteCal) registry.lookup("myrmi.client.RemoteCal");
            int result = r.selectOp(Ops[selected], c1, c2);
            System.out.printf("%d %s %d = %d", c1, Ops[selected], c2, result);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
