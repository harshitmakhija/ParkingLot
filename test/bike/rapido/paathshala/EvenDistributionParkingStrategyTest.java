package bike.rapido.paathshala;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvenDistributionParkingStrategyTest {

    private Attendant attendant;

    ArrayList<ParkingLotManagement> getListOfEmptyParkingLots(int numberOfParkingLots, int spaceInEachLot) {
        ArrayList<ParkingLotManagement> parkingLotManagementsList = new ArrayList<>();

        for (int parkingLot = 0; parkingLot < numberOfParkingLots; parkingLot++) {
            parkingLotManagementsList.add(new ParkingLotManagement(spaceInEachLot, parkingLot + 1));
        }
        return parkingLotManagementsList;
    }

    @BeforeEach
    void setUp() {
        attendant = new Attendant();
    }

    @Test
    void shouldParkTheCarsEvenly() {
        List<ParkingLotManagement> emptyParkingLotList = getListOfEmptyParkingLots(2, 2);
        for (ParkingLotManagement parkingLot : emptyParkingLotList) {
            attendant.assignParkingLot(parkingLot);
        }

        Strategy evenDistributionStrategy = new EvenDistributionParkingStrategy();

        Car car1 = new Car("UP320994");
        Car car2 = new Car("TU32094");
        Car car3 = new Car("PP320914");
        Car car4 = new Car("LP320977");
        attendant.parkTheCarInTheParkingLot(car1, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car2, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car3, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car4, evenDistributionStrategy);

        int car1ParkingLotNumber = attendant.carVsLotHashmap.get(car1).parkingLotID;
        int car2ParkingLotNumber = attendant.carVsLotHashmap.get(car2).parkingLotID;
        int car3ParkingLotNumber = attendant.carVsLotHashmap.get(car3).parkingLotID;
        int car4ParkingLotNumber = attendant.carVsLotHashmap.get(car4).parkingLotID;


        assertEquals(1, car1ParkingLotNumber);
        assertEquals(2, car2ParkingLotNumber);
        assertEquals(1, car3ParkingLotNumber);
        assertEquals(2, car4ParkingLotNumber);
    }

    @Test
    void shouldParkTheCarsEvenlyEvenAfterSomeCarsAreUnparked() {
        ArrayList<ParkingLotManagement> emptyParkingLotList = getListOfEmptyParkingLots(2, 3);
        for (ParkingLotManagement parkingLot : emptyParkingLotList) {
            attendant.assignParkingLot(parkingLot);
        }
        Strategy evenDistributionStrategy = new EvenDistributionParkingStrategy();

        Car car1 = new Car("CIN383");
        Car car2 = new Car("ABC383");
        Car car3 = new Car("XYZ383");
        Car car4 = new Car("DEL383");


        attendant.parkTheCarInTheParkingLot(car1, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car2, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car3, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car4, evenDistributionStrategy);
        attendant.unparkTheCarFromTheParkingLot(car2);
        attendant.unparkTheCarFromTheParkingLot(car4);

        Car car5 = new Car("UP383");
        attendant.parkTheCarInTheParkingLot(car5, evenDistributionStrategy);


        int car1ParkingLotNumber = attendant.carVsLotHashmap.get(car1).parkingLotID;
        int car3ParkingLotNumber = attendant.carVsLotHashmap.get(car3).parkingLotID;
        int car5ParkingLotNumber = attendant.carVsLotHashmap.get(car5).parkingLotID;

        assertEquals(1, car1ParkingLotNumber);
        assertEquals(1, car3ParkingLotNumber);
        assertEquals(2, car5ParkingLotNumber);
    }
}