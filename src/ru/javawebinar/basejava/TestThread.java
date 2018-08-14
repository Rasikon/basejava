package ru.javawebinar.basejava;

public class TestThread {
    public static String Lock1 = "lock1";
    public static String Lock2 = "lock2";

    public static void main(String args[]) {
        ThreadDemo1 T1 = new ThreadDemo1(Lock1, Lock2);
        ThreadDemo1 T2 = new ThreadDemo1(Lock2, Lock1);
        T1.start();
        T2.start();
    }

    private static class ThreadDemo1 extends Thread {
        Object lock1;
        Object lock2;

        public ThreadDemo1(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        public void run() {
            System.out.println("Waiting "+lock1);
            synchronized (lock1) {
                System.out.println("Holding "+lock1);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println("Waiting "+ lock2);

                synchronized (lock2) {
                    System.out.println("Holding "+lock2);
                }
            }
        }
    }
}

