package factory_method002_enum;

/**
 * Private конструктор и Enum
 */

public class Factory002 {
    public static void main(String[] args) throws Exception {
        Employee employee1 = new Employee("Имя 1", 43, Params.SET_AGE);
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

    public Employee(String name, int arg, Params params) throws Exception {
        switch (params) {
            case SET_AGE -> this.age = arg;
            case SET_SALARY -> this.salary = arg;
            default -> throw new Exception("Такого аргумента нет");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[Name: %s, Age: %d, Salary: %d]",
                name, age, salary);
    }
}
