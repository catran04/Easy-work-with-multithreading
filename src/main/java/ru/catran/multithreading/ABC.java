package ru.catran.multithreading;

/**
 * Created by comp on 16.07.2017.
 */
public class ABC {
    static Object mon = new Object();
    static char queue = 'A';

    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (queue != 'A') {
                            try {
                                mon.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(queue);
                        queue = 'B';
                        mon.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (queue != 'B') {
                            try {
                                mon.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(queue);
                        queue = 'C';

                        mon.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while(queue != 'C') {
                            try {
                                mon.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(queue);
                        queue = 'A';

                        mon.notifyAll();
                    }
                }
            }
        }).start();

    }


}
