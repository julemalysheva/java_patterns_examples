public class Builder001 {
    // Решение "в лоб"
    public static void main(String[] args) {
        StringBuilder xml = new StringBuilder();
        String[] items = {"Item1", "Item2", "Item3"};

        //append работает сильно быстрее по сравнению с обычной конкатенацией
        xml.append("<xml>" + System.lineSeparator());
        xml.append(" <items>" + System.lineSeparator());

        for (String item: items) {
            xml.append("  <item>" + item + "</item>" + System.lineSeparator());
        }

        xml.append(" </items>" + System.lineSeparator());
        xml.append("</xml>" + System.lineSeparator());

        System.out.println(xml);
    }
}

