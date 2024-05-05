package v4_synchronized;


//Singleton synchronized - основная проблема в его потоко небезопасности

public class Singleton003 {
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
    private static Settings lazyInstance;

//    public static Settings getInstance() {
//        if (lazyInstance == null) {
//            //добавим блок synchronized и внутри снова проверим на null
//            //устаревший подход
//            synchronized (Settings.class) {
//                if (lazyInstance == null) {
//                    System.out.println("Инициализация...");
//                    lazyInstance = new Settings();
//                }
//            }
//        }
//        System.out.println("Обращение...");
//        return lazyInstance;
//    }

    public static synchronized Settings getInstance() {
        if (lazyInstance == null) {
            System.out.println("Инициализация...");
            lazyInstance = new Settings();
        }
        System.out.println("Обращение...");
        return lazyInstance;
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
