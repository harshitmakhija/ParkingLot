package bike.rapido.paathshala;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FirstFreeDistributionStrategyTest {

    private Attendant attendant;

    ArrayList<ParkingLotManagement> getListOfEmptyParkingLots(int numberOfParkingLots, int spaceInEachLot) {
        ArrayList<ParkingLotManagement> parkingLotManagementsList = new ArrayList<>();

        for (int parkingLot = 0; parkingLot < numberOfParkingLots; parkingLot++) {
            parkingLotManagementsList.add(new ParkingLotManagement(spaceInEachLot, parkingLot+1));
        }
        return parkingLotManagementsList;
    }

    @BeforeEach
    void setUp() {
        attendant = new Attendant();
    }
}