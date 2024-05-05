package v8_MonoState;


//Singleton и паттерн MonoState
//Ситуация, где-то уже в разных частях программы был описан Синглтон и нужно сделать так,
//чтоб был создан только один единств. экземпляр. Придется менять исходный код.?
//решение топорное...

public class Singleton007 {
    public static void main(String[] args) {
        Settings settings1 = new Settings();
        settings1.setIp("100.100.100.1");
        settings1.setPort(8000);
        settings1.setUsername("admin");
        System.out.println(settings1);

        Settings settings2 = new Settings();
        settings2.setIp("10.1.10.1");

        System.out.println(settings1);
        System.out.println(settings2);
        //здесь обращаясь через settings1 или settings2 мы будем получать значения
        //одних и тех же полей, учитывая то, что они static
    }
}

class Settings {
    //добавим модификатор static
    private static Integer port;
    private static String ip;
    private static String username;

    //реализуем геттеры и сеттеры, по умолчанию они static, но мы их сделаем экземплярными
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        Settings.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        Settings.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Settings.username = username;
    }

    @Override
    public String toString() {
        return String.format("[Host: %s:%d, user:%s]",
                this.ip, this.port, this.username);
    }
}

