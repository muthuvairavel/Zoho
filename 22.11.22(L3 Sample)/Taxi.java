import java.util.List;
import java.util.ArrayList;
class Taxi{
	private int id;
	private char currentSpot;
	private int totalEarnings;
	private int freeTime;
	private static int count =1;
	private List<String> trips;
	
	public int getId(){
		return id;
	}
	
	public char getCurrentSpot(){
		return currentSpot;
	}
	
	public int getTotalEarnings(){
		return totalEarnings;
	}
	
	public int getFreeTime(){
		return freeTime;
	}	
	
	public List<String> getTrips(){
		return trips;
	}
	
	public Taxi(){
		this.currentSpot = 'A';
		this.freeTime = 6;
		this.totalEarnings = 0;
		this.id = count++;
		trips = new ArrayList<String>();
	}
	
	
	public void setDetails(char currentSpot,int freeTime,int totalEarnings,String tripDetail){
		this.currentSpot = currentSpot;	
		this.freeTime = freeTime;
		this.totalEarnings = totalEarnings;
		this.trips.add(tripDetail);
	}
}
