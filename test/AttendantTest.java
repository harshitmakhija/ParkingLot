import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AttendantTest {

    void fillTheParkingLotFully(ParkingLotManagement parkingLot) {
        for (int carCount = 0; carCount < parkingLot.getMaxSpace(); carCount++) {
            Car tempCar = new Car("873DGD" + carCount);
            parkingLot.parkTheCar(tempCar);
        }
    }


    @Test
    void shouldReturnAParkingLotThatIsNotFull() {
        Attendant attendant = new Attendant(3, 3);

        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(2));

        ParkingLotManagement parkingLot = attendant.getParkingLot();



        assertNotNull(parkingLot);
    }


    @Test
    void shouldReturnNullWhenEveryParkingLotIsFull() {
        Attendant attendant = new Attendant(3, 3);

        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(1));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(2));

        ParkingLotManagement parkingLot = attendant.getParkingLot();



        assertNull(parkingLot);
    }

    @Test
    void shouldReturnCarIsParkedAtParkingLot1() {
        Attendant attendant = new Attendant(3, 3);

        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(2));

        Car car = new Car("ADBI232");
        String response = attendant.parkTheCarInTheParkingLot(car);


        assertEquals("CAR IS PARKED AT PARKING LOT 1", response);
    }

    @Test
    void shouldReturnNoSpaceAvailable() {
        Attendant attendant = new Attendant(3, 3);

        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(1));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(2));

        Car car = new Car("NEF4435");
        String response = attendant.parkTheCarInTheParkingLot(car);


        assertEquals("NO SPACE AVAILABLE", response);
    }

    @Test
    void shouldReturnParkingLotID1WhenSpaceIsAvailableAgain() {
        Attendant attendant = new Attendant(3, 2);
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));

        Car car1AtParkingLot1 = new Car("NEES232");
        Car car2AtParkingLot1 = new Car("TUS232");

        attendant.parkTheCarInTheParkingLot(car1AtParkingLot1);
        attendant.parkTheCarInTheParkingLot(car2AtParkingLot1);

        int parkingLotIdWhenLot0AndLot1isFull = attendant.getParkingLot().getParkingLotID();

        attendant.getParkingLotManagementList().get(1).unparkTheCar(car1AtParkingLot1);

        int parkingLotIdAfterUnParkingFromLot1 = attendant.getParkingLot().getParkingLotID();



        assertEquals(2, parkingLotIdWhenLot0AndLot1isFull);
        assertEquals(1, parkingLotIdAfterUnParkingFromLot1);
    }

    @Test
    void shouldReturnCarIsUnparkedFromParkingLot2() {
        Attendant attendant = new Attendant(3, 3);
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(1));

        Car car = new Car("CIN383");
        attendant.parkTheCarInTheParkingLot(car);

        String response = attendant.unparkTheCarFromTheParkingLot(car);

        assertEquals("CAR IS UNPARKED FROM PARKING LOT 2", response);
    }
}
