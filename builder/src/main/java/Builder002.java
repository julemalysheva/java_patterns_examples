import java.util.ArrayList;

//Простой строитель
public class Builder002 {
    public static void main(String[] args) {
        XmlBuilder root = new XmlBuilder("root");
        XmlBuilder xml = new XmlBuilder("xml");

        xml.appendPair("item", "Item1")
        .appendPair("item", "Item2")
        .appendPair("item", "Item3");

        root.append(xml);
        System.out.println(root);
    }
}

class XmlItem {
    public  String tag, value;
    public ArrayList<XmlItem> items = new ArrayList<>();

    public XmlItem(String tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public XmlItem(String tag) {
        this(tag, "");
    }

    public XmlItem() {
        this("", "");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        xmlBuild(sb, "");
        return sb.toString();
    }

    //описываем шаблон добавления некого узла
    private void xmlBuild(StringBuilder sb, String sp) {
        //открывающий тег
        sb.append(String.format(
           "%s<%s>%s",
                sp,
                this.tag,
                System.lineSeparator()
        ));

        //узел
        if (this.value != null && !this.value.isEmpty()) {
            sb.append(sp + " ");
            sb.append(this.value);
            sb.append(System.lineSeparator());
        }

        //рекурсивно обойти все узлы коллекции узлов
        for (XmlItem item: items) {
            item.xmlBuild(sb, sp + " ");
        }

        //закрывающий тег
        sb.append(String.format(
                "%s</%s>%s",
                sp,
                this.tag,
                System.lineSeparator()
        ));
    }
}

class XmlBuilder {
    private String rootName;
    private XmlItem root = new XmlItem();

    public XmlBuilder(String rootName) {
        this.rootName = rootName;
        this.root.tag = rootName;
    }

    public XmlBuilder appendPair(String tagName, String value) {
        XmlItem item = new XmlItem(tagName, value);
        this.root.items.add(item);
        return this;
    }

    public XmlBuilder append(XmlBuilder builder) {
        root.items.add(builder.root);
        return this;
    }

    public void clear() {
        root = new XmlItem("xml", "");//либо оставить пустыми аргументы
        this.root.tag = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
