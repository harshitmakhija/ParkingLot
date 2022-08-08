package bike.rapido.paathshala;

import java.util.List;

public interface Strategy {
    ParkingLotManagement applyStrategy(List<ParkingLotManagement> parkingLotManagementList);
}
