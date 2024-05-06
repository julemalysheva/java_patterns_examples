package v5_serialization_2;


//Singleton serialization - решение проблемы Singleton используя сериализацию
//Решение в методе readResolve()
import java.io.*;

public class Singleton004 {
    public static void main(String[] args) throws Exception {
        Settings settings = Settings.getInstance();

        settings.ip = "123.123.123.1";
        settings.port = 8888;
        settings.username = "admin";
        System.out.println(settings.getInfo());

        String path = "Singleton004.java.bin";
        SettingsSaver.SaveAs(path, settings);

        Settings settings2 = SettingsSaver.Load(path);
        settings2.ip = "223.223.223.2";
        //при сериализации и десериализации с методом readResolve
        System.out.println(settings2.getInfo()); //[Host: 223.223.223.2:8888, user:admin]
        System.out.println(settings.getInfo()); // [Host: 223.223.223.2:8888, user:admin]
        System.out.println(settings2 == settings); //true

    }
}

class SettingsSaver {
    public static void SaveAs(String path, Settings settings) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(settings);
        }
    }

    public static Settings Load(String path) throws Exception {
        Settings settings = null;
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            settings = (Settings) (objectIn.readObject());
        }
        return settings;
    }
}

class Settings implements Serializable {
    private static Settings lazyInstance;

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

    /**
     * При десериализации Java метод readResolve() используется для замены
     * объекта, созданного во время десериализации, другим объектом.
     * Это может быть полезно в ситуациях, когда нам нужно убедиться,
     * что в нашем приложении существует только один экземпляр определенного
     * класса, или когда мы хотим заменить объект другим экземпляром, который,
     * возможно, уже существует в памяти.
     * https://www.baeldung.com/java-serialization-readobject-vs-readresolve
     * @return
     */
    protected Object readResolve() {
        return lazyInstance;
    }

    public String getInfo() {
        return String.format("[Host: %s:%d, user:%s]",
                ip, port, username);
    }
}
