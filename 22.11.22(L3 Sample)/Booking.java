import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
class Booking{
	public static void main(String args[]){
		List<Taxi> taxis = createTaxis(4);
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag){
			System.out.println("---------------------------------------");
			System.out.println("*****MENU*****\n1.Book Taxi\n2.Show Details\n3.Exit");
			int choice = sc.nextInt();
			int customerId =1;
			switch(choice){
				case 1: System.out.println("Enter Starting point:");
					char startPoint = sc.next().charAt(0);
					System.out.println("Enter Destination point:");
					char endPoint = sc.next().charAt(0);
					System.out.println("Enter the pickUp Time:");
					int pickUpTime = sc.nextInt();
					List<Taxi> freeTaxis = getFreeTaxis(taxis,pickUpTime,startPoint);
					
					if(freeTaxis.size() == 0)
					{
						System.out.println("No taxis are free ryt now.....!!!!");	
						break;
					}
					bookTaxi(customerId++,startPoint,endPoint,pickUpTime,freeTaxis);
					break;
				case 2: getAllTaxiDetails(taxis);
				case 3: flag=false;
			}
		}
	}
	public static List<Taxi> createTaxis(int n){
		List<Taxi> list = new ArrayList<Taxi>();
		for(int i=0;i<n;i++){
			Taxi t = new Taxi();
			list.add(t);
		}
		return list;
	}
		
	public static List<Taxi> getFreeTaxis(List<Taxi> taxis,int pickUpTime,char startPoint){
		List<Taxi> freeTaxis = new ArrayList<Taxi>();
		for(Taxi t: taxis){
			if((pickUpTime >= t.getFreeTime()) && (pickUpTime - t.getFreeTime() >= Math.abs(t.getCurrentSpot() - startPoint) ))
				freeTaxis.add(t);
		}
		return freeTaxis;
	}
		
	public static void bookTaxi(int customerId,char startPoint,char endPoint,int pickUpTime,List<Taxi> freeTaxis){
		String tripDetail = "";
		int min = 1000;
		int nextFreeTime =0;
		int earning =0;
		int distanceBetweenPickUpAndDrop =0;
		Taxi bookedTaxi = null;
		char nextSpot = '0';
		int distanceBetweenCustomerAndTaxi =0;
		
		for(Taxi t:freeTaxis){
			distanceBetweenCustomerAndTaxi = Math.abs((t.getCurrentSpot() - '0') - (startPoint - '0')) * 15;
			if(distanceBetweenCustomerAndTaxi < min){
				bookedTaxi =t;
				distanceBetweenPickUpAndDrop = Math.abs((startPoint - '0') - (endPoint - '0')) * 15;
				earning = (distanceBetweenPickUpAndDrop-5) * 10 + 100;
				int dropTime = pickUpTime + distanceBetweenPickUpAndDrop/15;
				nextFreeTime = dropTime;
				nextSpot =  endPoint;
				tripDetail = customerId+"\t               "+startPoint+"\t"+endPoint+"\t"+pickUpTime+"\t"+dropTime+"\t"+earning;
				min = distanceBetweenCustomerAndTaxi;
			} 
		}
		bookedTaxi.setDetails(nextSpot,nextFreeTime,bookedTaxi.getTotalEarnings() + earning,tripDetail);
		System.out.println("Taxi "+bookedTaxi.getId()+" booked");
	}
	
	public static void getAllTaxiDetails(List<Taxi> taxis){
		System.out.println("Customer ID    From    To    PickUpTime    DropTime    Earning");
		for(Taxi t:taxis){
			System.out.println("Taxi :"+t.getId()+"      Total Earnings :"+t.getTotalEarnings());
			List<String> trips = t.getTrips();
			for(String s : trips){
				System.out.println(s);
			}
			System.out.println("---------------------------------------------------");
			
		}
	}
}