package v4_function;

import java.util.function.Supplier;

/**
 * Решение в функциональном стиле/ при помощи функциональных интерфейсов.
 * Представим ситуацию, что нам нужно поддерживать код финальных классов, от которых
 * мы соотв. не можем наследоваться и вмешиваться в них тоже.
 * Классы однотипные в некоторой части.
 * В нашем случае описали 4 параметра, аналогично можно сделать с массивом аргументов,
 * а если для какого-то метода/действия нужна специфичная логика, его легко можно вынести
 * отдельным аргументом.
 */


public class TemplateMethod004 {
    public static void main(String[] args) {
        Mac mac = new Mac();
        Pc pc = new Pc();

        Computer.run(
                () -> mac.powerOn(),
                () -> mac.power(),
                () -> mac.rendering(),
                () -> mac.powerOff()
        );

        Computer.run(
                () -> pc.powerOn(),
                () -> pc.power(),
                () -> pc.playDota(),
                () -> {} //заглушка
        );
    }
}

final class Mac {
    private boolean power;

    public void powerOn() {
        this.power = true;
        System.out.println("Apple Computer Power On");
    }
    public void powerOff() {
        this.power = false;
        System.out.println("Apple Computer Power Off");
    }

    public Mac() {
    }

    public boolean power() {
        return power;
    }

    public void rendering()  {
        int step = 3;

        System.out.println("Start rendering...");
        while (step-- > 0) {
            try {
                Thread.sleep(500, 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Rendering...");
        }
        System.out.println("Task accomplished!");
        this.power = false;
    }
}

final class Pc {
    private boolean power;

    public void powerOn() {
        power = true;
        System.out.println("Start bios..start os..");
    }

    public Pc() {
    }

    public boolean power() {
        return power;
    }

    public void playDota() {
        System.out.println("gl al...");
        int step = 5;
        String act = " click";
        while (step-- > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(act);
        }
        this.power = false;
        System.out.println("gg wp!");
    }
}

interface Action {
    void execute();
}

//interface Func<T> {
//    T get();
//}

class Computer {
    //метод будет принимать некоторые действия.
    //Func<Boolean> switchedOn - предположим это будет проверка какого-то условия
    public static void run(Action turnOn,
                           //Func<Boolean> switchedOn,
                           Supplier<Boolean> switchedOn,
                           Action action,
                           Action turnOff) {
        turnOn.execute();
        while (switchedOn.get()) {
            action.execute();
        }
        turnOff.execute();
    }
}
