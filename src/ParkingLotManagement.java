import java.util.ArrayList;
import java.util.List;

public class ParkingLotManagement {
    private final int  MAX_SPACE ;
    private int spaceAvailable;
    private final int parkingLotID ;
    private List<Subscriber> mySubscribers =  new ArrayList<>() ;

    ParkingLotManagement(int totalSpace, int parkingLotID)
    {
        this.MAX_SPACE = totalSpace ;
        this.spaceAvailable = totalSpace ;
        this.parkingLotID = parkingLotID ;
    }

    public int noOfCarsInTheParkingLot() {
        return (this.MAX_SPACE-this.spaceAvailable);
    }

    public int getSpaceAvailable()
    {
        return this.spaceAvailable ;
    }

    int getMaxSpace()
    {
        return MAX_SPACE ;
    }

    int getParkingLotID() {
        return parkingLotID ;
    }

    public boolean checkIfParkingSlotAvailable() {

        return getSpaceAvailable() > 0 ;
    }

    public String parkTheCar(Car car) {

        if(car.checkIfCarIsParked() )
            return "CAR IS ALREADY PARKED" ;

        if(!checkIfParkingSlotAvailable())
            return "NO SPACE AVAILABLE" ;

        spaceAvailable--;

        if(spaceAvailable == 0 ) {
           notifyParkingLotFull();
        }

        car.setAsParked();
        return "CAR PARKED";
    }

    public void notifyOwnerThatSpaceIsAvailableAgain(){
        for (Subscriber subscriber : mySubscribers){
            if(subscriber.isOwner()){
                subscriber.updateParkingStatus(false);
            }
        }
    }

    public String unparkTheCar(Car car)
    {
        if(!car.checkIfCarIsParked())
            return "CAR IS NOT YET PARKED" ;

        if(spaceAvailable == 0)
            notifyOwnerThatSpaceIsAvailableAgain();

        spaceAvailable++ ;
        car.setAsUnParked();
        return "CAR UNPARKED";
    }

    public void subscribe(Subscriber subscriber)
    {
        mySubscribers.add(subscriber) ;
    }

    private void notifyParkingLotFull()
    {
        for(Subscriber subscriber: mySubscribers)
        {
            subscriber.updateParkingStatus(true);
        }
    }

    boolean doesAllSubsribersGetNotification()
    {
        boolean allGotNotified = true ;

        for(Subscriber subscriber: mySubscribers)
        {
            allGotNotified = allGotNotified & subscriber.isParkingLotFull() ;
        }

        return allGotNotified ;
    }
}
