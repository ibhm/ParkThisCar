import java.util.ArrayList;


public class MainClass {
	public static ArrayList<String> mainFunc(String lat, String lon, String day) throws Exception{
		//line 6 runs with a small dataset, try it with the full dataset though to see if there are any issues
		ParkingSpot[] p = FileIO.importList("Code/data/Street_parking_sign.txt");
		
		//line 9 there is an issue (looks to be with the bag class, have a look, let me know if there are any troubles...)
		EdgeWeightedDigraph g = FileIO.importGraph("Code/data/Street_Network_Database.txt");
		
		
		//everything else below appears to be fine, there will be more once we have the code from me and ibrahim...
		Coordinate c = new Coordinate(Double.parseDouble(lat),
				Double.parseDouble(lon));
		int start = g.findClosestIntersection(c);
		
		DijkstraSP d = new DijkstraSP(g,start);
		
		Coordinate[] cs = new Coordinate[10];
		Double[] ds = new Double[10];
		for(int i = 0; i<10;i++){
			int vertex = g.findClosestIntersection(p[i].loc());
			cs[i] = g.coord(vertex);
			ds[i] = d.getDist(vertex);
		}
		
		
		QuickSort.sort(ds,p,0,9);
		
		
		int today = Integer.parseInt(day);
		int i = 0;
		ArrayList<String> out = new ArrayList<>();
		while(i<5 && i < p.length){
			if(p[i].freeToday(today)){
				String a = "Parking spot available at: " + p[i].loc().toString()+
						" Available from: " + p[i].sttme() + " to " + p[i].endtme();
				out.add(a);
				System.out.println(a);
				i++;
				
			}
		}
		return out;
	}
	
	public static void main(String[] args) throws Exception{
		mainFunc("47.32424","-122.34242","1");
	}
}
