package OOD;

public class Pig extends Animal{
    public Pig(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Pig pig = new Pig(1);
        pig.run();
    }
}
