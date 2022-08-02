public class Subscriber {
    private boolean isParkingLotFull;
    private String subscriberName ;


    Subscriber(String name)
    {
        this.subscriberName = name ;
        this.isParkingLotFull = false ;
    }

    public Boolean getNotificationStatus()
    {
        return this.isParkingLotFull ;
    }

    public void update(boolean isParkingLotFull)
    {
        this.isParkingLotFull = isParkingLotFull;
    }

}
