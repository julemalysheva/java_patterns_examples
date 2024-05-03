public class Builder001 {
    // Решение "в лоб"
    public static void main(String[] args) {
        StringBuilder xml = new StringBuilder();
        String[] items = {"Item1", "Item2", "Item3"};

        //append работает сильно быстрее по сравнению с обычной конкатенацией
        xml.append("<xml>")
                .append(System.lineSeparator())
                .append(" <items>")
                .append(System.lineSeparator());

        for (String item : items) {
            xml.append("  <item>")
                    .append(item)
                    .append("</item>")
                    .append(System.lineSeparator());
        }

        xml.append(" </items>")
                .append(System.lineSeparator())
                .append("</xml>")
                .append(System.lineSeparator());

        System.out.println(xml);
    }
}
//fluent interface
