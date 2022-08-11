package bike.rapido.paathshala;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    void shouldParkTheCarsUsingFirstFreeStrategy() {

        List<ParkingLotManagement> emptyParkingLotList = getListOfEmptyParkingLots(2, 4);
        for (ParkingLotManagement parkingLot : emptyParkingLotList) {
            attendant.assignParkingLot(parkingLot);
        }

        Strategy firstFreeDistributionStrategy = new FirstFreeDistributionStrategy();

        Car car1 = new Car("UP320994");
        Car car2 = new Car("TU32094");
        Car car3 = new Car("PP320914");
        Car car4 = new Car("LP320977");

        attendant.parkTheCarInTheParkingLot(car1, firstFreeDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car2, firstFreeDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car3, firstFreeDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car4, firstFreeDistributionStrategy);

        int car1ParkingLotNumber = attendant.carVsLotHashmap.get(car1).parkingLotID;
        int car2ParkingLotNumber = attendant.carVsLotHashmap.get(car2).parkingLotID;
        int car3ParkingLotNumber = attendant.carVsLotHashmap.get(car3).parkingLotID;
        int car4ParkingLotNumber = attendant.carVsLotHashmap.get(car4).parkingLotID;

        assertEquals(1, car1ParkingLotNumber) ;
        assertEquals(1, car2ParkingLotNumber) ;
        assertEquals(1, car3ParkingLotNumber) ;
        assertEquals(1, car4ParkingLotNumber) ;
    }

    @Test
    void shouldParkTheCarInFirstLotAfterItHasSpaceAgain()
    {
        List<ParkingLotManagement> emptyParkingLotList = getListOfEmptyParkingLots(2, 2);
        for (ParkingLotManagement parkingLot : emptyParkingLotList) {
            attendant.assignParkingLot(parkingLot);
        }

        Strategy firstFreeDistributionStrategy = new FirstFreeDistributionStrategy();

        Car car1 = new Car("UP320994");
        Car car2 = new Car("TU32094");
        Car car3 = new Car("PP320914");
        Car car4 = new Car("NEW3248") ;

        attendant.parkTheCarInTheParkingLot(car1, firstFreeDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car2, firstFreeDistributionStrategy);
        attendant.parkTheCarInTheParkingLot(car3, firstFreeDistributionStrategy);

        int car1ParkingLotNumber = attendant.carVsLotHashmap.get(car1).parkingLotID;
        int car2ParkingLotNumber = attendant.carVsLotHashmap.get(car2).parkingLotID;
        int car3ParkingLotNumber = attendant.carVsLotHashmap.get(car3).parkingLotID;

        assertEquals(1, car1ParkingLotNumber);
        assertEquals(1, car2ParkingLotNumber);
        assertEquals(2, car3ParkingLotNumber);

        UnparkResponse response =  attendant.unparkTheCarFromTheParkingLot(car1) ;

        assertTrue(response.successfullyUnparked);

        attendant.parkTheCarInTheParkingLot(car4, firstFreeDistributionStrategy);
        int car4ParkingLotNumber = attendant.carVsLotHashmap.get(car4).parkingLotID;



        assertEquals(1, car4ParkingLotNumber);
    }
}