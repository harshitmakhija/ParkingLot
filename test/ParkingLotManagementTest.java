import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagementTest {
     ParkingLotManagement parkingLotManagement;
     Driver driver;
     Car car;

     ParkingLotManagement getFullyParkedLot()
     {
         ParkingLotManagement parkingLotObj = new ParkingLotManagement(3) ;

         for(int i=0; i<parkingLotObj.getMaxSpace(); i++)
         {
            Car tempCar = new Car("873DGD" + i) ;
            parkingLotObj.parkTheCar(tempCar) ;
         }

         return  parkingLotObj ;
     }


    @BeforeEach
    void setUp() {
        driver = new Driver("Asmit") ;
        car = new Car("DL2194 GG") ;
        driver.setCar(car);
        parkingLotManagement =new ParkingLotManagement(2);
    }

    @Test
    void shouldParkTheCar() {
        String response = parkingLotManagement.parkTheCar(driver.getCar()) ;

        assertEquals("CAR PARKED", response);
    }

    @Test
    void shouldUnparkTheCarIfParked()
    {
        Car car = driver.getCar();
        parkingLotManagement.parkTheCar(car) ;

        String response = parkingLotManagement.unparkTheCar(car) ;

        assertEquals("CAR UNPARKED", response);
    }
    @Test
    void shouldNotParkTheCarIfAlreadyParked() {
        parkingLotManagement.parkTheCar(driver.getCar());

       String response =  parkingLotManagement.parkTheCar(driver.getCar()) ;
       assertEquals("CAR IS ALREADY PARKED", response);
    }

    @Test
    void shouldNotUnparkTheCarIfCarIsNotParked() {
        String response = parkingLotManagement.unparkTheCar(driver.getCar());

        assertEquals("CAR IS NOT YET PARKED", response);

    }

    @Test
    void shouldNotAllowParkingIfSpaceIsFull() {

         ParkingLotManagement fullyParked = getFullyParkedLot() ;

         String response = fullyParked.parkTheCar(driver.getCar()) ;

         assertEquals("NO SPACE AVAILABLE", response);
    }

}