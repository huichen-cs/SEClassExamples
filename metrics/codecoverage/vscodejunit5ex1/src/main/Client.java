package main;
public class Client {
    public void checkBool(boolean x, boolean y, boolean  z) {
        if (x) {
            if (y && z) {
                showFlag(0);
            } else {
                showFlag(1);
            }
        } else {
            System.out.println("x is " + x);
        }
    }

    public void showFlag(int flag) {
        System.out.printf("Flag is %d", flag);
    }
}