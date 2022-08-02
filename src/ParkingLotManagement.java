public class ParkingLotManagement {
    private int  MAX_SPACE ;
    private int spaceAvailable;

    private Publisher publisher ;

    ParkingLotManagement(int totalSpace)
    {
        this.MAX_SPACE = totalSpace ;
        this.spaceAvailable = totalSpace ;
        this.publisher = new Publisher() ;
    }

    void alertThePublished()
    {
        if(spaceAvailable != 0)
            return ;

        publisher.updateStatus();
    }

    int getSpaceAvailable()
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
//            SecurityPersonnel securityPersonnel = new SecurityPersonnel("Bahadur") ;
//            alertSecurityPersonnel(securityPersonnel);

            // TODO:  ALERT THE SECURITY PERSONNEL BY PASSING THE SECURITY PERSONNEL TO THE ALERT_SECURITY FUNCTION !!
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

    public static void main(String[] args) {
        ParkingLotManagement parkingLotManagement = new ParkingLotManagement(2);
    }
}
