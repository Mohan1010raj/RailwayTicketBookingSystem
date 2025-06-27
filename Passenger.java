public class Passenger {
    String name;
    int age;
    String gender;
    String berthPreference;
    String allocatedBerth;
    String ticketId;

    public Passenger(String name,int age,String gender,String berthPreference,String allocatedBerth,String ticketId){
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.berthPreference=berthPreference;
        this.allocatedBerth=allocatedBerth;
        this.ticketId=ticketId;
    }

    public String toString(){
        return "TicketID:"+ticketId+" Name:"+name+" Age:"+age+" Gender:"+gender+" Berth:"+allocatedBerth;
    }
}
