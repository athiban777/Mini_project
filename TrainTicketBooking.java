import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Passenger {
    String name;
    int age;
    char gender;
    String pnr;
    String source;
    String destination;
    double ticketPrice;

    Passenger(String name, int age, char gender, String pnr, String source, String destination, double ticketPrice) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pnr = pnr;
        this.source = source;
        this.destination = destination;
        this.ticketPrice = ticketPrice;
    }

    void displayPassengerDetails() {
        System.out.println("PNR: " + pnr);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Ticket Price: ₹" + ticketPrice);
        System.out.println("------------------------------");
    }
}

public class TrainTicketBooking {

    private static final int TOTAL_SEATS = 5;  // Total number of seats in the train
    private static int seatsAvailable = TOTAL_SEATS;
    private static Map<String, Passenger> passengerMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Train Ticket Booking System ====");
            System.out.println("1. Book a Ticket");
            System.out.println("2. Show Available Seats");
            System.out.println("3. Check Passenger Details by PNR");
            System.out.println("4. Show All Booked Tickets");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    showAvailableSeats();
                    break;
                case 3:
                    checkPassengerDetailsByPNR();
                    break;
                case 4:
                    showBookedTickets();
                    break;
                case 5:
                    showThankYouAnimation();  // Show "Thank You" animation before exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void bookTicket() {
        if (seatsAvailable > 0) {
            System.out.print("Enter Passenger Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Passenger Age: ");
            int age = scanner.nextInt();

            System.out.print("Enter Passenger Gender (M/F): ");
            char gender = scanner.next().charAt(0);
            scanner.nextLine();  // Consume the newline character

            System.out.print("Enter Source: ");
            String source = scanner.nextLine();

            System.out.print("Enter Destination: ");
            String destination = scanner.nextLine();

            double ticketPrice = generateTicketPrice();

            String pnr = generatePNR();
            Passenger passenger = new Passenger(name, age, gender, pnr, source, destination, ticketPrice);
            passengerMap.put(pnr, passenger);
            seatsAvailable--;

            System.out.println("Booking your ticket, please wait...");
            showLoadingAnimation();  // Show loading animation

            System.out.println("\nTicket booked successfully! Your PNR: " + pnr);
            passenger.displayPassengerDetails();
            showAvailableSeats();  // Show available seats after booking
        } else {
            System.out.println("Sorry, all seats are occupied.");
        }
    }

    private static void showLoadingAnimation() {
        String animationChars = "|/-\\";
        for (int i = 0; i < 10; i++) {
            System.out.print("\r" + animationChars.charAt(i % animationChars.length()) + " Processing...");
            try {
                Thread.sleep(200);  // Delay of 200ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print("\rDone!           \n");  // Clear the line after the animation
    }

    private static void showAvailableSeats() {
        System.out.println("Seats available: " + seatsAvailable + "/" + TOTAL_SEATS);
    }

    private static void checkPassengerDetailsByPNR() {
        System.out.print("Enter PNR to check details: ");
        String pnr = scanner.nextLine();
        Passenger passenger = passengerMap.get(pnr);

        if (passenger != null) {
            System.out.println("Passenger Details:");
            passenger.displayPassengerDetails();
        } else {
            System.out.println("No passenger found with PNR: " + pnr);
        }
    }

    private static void showBookedTickets() {
        if (passengerMap.isEmpty()) {
            System.out.println("No tickets booked yet.");
        } else {
            System.out.println("\nList of Booked Tickets:");
            for (Passenger passenger : passengerMap.values()) {
                passenger.displayPassengerDetails();
            }
        }
    }

    private static String generatePNR() {
        Random random = new Random();
        int pnrNumber = random.nextInt(900000) + 100000; // Generates a 6-digit PNR number
        return "PNR" + pnrNumber;
    }

    private static double generateTicketPrice() {
        Random random = new Random();
        return 500.0 + (random.nextDouble() * 2500.0); // Generates a random price between ₹500 and ₹3000
    }

    private static void showThankYouAnimation() {
        String[] thankYouMessages = {
            "T   H   A   N   K",
            "    Y   O   U      ",
            "    F   O   R       ",
            " U   S   I   N   G   ",
            "    O   U   R          ",
            " S   E   R   V   I   C   E   S   !"
        };

        for (String message : thankYouMessages) {
            System.out.print("\r" + message);
            try {
                Thread.sleep(500);  // Delay of 500ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nW E L C O M E   A G A I N!");
    }
}
