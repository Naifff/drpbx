package alg;

public class dz1 {
    public static void main(String[] args) {
//        long t1 =  System.currentTimeMillis();

/*
Правила подсчёта асимптотической сложности алгоритма

(1:21:48) Если алгоритму необходимо выполнить определённые действия n раз, то сложность этого алгоритма O(n).

(1:23:21) Если алгоритм выполняет одну операцию состоящую из n шагов, а затем вторую операцию из k шагов, то общая производительность алгоритма составит O(n+k) или в простонародье O(2n).

(1:29:43) Если алгоритму необходимо сделать O(n+k) шагов и область значений n больше, чем область значений k, то сложность можно упростить до O(n).

(1:35:12) Если алгоритму внутри каждого шага O(n) приходится делать ещё O(k) шагов, то общая производительность составит O(n*k) или в простонародье O(n^2).

(1:39:56) Постоянными множителями и константами можно пренебречь.
 */
        System.out.println(stepen(9,19));
//        long t2 =  System.currentTimeMillis();
//        System.out.println("Прошло времени: " + (t2 - t1)+"мс.");
        System.out.println(stepen2(9,19));
//        long t3 =  System.currentTimeMillis();
//        System.out.println("Прошло времени: " + (t3 - t2)+"мс.");
        int[] arr={9,8,7,6,5,4,3,2,1,1};
        System.out.println(arrMin(arr));
        System.out.println(sredArif(arr));
    }

    private static long stepen(long a, long step){//On
        long result=1L;

        for (int i=0;i<step;i++){
            result*=a;
        }

        return result;
    }

    private static long stepen2(long a, long step){//O(log n)
        long result=1L;
        while(step>0){
            if (step%2==1){
                result*=a;
                System.out.println("res="+result);
            }
            a*=a;
            System.out.println("a="+a);
            step/=2;
            System.out.println("step="+step);
        }
        return result;
    }

    private static int arrMin(int[] arr){//On
        int min=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]<min){
                min=arr[i];
            }
        }
        return min;
    }

    private static float sredArif(int [] arr){//On
        float summ=0f;
        for(int i=0;i<arr.length;i++){
            summ+=arr[i];
        }
        return summ/arr.length;
    }
}
