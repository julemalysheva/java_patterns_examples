package serializable_prototype;
/**
 * Сериализация сложных объектов - наиболее частый и простой вариант глубокого копирования.
 * В данном примере, если реализовать копирование через конструкторы, каждый будет тянуть за собой конструкторы
 * для копирования вложенных элементов.
 */

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.Arrays;

public class Prototype004 {
    public static void main(String[] args) {
        Address address = new Address("460052",
                new City("Город 1",
                        new Street("Улица 1",
                                new House("23/1"))));

        Worker w1 = new Worker("Имя", "Фамилия", address);

        Worker w2 = w1.getPrototype();

        w2.address.city.cityName = "Москва";

        System.out.println(w1);
        System.out.println(w2);
    }
}

class Worker implements Serializable {
    public String firstName;
    public String lastName;
    public Address address;


    public Worker(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    //метод для создания копии объекта
    public Worker getPrototype() {
        return SerializationUtils.roundtrip(this);
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s]",
                this.firstName,
                this.lastName,
                this.address);
    }
}

class Address implements Serializable {
    public String index;
    public City city;

    public Address(String index, City city) {
        this.index = index;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s, %s]",
                this.index,
                this.city.cityName,
                this.city.street.streetName,
                this.city.street.houseNumber.house);
    }
}

class City implements Serializable {
    public String cityName;
    public Street street;

    public City(String cityName, Street street) {
        this.cityName = cityName;
        this.street = street;
    }
}

class Street implements Serializable {
    public String streetName;
    public House houseNumber;

    public Street(String streetName, House houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }
}

class  House implements Serializable {
    public String house;

    public House(String house) {
        this.house = house;
    }
}


