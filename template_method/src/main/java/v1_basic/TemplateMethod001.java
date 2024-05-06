package v1_basic;

public class TemplateMethod001 {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        coffee.makeElixir();

        Tea tea = new Tea();
        tea.makeElixir();
    }
}

abstract class Elixir {
    protected void  boidWater() {
        System.out.println("Вскипятить воду");
    }

    protected void appendSugar() {
        System.out.println("Добавить сахар");
    }

    protected abstract void appendComponent();

    //Шаблонный метод получения напитка
    public void makeElixir() {
        boidWater();
        appendComponent();
        appendSugar();
    }
}

class Tea extends Elixir {
    @Override
    protected void appendComponent() {
        System.out.println("Добавить чай");
    }
}

class Coffee extends Elixir {
    @Override
    protected void appendComponent() {
        System.out.println("Добавить кофе");
    }
}
