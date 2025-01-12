import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;


class Room 
{
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String roomType, double price) 
    {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;  // All rooms are available initially.
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        this.isAvailable = false; // Room is now booked
    }

    public void releaseRoom() {
        this.isAvailable = true;  // Room is now available again
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - $" + price;
    }
}


class Reservation {
    private Room room;
    private String customerName;
    private Date checkInDate;
    private Date checkOutDate;
    double totalAmount;
    
        public Reservation(Room room, String customerName, Date checkInDate, Date checkOutDate) {
            this.room = room;
            this.customerName = customerName;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.totalAmount = calculateTotalAmount();
        }
    
        private double calculateTotalAmount() {
            long diff = checkOutDate.getTime() - checkInDate.getTime();
            long days = diff / (1000 * 60 * 60 * 24); // Calculate the number of days
            return days * room.getPrice();
        }
    
        public void displayReservationDetails() {
            System.out.println("Reservation Details:");
            System.out.println("Customer: " + customerName);
            System.out.println("Room: " + room);
            System.out.println("Check-in: " + checkInDate);
            System.out.println("Check-out: " + checkOutDate);
            System.out.println("Total Amount: $" + totalAmount);
        }
    
        public Room getRoom() {
            return room;
        }
    }
    
    
    class Hotel {
        private ArrayList<Room> rooms;
        private ArrayList<Reservation> reservations;
    
        public Hotel() {
            rooms = new ArrayList<>();
            reservations = new ArrayList<>();
            initializeRooms();
        }
    
        private void initializeRooms() {
            rooms.add(new Room(101, "Single", 100));
            rooms.add(new Room(102, "Double", 150));
            rooms.add(new Room(103, "Suite", 250));
            rooms.add(new Room(104, "Single", 100));
            rooms.add(new Room(105, "Double", 150));
            rooms.add(new Room(106, "Suite", 250));
        }
    
        public void searchAvailableRooms(Date checkInDate, Date checkOutDate) {
            System.out.println("Searching for available rooms...");
            for (Room room : rooms) {
                if (room.isAvailable()) {
                    System.out.println(room);
                }
            }
        }
    
        public Reservation makeReservation(String customerName, Date checkInDate, Date checkOutDate, int roomNumber) {
            Room selectedRoom = null;
            for (Room room : rooms) {
                if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                    selectedRoom = room;
                    break;
                }
            }
            if (selectedRoom == null) {
                System.out.println("Room not available.");
                return null;
            }
    
            Reservation reservation = new Reservation(selectedRoom, customerName, checkInDate, checkOutDate);
            reservations.add(reservation);
            selectedRoom.bookRoom();
            return reservation;
        }
    
        public void displayBookingDetails(int roomNumber) {
            for (Reservation reservation : reservations) {
                if (reservation.getRoom().getRoomNumber() == roomNumber) {
                    reservation.displayReservationDetails();
                    return;
                }
            }
            System.out.println("No booking found for this room.");
        }
    
        public void processPayment(Reservation reservation) {
            if (reservation != null) {
                System.out.println("Processing payment of $" + reservation.totalAmount);
            // Simulate payment processing
            System.out.println("Payment successful. Reservation confirmed!");
        } else {
            System.out.println("No reservation found.");
        }
    }
}
public class HotelReservationSystem 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println();
        System.out.println("Welcome to the Hotel Reservation System!");
        while (true) {
            System.out.println("\n1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Process Payment");
            System.out.println("5. Exit");
            System.out.println();
            System.out.println("Enter your choice");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                // Search Available Rooms
                try {
                    System.out.print("Enter check-in date (yyyy-MM-dd): ");
                    String checkInStr = scanner.nextLine();
                    Date checkInDate = dateFormat.parse(checkInStr);
                    
                    System.out.print("Enter check-out date (yyyy-MM-dd): ");
                    String checkOutStr = scanner.nextLine();
                    Date checkOutDate = dateFormat.parse(checkOutStr);
                    
                    hotel.searchAvailableRooms(checkInDate, checkOutDate);
                } catch (Exception e) {
                    System.out.println("Invalid date format.");
                }
            } else if (choice == 2) {
                // Make Reservation
                System.out.print("Enter customer name: ");
                String customerName = scanner.nextLine();
                
                try {
                    System.out.print("Enter check-in date (yyyy-MM-dd): ");
                    String checkInStr = scanner.nextLine();
                    Date checkInDate = dateFormat.parse(checkInStr);
                    
                    System.out.print("Enter check-out date (yyyy-MM-dd): ");
                    String checkOutStr = scanner.nextLine();
                    Date checkOutDate = dateFormat.parse(checkOutStr);
                    
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    
                    Reservation reservation = hotel.makeReservation(customerName, checkInDate, checkOutDate, roomNumber);
                    if (reservation != null) {
                        System.out.println("Reservation made successfully.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date format.");
                }
            } else if (choice == 3) {
                // View Booking Details
                System.out.print("Enter room number to view booking details: ");
                int roomNumber = scanner.nextInt();
                hotel.displayBookingDetails(roomNumber);
            } else if (choice == 4) {
                // Process Payment
                System.out.print("Enter room number to process payment: ");
                int roomNumber = scanner.nextInt();
                hotel.displayBookingDetails(roomNumber);
                System.out.print("Proceed with payment? (yes/no): ");
                String paymentChoice = scanner.next();
                if (paymentChoice.equalsIgnoreCase("yes")) {
                    hotel.processPayment(null); // Call with actual reservation object when integrated with payment.
                }
            } else if (choice == 5) {
                // Exit the system
                System.out.println("Thank you for using the Hotel Reservation System!");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}



