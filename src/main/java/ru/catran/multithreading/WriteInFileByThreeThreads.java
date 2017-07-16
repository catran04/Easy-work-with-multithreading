package ru.catran.multithreading;

import java.io.*;

/**
 * Created by comp on 16.07.2017.
 */
public class WriteInFileByThreeThreads implements Runnable {
    static Object mon = new Object();

    private void writeInFile() throws IOException{

        FileWriter fileWriter = new FileWriter("1.txt", true);
        String s = "abc";
        fileWriter.write(s);
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Thread thread1 = new Thread(new WriteInFileByThreeThreads());
        Thread thread2 = new Thread(new WriteInFileByThreeThreads());
        Thread thread3 = new Thread(new WriteInFileByThreeThreads());
        thread1.start();
        thread2.start();
        thread3.start();



    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                writeInFile();
                Thread.sleep(20);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
