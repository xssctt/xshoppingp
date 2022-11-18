package com.javaclimb.xshopping.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class main {


    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> local=new ThreadLocal();
        local.set("111");//绑定线程的存储

        Object o=new Object();
        Object o1=new Object();
        Thread p=new Thread(()->{
           while (true){
               System.out.println("jion ");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           // o.notify();
        });

        new Thread(()->{
            System.out.println("1 start");
            synchronized (o){
                for (int i = 0; i < 5; i++) {
                    System.out.println("1 ing");
                    try {
                        Thread.sleep(1000);
                        //o.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("1 end");
        }).start();


        new Thread(()->{
            System.out.println("2 start");
//            try {
//                p.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (o){

                for (int i = 0; i < 5; i++) {
                    System.out.println("2 ing");
                    try {

                        //o.wait();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2end");
        }).start();
        p.setDaemon(true);//守护线程 所有线程结束 守护线程自动结束
        p.start();
//        Thread t1=new Thread(()->{
//            int i=0;
//            while (true){
//                i++;
//                if(i==5){
//                    Thread.yield();
//                }
//                System.out.println("11"+Thread.currentThread().getName());
//                if(Thread.currentThread().isInterrupted()){
//                    System.out.println("中断停止");
//                    break;
//                }
//            }
//            //t1.isInterrupted();
////            List<Integer> list1=new ArrayList<>();
////            list1.add(1);
////            list1.add(2);
////            list1.add(3);
////            list1.add(4);
////            list1= list1
////                    .stream()
////                    .sorted((a,b)->b-a)
////                    .collect(Collectors.toList());
////            System.out.println(list1);
//        },"11");
//        Thread t2=new Thread(()->{
//            for (int i = 0; i < 7; i++) {
//                System.out.println(i);
//                if (i==5){
//                    try {
//                        t1.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
////        System.out.println(Thread.currentThread().getName());
////        System.out.println("h");
////        Thread.sleep(1000);
////        System.out.println("e");
////        Thread.sleep(1000);
////        System.out.println("l");
////        Thread.sleep(1000);
////        System.out.println("l");
////        Thread.sleep(1000);
////        System.out.println("o");
////        Thread.sleep(1000);
//        t1.start();
//        Thread.sleep(3000);
//        t1.interrupt();

    }
}
