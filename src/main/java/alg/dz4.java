package alg;

/*
 **** Реализовать класс итератора
 **** Написать программу, использующую двусвязный список через методы итератора
 */

import java.util.Iterator;

public class dz4 {
    public static void main(String[] args) {
        
    }


}

//------------------------------------------------------------------------------------

class MyIterator{
     private DoublyNode current;
     private DoublyNode previous;
     private MyDoublyLinkedList list;

     public MyIterator(MyDoublyLinkedList list){
         this.list=list;
         this.reset();
     }

     private void reset(){
         this.current=list.getFirst();
         this.previous=null;
     }

     public boolean atEnd(){
         return current.next==null;
     }

    public void nextLink (){
        previous = current;
        current = current . next;
    }
    public DoublyNode getCurrent (){
        return current;
    }
    public void insertAfter ( String name , int age ){
        DoublyNode newLink = new DoublyNode ( name , age );
        if ( list . isEmpty ()){
            list . setLast ( newLink );
            current = newLink;
        } else {
            newLink . next = current . next;
            current . next = newLink;
            nextLink ();
        }
    }
    public void insertBeforeFirst ( String name , int age ){
        DoublyNode newLink = new DoublyNode ( name , age );
        if ( previous == null ){
            newLink . next = list . getFirst ();
            list . setFirst ( newLink );
            reset ();
        }
        else{
            newLink . next = previous . next;
            previous . next = newLink;
            current = newLink;
        }






}

//---------------------------------------------------------------------------------

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

    public void display (){
        System . out . println ( "Name: " + this . name + ", age: " + this . age );
    }
}

//-------------------------------------------------------------------------------

class MyDoublyLinkedList {

    private DoublyNode head;
    private DoublyNode tail;

    public MyDoublyLinkedList() {
        head=null;
        tail=null;
    }

    public DoublyNode getFirst() {
        return head;
    }

    public DoublyNode getLast() {
        return tail;
    }

    public void setFirst(DoublyNode first) {
        head = first;
    }

    public void setLast(DoublyNode last) {
        tail = last;
    }

    public MyIterator getIterator() {
        return new MyIterator(this);
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void display() {
        DoublyNode current = head;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }
}

}