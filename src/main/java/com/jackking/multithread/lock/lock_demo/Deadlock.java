package com.jackking.multithread.lock.lock_demo;


/**
 * @author Created by JackKing on 2020/4/29.
 */
public class Deadlock {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread t1 = new Thread(new SynAddRunalbe(obj1,obj2,1,2,true));
        t1.setName("thread1");
        t1.start();

        Thread t2 = new Thread(new SynAddRunalbe(obj1,obj2,2,1,false));
        t2.setName("thread2");
        t2.start();
    }
}


class SynAddRunalbe implements Runnable{
    private Object obj1;
    private Object obj2;
    private int a,b;
    private boolean flag;

    public SynAddRunalbe(Object obj1, Object obj2, int a, int b, boolean flag) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.a = a;
        this.b = b;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if (flag){
                synchronized (obj1){
                    Thread.sleep(100);
                    synchronized (obj2){
                        System.out.print(a+b);
                    }
                }
            }else{
                synchronized (obj2){
                    Thread.sleep(100);
                    synchronized (obj1){
                        System.out.println(a+b);
                    }
                }
            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}