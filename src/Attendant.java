import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Attendant {
    private int totalParkingLot ;
    private List<ParkingLotManagement> parkingLotManagementList = new ArrayList<>() ;

    private HashMap<Car,ParkingLotManagement> carVsLotHashmap = new HashMap<>() ;

    Attendant(int totalParkingLot, int spaceInEachLot)
    {
        this.totalParkingLot = totalParkingLot ;

        for(int parkingLotCount = 0 ; parkingLotCount < totalParkingLot ; parkingLotCount++)
        {
            ParkingLotManagement parkingLotManagement = new ParkingLotManagement(spaceInEachLot, parkingLotCount );
            parkingLotManagementList.add(parkingLotManagement) ;
        }
    }

    public List<ParkingLotManagement> getParkingLotManagementList()
    {
        return this.parkingLotManagementList ;
    }


    public ParkingLotManagement getParkingLot()
    {
        for(int i=0; i<totalParkingLot ; i++)
        {
            if( parkingLotManagementList.get(i).checkIfParkingSlotAvailable() )
                return parkingLotManagementList.get(i) ;
        }

        return null ;
    }

    public String parkTheCarInTheParkingLot(Car car)
    {
        ParkingLotManagement parkingLot = getParkingLot() ;

        if( parkingLot == null)
            return "NO SPACE AVAILABLE" ;

        parkingLot.parkTheCar(car) ;

        carVsLotHashmap.put(car,parkingLot) ;

        return "CAR IS PARKED AT PARKING LOT " + parkingLot.getParkingLotID() ;
    }

    public String unparkTheCarFromTheParkingLot(Car car)
    {
        if( !carVsLotHashmap.containsKey(car) )
            return "CAR NOT FOUND!" ;

        ParkingLotManagement parkingLot = carVsLotHashmap.get(car) ;

        parkingLot.unparkTheCar(car) ;

        carVsLotHashmap.remove(car) ;

        return "CAR IS UNPARKED FROM PARKING LOT " + parkingLot.getParkingLotID() ;
    }
}
