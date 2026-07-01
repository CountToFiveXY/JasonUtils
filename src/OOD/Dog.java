package OOD;

import java.util.ArrayList;
import java.util.List;

public class Dog extends Animal implements Living {

    public Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public void live() {
        System.out.println("x: " + "wang" );
    }

    @Override
    public void die() {
        System.out.println("x: " + "wangwang" );
    }

    void run() {
        System.out.println("age is: " + age);
    }


    public static void main(String[] args) {
        Dog dog = new Dog(1, "Wangcai");
        dog.run();

        ArrayList list = new ArrayList<>();
    }
}


