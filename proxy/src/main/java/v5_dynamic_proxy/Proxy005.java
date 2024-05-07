package v5_dynamic_proxy;
//Динамический Proxy, регистрация вызова любого метода как пример

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Proxy005 {
    public static void main(String[] args) {
        Hero hero = new Hero(123);
        GameUnit gu = DynamicProxy.info(hero, GameUnit.class);
        gu.getHp();
        gu.setHp(111);
        System.out.println(gu.getHp());
    }
}

//описываем перечень методов, кот.нужно регистрировать, в данном примере
//это набор геттеров и сеттеров
interface GameUnit {
    Integer getMp();
    void setMp(Integer mp);
    Integer getHp();
    void setHp(Integer hp);

}

class Hero implements GameUnit {
    private Integer hp;
    private Integer mp;

    public Hero(Integer hp) {
        this.hp = hp;
        this.mp = 100;
    }

    @Override
    public Integer getHp() {
        return hp;
    }

    @Override
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    @Override
    public Integer getMp() {
        return mp;
    }

    @Override
    public void setMp(Integer mp) {
        this.mp = mp;
    }
}

//используем рефлексию
class TerminalLogger implements InvocationHandler {
    private final Object obj;

    public TerminalLogger(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("Вызван %s", method.getName()));
        return method.invoke(obj, args);
    }
}

class DynamicProxy {
    /**
     * Метод info имеет два параметра: arg и obj. Первый параметр arg представляет
     * объект, для которого необходимо создать прокси-объект, а второй параметр obj
     * указывает на класс или интерфейс, которому должен принадлежать прокси-объект.
     *
     * Внутри метода info используется метод Proxy.newProxyInstance, который
     * создает и возвращает прокси-объект. В этом методе передаются три аргумента:
     *
     * 1. obj.getClassLoader() - в этом коде мы получаем класслоадер объекта obj,
     * который будет использован для загрузки класса прокси-объекта.
     * 2. new Class<?>[] { obj } - это массив классов интерфейсов, которые будут
     * реализованы прокси-объектом. В данном случае мы передаем только один класс
     * obj.
     * 3. new TerminalLogger(arg) - это объект типа TerminalLogger, который будет
     * использован для обработки вызовов методов прокси-объекта.
     *
     * TerminalLogger представляет собой класс, который, реализует интерфейс
     * InvocationHandler, так как это требуется для создания прокси-объекта.
     * Этот класс используется для перехвата вызовов методов на оригинальном
     * объекте arg и предоставляет возможность выполнить какую-то логику до
     * или после выполнения этих методов.
     * @param arg объект, для которого необходимо создать прокси-объект
     * @param obj указывает на класс или интерфейс, которому должен принадлежать прокси-объект
     * @return
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> T info(T arg, Class<T> obj) {
        return (T) Proxy.newProxyInstance(
                obj.getClassLoader(),
                new Class<?>[] { obj },
                new TerminalLogger(arg)
        );
    }
}


