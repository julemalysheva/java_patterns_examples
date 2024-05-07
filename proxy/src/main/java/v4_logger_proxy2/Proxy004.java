package v4_logger_proxy2;
//логирование через Proxy обёртку.


public class Proxy004 {
    public static void main(String[] args) {
        Hero hero = new Hero(100);
        hero.getHp();
        hero.setMp(111);
    }
}

//Делаем класс-обертку над тем типом, который нам требуется и всю логику
//логирования зашиваем в него - один раз в одном месте
class Field<T> {
    private T value;
    private String fieldName;
    private Logger logger = new TerminalLogger();

    public Field(T value, String fieldName) {
        this.value = value;
    }

    public T getValue() {
        logger.info("Запрошен get-метод поля " + fieldName);
        return value;
    }

    public void setValue(T value) {
        logger.info("Запрошен set-метод поля " + fieldName);
        this.value = value;
    }
}

class Hero {
    private Field<Integer> hp;
    private Field<Integer> mp;

    public Hero(Integer hp) {
        this.hp = new Field<Integer>(hp, "hp");
        this.mp = new Field<Integer>(100, "mp");
    }

    public Integer getHp() {
        return hp.getValue();
    }

    public void setHp(Integer hp) {
        this.hp.setValue(hp);
    }

    public Integer getMp() {
        return mp.getValue();
    }

    public void setMp(Integer mp) {
        this.mp.setValue(mp);
    }

}

interface  Logger {
    void info(String msg);
}

class TerminalLogger implements  Logger {
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }
}
