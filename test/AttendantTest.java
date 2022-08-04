import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AttendantTest {

    void fillTheParkingLotFully(ParkingLotManagement parkingLot) {
        for (int carCount = 0; carCount < parkingLot.getMaxSpace(); carCount++) {
            Car tempCar = new Car("873DGD" + carCount);
            parkingLot.parkTheCar(tempCar);
        }
    }

    ArrayList<ParkingLotManagement> getListOfEmptyParkingLots(int numberOfParkingLots, int spaceInEachLot)
    {
        ArrayList<ParkingLotManagement> parkingLotManagementsList  = new ArrayList<>();

        for(int parkingLot = 0 ; parkingLot < numberOfParkingLots ; parkingLot++)
        {
            parkingLotManagementsList.add(new ParkingLotManagement(spaceInEachLot) );
        }
        return parkingLotManagementsList ;
    }

    private  Attendant attendant ;
    @BeforeEach
    void setUp() {
        attendant = new Attendant();
    }

    @Test
    void shouldNotParkTheCarIfNoSpaceIsAvailable() {
        ArrayList<ParkingLotManagement> parkingLotManagementList = getListOfEmptyParkingLots(3,3) ;

        for(ParkingLotManagement parkingLot : parkingLotManagementList) {
            fillTheParkingLotFully(parkingLot);
            attendant.AssignParkingLot(parkingLot);
        }


        Car car = new Car("NEF4435");
        ParkResponse response = attendant.parkTheCarInTheParkingLot(car);




        assertFalse( response.successfullyParked);
        assertEquals(-1,response.parkingLotID);
        assertEquals("NO SPACE AVAILABLE", response.additionalComments);
    }

    @Test
    void shouldUnparkTheCarFrom3rdParkingLot() {
        ArrayList<ParkingLotManagement> emptyParkingLotList = getListOfEmptyParkingLots(3,3) ;

        for(ParkingLotManagement parkingLot : emptyParkingLotList) {
            attendant.AssignParkingLot(parkingLot);
        }
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(1));

        Car car = new Car("CIN383");
        attendant.parkTheCarInTheParkingLot(car);




        UnparkResponse response = attendant.unparkTheCarFromTheParkingLot(car);




        assertTrue(response.successfullyUnparked);
        assertEquals("CAR IS UNPARKED FROM PARKING LOT 2" , response.additionalComments);
    }
}
