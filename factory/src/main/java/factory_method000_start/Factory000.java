package factory_method000_start;

/**
 * Постановка проблемы - Java не поддерживает перегрузку
 * конструкторов (методов) по имени аргументов
 */

public class Factory000 {
    public static void main(String[] args) {
        Employee employee1 = new Employee(43, "Имя 1");
        System.out.println(employee1);
        Employee employee2 = new Employee("Имя 2", 123456);
        System.out.println(employee2);
    }
}

class Employee {
    public int salary;
    public int age;
    public String name;

    public Employee(String name, int salary) {
        this.salary = salary;
        this.name = name;
    }

    public Employee(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[Name: %s, Age: %d, Salary: %d]",
                name, age, salary);
    }
}
