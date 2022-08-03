import java.util.ArrayList;
import java.util.List;

public class Attendant {
    private int totalParkingLot ;

    private List<ParkingLotManagement> parkingLotManagementList = new ArrayList<>() ;
    Attendant(int totalParkingLot, int spaceInEachLot)
    {
        this.totalParkingLot = totalParkingLot ;

        for(int i=0 ; i<totalParkingLot ; i++)
        {
            ParkingLotManagement parkingLotManagement = new ParkingLotManagement(spaceInEachLot);
            parkingLotManagementList.add(parkingLotManagement) ;
        }
    }
//
//    public int getTotalParkingLot()
//    {
//        return this.totalParkingLot ;
//    }

    public List<ParkingLotManagement> getParkingLotManagementList()
    {
        return this.parkingLotManagementList ;
    }

    public int getLotNumberThatHaveSpace()
    {
        for(int i=0; i<totalParkingLot ; i++)
        {
            if( parkingLotManagementList.get(i).checkIfParkingSlotAvailable() == true )
                return i ;
        }

        return -1;
    }

}
