package com.javaclimb.xshopping.demo;

public class LinledList<E> {
    private final Node<E> head=new Node<>(null);
    private int size;

    public void add(E element,int index){
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("add error"+size);
        }

        Node<E> addnode=new Node<>(element);

        Node<E> prev=head;
        for (int i = 0; i < index; i++) {
            prev=prev.next;
        }
        addnode.next=prev.next;
        prev.next=addnode;
        size++;

    }

    public E remove(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("add error"+size);
        }

        Node<E> prev=head;
        for (int i = 0; i < index; i++) {
            prev=prev.next;
        }
        E e=prev.next.element;
        prev.next=prev.next.next;
        return e;
    }

    public E get(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("add error"+size);
        }

        Node<E> prev=head;
        while (index-- >=0) {
            prev=prev.next;
        }
        return prev.element;
    }

    public int size(){
        return size;
    }

    private static class Node<E>{
         E element;
         Node<E> next;

        public Node(E e) {
            this.element = e;
        }
    }
}
