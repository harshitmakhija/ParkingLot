public class SecurityPersonnel implements Subscriber{
    private String name ;
    private Boolean isParkingLotFull ;

    SecurityPersonnel(String securityName)
    {
        this.name = securityName ;
        this.isParkingLotFull = false ;
    }

    public Boolean isOwner()
    {
        return false ;
    }
    public Boolean isParkingLotFull()
    {
        return this.isParkingLotFull ;
    }

    public void updateParkingStatus(Boolean update)
    {
        this.isParkingLotFull = update;
    }


}
