import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
            parkingLotManagementsList.add(new ParkingLotManagement(spaceInEachLot, parkingLot));
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


        Car car = new Car("NEF4435");
        ParkResponse response = attendant.parkTheCarInTheParkingLot(car, "FIRST FREE DISTRIBUTION");


        assertFalse(response.successfullyParked);
        assertEquals(-1, response.parkingLotID);
        assertEquals("NO SPACE AVAILABLE", response.additionalComments);
    }

    @Test
    void shouldUnparkTheCarFromTheParkingLotHavingID2() {
        ArrayList<ParkingLotManagement> emptyParkingLotList = getListOfEmptyParkingLots(3, 3);

        for (ParkingLotManagement parkingLot : emptyParkingLotList) {
            attendant.assignParkingLot(parkingLot);
        }
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(1));

        Car car = new Car("CIN383");
        attendant.parkTheCarInTheParkingLot(car, "FIRST FREE DISTRIBUTION");

        UnparkResponse response = attendant.unparkTheCarFromTheParkingLot(car);

        assertTrue(response.successfullyUnparked);
        assertEquals("CAR IS UNPARKED FROM PARKING LOT 2", response.additionalComments);
    }

    @Test
    void shouldParkTheCarsEvenly() {
        ArrayList<ParkingLotManagement> emptyParkingLotList = getListOfEmptyParkingLots(3, 5);
        for (ParkingLotManagement parkingLot : emptyParkingLotList) {
            attendant.assignParkingLot(parkingLot);
        }

        int noOfIncomingCars = 7;
        for (int count = 0; count < noOfIncomingCars; count++) {
            Car car = new Car("CIN383" + count);
            attendant.parkTheCarInTheParkingLot(car, "EVEN DISTRIBUTION");
        }




        int noOfCarsInParkingLot1 = attendant.parkingLotManagementList.get(0).noOfCarsInTheParkingLot();
        int noOfCarsInParkingLot2 = attendant.parkingLotManagementList.get(1).noOfCarsInTheParkingLot();
        int noOfCarsInParkingLot3 = attendant.parkingLotManagementList.get(2).noOfCarsInTheParkingLot();
        Integer[] actualArray = {noOfCarsInParkingLot1, noOfCarsInParkingLot2, noOfCarsInParkingLot3};
        Arrays.sort(actualArray, Collections.reverseOrder());
        Integer[] expectedArray = {3, 2, 2};




        assertTrue(Arrays.equals(actualArray, expectedArray));
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

        attendant.parkTheCarInTheParkingLot(car1, "EVEN DISTRIBUTION");
        attendant.parkTheCarInTheParkingLot(car2, "EVEN DISTRIBUTION");
        attendant.parkTheCarInTheParkingLot(car3, "EVEN DISTRIBUTION");
        attendant.parkTheCarInTheParkingLot(car4, "EVEN DISTRIBUTION");

        attendant.unparkTheCarFromTheParkingLot(car2);
        attendant.unparkTheCarFromTheParkingLot(car4);

        Car car5 = new Car("UP383");
        attendant.parkTheCarInTheParkingLot(car5,"EVEN DISTRIBUTION");




        int noOfCarsInParkingLot1 = attendant.parkingLotManagementList.get(0).noOfCarsInTheParkingLot();
        int noOfCarsInParkingLot2 = attendant.parkingLotManagementList.get(1).noOfCarsInTheParkingLot();
        Integer[] actualArray = {noOfCarsInParkingLot1, noOfCarsInParkingLot2};
        Arrays.sort(actualArray, Collections.reverseOrder());
        Integer[] expectedArray = {2, 1};



        assertTrue(Arrays.equals(actualArray, expectedArray));
    }

}
