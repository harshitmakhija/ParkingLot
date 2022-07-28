public class Driver {
    String driverName ;
    String driverContact ;
    ParkingTicket parkingTicket ;
    Car car;

    Driver(String name, String contact, String carNumber)
    {
        this.driverName = name ;
        this.driverContact = contact ;

        Car carObj = new Car(carNumber) ;
        this.car = carObj ;
    }

}
