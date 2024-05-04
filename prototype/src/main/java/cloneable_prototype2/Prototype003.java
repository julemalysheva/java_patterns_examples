package cloneable_prototype2;
//Решение Cloneable проблемы

import java.util.Arrays;

public class Prototype003 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Worker w1 = new Worker(
                "Имя1",
                "Фамилия1",
                new String[]{"460052", "Оренбург", "Советская"}
        );

        //учитывая что метод вернет Object, нужно явно скастовать к нужному типу.
        //как следствие, тратим время на запаковку и распаковку
        Worker w2 =(Worker) w1.clone();
        w2.firstName = "Имя2";
        w2.address[2] = "Просторная";
        /**
         * при таком варианте метода clone данные изменения пройдут корректно только в нужном объекте
         * [Имя1, Фамилия1, [460052, Оренбург, Советская]]
         * [Имя2, Фамилия1, [460052, Оренбург, Просторная]]
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

    //для решения Cloneable проблемы меняем метод, создаем отдельно массив для адреса и делаем его копию
    @Override
    public Object clone() throws CloneNotSupportedException {
        String[] address = Arrays.copyOf(this.address, this.address.length);
        return new Worker(this.firstName, this.lastName, address);
    }
}
