/**
 * @author ibrahimmalik
 * @version ParkThisCar2Assignment
 */

package com.model;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
 

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
 
public class GoogleAPI{
	
	public static String Location(double a, double b) throws ApiException, InterruptedException, IOException{
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBBqdKvyXB7h-pfQl0OolfxNThB3KZO_6g"
).build();
	    LatLng latlng = new LatLng(a, b);
	    GeocodingResult[] results = GeocodingApi.newRequest(context).latlng(latlng).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String Location = gson.toJson(results[0].formattedAddress);
		return Location;
	}
	
	public static String LatAPI(String address) throws ApiException, InterruptedException, IOException{
		String address2 = (address + "Seattle WA USA");
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyAPTRWi9d3eUjVp4mox8EUVoDxv44BJuLo"
).build();
		GeocodingResult[] results =  GeocodingApi.geocode(context,
		   address2).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String lat= gson.toJson(results[0].geometry.location.lat);
		return lat;
	}
	
	public static String LngAPI(String address) throws ApiException, InterruptedException, IOException{
		String address2 = (address + "Seattle WA USA");
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCowTY4tPr7nXcJOaEWg1RJoYfATWqeswo"
).build();
		GeocodingResult[] results =  GeocodingApi.geocode(context,
		   address2).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String lng= gson.toJson(results[0].geometry.location.lng);
		return lng;
	}
	
	
	public static String CoordConvert(String address, String postcode) throws ApiException, InterruptedException, IOException{
		String address2 = (address + "Seattle WA USA");
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDyWlXPtOMcT2lsMylwTKGu0lhevkE_Io4"
).build();
		GeocodingResult[] results =  GeocodingApi.geocode(context,
		   address2).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String lataddFrom = gson.toJson(results[0].geometry.location.lat);
		String lngaddFrom = gson.toJson(results[0].geometry.location.lng);
		String Coord = (lataddFrom+", "+lngaddFrom);
		return Coord;
	}
	
}