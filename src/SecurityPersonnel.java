public class SecurityPersonnel {
    private String name ;
    private String statusOfParkingLot ;

    SecurityPersonnel(String name)
    {
        this.name = name ;
        this.statusOfParkingLot = "NOT FULL" ;
    }
    void updateStatusOfParkingLot(String status)
    {
        this.statusOfParkingLot = status ;
    }

    String getStatusOfParkingLot()
    {
        return this.statusOfParkingLot ;
    }
}
