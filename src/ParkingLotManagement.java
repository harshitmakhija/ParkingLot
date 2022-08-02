public class ParkingLotManagement {
    private int  MAX_SPACE ;
    private int spaceAvailable;

    private Publisher publisher ;

    ParkingLotManagement(int totalSpace, Publisher publisher)
    {
        this.MAX_SPACE = totalSpace ;
        this.spaceAvailable = totalSpace ;
        this.publisher = publisher ;
    }

    void alertThePublisher()
    {
        if(spaceAvailable != 0)
            return ;

        publisher.updateStatus();
    }

    private int getSpaceAvailable()
    {
        return this.spaceAvailable ;
    }

    int getMaxSpace()
    {
        return MAX_SPACE ;
    }

    public boolean checkIfParkingSlotAvailable() {
        return getSpaceAvailable() > 0 ? true : false;
    }

    public String parkTheCar(Car car) {

        if(car.checkIfCarIsParked() == true)
            return "CAR IS ALREADY PARKED" ;

        if(checkIfParkingSlotAvailable() == false)
            return "NO SPACE AVAILABLE" ;

        spaceAvailable--;

        if(spaceAvailable == 0 ) {
            publisher.notifyParkingLotFull();
        }

        car.setAsParked();
        return "CAR PARKED";
    }

    public String unparkTheCar(Car car)
    {
        if(car.checkIfCarIsParked() == false)
            return "CAR IS NOT YET PARKED" ;

        spaceAvailable++ ;
        car.setAsUnParked();
        return "CAR UNPARKED";
    }
    
}
