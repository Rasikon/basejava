package ru.javawebinar.basejava;

public class MainConcurrency {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+ ", " + Thread.currentThread().getState());
        }).start();
        
        System.out.println(thread0.getState());
    }
}
