import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagementTest {


     ParkingLotManagement parkingLotManagement;
    @BeforeEach
    void setUp() {
        parkingLotManagement =new ParkingLotManagement();
    }

    @Test
    void shouldReturnParkedIforFirstCar() {
        String status=parkingLotManagement.GetStatus();

        assertEquals("CAR PARKED", status);

    }

    @Test
    void shouldReturnParkedIforSecondCar() {
        parkingLotManagement.GetStatus();
        String status=parkingLotManagement.GetStatus();

        assertEquals("CAR PARKED", status);

    }

    @Test
    void shouldReturnNoSlotAvailableForThirdCar() {
        parkingLotManagement.GetStatus();
        parkingLotManagement.GetStatus();
        String status=parkingLotManagement.GetStatus();

        assertEquals("NO SLOT AVAILABLE", status);
    }

    @Test
    void shouldReturnNoSlotAvailableForFourthCar() {
        parkingLotManagement.GetStatus();
        parkingLotManagement.GetStatus();
        parkingLotManagement.GetStatus();
        String status=parkingLotManagement.GetStatus();

        assertEquals("NO SLOT AVAILABLE", status);
    }
}