
import java.util.ArrayList;
import java.util.Scanner;

// Abstraction: Passenger defines a common discount contract
abstract class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double getDiscountRate();

    public abstract String getPassengerType();
}

// Inheritance
class RegularPassenger extends Passenger {
    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountRate() {
        return 0.0;
    }

    @Override
    public String getPassengerType() {
        return "Regular";
    }
}

class StudentPassenger extends Passenger {
    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountRate() {
        return 0.10;
    }

    @Override
    public String getPassengerType() {
        return "Student";
    }
}

// Flight class
class Flight {
    private String flightCode;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String flightCode, String airline,
                  String source, String destination,
                  int availableSeats, double baseFare) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getAirline() {
        return airline;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public boolean hasEnoughSeats(int tickets) {
        return tickets > 0 && tickets <= availableSeats;
    }

    public void reserveSeats(int tickets) {
        if (hasEnoughSeats(tickets)) {
            availableSeats -= tickets;
        }
    }

    public void displayFlight() {
        System.out.printf(
            "%-8s %-15s %-12s %-12s %-8d Rs.%.2f%n",
            flightCode, airline, source, destination,
            availableSeats, baseFare
        );
    }
}

// Booking class
class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int tickets;
    private double subtotal;
    private double discount;
    private double totalFare;

    public Booking(String bookingId, Flight flight,
                   Passenger passenger, int tickets) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.tickets = tickets;

        this.subtotal = flight.getBaseFare() * tickets;
        this.discount = subtotal * passenger.getDiscountRate();
        this.totalFare = subtotal - discount;
    }

    public void displaySummary() {
        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight Code: " + flight.getFlightCode());
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Route: " + flight.getSource()
                           + " to " + flight.getDestination());
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Age: " + passenger.getAge());
        System.out.println("Passenger Type: "
                           + passenger.getPassengerType());
        System.out.println("Tickets: " + tickets);

        System.out.printf("Base fare per ticket: Rs.%.2f%n",
                          flight.getBaseFare());
        System.out.printf("Subtotal: Rs.%.2f%n", subtotal);
        System.out.printf("Discount (%.0f%%): Rs.%.2f%n",
                          passenger.getDiscountRate() * 100, discount);
        System.out.printf("Final amount: Rs.%.2f%n", totalFare);
        System.out.println("=====================================");
    }
}

// Main booking system
public class FlightBookingApp {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private int nextBookingNumber = 1001;

    public FlightBookingApp() {
        flights.add(new Flight("PN101", "IndiGo",
                "Pune", "Delhi", 20, 4000));

        flights.add(new Flight("MB202", "Air India",
                "Mumbai", "Bengaluru", 30, 3500));

        flights.add(new Flight("PH303", "Akasa Air",
                "Pune", "Hyderabad", 25, 3000));
    }

    // Display available flights
    public void displayFlights() {
        System.out.println("\n================ AVAILABLE FLIGHTS ================");
        System.out.printf("%-8s %-15s %-12s %-12s %-8s %s%n",
                "Code", "Airline", "Source", "Destination",
                "Seats", "Base Fare");

        for (Flight flight : flights) {
            flight.displayFlight();
        }
    }

    // Find a flight by code
    public Flight findFlight(String code) {
        for (Flight flight : flights) {
            if (flight.getFlightCode().equalsIgnoreCase(code)) {
                return flight;
            }
        }
        return null;
    }

    // Validate passenger and booking details
    private boolean validateBooking(String name, int age, int tickets) {
        if (name.trim().isEmpty()) {
            System.out.println("Error: Passenger name cannot be blank.");
            return false;
        }

        if (age <= 0) {
            System.out.println("Error: Age must be greater than zero.");
            return false;
        }

        if (tickets <= 0) {
            System.out.println("Error: Ticket count must be greater than zero.");
            return false;
        }

        return true;
    }

    // Book a flight
    public void bookFlight() {
        try {
            System.out.print("Enter flight code: ");
            String code = sc.nextLine().trim();

            Flight flight = findFlight(code);

            if (flight == null) {
                System.out.println("Error: Flight not found.");
                return;
            }

            System.out.print("Enter passenger name: ");
            String name = sc.nextLine();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Passenger type (Regular/Student): ");
            String type = sc.nextLine().trim();

            if (!type.equalsIgnoreCase("Regular")
                    && !type.equalsIgnoreCase("Student")) {
                System.out.println("Error: Invalid passenger type.");
                return;
            }

            System.out.print("Enter number of tickets: ");
            int tickets = Integer.parseInt(sc.nextLine().trim());

            if (!validateBooking(name, age, tickets)) {
                return;
            }

            if (!flight.hasEnoughSeats(tickets)) {
                System.out.println(
                    "Booking failed: Not enough seats available."
                );
                System.out.println(
                    "No booking was created. Available seats remain unchanged."
                );
                return;
            }

            // Polymorphism: both passenger types use the same contract
            Passenger passenger;

            if (type.equalsIgnoreCase("Student")) {
                passenger = new StudentPassenger(name.trim(), age);
            } else {
                passenger = new RegularPassenger(name.trim(), age);
            }

            // Update seats and create booking only after validation
            String bookingId = "BK" + nextBookingNumber;
            Booking booking = new Booking(
                    bookingId, flight, passenger, tickets);

            flight.reserveSeats(tickets);
            bookings.add(booking);
            nextBookingNumber++;

            System.out.println("\nBooking confirmed!");
            booking.displaySummary();

        } catch (NumberFormatException e) {
            System.out.println(
                "Error: Age and ticket count must be valid integers."
            );
        }
    }

    // Display the most recent booking
    public void viewBooking() {
        if (bookings.isEmpty()) {
            System.out.println("No confirmed bookings available.");
            return;
        }

        bookings.get(bookings.size() - 1).displaySummary();
    }

    // Main menu
    public void start() {
        while (true) {
            System.out.println("\n===== Welcome to Kiran Air Booking =====");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    displayFlights();
                    break;

                case "2":
                    bookFlight();
                    break;

                case "3":
                    viewBooking();
                    break;

                case "4":
                    System.out.println(
                        "Thank you for using Kiran Air Booking!"
                    );
                    sc.close();
                    return;

                default:
                    System.out.println(
                        "Invalid choice. Please select 1, 2, 3, or 4."
                    );
            }
        }
    }

    public static void main(String[] args) {
        FlightBookingApp app = new FlightBookingApp();
        app.start();
    }
}
