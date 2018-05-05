package alg;

/*
 **** Написать программу по возведению числа в степень с помощью рекурсии.
 **** Написать программу «Задача о рюкзаке» с помощью рекурсии. В общем виде задачу можно сформулировать так: из
 * заданного множества предметов со свойствами «стоимость» и «вес» требуется отобрать подмножество с максимальной полной
 * стоимостью, соблюдая при этом ограничение на суммарный вес. Вывести итоговую стоимость "рюкзака". Нерекурсивное
 * решение и рекуррентные соотношения в википедии
 **** ИЛИ ханойская башня
 */

import java.util.Arrays;

public class dz5 {
    public static void main(String[] args) {
        System.out.println(step(2,8));
        System.out.println();
        rukzac();

    }
    public  static int step(int v, int s){
        int tmp=1;
        if (s<1){return 1;}
            tmp=v*step(v,--s);
        return tmp;
    }
    public static void rukzac(){
        Item[] items=new Item[5];
        for (int i = 0; i < items.length ; i++) {
            items[i]=new Item();
            System.out.println(i+": "+items[i]);
        }
        System.out.println();

        getAnagrams(items,items.length);
        System.out.println(max);
        done.print();


    }
public static Rukzak rukzak=new Rukzak(12);
   public static Rukzak done =new Rukzak(12);
    public static int max=0;
    private static void getAnagrams(Item[] arr, int size) {
        if (size == 1) return;

        for (int i = 0; i < size; i++) {
            getAnagrams(arr, size - 1);
            if (size == 2){
                for (int j = 0; j < arr.length; j++) {
                    if(rukzak.addItem(arr[j])==-1){


                        break;}

                }
                if(max<rukzak.getPrice()){
                    max=rukzak.getPrice();
                    rukzak.copy(done);
                }
//                System.out.println(rukzak.getPrice());
//                rukzak.print();
                rukzak.wipe();

            }
            shiftArray(arr, size);
        }
    }

    private static void shiftArray(Item[] arr, int size) {
        int i;
        int pos = arr.length - size;
        Item temp = arr[pos];
        for (i = pos + 1; i < arr.length; i++)
            arr[i - 1] = arr[i];
        arr[i - 1] = temp;
    }
}

class Rukzak{
    Item[] items;
    int weight;

    @Override
    public String toString() {
        return ("Rukzak{" +
                "items=" + Arrays.toString(items) +
                ", weight=" + weight +
                '}').trim();
    }

    Rukzak(int weight){
        this.weight=weight;
        items=new Item[weight*100];
    }

    public void wipe(){
        for (int i = 0; i <items.length ; i++) {
            items[i]=null;
        }
    }

    public void print(){
        for (int i = 0; i < items.length; i++) {
            if(items[i]==null){break;}
            System.out.println(i+": "+items[i]);
        }
    }

    public int addItem(Item item){
        if(this.getWeight()+item.getWeigrh()>weight){return -1;}
        for (int i = 0; i < items.length; i++) {
            if(items[i]==null){items[i]=item;return this.getWeight();}

        }
        return -1;
    }

    public int removeItem(){
        for (int i = 0; i <items.length-1 ; i++) {
            if(items[i+1]==null){
                items[i]=null;
                return this.getWeight();
            }
        }
        return -1;
    }

    public int getWeight(){
        int tmp=0;
        for (int i = 0; i < items.length; i++) {
            if(items[i]==null){break;}
            tmp+=items[i].getWeigrh();
        }
        return tmp;
    }

    public int getPrice(){
        int tmp=0;
        for (int i = 0; i < items.length; i++) {
            if(items[i]==null){break;}
            tmp+=items[i].getCost();
        }
        return tmp;
    }

    public void copy(Rukzak rukzaks){
        rukzaks.wipe();
        for (int i = 0; i <items.length ; i++) {
            if(items[i]==null){break;}
            rukzaks.addItem(items[i]);
        }
    }


}

class Item{
    private int weigrh;
    private int cost;

    public Item(){
        weigrh=(int)( Math.random()*10)+1;
        cost=(int)(Math.random()*20)+1;
    }

    public double getWeigrh() {
        return weigrh;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weigrh=" + weigrh +
                ", cost=" + cost +
                '}';
    }
}