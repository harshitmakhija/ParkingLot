import java.util.ArrayList;
import java.util.List;

public class ParkingLotManagement {
    private int  MAX_SPACE ;
    private int spaceAvailable;
    private int parkingLotID ;
    private List<Subscriber> mySubscribers =  new ArrayList<>() ;

    ParkingLotManagement(int totalSpace, int ID)
    {
        this.MAX_SPACE = totalSpace ;
        this.spaceAvailable = totalSpace ;
        this.parkingLotID = ID;
    }

    private int getSpaceAvailable()
    {
        return this.spaceAvailable ;
    }

    int getMaxSpace()
    {
        return MAX_SPACE ;
    }

    int getParkingLotID() {
        return this.parkingLotID ;
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
        if(car.checkIfCarIsParked() == false )
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

    boolean allSubscribersGotNotification()
    {
        boolean allGotNotified = true ;

        for(Subscriber subscriber: mySubscribers)
        {
            allGotNotified = allGotNotified & subscriber.isParkingLotFull() ;
        }

        return allGotNotified ;
    }
}
