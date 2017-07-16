package ru.catran.multithreading;

/**
 * Created by comp on 16.07.2017.
 */
public class MFU implements Runnable{

    Object s = new Object();
    Object p = new Object();
    int scanValue = 0;
    int printValue = 0;

    private void scan() {
        synchronized (s){
            scanValue++;
            System.out.println(Thread.currentThread().getName() + " отсканировал " + scanValue + " листов");

        }
    }

    private void print() {
        synchronized (p) {
            printValue++;
            System.out.println(Thread.currentThread().getName() + " напечатал "  + printValue + " листов");
        }
    }

    public static void main(String[] args) {

        Thread mfu1 = new Thread(new MFU());
        Thread mfu2 = new Thread(new MFU());
        mfu1.setName("MFU1");
        mfu2.setName("MFU2");
        mfu1.start();
        mfu2.start();

    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            scan();
            print();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
