package v1_bridge;

import java.util.ArrayList;

/**
 * Отделить абстракции от реализации, Bridge в действии на простом примере.
 * В данном примере отдельно иерархия цветов и фигур.
 */
public class Bridge001 {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(new RedColor(), 10));
        shapes.add(new Circle(new GreenColor(), 15));
        shapes.add(new Rectangle(new RedColor(), 10, 20));
        shapes.add(new Rectangle(new YellowColor(), 10, 15));
        shapes.add(new Rectangle(new GreenColor(), 25, 15));
        shapes.add(new Square(new RedColor(), 5));

        for (var shape: shapes) {
            shape.draw();
        }
    }
}

//Опишем абстракцию Цвет, в общем случае это может быть интерфейс или абстрактный класс
class Color {
    protected String color;

    //создать цвет типа цвет невозможно, потому что конструктор скрыт, но у нас есть
    //иерархия в виде цветов.
    protected Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}

class GreenColor extends Color {
    public GreenColor() {
        super("Зелёный");
    }
}

class RedColor extends Color {
    public RedColor() {
        super("Красный");
    }
}

class YellowColor extends Color {
    public YellowColor() {
        super("Жёлтый");
    }
}

//Базовое представление фигуры
abstract class Shape {
    protected Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

class Square extends Shape {
    public int side;

    public Square(Color color, int side) {
        super(color);
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println(
                String.format(
                        "Квадрат с ребром %d нарисован. Цвет: %s",
                        side,
                        color
                ));
    }
}

class Rectangle extends Shape {
    public int width;
    public int height;

    public Rectangle(Color color, int width, int height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println(
                String.format(
                        "Прямоугольник %d x %d нарисован. Цвет: %s",
                        width,
                        height,
                        color
                ));
    }

}

class Circle extends Shape {
    private int radius;

    public Circle(Color color, int radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println(
                String.format(
                        "Окружность радиусом %d нарисована. Цвет: %s",
                        radius,
                        color
                ));
    }
}


