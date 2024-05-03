public class Builder000 {
    public static void main(String[] args) {
        String xml = "";
        String[] items = {"Item1", "Item2", "Item3"};

        xml += "<xml>" + System.lineSeparator();
        xml += " <items>" + System.lineSeparator();

        for (String item: items) {
            xml += "  <item>" + item + "</item>" + System.lineSeparator();
        }

        xml += " </items>" + System.lineSeparator();
        xml += "</xml>" + System.lineSeparator();

        System.out.println(xml);
    }
}

// при таком подходе (конкатенация) к построению строки каждый раз в памяти сохраняется ее новая версия,
// усложняя при этом создание объекта строки.
// много памяти и времени уходит на обработку обычных строк

//Решение дальше