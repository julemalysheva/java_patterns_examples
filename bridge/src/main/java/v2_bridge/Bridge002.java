package v2_bridge;

/**
 * На примере иерархии автомобилей, представим что разработка ведется
 * разными командами. Делим отдельно иерархию узлов авто и типов двигателя, авто концерна
 */
public class Bridge002 {
    public static void main(String[] args) {

    }
}

abstract class Options {

}

class Engine extends Options {

}

class Suspension extends Options {

}
//body type
abstract class CarBodyType {
    final Options[] args;

    public CarBodyType(Options... arg) {
        this.args = arg;
    }

    public abstract String bodySpecification();
}

class Electric extends CarBodyType {
    public Electric(Options... arg) {
        super(arg);
    }

    @Override
    public String bodySpecification() {
        return "Electric";
    }
}

class SUV extends CarBodyType {
    public SUV(Options... arg) {
        super(arg);
    }

    @Override
    public String bodySpecification() {
        return "SUV";
    }
}

class Sedan extends CarBodyType {
    public Sedan(Options... arg) {
        super(arg);
    }

    @Override
    public String bodySpecification() {
        return "Sedan";
    }
}

class Sportback extends CarBodyType {
    public Sportback(Options... arg) {
        super(arg);
    }

    @Override
    public String bodySpecification() {
        return "Sportback";
    }
}

//Aktiengesellschaft
abstract class VolkswagenGroup {
    private final CarBodyType bodyType;

    public abstract String carInfo();

    public VolkswagenGroup(CarBodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return carInfo() + " " + bodyType.bodySpecification();
    }
}
//простраиваем иерархию классов отдельной группы авто
//Audi
abstract class Audi extends VolkswagenGroup {
    public Audi(CarBodyType bodyType) {
        super(bodyType);
    }
}
class AudiRS4 extends Audi {
    public AudiRS4(CarBodyType bodyType) {
        super(bodyType);
    }

    @Override
    public String carInfo() {
        return "Audi RS4";
    }
}

class AudiRS5 extends Audi {
    public AudiRS5(CarBodyType bodyType) {
        super(bodyType);
    }

    @Override
    public String carInfo() {
        return "Audi RS5";
    }
}
class Etron extends Audi {
    public Etron(CarBodyType bodyType) {
        super(bodyType);
    }

    @Override
    public String carInfo() {
        return "Audi e-tron";
    }
}
//Bentley
abstract class Bentley extends VolkswagenGroup {
    public Bentley(CarBodyType bodyType) {
        super(bodyType);
    }
}
//Bugatti
abstract class Bugatti extends VolkswagenGroup {
    public Bugatti(CarBodyType bodyType) {
        super(bodyType);
    }
}
//Ducati
abstract class Ducati extends VolkswagenGroup {
    public Ducati(CarBodyType bodyType) {
        super(bodyType);
    }
}
//Porsche
abstract class Porsche extends VolkswagenGroup {
    public Porsche(CarBodyType bodyType) {
        super(bodyType);
    }
}

class PorscheMacan extends Porsche {
    public PorscheMacan(CarBodyType bodyType) {
        super(bodyType);
    }
    @Override
    public String carInfo() {
        return "Porsche Macan";
    }

}



