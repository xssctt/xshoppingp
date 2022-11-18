package com.javaclimb.xshopping.demo;


public class Linkedlistp<E> {
    private final Node<E> head=new Node<>(null);
    private int size;

    public void add(E element,int index){
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("add error"+size);
        }

        Node<E> addnode=new Node<>(element);

        Node<E> prev=head;
        for (int i = 0; i < index; i++) {
            prev=prev.rnext;
        }
        addnode.rnext=prev.rnext;
        addnode.lnext=prev;
        prev.rnext=addnode;
        size++;

    }

    public E remove(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("add error"+size);
        }

        Node<E> prev=head;
        for (int i = 0; i < index; i++) {
            prev=prev.rnext;
        }
        E e=prev.rnext.element;
        prev.rnext=prev.rnext.rnext;
        prev.rnext.rnext.lnext=prev;
        return e;
    }

    public E get(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("add error"+size);
        }

        Node<E> prev=head;
        while (index-- >=0) {
            prev=prev.rnext;
        }
        return prev.element;
    }

    public int size(){
        return size;
    }

    private static class Node<E>{
        E element;
        Node<E> lnext;
        Node<E> rnext;

        public Node(E e) {
            this.element = e;
        }
    }
}
