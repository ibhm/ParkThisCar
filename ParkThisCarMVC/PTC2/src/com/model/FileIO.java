package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class FileIO {
    
    
	public static String[][] importCSV(String fileName) throws IOException{
        LinkedList<String[]> linkedList = new LinkedList<>();
        String[][] result;
            
		File Annual_Parking_Study_Data = new File(fileName);		
		
		BufferedReader br;
		FileReader fr;
        String regex = "\t";
		
		fr = new FileReader(Annual_Parking_Study_Data);
		br = new BufferedReader(fr);

		String Parking_Lot_Info_Line;
                        
        br.readLine(); //deals with the title row
		while (( Parking_Lot_Info_Line = br.readLine()) != null) {
			String[] line = Parking_Lot_Info_Line.split(regex);
            linkedList.add(line);  
        }			
        result = linkedList.toArray(new String[linkedList.size()][]);
		
        return result;		
	}
      
	//temporarily commented out while we figure out the stuff for working copy
    public static ParkingSpot[] importList(String fileName) throws IOException{
        String[][] file = importCSV(fileName);
        ParkingSpot[] spots = new ParkingSpot[file.length];
        int i = 0;
        for(String[] line : file){
        	if(line.length > 21){
        		
        	//String txt = line[10];  //sign text
        	String cat = line[10];  //sign category ( PTIML is a good temp example)
        	char dir = line[11].charAt(0);   //sign facing direction
        	//String txt = line[18];  //custom txt on the sign
        	int stday = (line[17].equals("") ? 1 : Integer.parseInt(line[17]));  //startday
        	int endday = (line[18].equals("") ? 7 : Integer.parseInt(line[18]));  //endday
        	int sttme = (line[19].equals("") ? 0 : Integer.parseInt(line[19]));  //starttime
        	int endtme = line[20].equals("") ? 2359 : Integer.parseInt(line[20]);  //endtime
        	
        	String[] latLong = line[21].split(",");
        	Double lat = Double.parseDouble(latLong[0].substring(2,latLong[0].length()));  //latlong coordinates
        	Double longi = Double.parseDouble(latLong[1].substring(0, latLong[1].length()-2));
        	Coordinate coord = new Coordinate(lat,longi); 
        	ParkingSpot spot = new ParkingSpot(cat,
        			dir,stday,endday,sttme,endtme,coord);
        	spots[i] = spot;
        	i++;
        	}
        }
        return spots;
        
    }
        
	
	public static EdgeWeightedDigraph importGraph(String fileName) throws IOException{
		String[][] file = importCSV(fileName); //reads csv file as a 2d array
		HashST<Coordinate, Integer> bg = new HashST<>(file.length*2);
		Coordinate[] coordBg = new Coordinate[file.length*2];
		LinkedList<DirectedEdge> lst = new LinkedList<>();
		int numNodes = 0;
		Stack<DirectedEdge> edges = new Stack<DirectedEdge>();
		String[] line;
		int from;
		int to;
		Double speed;
		String[] fromCoord;
		String[] toCoord;
		Coordinate fromCo;
		Coordinate toCo;
		Double stWeight;
		int toNode, fromNode;
		DirectedEdge edge;
		for(int i = 1; i<file.length; i++){
			line = file[i];
			from = Integer.parseInt(line[0]);
			to = Integer.parseInt(line[1]);
			speed = Integer.parseInt(line[2]) == 4 ? 5280. : 3080.;
			fromCoord = line[3].substring(1,line[3].length()-1).split(",");
			toCoord = line[4].substring(1,line[4].length()-1).split(",");
			fromCo = new Coordinate(Double.parseDouble(fromCoord[0]),
					Double.parseDouble(fromCoord[1]));
			toCo = new Coordinate(Double.parseDouble(toCoord[0]),
					Double.parseDouble(toCoord[1]));
			stWeight = fromCo.dist(toCo)/speed;
			if(!bg.contains(fromCo)){
				bg.put(fromCo,numNodes);
				coordBg[numNodes] = fromCo;
				fromNode = numNodes++;
			}
			else fromNode = bg.get(fromCo);
			if(!bg.contains(toCo)){
				bg.put(toCo,  numNodes);
				coordBg[numNodes] = toCo;
				toNode = numNodes++;
			}
			else toNode = bg.get(toCo);
						
			edge = new DirectedEdge(toNode, fromNode,stWeight);
			edges.push(edge);
			System.out.println(numNodes);
		}
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numNodes);
		DirectedEdge e;
		while(!edges.isEmpty()){
			e = edges.pop();
			graph.addEdge(e,coordBg[e.from()],coordBg[e.to()]);
		}
		return graph;
	}
}