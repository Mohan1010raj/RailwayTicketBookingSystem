import java.util.*;

public class TicketSystem {
    List<String> availableBerth=new ArrayList<>(Arrays.asList("L","M","U"));
    Queue<Passenger> racQueue=new LinkedList<>();
    Queue<Passenger> waitingListQueue=new LinkedList<>();
    List<Passenger> confirmedPassengers=new ArrayList<>();
    private int ticketCounter;

    public TicketSystem(){
        this.ticketCounter=1;
    }

    public void bookTickets(String name,int age,String gender,String berthPreference){
        String ticketId="T"+ticketCounter++;
        Passenger passenger;

        if(!availableBerth.isEmpty()){
            String allocatedBerth=allocatedBerth(age,gender,berthPreference);
            passenger=new Passenger(name, age, gender, berthPreference, allocatedBerth, ticketId);
            confirmedPassengers.add(passenger);
            availableBerth.remove(allocatedBerth);
            System.out.println("Ticket confirmed "+passenger);
        }
        else if(racQueue.size()<1){
            passenger=new Passenger(name, age, gender, berthPreference, "RAC", ticketId);
            racQueue.add(passenger);
            System.out.println("Ticket in RAC: "+passenger);
        }
        else if(waitingListQueue.size()<1){
            passenger=new Passenger(name, age, gender, berthPreference, "Waiting", ticketId);
            waitingListQueue.add(passenger);
            System.out.println("Ticket in RAC: "+passenger);
        }
        else{
            System.out.println("No tickets available.");
        }

    }
    public String allocatedBerth(int age,String gender,String berthPreference){
        if(age>60 || gender.equals("female") && availableBerth.contains("L")){
            return "L";
        }
        if(availableBerth.contains(berthPreference)){
            return berthPreference;
        }
        return availableBerth.get(0);
    }
    public void cancelTicket(String ticketId){
        Optional<Passenger> passengerOpt=confirmedPassengers.stream().filter(p->p.ticketId.equals(ticketId)).findFirst();

        if(passengerOpt.isPresent()){
            Passenger passenger=passengerOpt.get();
            confirmedPassengers.remove(passenger);
            availableBerth.add(passenger.allocatedBerth);

            if(!racQueue.isEmpty()){
                Passenger racPassenger=racQueue.poll();
                String allocatedBerth=allocatedBerth(racPassenger.age,racPassenger.gender,racPassenger.berthPreference);
                racPassenger.allocatedBerth=allocatedBerth;
                confirmedPassengers.add(racPassenger);
                availableBerth.remove(allocatedBerth);
                System.out.println("RAC ticket moved to WaitingList "+racPassenger);
            }
            if(!waitingListQueue.isEmpty()){
                Passenger waitingPassenger=waitingListQueue.poll();
                waitingPassenger.berthPreference="RAC";
                racQueue.add(waitingPassenger);
                
                System.out.println("Waiting List ticket moved to RAC "+waitingPassenger);
            }
            System.out.println("Ticket cancelled successfully of ID "+ticketId);
        }
        else{
            System.out.println("Ticket ID "+ticketId+" not found");
        }
    }
    public void printBookedTickets(){
        if(confirmedPassengers.isEmpty()){
            System.out.println("No confirmed Tickets.");
        }
        else{
            System.out.println("Confirmed Passengers ");
            for(Passenger passenger:confirmedPassengers){
                System.out.println(passenger);
            }
        }
    }
    public void printAvailableTickets(){
        System.out.println("Available Tickets: "+availableBerth.size());
        System.out.println("Available RAC Tickets: "+ (1-racQueue.size()));
        System.out.println("Available Waiting Tickets: "+(1-waitingListQueue.size()));
    }

    public void viewRacTickets(){
        if(racQueue.isEmpty()){
            System.out.println("No RAC Tickets.");
        }
        else{
            System.out.println("RAC Tickets ");
            for(Passenger passenger:racQueue){
                System.out.println(passenger);
            }
        }
    }
    public void viewWaitingListTickets(){
        if(waitingListQueue.isEmpty()){
            System.out.println("No Waiting Tickets.");
        }
        else{
            System.out.println("Waiting List Tickets ");
            for(Passenger passenger:waitingListQueue){
                System.out.println(passenger);
            }
        }
    }
}
