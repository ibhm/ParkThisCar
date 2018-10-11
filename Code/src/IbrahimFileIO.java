import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;


public class IbrahimFileIO {
	//remember to change this to be private again
	String[][] file = FileIO.importCSV("Street_Network_Database.txt");
	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter
			("Street_Network_Database_new.txt")));
	
	pw.print(file[0][0]+"\t"+file[0][1]+"\t"+file[0][6]+"\t"+"FROM\tTO\n");
	
	
	for(int i = 1; i<file.length; i++){
		String[] line = file[i];
		int streetType = Integer.parseInt(line[6]);
		if(streetType < 9){
			String stName = line[24];  //street name
			
			//all of the below is literally JUST to get the address range
			Double fromAddress1 = Double.parseDouble(line[16]); 
			Double toAddress1 = Double.parseDouble(line[17]);
			Double fromAddress2 = Double.parseDouble(line[18]);
			Double toAddress2 = Double.parseDouble(line[19]);
			
			Double minAddressFrom = Math.min(fromAddress1, fromAddress2);
			Double maxAddressFrom = Math.max(fromAddress1, fromAddress2);
			
			Double addressTo = Math.max(toAddress1, toAddress2);
			Double addressFrom = minAddressFrom == 0 ? maxAddressFrom : minAddressFrom;
			
			String addTo = addressTo.toString()+" "+stName;
			String addFrom = addressFrom.toString()+" "+stName;
			
			//*** IBRAHIM's STUFF NEEDS TO CHANGE THE BELOW TWO LINES ***
			//*** Addresses in the following form:
			// "Number StreetName" 
			Coordinate from = GOOGLEAPISTUFF(addTo);
			Coordinate to = GOOGLEAPISTUFF(addFrom);
			
			pw.print(line[0]+"\t"+line[1]+"\t"+line[6]+"\t"+from+"\t"+to+"\n");
			
		}
		pw.close();
	}
}
