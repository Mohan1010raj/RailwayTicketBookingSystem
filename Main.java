import java.util.*;

public class Main {
    public static void main(String ar[]){
        TicketSystem system=new TicketSystem();
        Scanner in=new Scanner(System.in);

        while(true){
            System.out.println("------------------------------------------------------");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Confirmed Tickets");
            System.out.println("4. Available Tickets");
            System.out.println("5. RAC Tickets");
            System.out.println("6. Waiting Tickets");
            System.out.println("7. Exit");
            System.out.println("------------------------------------------------------");

            System.out.print("Enter your choice: ");
            int choice=in.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name=in.next();
                    System.out.print("Enter age: ");
                    int age=in.nextInt();
                    System.out.print("Enter gender: ");
                    String gender=in.next();
                    System.out.print("Enter Berth Preference: ");
                    String berthPreference=in.next();
                    system.bookTickets(name, age, gender, berthPreference);
                    break;
            
                case 2:
                    System.out.print("Enter Ticket ID: ");
                    String ticketId=in.next();
                    system.cancelTicket(ticketId);
                    break;
                
                case 3:
                    system.printBookedTickets();
                    break;
                
                case 4:
                    system.printAvailableTickets();
                    break;
                
                case 5:
                    system.viewRacTickets();
                    break;

                case 6:
                    system.viewWaitingListTickets();
                    break;

                case 7:
                    System.out.println("Exit...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");  
            }
        }
    }
}
