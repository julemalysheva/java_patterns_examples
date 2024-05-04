package cloneable_prototype;
//Первое решение - частичное копирование

import java.util.Arrays;

public class Prototype002 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Worker w1 = new Worker(
                "Имя1",
                "Фамилия1",
                new String[]{"460052", "Оренбург", "Советская"}
        );

        //учитывая что метод вернет Object, нужно явно скастовать к нужному типу.
        Worker w2 =(Worker) w1.clone();
        w2.firstName = "Имя2";
//        w2.address = new String[]{"460000", "Оренбург", "Кирова"}; //в таком случае адрес изменится только в нужном объекте
        w2.address[2] = "Просторная";
        //в этом случае улица будет изменена в каждом объекте, а не только во втором
//        [Имя1, Фамилия1, [460052, Оренбург, Просторная]]
//        [Имя2, Фамилия1, [460052, Оренбург, Просторная]]
        /**
         * адрес у нас массив, т.к. это ссылочный тип, и если полностью ссылку меняем, все ОК,
         * если меняем частично, то ссылаемся на один участок памяти и получаем проблему
         */

        System.out.println(w1);
        System.out.println(w2);
    }
}

class Worker implements Cloneable {
    public String firstName;
    public String lastName;
    public String[] address;

    public Worker(String firstName, String lastName, String[] address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s]",
                this.firstName,
                this.lastName,
                Arrays.toString(this.address));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Worker(this.firstName, this.lastName, this.address);
    }
}
