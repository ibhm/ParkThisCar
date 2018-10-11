package com.model;


public class Edge{
	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int getFrom(){
		return this.v;
	}
	public int getTo() {
		return this.w;
	}
	public double getWeight(){
		return this.weight;
	}
}