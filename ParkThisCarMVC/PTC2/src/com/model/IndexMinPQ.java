package com.model;

public class IndexMinPQ<Key extends Comparable<Key>> {
	private int N;
	private int[] pq;
	private int[] qp;
	private Key[] keys;

	public IndexMinPQ(int maxN) {
		keys = (Key[]) new Comparable[maxN + 1];
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		for (int i = 0; i <= maxN; i++) 
			qp[i] = -1; 
	}
	
	public void changeKey(int k, Key item){
		if(!contains(k))
			return;
		keys[k] = item;
	}

	
	public boolean isEmpty() 
	{ return N == 0; }
	public boolean contains(int k)
	{ return qp[k] != -1; }
	public void insert(int k, Key key) {
		N++;
		qp[k] = N;
		pq[N] = k;
		keys[k] = key;
		swim(N);
	}
	
	public Key min() 
	{ return keys[pq[1]]; }

	public int delMin() {
		int indexOfMin = pq[1];
		exch(1, N--);
		sink(1);
		keys[pq[N+1]] = null; qp[pq[N+1]] = -1; return indexOfMin;
	}
	private void exch(int i, int j){
		int t = pq[i]; pq[i] = pq[j]; pq[j] = t;
		qp[pq[i]]= i; qp[pq[j]] = j;
	}
	
	private void swim(int k)
	{
		while (k >1 && greater(k/2, k)){
			exch(k/2, k);
			k = k/2;
		}
	}
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j<N && greater(j,j+1)) j++;
			if(!greater(k,j)) break;
			exch(k,j);
			k = j;
		}
	}

	public int size() 
	{ return N; }


	private boolean greater(int i, int j)
	{ return keys[i].compareTo(keys[j]) > 0; }

}
