package factory_method005;

/**
 * Решение проблемы из 004- Фабрика
 */

public class Factory005 {
    public static void main(String[] args) {
        //в этом случае получем разные варианты создания объекта, что не совсем хорошо и наглядно
        Employee employee1 = Employee.Factory.createWithAge("Имя 1", 43);
        System.out.println(employee1);
        Employee employee2 = Employee.Factory.createWithSalary("Имя 2", 123456);
                //new Employee("Имя 2", 123456, Params.SET_SALARY)
        System.out.println(employee2);
    }
}

enum Params {
    SET_AGE,
    SET_SALARY
}

class Employee {

    //класс фабрики делаем вложенным, а также public static, как следствие мы имеем доступ к членам
    //класса Employee
    public static class Factory {
        public static Employee createWithSalary(String name, int salary) {
            return new Employee(name, salary, Params.SET_SALARY);
        }

        public static Employee createWithAge(String name, int age) {
            return new Employee(name, age, Params.SET_AGE);
        }
    }

    public int salary;
    public int age;
    public String name;

    //снова скрываем конструктор, а класс фабрики делаем вложенным
    private Employee(String name, int arg, Params params) {
        switch (params) {
            case SET_AGE -> this.age = arg;
            case SET_SALARY -> this.salary = arg;
            //default -> throw new Exception("Такого аргумента нет");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[Name: %s, Age: %d, Salary: %d]",
                name, age, salary);
    }
}


