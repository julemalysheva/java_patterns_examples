package v1_simple_proxy;
//Простой Proxy

public class Proxy001 {
    public static void main(String[] args) {
        ActionObject human = new Human(20);
        human.run();

        ActionObject avatar = new AvatarV1((Human) human);
        avatar.run();

        ActionObject avatar2 = new AvatarV2(23);
        avatar2.run();
    }
}

interface ActionObject {
    void run();
}

//исходный объект
class Human implements ActionObject {
    private Integer age;

    public Human(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public void run() {
        System.out.println("Вперед!");
    }

    public Human clone() {
        Human t = new Human(this.age);
        return t;
    }
}

/**
 * Объекты - сурогаты:
 * Аватар будет иметь в себе Human и получать в качестве аргумента
 * исходный объект и предоставлять доступ к исходному методу бега,
 * при этом добавлять какие-то свои действия или выполнять какие-то проверки.
 */
class AvatarV1 implements ActionObject {
    private Human human;

    public Integer getAge() {
        return human.getAge();
    }

    public AvatarV1(Human human) {
//        this.human = human;
        this.human = human.clone();
    }

    @Override
    public void run() {
        System.out.println("Аватар в действии");
        human.run();
    }
}

class AvatarV2 implements ActionObject {
    Human human;

    public Integer getAge() {
        return human.getAge();
    }

    public AvatarV2(Integer arg) {
        this.human = new Human(arg);
    }

    @Override
    public void run() {
        System.out.println("Аватар в действии");
        human.run();
    }

}


