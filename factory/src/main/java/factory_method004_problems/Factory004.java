package factory_method004_problems;

/**
 * фабричный метод - проблемы
 * часто бывает, что есть модель объекта, а логика создания выносится отдельной компонентой
 */

public class Factory004 {
    public static void main(String[] args) {
        //в этом случае получем разные варианты создания объекта, что не совсем хорошо и наглядно
        Employee employee1 = EmployeeFactory.createWithAge("Имя 1", 43);
        System.out.println(employee1);
        Employee employee2 = new Employee("Имя 2", 123456, Params.SET_SALARY);
        System.out.println(employee2);
    }
}

enum Params {
    SET_AGE,
    SET_SALARY
}

class Employee {
    public int salary;
    public int age;
    public String name;

    //в случае когда логика создания, фабричные методы выносятся в другой класс, приватный констуктор там
    //явно не виден, простое решение сделать public, другое решение будет дальше в примерах
    public Employee(String name, int arg, Params params) {
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

class EmployeeFactory {
    public static Employee createWithSalary(String name, int salary) {
        return new Employee(name, salary, Params.SET_SALARY);
    }

    public static Employee createWithAge(String name, int age) {
        return new Employee(name, age, Params.SET_AGE);
    }
}
