package alg;

public class dz2 {
    public static int iter=0;
    public static void main(String[] args) {
        int arr[] = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println(arr[0] + " " + arr[arr.length / 2] + " " + arr[arr.length - 1]);
        long t1 = System.currentTimeMillis();
        long bbl = bubble(arr);
        long t2 = System.currentTimeMillis();
        System.out.println("Пузырек\nПрошло времени: " + (t2 - t1) + "мс.\nИтераций: " + bbl);
        System.out.println("Итераций в милесекунду: " + bbl / (t2 - t1));
        slognost(bbl);

        System.out.println();

        System.out.println(arr[0] + " " + arr[arr.length / 2] + " " + arr[arr.length - 1]);
        t1 = System.currentTimeMillis();
        long srt = sort(arr);
        t2 = System.currentTimeMillis();
        System.out.println("Выбор\nПрошло времени: " + (t2 - t1) + "мс.\nИтераций: " + srt);
        System.out.println("Итераций в милесекунду: " + srt / (t2 - t1));
        slognost(srt);

        System.out.println();

        System.out.println(arr[0] + " " + arr[arr.length / 2] + " " + arr[arr.length - 1]);
        t1 = System.currentTimeMillis();
        long itr = insertionSort(arr);
        t2 = System.currentTimeMillis();
        System.out.println("Вставка\nПрошло времени: " + (t2 - t1) + "мс.\nИтераций: " + itr);
        System.out.println("Итераций в милесекунду: " + itr / (t2 - t1));
        slognost(itr);

        System.out.println();

        System.out.println(arr[0] + " " + arr[arr.length / 2] + " " + arr[arr.length - 1]);
        t1 = System.currentTimeMillis();
        long rdx = radix(arr);
        t2 = System.currentTimeMillis();
        System.out.println("Поразрядная\nПрошло времени: " + (t2 - t1) + "мс.\nИтераций: " + rdx);
        System.out.println("Итераций в милесекунду: " + rdx / (t2 - t1));
        slognost(rdx);

        System.out.println();

        System.out.println(arr[0] + " " + arr[arr.length / 2] + " " + arr[arr.length - 1]);
        t1 = System.currentTimeMillis();
        quicksort(arr, 0, arr.length-1);
        t2 = System.currentTimeMillis();
        System.out.println(arr[0] + " " + arr[arr.length / 2] + " " + arr[arr.length - 1]);
        System.out.println("Быстрая\nПрошло времени: " + (t2 - t1) + "мс.\nИтераций: " + iter);
        System.out.println("Итераций в милесекунду: " + iter / (t2 - t1));
        slognost(iter);


    }

    private static long bubble(int[] arr) {
        long iter = 0L;
        int[] tmp = new int[arr.length];
        int temp = 0;
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        for (int j = 0; j < tmp.length; j++) {
            for (int i = 0; i < tmp.length - 1; i++) {
                if (tmp[i] > tmp[i + 1]) {
                    temp = tmp[i];
                    tmp[i] = tmp[i + 1];
                    tmp[i + 1] = temp;
                    iter++;
                }
            }
        }
        System.out.println(tmp[0] + " " + tmp[tmp.length / 2] + " " + tmp[tmp.length - 1]);
        return iter;
    }

    public static long sort(int[] arr) {
        long iter = 0L;
        int[] tmp = new int[arr.length];
        int temp = 0;
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        for (int min = 0; min < tmp.length - 1; min++) {
            int least = min;
            for (int j = min + 1; j < tmp.length; j++) {
                if (tmp[j] < tmp[least]) {
                    least = j;
                    iter++;
                }
            }
            temp = tmp[min];
            tmp[min] = tmp[least];
            tmp[least] = temp;
        }
        System.out.println(tmp[0] + " " + tmp[tmp.length / 2] + " " + tmp[tmp.length - 1]);
        return iter;
    }

    private static long insertionSort(int[] arr) {
        long iter = 0L;
        int[] tmp = new int[arr.length];
        int temp = 0;
        int j = 0;
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        for (int i = 1; i < tmp.length; i++) {
            temp = tmp[i];
            j = i;
            while (j > 0 && tmp[j - 1] > temp) {
                tmp[j] = tmp[j - 1];
                j--;
                iter++;
            }
            tmp[j] = temp;
        }
        System.out.println(tmp[0] + " " + tmp[tmp.length / 2] + " " + tmp[tmp.length - 1]);
        return iter;
    }

    static long radix(int[] arr) {
        long iter = 0L;
        int[] tmp = new int[arr.length];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        int i, m = tmp[0], exp = 1;
        int[] b = new int[tmp.length];

        for (i = 1; i < tmp.length; i++) {
            if (tmp[i] > m) {
                m = tmp[i];
                iter++;
            }
        }

        while (m / exp > 0) {
            int[] bucket = new int[10];

            for (i = 0; i < tmp.length; i++) {
                bucket[(tmp[i] / exp) % 10]++;
                iter++;
            }
            for (i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
                iter++;
            }
            for (i = tmp.length - 1; i >= 0; i--) {
                b[--bucket[(tmp[i] / exp) % 10]] = tmp[i];
                iter++;
            }
            for (i = 0; i < tmp.length; i++) {
                tmp[i] = b[i];
                iter++;
            }
            exp *= 10;
            iter++;
        }
        System.out.println(tmp[0] + " " + tmp[tmp.length / 2] + " " + tmp[tmp.length - 1]);
        return iter;
    }

       private static void quicksort(int[] numbers, int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(numbers, i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(numbers, low, j);
        if (i < high)
            quicksort(numbers, i, high);
    }

    private static void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
        iter++;
    }

    private static void slognost(long l){
        if (l<=1){
            System.out.println("O(1)");
            return;
        }
        if(l<=5){
            System.out.println("O(log n)");
            return;
        }
        if(l<=100000){
            System.out.println("O(n)");
            return;
        }
        if(l<=500000){
            System.out.println("O(n log n)");
            return;
        }
        if(l<=10000000000L){
            System.out.println("O(n^2)");
            return;
        }
        if(l<=20000000000L){
            System.out.println("O(2n^2)");
            return;
        }
        System.out.println("O(n!)");
    }

}
