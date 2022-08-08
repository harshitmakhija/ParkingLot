package bike.rapido.paathshala;

import java.util.List;

public class FirstFreeDistributionStrategy implements Strategy {

    @Override
    public ParkingLotManagement applyStrategy(List<ParkingLotManagement> parkingLotManagementList) {
        ParkingLotManagement parkingLot;
        parkingLot = getFirstFreeParkingLot(parkingLotManagementList);
        return parkingLot ;
    }

    private ParkingLotManagement getFirstFreeParkingLot(List<ParkingLotManagement> parkingLotManagementList) {
        for(int index =0 ; index < parkingLotManagementList.size() ; index++)
        {
            if(parkingLotManagementList.get(index).checkIfParkingSlotAvailable()){
                return parkingLotManagementList.get(index) ;
            }
        }
        return null ;
    }
}
