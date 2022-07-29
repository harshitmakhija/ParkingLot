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
    void shouldReturnParkedIfSlotAvailable() {
        String status=parkingLotManagement.parkMyCar();

        assert(status == "CAR PARKED");

    }
}