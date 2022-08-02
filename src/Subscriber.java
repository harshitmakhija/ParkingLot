public class Subscriber {
    private boolean  isParkingLotFull;
    private String subscriberName ;


    Subscriber(String name)
    {
        this.subscriberName = name ;
    }

    public void update(boolean isParkingLotFull)
    {
        this.isParkingLotFull = isParkingLotFull;
    }

}
