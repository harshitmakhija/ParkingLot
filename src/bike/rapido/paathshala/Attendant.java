package bike.rapido.paathshala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Attendant {
    public List<ParkingLotManagement> parkingLotManagementList = new ArrayList<>() ;

    public HashMap<Car,ParkingLotManagement> carVsLotHashmap = new HashMap<>() ;

    public void assignParkingLot(ParkingLotManagement parkingLot)
    {
        parkingLotManagementList.add(parkingLot) ;
    }

    public List<ParkingLotManagement> getParkingLotManagementList()
    {
        return this.parkingLotManagementList ;
    }



    public ParkResponse parkTheCarInTheParkingLot(Car car, Strategy parkingStrategy)
    {
        ParkResponse response = new ParkResponse();

        ParkingLotManagement parkingLot = parkingStrategy.applyStrategy(parkingLotManagementList) ;

        if( parkingLot == null)
        {
            response.parkingLotID = -1 ;
            response.successfullyParked = false ;
            response.additionalComments = "NO SPACE AVAILABLE" ;
            return response ;
        }

        parkingLot.parkTheCar(car) ;

        carVsLotHashmap.put(car,parkingLot) ;

        response.successfullyParked = true ;
        response.parkingLotID = parkingLot.getParkingLotID() ;
        response.additionalComments = "CAR WAS PARKED SUCCESSFULLY" ;
        return  response ;
    }

    public UnparkResponse unparkTheCarFromTheParkingLot(Car car)
    {   UnparkResponse response = new UnparkResponse();

        if( !carVsLotHashmap.containsKey(car) )
        {
            response.additionalComments = "CAR NOT FOUND!" ;
            response.successfullyUnparked = false ;
            return response ;
        }

        ParkingLotManagement parkingLot = carVsLotHashmap.get(car) ;
        parkingLot.unparkTheCar(car) ;
        carVsLotHashmap.remove(car) ;

        response.successfullyUnparked = true ;
        response.additionalComments = "CAR IS UNPARKED FROM PARKING LOT " + parkingLot.getParkingLotID() ;

       return response ;
    }
}
