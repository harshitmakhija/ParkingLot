import java.util.Scanner;

public class ParkingLotManagement {

    public int spaceAvailable = 1;

    static Driver getDriverDetailsFromUser() {
        String driverName, driverContact, driverCarNumber;

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter Driver's Name : ");
        driverName = scanner.next();
        System.out.println("Enter Driver's Contact : ");
        driverContact = scanner.next();
        System.out.println("Enter Driver's Car Number : ");
        driverCarNumber = scanner.next();

        return new Driver(driverName, driverContact, driverCarNumber);
    }

    public ParkingTicket createParkingTicket() {
        int parkingID = spaceAvailable;

        String checkInDate = "Random Date";
        String checkInTime = "Random Time";

        return new ParkingTicket(parkingID, checkInDate, checkInTime);
    }

    public String parkMyCar() {
        spaceAvailable--;
        return "CAR PARKED";
    }


    public boolean checkIfParkingSlotAvailable() {
        return spaceAvailable > 0 ? true : false;
    }

    public String GetStatus() {
        if (checkIfParkingSlotAvailable()) {
            return parkMyCar();
        } else {
            return "NO SLOT AVAILABLE";
        }
    }


    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Management System!");
        ParkingLotManagement parkingLotManagement = new ParkingLotManagement();
        parkingLotManagement.GetStatus();


    }
}
