package abstract_factory002;
/**
 * Абстрактная фабрика, и польза паттерна Null Object
 */

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        Seller seller = new Seller();
        seller.carInfo();
        System.out.println("=====");
        Car car = seller.getCar("RacingCar");
        car.Drive();

        car = seller.getCar("ConcreteMixerCar");
        car.Drive();

        car = seller.getCar("AudiCar");
        car.Drive();

    }
}

interface Car {
    void Drive();
}

class RacingCar implements Car {
    @Override
    public void Drive() {
        System.out.println("Ты едешь на гоночном болиде");
    }
}

class ConcreteMixerCar implements Car {
    @Override
    public void Drive() {
        System.out.println("Ты едешь на бетономешалке");
    }
}

//Null Object тип заглушка для ненайденных ошибочных инициализаций
//Здесь также можно добавить логирование, принимать какие-то аргументы,
//чтобы понимать, что написал пользователь и т.д.
class UnknownCar implements Car {
    @Override
    public void Drive() {
        System.out.println("Упс... Что-то пошло не так");
    }
}

interface CarFactory {
    Car getCar();
}

class RacingCarFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new RacingCar();
    }
}

class ConcreteMixerCarFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new ConcreteMixerCar();
    }
}

class Seller {
    private HashMap<String, CarFactory> factories = new HashMap<>();

    public Seller()
            throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        //получаем только типы, явл. наследниками CarFactory
        Set<Class<? extends CarFactory>> types = new Reflections("abstract_factory002")
                .getSubTypesOf(CarFactory.class);
        //помещаем в наше хранилище
        for (Class<? extends CarFactory> tClass: types) {
            factories.put(tClass.getSimpleName(),
                    tClass.getDeclaredConstructor().newInstance());
        }

    }

    //Добавляем проверку и использование Null
    public Car getCar(String carType) {
        CarFactory factory = factories.get(carType+"Factory");
        if (factory == null) {
            return new UnknownCar();
        }
        return factories.get(carType+"Factory").getCar();
    }

    public void carInfo() {
        for (String className: factories.keySet()) {
            System.out.println(className);
        }
    }
}

