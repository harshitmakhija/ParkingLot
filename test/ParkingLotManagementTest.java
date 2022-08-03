import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagementTest {
     ParkingLotManagement parkingLotManagement;
     Car car;

     Attendant attendant;


     void fillTheParkingLotFully (ParkingLotManagement parkingLot)
     {
         for(int carCount=0; carCount<parkingLot.getMaxSpace(); carCount++)
         {
            Car tempCar = new Car("873DGD" + carCount) ;
            parkingLot.parkTheCar(tempCar) ;
         }
         return ;
     }


    @BeforeEach
    void setUp() {
        car = new Car("DL2194 GG") ;
        parkingLotManagement =new ParkingLotManagement(2);
        attendant =new Attendant(3,3);
    }

    @Test
    void shouldParkTheCar() {
        String response = parkingLotManagement.parkTheCar(car) ;


        assertEquals("CAR PARKED", response);
    }

    @Test
    void shouldUnparkTheCarIfParked()
    {
        parkingLotManagement.parkTheCar(car) ;

        String response = parkingLotManagement.unparkTheCar(car) ;


        assertEquals("CAR UNPARKED", response);
    }
    @Test
    void shouldNotParkTheCarIfAlreadyParked() {
         parkingLotManagement.parkTheCar(car);
       String response =  parkingLotManagement.parkTheCar(car) ;


       assertEquals("CAR IS ALREADY PARKED", response);
    }

    @Test
    void shouldNotUnparkTheCarIfCarIsNotParked() {
        String response = parkingLotManagement.unparkTheCar(car);


        assertEquals("CAR IS NOT YET PARKED", response);
    }

    @Test
    void shouldNotAllowParkingIfSpaceIsFull() {
        fillTheParkingLotFully(parkingLotManagement) ;

         String response = parkingLotManagement.parkTheCar(car) ;


         assertEquals("NO SPACE AVAILABLE", response);
    }

    @Test
    void shouldReturnTrueWhenAllTheSubscribersGetTheNotificationThatLotIsFull() {

        Subscriber subscriber1 = new ParkingLotOwner("Asmit") ;
        Subscriber subscriber2 = new SecurityPersonnel("Harshit") ;
        parkingLotManagement.subscribe(subscriber1);
        parkingLotManagement.subscribe(subscriber2);

        fillTheParkingLotFully(parkingLotManagement) ;


        Boolean eachSubscriberGotNotification = parkingLotManagement.allSubscribersGotNotification();
        assertTrue(eachSubscriberGotNotification);
    }

    @Test
    void shouldReturnTrueWhenParkingLotWasFullEarlierAndNowHaveSpaceAgainAndOwnerIsNotified()
    {
        Subscriber subscriber1 = new ParkingLotOwner("Asmit") ;
        parkingLotManagement.subscribe(subscriber1);

        Car car1 = new Car("493SDJN");
        Car car2 = new Car("DSB4734") ;

        parkingLotManagement.parkTheCar(car1) ;
        parkingLotManagement.parkTheCar(car2) ;

        Boolean statusWhenFull = subscriber1.getParkingLotStatus() ;
        parkingLotManagement.unparkTheCar(car1);

        Boolean statusWhenEmpty = subscriber1.getParkingLotStatus() ;
        // #TODO
    }


    @Test
    void shouldReturnTheParkingLotIdThatIsNotFull()
    {
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(2));

        int emptyParkingLotNumber = attendant.getLotNumberThatHaveSpace();

        assertEquals(1, emptyParkingLotNumber);
    }


    @Test
    void shouldReturnMinus1WhenEveryParkingLotIsFull()
    {
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(0));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(1));
        fillTheParkingLotFully(attendant.getParkingLotManagementList().get(2));

        int emptyParkingLotNumber = attendant.getLotNumberThatHaveSpace();


        assertEquals(-1, emptyParkingLotNumber);
    }

}