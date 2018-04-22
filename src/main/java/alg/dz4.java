package alg;

/*
 **** Реализовать класс итератора
 **** Написать программу, использующую двусвязный список через методы итератора
 */

import java.util.Iterator;

public class dz4 {
    Iterator<Integer> iterator=new Iterator<Integer>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Integer next() {
            return null;
        }
    };


}

class MyIterator{
     private DoublyNode current;
     private DoublyNode previous;
     private MyDoublyLinkedList list;

     public MyIterator(MyDoublyLinkedList list){
         this.list=list;
         this.reset();
     }

     private void reaet(){
         this.current=list.getFirst();
         this.previous=null;
     }

     public boolean atEnd(){
         return current.next==null;
     }




}

class DoublyNode {
    public String name;
    public int age;

    public DoublyNode next;
    public DoublyNode prev;

    protected DoublyNode(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "(Name: " + name + ", age: " + age + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DoublyNode)) return false;
        DoublyNode other = (DoublyNode) obj;
        return other.name.equals(this.name) && other.age == this.age;
    }
}

class MyDoublyLinkedList{

    private DoublyNode head;
    private DoublyNode tail;
    public  MyDoublyLinkedList(){}

    public DoublyNode getFirst(){
        return this.head;
    }


}