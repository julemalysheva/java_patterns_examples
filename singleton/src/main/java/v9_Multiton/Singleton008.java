package v9_Multiton;


//Singleton и паттерн Multiton. если нужно несколько Singleton, допустим сервера расположены
//в разных частях света.

import java.util.HashMap;

public class Singleton008 {
    public static void main(String[] args) {
        Settings settings1 = Settings.get(Location.EASTERN_EUROPE);
        Settings settings2 = Settings.get(Location.ASIA);
        Settings settings3 = Settings.get(Location.LATIN_AMERICA);

        System.out.println(settings1);
        System.out.println(settings2);
        System.out.println(settings3);

        Settings settings11 = Settings.get(Location.EASTERN_EUROPE);
        settings1.ip = "10.10.10.0";

        System.out.println(settings1);
        System.out.println(settings11);
    }
}

enum Location {
    EASTERN_EUROPE,
    ASIA,
    LATIN_AMERICA
}

class Settings {
    private static HashMap<Location, Settings> instances = new HashMap<>();

    public Integer port;
    public String ip;
    public String username;

    private Settings() {
        //создан с целью, чтоб у конечного польз-ля не было возможности создать экз. напрямую
    }

    public static Settings get(Location location) {
        if (instances.containsKey(location))
            return instances.get(location);

        Settings setting = new Settings();

        switch (location) {
            case EASTERN_EUROPE -> {
                setting.ip = "111.111.111.1";
                setting.port = 8181;
                setting.username = "ea_admin";
                instances.put(Location.EASTERN_EUROPE, setting);
            }
            case ASIA -> {
                setting.ip = "111.111.112.2";
                setting.port = 8282;
                setting.username = "asia_admin";
                instances.put(Location.ASIA, setting);
            }
            case LATIN_AMERICA -> {
                setting.ip = "111.111.113.3";
                setting.port = 8383;
                setting.username = "la_admin";
                instances.put(Location.LATIN_AMERICA, setting);
            }
        }

    return instances.get(location);
    }

    @Override
    public String toString() {
        return String.format("[Host: %s:%d, user:%s]",
                this.ip, this.port, this.username);
    }
}
