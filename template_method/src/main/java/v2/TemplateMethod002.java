package v2;

public class TemplateMethod002 {
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

    protected abstract void appendComponent();

    protected void appendSugar() {
        System.out.println("Добавить сахар");
    }

    //закладываем еще какой-то метод, который что-то будет делать, а возможно и не будет
    protected abstract void something();

    //Шаблонный метод получения напитка
    public void makeElixir() {
        boidWater();
        appendComponent();
        appendSugar();
        something();
    }
}

class Tea extends Elixir {
    @Override
    protected void appendComponent() {
        System.out.println("Добавить чай");
    }

    private void appendMilk() {
        System.out.println("Добавить молоко");
    }

    @Override
    protected void something() {
        appendMilk();
    }
}

class Coffee extends Elixir {
    @Override
    protected void appendComponent() {
        System.out.println("Добавить кофе");
    }

    private void appendLemon() {
        System.out.println("Добавить лимон");
    }

    @Override
    protected void something() {
        appendLemon();
    }
}
