package v1_facade_simple;
//Фасад на примере магазина

import java.util.ArrayList;
import java.util.Arrays;

public class Facade001 {
    public static void main(String[] args) {
        Client client = new Client();
        System.out.println(client.productList());
        client.tripHypermarket();

        System.out.println(client.productList());
    }
}

interface IProduct {
    //Маркировочный интерфейс
}

class Candy implements IProduct {
    String name;

    public Candy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Fruit implements IProduct {
    String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class MilkProduct implements IProduct {
    String name;

    public MilkProduct(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class NullProduct implements IProduct {


    public NullProduct() {

    }

    @Override
    public String toString() {
        return "NullProduct";
    }
}

class CandyShop {
    public static Candy getCandy(String name) {
        return new Candy(name);
    }
}

class FruitsFarmer {
    public Fruit getFruit(String fruitName) {
        return new Fruit(fruitName);
    }
}

class MilkFarmer {


    public MilkProduct getMilkProduct(String name) {
        return new MilkProduct(name);
    }
}

class Client {
    ArrayList<IProduct> basket;

    public Client() {
        basket = new ArrayList<IProduct>();
    }

    //корзину мы можем создать путем обращения к отдельным фермерам и получения
    // продукта. Такой подход будет, если нет никакого Фасада.
    public void purchases() {
        FruitsFarmer fruitsFarmer = new FruitsFarmer();
        basket.add(fruitsFarmer.getFruit("Apple"));
        basket.add(fruitsFarmer.getFruit("Dragon fruit"));

        MilkFarmer milkFarmer = new MilkFarmer();
        basket.add(milkFarmer.getMilkProduct("Cheese"));
        basket.add(milkFarmer.getMilkProduct("Yogurt"));

        basket.add(CandyShop.getCandy("Step"));
    }

    public String productList() {
        return "My purchases: " + Arrays.toString(basket.toArray());
    }

    //когда у нас есть супермаркет в качестве Фасада
    public void tripHypermarket() {
        basket = new Hypermarket()
                .buyProduct("Apple")
                .buyProduct("Cheese")
                .buyProduct("Yogurt")
                .buyProduct("Step")
                .pay();

    }
}

/**
 * В нашем примере Hypermarket будет являться Фасадом над некими мелкими фермерами
 * и мелкими поставщиками
 */
class Hypermarket {
    FruitsFarmer fruitsFarmer;
    MilkFarmer milkFarmer;

    ArrayList<IProduct> products;

    public Hypermarket() {
        products = new ArrayList<IProduct>();
        fruitsFarmer = new FruitsFarmer();
        milkFarmer = new MilkFarmer();
    }

    /**
     * Для Фасада не имеет значения как реализована логика его элементов, fruitsFarmer,
     * milkFarmer и т.д., они могут быть написаны совершенно по разному
     * @param productName
     * @return this, т.е. Hypermarket, построен таким образом, чтоб метод можно было вызывать
     * по цепочке раз за разом добавляя нужный продукт.
     */
    public Hypermarket buyProduct(String productName) {
        IProduct product;
        switch (productName) {
            case "Apple":
                product = fruitsFarmer.getFruit("Apple");
                break;
            case  "Dragon fruit":
                product = fruitsFarmer.getFruit("Dragon fruit");
                break;
            case "Cheese":
                product = milkFarmer.getMilkProduct("Cheese");
                break;
            case "Yogurt":
                product = milkFarmer.getMilkProduct("Yogurt");
                break;
            case "Step":
                product = CandyShop.getCandy("Step");
                break;
            default:
                product = new NullProduct();
                break;
        }
        products.add(product);
        return this;
    }

    public ArrayList<IProduct> pay() {
        return products;
    }
}