package alg;

public class Person {
    private static int uid = 0;
    String name;
    int age;
    int id;

    protected Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = uid++;
    }
}
