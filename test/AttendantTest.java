import bike.rapido.paathshala.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AttendantTest {

    void fillTheParkingLotFully(ParkingLotManagement parkingLot) {
        for (int carCount = 0; carCount < parkingLot.getMaxSpace(); carCount++) {
            Car tempCar = new Car("873DGD" + carCount);
            parkingLot.parkTheCar(tempCar);
        }
    }

    ArrayList<ParkingLotManagement> getListOfEmptyParkingLots(int numberOfParkingLots, int spaceInEachLot) {
        ArrayList<ParkingLotManagement> parkingLotManagementsList = new ArrayList<>();

        for (int parkingLot = 0; parkingLot < numberOfParkingLots; parkingLot++) {
            parkingLotManagementsList.add(new ParkingLotManagement(spaceInEachLot, parkingLot+1));
        }
        return parkingLotManagementsList;
    }

    private Attendant attendant;

    @BeforeEach
    void setUp() {
        attendant = new Attendant();
    }

    @Test
    void shouldNotParkTheCarIfNoSpaceIsAvailable() {
        ArrayList<ParkingLotManagement> parkingLotManagementList = getListOfEmptyParkingLots(3, 3);

        for (ParkingLotManagement parkingLot : parkingLotManagementList) {
            fillTheParkingLotFully(parkingLot);
            attendant.assignParkingLot(parkingLot);
        }

        Strategy firstFreeParkingStrategy = new FirstFreeDistributionStrategy();

        Car car = new Car("NEF4435");
        ParkResponse response = attendant.parkTheCarInTheParkingLot(car, firstFreeParkingStrategy);


        assertFalse(response.successfullyParked);
        assertEquals(-1, response.parkingLotID);
        assertEquals("NO SPACE AVAILABLE", response.additionalComments);
    }

    @Test
    void shouldUnparkTheCarFromTheParkingLotHavingID3() {
        ArrayList<ParkingLotManagement> emptyParkingLotList = getListOfEmptyParkingLots(3, 3);

        for (ParkingLotManagement parkingLot : emptyParkingLotList) {
            attendant.assignParkingLot(parkingLot);
        }
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(1));

        Strategy firstFreeParkingStrategy = new FirstFreeDistributionStrategy();

        Car car = new Car("CIN383");
        attendant.parkTheCarInTheParkingLot(car, firstFreeParkingStrategy);

        UnparkResponse response = attendant.unparkTheCarFromTheParkingLot(car);

        assertTrue(response.successfullyUnparked);
        assertEquals("CAR IS UNPARKED FROM PARKING LOT 3", response.additionalComments);
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
        Car car1 = new Car("CIN383");
        Car car2 = new Car("ABC383");
        Car car3 = new Car("XYZ383");
        Car car4 = new Car("DEL383");

        Strategy evenDistributionStrategy = new EvenDistributionParkingStrategy();

        attendant.parkTheCarInTheParkingLot(car1, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car2, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car3, evenDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car4, evenDistributionStrategy);

        attendant.unparkTheCarFromTheParkingLot(car2);
        attendant.unparkTheCarFromTheParkingLot(car4);

        Car car5 = new Car("UP383");
        attendant.parkTheCarInTheParkingLot(car5,evenDistributionStrategy);




        int noOfCarsInParkingLot1 = attendant.parkingLotManagementList.get(0).noOfCarsInTheParkingLot();
        int noOfCarsInParkingLot2 = attendant.parkingLotManagementList.get(1).noOfCarsInTheParkingLot();
        Integer[] actualArray = {noOfCarsInParkingLot1, noOfCarsInParkingLot2};
        Arrays.sort(actualArray, Collections.reverseOrder());
        Integer[] expectedArray = {2, 1};



        assertTrue(Arrays.equals(actualArray, expectedArray));
    }

}
