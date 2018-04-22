package alg;

//          **** Создать программу, которая переворачивает вводимые строки
//          **** Создать класс для реализации дека
//          **** Описать класс с реализацией приоритетной очереди

import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;

public class dz3 {
    public static void main(String[] args) {
        System.out.println(rev("Privet"));
    }

    public static char[] rev(String in) {
        int size = in.length();
        char out[] = new char[size];
        for (int i = 0; i < size; i++) {
            out[size - i - 1] = in.charAt(i);
        }
        return out;

    }
}

class Dec {
    private int[] dec;
    private int size;
    private int head;   // leaves
    private int tail;   // comes
    private int items;

    public Dec(int size) {
        this.size = size;
        head = -0;
        tail = -1;
        items = 0;
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == size;
    }

    public int getSize() {
        return items;
    }

    public void insert(int i) {
        if (isFull()) {
            size *= 2;
            int[] tmpArr = new int[size];
            if (tail >= head) {
                System.arraycopy(dec, 0, tmpArr, 0, dec.length);
            } else {
                System.arraycopy(dec, 0, tmpArr, 0, tail + 1);
                System.arraycopy(dec, head, tmpArr, size - head - 1, dec.length - head);
                head = size - head - 1;
            }
            dec = tmpArr;
        }
        if (tail == size - 1)
            tail = -1;
        dec[++tail] = i;
        items++;
    }

    public int removeHead() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty!");
        int temp = dec[head++];
        if (head == size)
            head = 0;
        items--;
        return temp;
    }

    public int removeTail() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty!");
        int temp = dec[tail--];
        items--;
        return temp;
    }

    public int peekHead() {
        return dec[head];
    }

    public int peekTail() {
        return dec[tail];
    }
}

class PriorityQueue {
    private int[] queue;
    private int size;
    private int head;   // leaves
    private int tail;   // comes
    private int items;

    protected PriorityQueue(int size) {
        this.size = size;
        queue = new int[size];
        head = 0;
        tail = -1;
        items = 0;
    }

    public static void sort(int[] in, int head, int tail) {
        if (tail >= head) {
            int temp = 0;
            for (int min = head; min < tail - 1; min++) {
                int least = min;
                for (int j = min + 1; j < tail; j++) {
                    if (in[j] < in[least]) {
                        least = j;
                    }
                }
                temp = in[min];
                in[min] = in[least];
                in[least] = temp;
            }
        }else{
            int[] tmp=new int[in.length];
                    System.arraycopy(in,head,tmp,0,in.length-head);
                    System.arraycopy(in,0,tmp,in.length-head,tail);
                    sort(tmp,0,in.length);
                    System.arraycopy(tmp,0,in,head,in.length-head);
                    System.arraycopy(tmp,in.length-head,in,0,tail);

        }
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == size;
    }

    public int getSize() {
        return items;
    }

    public void insert(int i) {
        if (isFull()) {
            size *= 2;
            int[] tmpArr = new int[size];
            if (tail >= head) {
                System.arraycopy(queue, 0, tmpArr, 0, queue.length);
            } else {
                System.arraycopy(queue, 0, tmpArr, 0, tail + 1);
                System.arraycopy(queue, head, tmpArr, size - head - 1, queue.length - head);
                head = size - head - 1;
            }
            queue = tmpArr;
        }
        if (tail == size - 1)
            tail = -1;
        queue[++tail] = i;
        items++;
        sort(queue,head,tail);
    }

    public int remove() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty!");
        int temp = queue[head++];
        if (head == size)
            head = 0;
        items--;
        return temp;
    }

    public int peek() {
        return queue[head];
    }
}
