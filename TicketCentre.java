import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class TicketCentre {
    private String Username;
    private String password;

    public TicketCentre(String Username, String password)
    {
        this.Username = Username;
        this.password = password;
    }

    public String getUsername()
    {
        return Username;
    }

    public String getPassword()
    {
        return password;
    }
}

class Reservation
{
    private String pnr;
    private String username;
    private String trainNumber;
    private String classType;
    private String journeyDate;
    private String source;
    private String destination;

    public Reservation(String pnr, String username, String trainNumber, String classType, String journeyDate, String source, String destination)
    {
        this.pnr = pnr;
        this.username = username;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.source = source;
        this.destination = destination;
    }

    public String getPnr()
    {
        return pnr;
    }

    public String getUsername()
    {
        return username;
    }

    public String getTrainNumber()
    {
        return trainNumber;
    }

    public String getClassType()
    {
        return classType;
    }

    public String getJourneyDate()
    {
        return journeyDate;
    }

    public String getSource()
    {
        return source;
    }

    public String getDestination()
    {
        return destination;
    }
}

public class ReservationSystem
{
    private static Map<String, TicketCentre> users = new HashMap<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int pnrCounter = 1;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.err.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.err.println("Loging out of the System, thank you for your time! ");
                    System.exit(0);
                default:
                    System.err.println("your choice is invalid. Please try again.");
            }
        }
    }

    private static void register(Scanner scanner)
    {
        System.out.print(" Please enter a new username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username))
        {
            System.err.println(" This username already exists. Choose a different one.");
            return;
        }

        System.out.print(" Please enter a password: ");
        String password = scanner.nextLine();

        TicketCentre user = new TicketCentre(username, password);
        users.put(username, user);

        System.err.println(" Your Registration is successful!");
    }

    private static void login(Scanner scanner)
    {
        System.out.print(" Please enter your username: ");
        String username = scanner.nextLine();
        System.out.print(" Please enter your password: ");
        String password = scanner.nextLine();

        TicketCentre user = users.get(username);
        if (user != null && user.getPassword().equals(password))
        {
            System.err.println(" your login was successful!");

            while (true)
            {
                System.out.println("1 to make a Reservation");
                System.out.println("2. to cancel Reservation");
                System.out.println("3. to logout");
                System.err.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice)
                {
                    case 1:
                        makeReservation(scanner, username);
                        break;
                    case 2:
                        cancelReservation(scanner, username);
                        break;
                    case 3:
                        System.err.println("Logging out.");
                        return;
                    default:
                        System.err.println(" Your choice is invalid. Please try again.");
                }
            }
        }
        else
        {
            System.err.println("Login failed. Invalid username or password.");
        }
    }

    private static void makeReservation(Scanner scanner, String username)
    {
        System.out.print(" Please enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: (1/2/General)");
        String classType = scanner.nextLine();
        System.out.print("Enter journey date: (DD/MM/YYYY)");
        String journeyDate = scanner.nextLine();
        System.out.print("Enter Your current city to leave from : ");
        String source = scanner.nextLine();
        System.out.print("Enter Your destination: ");
        String destination = scanner.nextLine();

        String pnr = "PNR"+trainNumber +" "+ pnrCounter++;
        Reservation reservation = new Reservation(pnr, username, trainNumber, classType, journeyDate, source, destination);
        reservations.add(reservation);

        System.err.println(" Your reservation is successful!");
        System.out.println("PNR: " + pnr);
    }

    private static void cancelReservation(Scanner scanner, String username)
    {
        System.out.print("Enter PNR number to cancel: ");
        String pnr = scanner.nextLine();

        for (Reservation reservation : reservations) {
            if (reservation.getUsername().equals(username) && reservation.getPnr().equals(pnr)) {
                reservations.remove(reservation);
                System.err.println("Reservation canceled successfully.");
                return;
            }
        }
        System.err.println("Reservation not found or you do not have permission to cancel.");
    }
}




