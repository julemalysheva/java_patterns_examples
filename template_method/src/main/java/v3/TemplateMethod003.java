package v3;

import java.util.List;

public class TemplateMethod003 {
    public static void main(String[] args) {
        List<Person> family = List.of(
                new Daughter(),
                new Mom(),
                new Dad()
        );

        for (Person e: family) {
            e.eat();
            e.getBreakfast().kcalOfEnergy();
        }

    }
}

abstract class Product {
    public String price;

    public Product() {
        System.out.println(this.getClass().getSimpleName());
    }

    public abstract void kcalOfEnergy();
}

class Milk extends Product {
    @Override
    public void kcalOfEnergy() {
        System.out.println("Энергетическая ценность 60 ккал");
    }
}

class Coffee extends Product {
    @Override
    public void kcalOfEnergy() {
        System.out.println("Энергетическая ценность 30 ккал");
    }
}

class Cake extends Product {
    @Override
    public void kcalOfEnergy() {
        System.out.println("Энергетическая ценность 150 ккал");
    }
}

abstract class Person {
    protected Product breakfast;

    public Product getBreakfast() {
        return breakfast;
    }

    //шаблонный метод
    public abstract Product getProduct();

    public void  eat() {
        breakfast = getProduct();
    }
}

class Dad extends Person {
    @Override
    public Product getProduct() {
        return new Cake();
    }
}

class Daughter extends Person {
    @Override
    public Product getProduct() {
        return new Milk();
    }
}

class Mom extends Person {
    @Override
    public Product getProduct() {
        return new Coffee();
    }
}


