/**
 * 
 */


/**
 * @author ibrahimmalik
 * @version 1.0
 */
public class ParkingSpot {
	//Parking signs module//

	/*Goals
	-Information on sign
	-Return latitude and longitude of sign
	-Category of sign
	-Distance between sign and coordinate


	Information on sign
	Each sign is one of these.
	There are the following signs in column "L" that correspond to these categories
	P1H - 1 HOUR PARKING OR MORE
	PBLO - BUS LAYOVER 
	PBZ - BUS ZONE 
	PCARPL -  CARPOOL SIGNED PARKING
	PCVL - COMMERCIAL VEHICAL LOAD ZONES
	PDIS - DISABLED PARKING
	PGA - EXXEMPT VEHICLES ONLY : POLICE, FIRE, HEALTH
	PINST - GEOGRAPHICAL DIRECTION OF PARKING AREA
	PLU - 15/30 MINUTE LOAD ZONES, MAY BE PAID
	PNP - NO PARKING BUT STANDING ALLOWD
	PNS - NO STOPPING STANDING OR PARKING
	PPEAK - TOW ZONE, PEAK PARKING RESTRICTIONS
	PPL - 3 MINUTE PARKING LOAD ZONE
	PPP - SHORT TERM PAID PARKING 15 - 4 HOURS
	PR - PARKING REGULATORY
	PRZ - RESTRICTED PARKING
	PS - PAY STATION
	PSCH - SCHOOL OR BUS LOAD
	PTIML - 15/30 MIN SHORT TERM PARKING
	PTRKL - 30 MINUTE TRUCK LOAD ZONE
	PZONE - INCLUDES CHARTER BUS, EVENT, CARSHARE

	Other information can be returned via the "K" column including informational text
	Column "W" also gives the same information

	Latitude and Longitude:
	Section "AB" of file
	(Latitude, Longitude)

	Hours:
	Calculation of hours can be as follows
	Return "Z" column for Start hour of parking
	Return "AA" column for End hour of parking

	Days:
	Calculation of days where sign rules are enforced are as follows
	Return "X" - Start Day
	Return "Z" - End Day
	*/

		//Constructor for Sign, which will treat each row of Data as one object)//
		//Sign - object. [ x ] - Column number of relevant info//

	//private String txt;
	private String cat;
	private String custxt;
	private char dir;
	private int stday;
	private int endday;
	private int sttme;
	private int endtme;
	private Coordinate coord;
	private Double distToCoord;
	
	
	public ParkingSpot(/*String txt, */String cat, char dir,
			int stday,int endday, int sttme, int endtme, Coordinate coord){
		//this.txt = txt;
		this.cat = cat;
		this.dir = dir;
		this.stday = stday;
		this.endday = endday;
		this.sttme = sttme;
		this.endtme = endtme;
		this.coord = coord;	
	}
	public int sttme(){
		return sttme;
	}
	public int endtme(){
		return endtme;
	}
	
	public Double getDist(Coordinate coord){
		this.distToCoord = coord.dist(this.coord);
		return this.distToCoord;
	}
	public Double getDistToCoord(){
		return this.distToCoord;
	}
	
	//assumes getDist has already been called
	public int compareTo(ParkingSpot p){
		return this.distToCoord.compareTo(p.getDistToCoord());
	}
	
	public String toString(){
		return "(" + coord.getLat().toString()+","+coord.getLongit().toString()+")";
	}
	public boolean freeToday(int today){
		return stday <= today && endday >= today;
	}
	public Coordinate loc(){
		return coord;
	}
	
	/*
	
	
	public void SignInfo(){
		System.out.println("Info on the board = " + Sign[10]);
	}
	public void LatAndLong(){
		System.out.println("Lat and Long = (" + Sign[27] + ") ");
	}

	public void StartDay() {
		if (Sign[23] == "1"){
			System.out.println("Sun");
		}
		if (Sign[23] == "2"){
				System.out.println("Mon");
		}
		if (Sign[23] == "3"){
			System.out.println("Tue");			}
		if (Sign[23] == "4"){
			System.out.println("Wed");
		}
		if (Sign[23] == "5"){
			System.out.println("Thu");
		}
		if (Sign[23] == "6"){
			System.out.println("Fri");
		}
		if (Sign[23] == "7"){
			System.out.println("Sat");
		}
	}
	
	public void EndDay(){
		if (Sign[24] == "1"){
			System.out.println("Sun");
		}
		if (Sign[24] == "2"){
			System.out.println("Mon");
		}
		if (Sign[24] == "3"){
			System.out.println("Tue");
		}
		if (Sign[24] == "4"){
			System.out.println("Wed");
		}
		if (Sign[24] == "5"){
			System.out.println("Thu");
		}
		if (Sign[24] == "6"){
			System.out.println("Fri");
		}
		if (Sign[24] == "7"){
			System.out.println("Sat");
		}
	}
		
	public void StartHours(){
		System.out.println("Start Time of Regulations" + Sign[25]);
	}
		
	public void EndHours(){
		System.out.println("End Time of Regulations" + Sign[26]);
	}
		
	public void Category(){
		if (Sign[11] == "P1H"){
			System.out.println("1 HOUR PARKING OR MORE");
		}
			if (Sign[11] == "PBLO"){
				System.out.println("BUS LAYOVER");
			}
			if (Sign[11] == "PBZ"){
				System.out.println("BUS ZONE");
			}
			if (Sign[11] == "PCARPL"){
				System.out.println("CARPOOL SIGNED PARKING");
			}
			if (Sign[11] == "PDIS"){
				System.out.println("DISABLED PARKING");
			}
			if (Sign[11] == "PGA"){
				System.out.println("EXEMPT VEHICLES ONLY : POLICE, FIRE, HEALTH");
			}
			if (Sign[11] == "PINST"){
				System.out.println("GEOGRAPHICAL DIRECTION OF PARKING AREA");
			}
			if (Sign[11] == "PLU"){
				System.out.println("15/30 MIN LOAD ZONES, MAY BE PAID");
			}
			if (Sign[11] == "PNP"){
				System.out.println("NO PARKING BUT STANDING ALLOWD");
			}
			if (Sign[11] == "PNS"){
				System.out.println("NO STOPPING STANDING OR PARKING");
			}
			if (Sign[11] == "PPEAK"){
				System.out.println("TOW ZONE, PEAK PARKING RESTRICTIONS");
			}
			if (Sign[11] == "PPL"){
				System.out.println("3 MIN PARKING LOAD ZONE");
			}
			if (Sign[11] == "PPP"){
				System.out.println("15 MIN/4 HOUR SHORT TERM PAID PARKING");
			}
			if (Sign[11] == "PR"){
				System.out.println("PARKING REGULATORY");
			}
			if (Sign[11] == "PRZ"){
				System.out.println("RESTRICTED PARKING");
			}
			if (Sign[11] == "PS"){
				System.out.println("PAY STATION");
			}
			if (Sign[11] == "PSCH"){
				System.out.println("SCHOOL OR BUS LOAD");
			}
			if (Sign[11] == "PTIML"){
				System.out.println("15/30 MIN SHORT TERM PARKING");
			}
			if (Sign[11] == "PTRKL"){
				System.out.println("30 MIN TRUCK LOAD ZONE");
			}
			if (Sign[11] == "PZONE"){
				System.out.println("INCLUDES CHARTER BUS, EVENT, CARSHARE");
			}
		}
	
	}*/
}
