import java.util.ArrayList;

public class Taxi {
  public int id;
  public char location;
  public int earnings;
  public boolean available;
  public ArrayList<Booking> bookings = new ArrayList<Booking>();


  public Taxi(){
    
  }

  public Taxi (int n, ArrayList<Taxi> taxis){
    System.out.println("Create n Taxi Instances...");
    for(int i=1; i<=n; i++){
      taxis.add(new Taxi(i, 'A', 0, false));
    }
  }

  public Taxi(int id, char location, int earnings, boolean available) {
    this.id = id;
    this.location = location;
    this.earnings = earnings;
    this.available = available;
  }

  public void bookTaxi(int cid, char pick, char drop, int pickTime, int fare) {
  
  }
}
