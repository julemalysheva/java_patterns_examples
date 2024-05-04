//Постановка проблемы
//При копировании таким образом ссылочного типа копируется ссылка
import java.util.Arrays;

public class Prototype001 {
    public static void main(String[] args) {
        Worker w1 = new Worker(
                "Имя1",
                "Фамилия1",
                new String[]{"460052", "Оренбург", "Советская"}
        );
        //w1 w2 ссылаются на один участок памяти, при изменении одного меняется и другой
        //в таком виде копию создавать нельзя.
        Worker w2 = w1;
        w2.firstName = "Имя2";
        System.out.println(w1);
        System.out.println(w2);
    }
}

class Worker {
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
}
