import java.util.ArrayList;
// Решение проблемы дублирования данных

public class Builder005 {
    public static void main(String[] args) {
        //при таком подходе даже в случае ошибочного дублирования мы получим правильный адрес,
        //т.к. модель содержит только нужные нам элементы и методы работы с ними.
        Address address = new AddressBuilderImpl()
                .setCountry("РФ")
                .setCity("Оренбург")
                .setStreet("Просторная")
                .setHouse("125л")
                .setNumber("52")
                .build();

        System.out.println(address); //РФ Оренбург Просторная 125л 52
    }
}

class Address {
    public String country;
    public String city;
    public String street;
    public String house;
    public String number;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(country).append(" ")
                .append(city).append(" ")
                .append(street).append(" ")
                .append(house).append(" ")
                .append(number).toString();
    }
}

//используем интерфейс для того, чтобы можно было впоследствии расширять адрес, например, при работе с юр. адресом
interface AddressBuilderTemplate {
    AddressBuilderTemplate setCountry(String country);
    AddressBuilderTemplate setCity(String city);
    AddressBuilderTemplate setStreet(String street);
    AddressBuilderTemplate setHouse(String house);
    AddressBuilderTemplate setNumber(String number);
    Address build();
}

class AddressBuilderImpl implements AddressBuilderTemplate {
    Address address;

    public AddressBuilderImpl() {
        address = new Address();
    }

    public Address build() {
        return address;
    }

    @Override
    public AddressBuilderTemplate setCity(String city) {
        address.city = city;
        return this;
    }

    @Override
    public AddressBuilderTemplate setCountry(String country) {
        address.country = country;
        return this;
    }

    @Override
    public AddressBuilderTemplate setStreet(String street) {
        address.street = street;
        return this;
    }

    @Override
    public AddressBuilderTemplate setHouse(String house) {
        address.house = house;
        return this;
    }

    @Override
    public AddressBuilderTemplate setNumber(String number) {
        address.number = number;
        return this;
    }
}