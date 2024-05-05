package v1;
//Подход на статике - не есть хорошо
public class Singleton000 {
    public static void main(String[] args) {
        StaticSettings.ip = "123.123.123.1";
        StaticSettings.port = 8989;
        StaticSettings.username = "admin";
        System.out.println(StaticSettings.getInfo());

        Client client = new Client();
        client.connect();

    }
}

class Client {
    public Client() {
    }

    public void  connect() {
        System.out.println("connect...");
        System.out.println(StaticSettings.ip);
        System.out.println(StaticSettings.port);
        System.out.println(StaticSettings.username);
    }
}

class StaticSettings {
    public static Integer port;
    public static String ip;
    public static String username;

    private StaticSettings() {
    }

    public static String getInfo() {
        return String.format("[Host: %s:%d, user:%s]",
                ip, port, username);
    }
}
