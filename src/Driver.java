public class Driver {
    private String driverName ;
    private Car car;

    void setCar(Car car)
    {
        this.car = car;
    }
    Car getCar()
    {
        return this.car;
    }

    Driver(String name)
    {
        this.driverName = name ;
    }

}
