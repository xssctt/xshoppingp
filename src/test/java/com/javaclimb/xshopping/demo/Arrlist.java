package com.javaclimb.xshopping.demo;

public class Arrlist<E> {


    private int size=0;
    private int cap=10;
    private Object[] array=new Object[cap];

    public void add(E element,int index){
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("add error"+size);
        }
        if(size>=cap){
            int newcap=cap+(cap >> 1);//扩容
            Object[] newarray=new Object[newcap];
            System.arraycopy(array,0,newarray,0,size);
            array=newarray;
            cap=newcap;
        }
        for (int i=size;i>index;i--){
            array[i]=array[i-1];
        }
        array[index]=element;
        size++;
    }

    public E remove(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("add error"+size);
        }
        E e=(E) array[index];
        for (int i=index;i<size;i++){
            array[i]=array[i+1];
        }
        size--;
        return e;
    }
}

















