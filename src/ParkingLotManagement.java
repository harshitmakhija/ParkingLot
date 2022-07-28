import java.util.Scanner;

public class ParkingLotManagement {

     static int spaceAvailable = 3 ;

     static Driver getDriverDetailsFromUser()
     {
         String driverName, driverContact, driverCarNumber ;

         Scanner scanner = new Scanner(System.in).useDelimiter("\n") ;
         System.out.println("Enter Driver's Name : ");
         driverName = scanner.next() ;
         System.out.println("Enter Driver's Contact : ");
         driverContact = scanner.next();
         System.out.println("Enter Driver's Car Number : ");
         driverCarNumber = scanner.next();

         return new Driver(driverName, driverContact, driverCarNumber);
     }

     static ParkingTicket createParkingTicket()
     {
        int parkingID = spaceAvailable ;
        spaceAvailable-=1;

        String checkInDate = "Random Date" ;
        String checkInTime = "Random Time" ;

         return new ParkingTicket(parkingID, checkInDate, checkInTime);
     }


    public static void main(String [] args)
    {
        System.out.println("Welcome to the Parking Management System!");

        if (spaceAvailable == 0) {
            System.out.println("No Space Available!! Sorry");
            System.exit(0);
        }
        else{
            System.out.println("Space is Available, you can park");
        }

        Driver driverObject = getDriverDetailsFromUser() ;
        driverObject.parkingTicket = createParkingTicket();



        System.out.println("HELLLOO");
    }
}
