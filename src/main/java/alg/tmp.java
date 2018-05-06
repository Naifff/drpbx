package alg;

public class tmp {

    private static void iterPrint(int i) {
        while (i >= 0) {
            System.out.print(i-- + " ");
        }
    }

    private static void recPrint(int i) {
        if (i >= 0) {
            recPrint(--i);
            System.out.print(i + " ");
        }
    }

    private static void shiftArray(char[] arr, int size) {
        int i;
        int pos = arr.length - size;
        char temp = arr[pos];
        for (i = pos + 1; i < arr.length; i++)
            arr[i - 1] = arr[i];
        arr[i - 1] = temp;
    }

    private static int opers = 0;
    private static void getAnagrams(char[] arr, int size) {
        if (size == 1) return;

        for (int i = 0; i < size; i++) {
            getAnagrams(arr, size - 1);
            if (size == 2)
                System.out.println(opers++ + " " + String.copyValueOf(arr));
            shiftArray(arr, size);
        }
    }
    private static int[] arr = {0,1,2,3,4,5,6,7,8,9};
    public static int binSearchRec(int value, int low, int high) {
        int mid = (low + high) / 2;
        if (arr[mid] == value) {
            return mid;
        } else if (low > high) {
            return -1;
        } else {
            if (arr[mid] < value) {
                return binSearchRec(value, mid + 1, high);
            } else {
                return binSearchRec(value, low, mid - 1);
            }
        }
    }
    /*
     **** Написать программу по возведению числа в степень
     *              с помощью рекурсии.
     *
     **** Написать программу «Задача о рюкзаке» с помощью рекурсии.
     * В общем виде задачу можно сформулировать так: из заданного
     * множества предметов со свойствами «стоимость» и «вес»
     * требуется отобрать подмножество с максимальной полной
     * стоимостью, соблюдая при этом ограничение на суммарный вес.
     * Вывести итоговую стоимость "рюкзака".
     *
     * Нерекурсивное решение и рекуррентные соотношения в википедии
     *
     * ИЛИ Задача о Ханойской башне
     * */
    public static void main(String[] args) {
        iterPrint(3);
        System.out.println();
        recPrint(3);

        System.out.println();
        String input = "123"; // cat cta act atc tac tca
        char[] arr = input.toCharArray();
        getAnagrams(arr, arr.length);

        System.out.println(binSearchRec(0, 0, arr.length));
    }
}
