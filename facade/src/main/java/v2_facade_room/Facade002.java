package v2_facade_room;

import java.util.Random;

public class Facade002 {
    public static void main(String[] args) {
        LivivngRoom livivngRoom = new LivivngRoom();
        livivngRoom.turnOn();

        BedRoom bedRoom = new BedRoom();
        bedRoom.turnOn();
    }
}

class AirPurifier {
    boolean enable;

    public AirPurifier() {
        enable = false;
    }

    public void power() {
        enable = !enable;
    }
}

enum BulbColor {
    RED,
    GREEN,
    BLUE,
    WARM,
    COLD
}

class LightBulb {
    BulbColor color;

    public LightBulb() {
    }

    public void switchOff() {

    }

    public void setColor(BulbColor color) {
        this.color = color;
    }
}

class Plug {
    public Plug() {
    }

    public void turnOn() {

    }

    public void turnOff() {

    }
}

class TemperatureHumiditySensor {
    private int temperatureData() {
        return new Random().nextInt(10, 30);
    }

    private int humidityData() {
        return new Random().nextInt(30, 99);
    }

    public String getTemperature() {
        return temperatureData() + "°C";
    }

    public String getHumidity() {
        return humidityData() + "%";
    }
}

class Tv {
    public Tv() {
    }
    public void turnOn() {

    }

    public void turnOff() {

    }

    public void playChannel(int args) {

    }

}

abstract class Room {
    protected AirPurifier purifier;
    protected LightBulb bulb;
    protected TemperatureHumiditySensor sensor;
    protected Plug plug;
    protected Tv tv;

    public Room() {
        purifier = new AirPurifier();
        bulb = new LightBulb();
        plug = new Plug();
        sensor = new TemperatureHumiditySensor();
        tv = new Tv();
    }

    protected abstract void deviceActivate(BulbColor color, int channel);
}

/**
 * создаем конкретную комнату, описываем что должно происходить в момент вызова
 * инструкции
 */
class LivivngRoom extends Room {
    public LivivngRoom() {
        super();
    }

    /**
     * настраивается сценарий
     * @param color
     * @param channel
     */
    @Override
    protected void deviceActivate(BulbColor color, int channel) {
        purifier.power();

        bulb.setColor(color);
        plug.turnOn();
        sensor.getTemperature();
        sensor.getHumidity();

        tv.playChannel(channel);
    }

    /**
     * момент срабатывания сценария
     */
    public void turnOn() {
        deviceActivate(BulbColor.WARM, 1);
    }
}

class BedRoom extends Room {
    public BedRoom() {
        super();
    }

    /**
     * настраивается сценарий
     * @param color
     * @param channel
     */
    @Override
    protected void deviceActivate(BulbColor color, int channel) {
        purifier.power();

        bulb.setColor(color);

        plug.turnOn();

        sensor.getTemperature();
        sensor.getHumidity();

        tv.playChannel(channel);
    }

    /**
     * момент срабатывания сценария
     */
    public void turnOn() {
        deviceActivate(BulbColor.WARM, 1);
    }
}

class Kitchen {
    //здесь мы уже не указываем целый пул устройств, а говорим, что у нас есть надстройка
    //над нашей комнатой, где мы нужным образом уже их инициализировали
    FacadeRoom facade;

    public Kitchen() {
        facade = new FacadeRoom();
    }

    public void turnOn(BulbColor color, int channel) {
        facade.deviceActivate(color, 0);
    }
}

class FacadeRoom {
    AirPurifier purifier = new AirPurifier();
    LightBulb bulb = new LightBulb();
    TemperatureHumiditySensor sensor = new TemperatureHumiditySensor();
    Plug plug = new Plug();
    Tv tv = new Tv();

    public void deviceActivate(BulbColor color, int channel) {
        purifier.power();

        bulb.setColor(color);

        plug.turnOn();

        sensor.getTemperature();
        sensor.getHumidity();

        tv.playChannel(channel);
    }
}
