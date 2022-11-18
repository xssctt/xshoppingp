package com.javaclimb.xshopping.demo.xiaofei;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


// list 有东西 take（有三个 多线程） 拿出来 没有等待
public class main {
    private static final Queue<Object> list=new LinkedList<>();

    public static void main(String[] args) {

        new Thread(main::add,"..1").start();
        new Thread(main::add,"..2").start();

        new Thread(main::take,"..1").start();
        new Thread(main::take,"..2").start();
        new Thread(main::take,"..3").start();
    }


    public static void add(){

      while (true){

          try {

              Thread.sleep(3000);
              synchronized (list){

                  System.out.println(new Date()+""+Thread.currentThread().getName()+"111");
                  list.offer(new Object());
                  list.notifyAll();//  有东西  唤醒
              }

          } catch (InterruptedException e) {
              e.printStackTrace();
          }

      }
    }


    public static void take(){
        while (true){

            try {


                synchronized (list){
                    while (list.isEmpty()) list.wait(); // 没东西 等待
                    list.poll();
                    System.out.println(new Date()+""+Thread.currentThread().getName()+"222");

                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
