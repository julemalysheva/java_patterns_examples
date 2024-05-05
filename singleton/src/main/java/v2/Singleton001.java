package v2;


//Простой Singleton
public class Singleton001 {
    public static void main(String[] args) {
        Settings settings = Settings.getInstance();

        settings.ip = "123.123.123.1";
        settings.port = 8989;
        settings.username = "admin";
        System.out.println(settings.getInfo());

        Settings settings2 = Settings.getInstance();
        settings2.ip = "223.223.223.2";

        System.out.println(settings.getInfo());
        System.out.println(settings2.getInfo());
    }
}

class Settings {
    private static final Settings instance = new Settings();

    public static Settings getInstance() {
        return instance;
    }

    public Integer port;
    public String ip;
    public String username;

    private Settings() {
    }

    public String getInfo() {
        return String.format("[Host: %s:%d, user:%s]",
                ip, port, username);
    }
}
