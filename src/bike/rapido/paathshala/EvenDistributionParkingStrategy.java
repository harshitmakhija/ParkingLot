package bike.rapido.paathshala;

import java.util.List;

public class EvenDistributionParkingStrategy implements Strategy{
    @Override
    public ParkingLotManagement applyStrategy(List<ParkingLotManagement> parkingLotManagementList) {
        ParkingLotManagement parkingLot;
        parkingLot = getParkingLotWithMaximumSpace(parkingLotManagementList);
        return parkingLot ;
    }

    private ParkingLotManagement getParkingLotWithMaximumSpace(List<ParkingLotManagement> parkingLotManagementList) {
        ParkingLotManagement parkingLot = null;
        int maxSpaceAvailable = 0;

        for(int index = 0; index < parkingLotManagementList.size() ; index++)
        {
            int spaceAvailable =  parkingLotManagementList.get(index).getSpaceAvailable() ;

            if(maxSpaceAvailable < spaceAvailable) {
                maxSpaceAvailable = spaceAvailable;
                parkingLot = parkingLotManagementList.get(index);
            }
        }
        return parkingLot;
    }


}
