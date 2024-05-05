package v6_sync;


//Sync-Singleton через вложенные классы - использование вложенных статических классов-оберток.

public class Singleton005 {
    public static void main(String[] args) {
        Settings init = Settings.getInstance();
        init.ip = "123.0.0.1";
        init.port = 8080;
        init.username = "admin";
        System.out.println(init);
    }
}

class Settings {
    //Описываем приватный статич.класс-обертку, внутри кот. и будет инстанс,
    //и непосредственно в классе описываем доступ к этому инстансу
    private static class Wrapper {
        private static final Settings instance = new Settings();
    }

    public static Settings getInstance() {
        return Wrapper.instance;
}

    public Integer port;
    public String ip;
    public String username;

    private Settings() {
    }

    @Override
    public String toString() {
        return String.format("[Host: %s:%d, user:%s]",
                this.ip, this.port, this.username);
    }
}
