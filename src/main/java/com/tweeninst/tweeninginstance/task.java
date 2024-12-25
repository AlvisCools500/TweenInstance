package com.tweeninst.tweeninginstance;

import javafx.concurrent.Task;

public class task {
    public static void Wait(double Seconds) {
        try {
            Thread.sleep((int) (Seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitTask() {

    }

    public static Thread spawnThread_Returnal(Runnable f) {
        Thread thread = new Thread(f);
        thread.start();

        return thread;
    }

    public static void spawnThread(Runnable f) {
        Thread thread = spawnThread_Returnal(f);
    }

    public static Thread spawnTask_Returnal(Runnable f) {
        Task<Void> resTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    f.run();
                }catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        Thread myThread = new Thread(resTask);
        myThread.setDaemon(true);
        myThread.start();

        return myThread;
    }

    public static void spawnTask(Runnable f) {
        Thread myThread = spawnTask_Returnal(f);
    }
}

