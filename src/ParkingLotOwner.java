public class ParkingLotOwner implements Subscriber {
    private String name ;
    private Boolean isParkingLotFull ;

    ParkingLotOwner(String ownerName)
    {
        this.name = ownerName ;
        this.isParkingLotFull = false ;
    }

    public Boolean getParkingLotStatus()
    {
        return this.isParkingLotFull ;
    }

    public void updateParkingStatus(Boolean update)
    {
        this.isParkingLotFull = update;
    }

}
