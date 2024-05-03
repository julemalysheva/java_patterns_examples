package composite_builder;//Композитный строитель

public class Builder007 {
    public static void main(String[] args) {
        Worker worker = new WorkerBuilder()
                .lives()
                .setCity("Moscow")
                .setStreet("Lenina pr.")
                .setHouse("25")
                .job()
                .setCompany("VTB")
                .setPosition("manager")
                .setSalary(250000)
                .build();

        System.out.println(worker);

    }
}

class Worker {
    public String city, street, house, company, position;
    public Integer salary;

    @Override
    public String toString() {
        return String.format(
                "City = [%s], Street = [%s], House = [%s], Company = [%s], Position = [%s], Salary = [%d]",
                city, street, house, company, position, salary);
    }
}

//описываем объект, который отвечает исключительно за добавление информации об адресе
class AddressBuilder extends WorkerBuilder {

    public AddressBuilder(Worker worker) {
        this.worker = worker;
    }

    public AddressBuilder setCity(String city) {
        worker.city = city;
        return this;
    }

    public AddressBuilder setStreet(String street) {
        worker.street = street;
        return this;
    }

    public AddressBuilder setHouse(String house) {
        worker.house = house;
        return this;
    }
}

class JodBuilder extends WorkerBuilder {

    public JodBuilder(Worker worker) {
        this.worker = worker;
    }

    public JodBuilder setCompany(String company) {
        worker.company = company;
        return this;
    }

    public JodBuilder setPosition(String position) {
        worker.position = position;
        return this;
    }

    public JodBuilder setSalary(Integer salary) {
        worker.salary = salary;
        return this;
    }
}

//здесь мы разделили логику добавления данных о работе и адресе в разные методы,
//вызывая которые мы можем наполнять создаваемый объект нужными нам деталями.
class WorkerBuilder {
    protected Worker worker = new Worker();

    public AddressBuilder lives() {
        return new AddressBuilder(this.worker);
    }

    public JodBuilder job() {
        return new JodBuilder(this.worker);
    }

    public Worker build() {
        return this.worker;
    }

}




