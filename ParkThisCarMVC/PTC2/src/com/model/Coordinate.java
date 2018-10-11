package com.model;

/**
* @author Hareem Arif
*  @version 1
* 
*/




/**
 * Class for Coordinate 
 * @param lat - represents the numerical latitude value 
 * @param longit - represents the numerical latitude value 
 */ 
public class Coordinate {

private Double lat;
private Double longit; //cannot call this long as that is a Java term
private Double tol = 0.0001;


/**
 * Constructor for Coordinates Class 
 * @param lat - represents the numerical latitude value 
 * @param longit - represents the numerical latitude value 
 */ 

public Coordinate(double lat, double longit) {
	
	this.lat = lat;
	this.longit = longit;

}

/**
 * Accessor for latitude value for Coordinate
 * @param lat - represents the numerical latitude value 
 * @return the latitude value for Coordinate
 */ 
public Double getLat() {
	return this.lat;
}
public boolean equals(Coordinate that){
	if (Math.abs(this.lat - that.getLat()) < tol && 
			Math.abs(this.longit - that.getLongit()) < tol)
		return true;
	return false;
}
/**
 * Accessor for longitude value for Coordinate
 * @param lat - represents the numerical longitude value 
 * @return the longitude value for Coordinate
 */	
public Double getLongit() {
	return this.longit;
}


/**
 * Method dist cacluated pythagorean distance between 2 coordinates  
 * @param 
 * @return the longitude value for Coordinate
 */		
public double dist(Coordinate P1) {
	
	double dist;
	double x1;
	double x2;
	double y1;
	double y2;
	
	x1 = P1.getLat();
	x2 = this.lat;
	y1 = P1.getLongit();
	y2 = this.longit;

	
	dist = Math.hypot(x1-x2, y1-y2);
	
	return dist;
}
public String toString(){
	return "("+lat+","+longit+")";
}


//Currently using the main function to test the various methods of this class
//Testing getLat(), getLongit() and dist() with instances of the class Coordinate, P1, P2
/*
public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	Coordinate P1 = new Coordinate(3,4);
	Coordinate P2 = new Coordinate(5,6);
	int i = 0; //this is the manually calculated value for dist() using P1 and P2 as parameters
	//temporarily set to 0 but will change and put actual expected value 
	
	if (P1.getLat() == 3) {
		System.out.print("Test getLat() passed\n");
	}
	else
		System.out.print("Test getLat() failed\n");			
	
	if (P1.getLongit() == 4) {
		System.out.print("Test getLongit() passed\n");
	}
	
	else
		System.out.print("Test getLongit() failed\n");
	
	double distchecker;
	
	distchecker = dist(P1, P2);
	
	if (distchecker == i) {
		System.out.print("Test dist() passed\n");
	}
	
	else
		System.out.print("Test dist() failed\n");
		
	}
}*/
}		