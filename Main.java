import java.util.Scanner;
import java.util.ArrayList;

public class Main{
  public static ArrayList<Taxi> taxis = new ArrayList<Taxi>();
  public static int bid = 1;
  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args){
    System.out.println("Enter No. of Taxi's :");
    int n = sc.nextInt();
    new Taxi(n, taxis);
    System.out.println("For booking Taxi. press 1");
    int b = sc.nextInt();
    if(b==1){
      bookTaxi();
    }
    printTaxiDetails();
  }

  public static void bookTaxi(){
    try{
      System.out.print("Customer ID:");
      int cid = sc.nextInt();
      System.out.print("Pickup Point:");
      char pick = sc.next().toUpperCase().charAt(0);
      System.out.print("Drop Point:");
      char drop = sc.next().toUpperCase().charAt(0);
      if(pick>'F' || drop>'F'){
        System.out.println("Invalid Point. Try again?");
        tryAgain();
      }
      System.out.print("Pickup Time:");
      int pickTime = sc.nextInt();
      int dropTime = pickTime+Math.abs(drop-pick);
      int dist = Math.abs(drop-pick)*15;
      int fare = (dist-5)*10+100;
      System.out.println("Fare: "+ fare);
      

      Taxi t = findTaxi(pick, pickTime, dropTime);
      if(t!=null){
        System.out.println("Taxi can be allotted");
        System.out.printf("Taxi-%d id alloted\n", t.id);
        t.bookings.add(new Booking(bid, cid, pick, drop, pickTime, dropTime, fare));
        bid++;
        t.location = drop;
        t.earnings += fare;
      }
      else{
        System.out.println("No Taxi is available at the time. Pick another slot.");
        System.out.println("Try Again?");
        tryAgain();
      }
      for(Booking b : t.bookings){
        System.out.println(b.fare+" "+b.dropTime);
      }
      System.out.println("Book another Ride?");
      tryAgain();
    }catch(Exception e){
      System.out.println("Error has occured. Try Again?");
      tryAgain();
    }
  }

  public static void tryAgain(){
    System.out.println("Press 1");
    int b = sc.nextInt();
    if(b==1){
      bookTaxi();
    }
  }

  public static Taxi findTaxi(char pick, int pickTime, int dropTime){
    ArrayList<Taxi> taxiId = new ArrayList<Taxi>();
    int min = 7;
    for(int i=0; i<taxis.size(); i++){
      Taxi t = taxis.get(i);
      if(Math.abs(t.location-pick)<=min){
        boolean available = true;
        for(Booking b : t.bookings){
          if(b.pickTime<=pickTime && b.dropTime>=pickTime){
            available = false;
          }
        }
        if(available){
          min = Math.abs(t.location-pick);
          taxiId.add(t);
        }
      }
    }
    int fare = 0;
    Taxi tid = new Taxi();
    if(taxiId.size()==0){
      return null;
    }
    if(taxiId.size()==1){
      return taxiId.get(0);
    }
    else{
      for(int i=0; i<taxiId.size(); i++){
        Taxi t = taxiId.get(i);
        if(i==0){
          fare = t.earnings;
          tid = t;
          continue;
        }
        if(t.earnings < fare){
          fare = t.earnings;
          tid = t;
        }
      }
    }
    return tid;
  }

  public static void printTaxiDetails(){
    for(Taxi t : taxis){
      System.out.printf("Taxi-%d\t Total Earnings: Rs. %d\n", t.id, t.earnings);
      if(t.bookings.size()!=0){
        for(Booking b : t.bookings){
          System.out.printf("%d\t%d\t%c\t%c\t%d\t%d\t%d\n", b.bid, b.cid, b.pick, b.drop, b.pickTime, b.dropTime, b.fare);
        }
      }
      else{
        System.out.println("No Bookings done.");
      }
    }
  }
}