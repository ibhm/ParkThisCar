package com.model;
import java.io.IOException;
import java.util.*;


public class Parker{
	private ParkingSpot[] p;
	private EdgeWeightedDigraph g;
	
	public Parker(){
		ParkingSpot[] p = FileIO.importList("PTC2/src/data/Street_parking_sign.txt");
		EdgeWeightedDigraph g = FileIO.importGraph("PTC2/data/data/Street_Network_Database.txt");
		
	}
	
	public static ArrayList<String> mainFunc2(String adr, String day) throws Exception{
		String lat = GoogleAPI.LatAPI(adr);
		String lng = GoogleAPI.LngAPI(adr);
		
		Coordinate cnew = new Coordinate(Double.parseDouble(lat), Double.parseDouble(lng));
		int start = g.findClosestIntersection(cnew);
		
		DijkstraSP d = new DijkstraSP(g,start);
		
		Double[] dist = new Double[p.length];
		for(int i = 0; i<p.length; i++)
			dist[i] = p[i].getDist(c);
		
		QuickSort.sort(dist,p, 0, p.length);
		
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
				/**String a3 = "Parking spot available at: " + p[i].loc().toString()+". Available from: " + p[i].sttme() + " to " + p[i].endtme() + "for (" + lat + ", " + lng + ")";
					//	" Available from: " + p[i].sttme() + " to " + p[i].endtme();
				//double a2 =  p[i].loc().getLat();
				//double b2 =  p[i].loc().getLongit();
				//String place = GOOGLEAPI.Location(a2, b2);**/
				String a3 ="Parking spot available at: " + p[i].loc().toString() + ".  Available from: " + p[i].sttme() + " to " + p[i].endtme() + ". ";
				out.add(a3);
				System.out.println(a3);
				i++;
			}
		}
	return out; 
	}
}
		
	 

