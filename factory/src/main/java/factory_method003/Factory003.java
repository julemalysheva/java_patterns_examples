package factory_method003;

/**
 * Private конструктор, фабричный метод
 */

public class Factory003 {
    public static void main(String[] args) {
        Employee employee1 = Employee.createWithAge("Имя 1", 43);
        System.out.println(employee1);
        Employee employee2 = Employee.createWithSalary("Имя 2", 123456);
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

    //Опишем несколько фабричных методов, кот.будут отвечать за создание сотрудников
    //которые будут принимать аргументы, уже понятные конечному пользователю.
    public static Employee createWithSalary(String name, int salary) {
        return new Employee(name, salary, Params.SET_SALARY);
    }

    public static Employee createWithAge(String name, int age) {
        return new Employee(name, age, Params.SET_AGE);
    }

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
