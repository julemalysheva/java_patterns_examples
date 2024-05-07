package v3_logger_proxy;
//Простое логирование через Proxy.
// Однако в данном примере логирование нужно явно прописывать в каждом методе

public class Proxy003 {
    public static void main(String[] args) {
        Hero hero = new Hero(100, new TerminalLogger());
        hero.getHp();
        hero.setMp(111);
    }
}

class Hero {
    private Integer hp;
    private Integer mp;
    private Logger logger;

    public Hero(Integer hp, Logger logger) {
        this.hp = hp;
        this.mp = 100;
        this.logger = logger;
    }

    public Integer getHp() {
        logger.info("Обращение к getHp");
        return hp;
    }

    public void setHp(Integer hp) {
        logger.info("Обращение к setHp");
        this.hp = hp;
    }

    public Integer getMp() {
        logger.info("Обращение к getMp");
        return mp;
    }

    public void setMp(Integer mp) {
        logger.info("Обращение к setMp");
        this.mp = mp;
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
