public class Booking {
  public int bid;
  public int cid;
  public char pick;
  public char drop;
  public int pickTime;
  public int dropTime;
  public int fare;

  public Booking(int bid, int cid, char pick, char drop, int pickTime, int dropTime, int fare) {
    this.bid = bid;
    this.cid = cid;
    this.pick = pick;
    this.drop = drop;
    this.pickTime = pickTime;
    this.dropTime = dropTime;
    this.fare = fare;
  }
  
  
}
