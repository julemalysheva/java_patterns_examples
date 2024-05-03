public class Builder006 {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder()
                .withFullName("Имя Фамилия")
                .setSalary(10);

        Worker worker = eb.build();
        System.out.println(worker);
    }
}

class Worker {
    public String fullName;
    public Integer salary;

    @Override
    public String toString() {
        return String.format("Full Name = [%s], Salary = [%d]",
                this.fullName, this.salary);
    }
}

//почитать про рекурсивные дженерики
class WorkerBuilder<T extends WorkerBuilder<T>> {
    protected Worker worker = new Worker();

    public T withFullName(String fullName) {
        worker.fullName = fullName;
        return self();
    }

    public T atSalary(Integer salary) {
        worker.salary = salary;
        return self();
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    public Worker build() {
        return worker;
    }
}

class EmployeeBuilder extends WorkerBuilder<EmployeeBuilder> {
    public EmployeeBuilder setSalary(Integer salary) {
        super.worker.salary = salary * 10;
        return this.self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}
