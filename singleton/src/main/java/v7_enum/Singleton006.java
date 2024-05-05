package v7_enum;


//Singleton через Enum - нестандартный подход, на практике вряд ли встречается


public class Singleton006 {
    public static void main(String[] args) {
        Settings s1 = Settings.Instance;
        System.out.println(s1.getInfo());
        s1.ip = "222.222.222.1";

        Settings s2 = Settings.Instance;
        System.out.println(s1.getInfo());
        System.out.println(s2.getInfo());
    }
}

enum Settings {

    Instance;

    public Integer port;
    public String ip;
    public String username;

    Settings() {
        ip = "123.0.0.1";
        port = 8080;
        username = "admin";
    }

    public String getInfo() {
        return String.format("[Host: %s:%d, user:%s]",
                this.ip, this.port, this.username);
    }
}
