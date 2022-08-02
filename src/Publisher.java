import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private Boolean isParkingLotFull;

    private List<Subscriber> mySubscribers =  new ArrayList<>() ;;

    Publisher()
    {

        this.isParkingLotFull = false ;
    }

    void updateStatus()
    {
        this.isParkingLotFull = true ;
        notifyParkingLotFull();
    }

    void notifyParkingLotFull()
    {
        for(int i=0; i<mySubscribers.size() ; i++)
        {
            mySubscribers.get(i).update(true) ;
        }
    }

    void subscribe(Subscriber subscriber)
    {
        mySubscribers.add(subscriber) ;
    }

    void unsubscribe(Subscriber subscriber)
    {
        mySubscribers.remove(subscriber);
    }

}
