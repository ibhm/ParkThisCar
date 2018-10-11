package com.model;

public class QuickSort {

	public static void sort(Double[] p, ParkingSpot[] q, int lo, int hi){
		if (hi<=lo) return;
		int lt = lo, i=lo+1, gt = hi;
		Double v = p[lo];
		while(i<=gt){
			int cmp = p[i].compareTo(v);
			if(cmp<0) exch(p,q,lt++,i++);
			else if (cmp>0) exch(p,q,i,gt--);
			else i++;
		}
		sort(p,q,lo,lt-1);
		sort(p,q,gt+1,hi);
	}
	private static void exch(Double[] a,ParkingSpot[] q, int i, int j){
		Double temp1 = a[i];
		ParkingSpot temp2 = q[i];
		a[i] = a[j];
		q[i] = q[j];
		a[j] = temp1;
		q[j] = temp2;
	}
}
