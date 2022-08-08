package bike.rapido.paathshala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Attendant {
    public List<ParkingLotManagement> parkingLotManagementList = new ArrayList<>() ;

    private HashMap<Car,ParkingLotManagement> carVsLotHashmap = new HashMap<>() ;

    public void assignParkingLot(ParkingLotManagement parkingLot)
    {
        parkingLotManagementList.add(parkingLot) ;
    }

    public List<ParkingLotManagement> getParkingLotManagementList()
    {
        return this.parkingLotManagementList ;
    }

    public ParkingLotManagement getFreeParkingLotForEvenDistribution()
    {
        ParkingLotManagement parkingLot = null;
        int maxSpaceAvailable = 0;

        for(int index = 0; index <parkingLotManagementList.size() ; index++)
        {
            int spaceAvailable =  parkingLotManagementList.get(index).getSpaceAvailable() ;

            if(maxSpaceAvailable < spaceAvailable) {
                maxSpaceAvailable = spaceAvailable;
                parkingLot = parkingLotManagementList.get(index);
            }
        }

        return parkingLot ;
    }

    public ParkingLotManagement getFirstFreeParkingLot()
    {
        for(int index =0 ; index < parkingLotManagementList.size() ; index++)
        {
            if(parkingLotManagementList.get(index).checkIfParkingSlotAvailable()){
                return parkingLotManagementList.get(index) ;
            }
        }
        return null ;
    }


    public ParkResponse parkTheCarInTheParkingLot(Car car, String parkingPattern)
    {
        ParkResponse response = new ParkResponse();

        ParkingLotManagement parkingLot = null ;

        if(parkingPattern.equals("EVEN DISTRIBUTION")) {
            parkingLot = getFreeParkingLotForEvenDistribution();
        }
        else if(parkingPattern.equals("FIRST FREE DISTRIBUTION"))
        {
            parkingLot = getFirstFreeParkingLot() ;
        }
        else {
            response.parkingLotID = -1 ;
            response.successfullyParked = false ;
            response.additionalComments = "NO SUCH PARKING PATTERN EXIST" ;
            return response;
        }

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
