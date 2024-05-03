import java.util.ArrayList;
// Проблемы строителя

public class Builder004 {
    public static void main(String[] args) {
        AddressBuilder addressBuilder = AddressBuilder
                .build("Адрес", "Рабочий адрес")
                .appendItem("индекс", "460052")
                .appendItem("страна", "РФ")
                .appendItem("область", "Оренбургская")
                .appendItem("город", "Оренбург")
                .appendItem("улица", "Просторная")
                .appendItem("дом", "28")
                .appendItem("корпус", "3")
                .appendItem("Получатель", "Юля");

        System.out.println(addressBuilder);
    }
}

class AddressElement {
    public String title;
    public String value;
    private ArrayList<AddressElement> elements;

    public void appendElement(String title, String value) {
        elements.add(new AddressElement(title, value));
    }

    public AddressElement(String title, String value) {
        this.title = title;
        this.value = value;
        elements = new ArrayList<AddressElement>();
    }

    private String print(String indent) {
        StringBuilder result = new StringBuilder();
        indent += " ";
        result.append(String.format("%s#%s:%s%s",
            indent, title, value, System.lineSeparator()
        ));

        for (AddressElement item: elements) {
            result.append(item.print(indent));
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return print("");
    }
}

class AddressBuilder {
    AddressElement address;

    public AddressBuilder() {
    }

    public static AddressBuilder build(String title, String value) {
        AddressBuilder ab = new AddressBuilder();

        ab.address = new AddressElement(title, value);
        return ab;
    }

    public AddressBuilder appendItem(String title, String value) {
        address.appendElement(title, value);
        return this;
    }

    @Override
    public String toString() {
        return address.toString();
    }
}

/**
 * Проблемы могут возникнуть при случайном дублировании
 * одинаковых команд и элементов. В нашем примере указав 5 раз индекс.
 * т.е. все зависит от задачи, если в примере с xml нам было неважно, есть ли повторы
 * ь.е. элементы не обязаны быть уникальными, то в др. задачах может быть иначе,
 * см. Решение в Примере 5
 */