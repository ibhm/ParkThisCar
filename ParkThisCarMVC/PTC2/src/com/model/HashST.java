package com.model;

/**
 * Implements a Linear Probing Hash Table
 * @author ircur
 * @version 3
 * @param <Key> Keys to be hashed
 * @param <Value> Values to be stored
 */
public class HashST<Key, Value> {
	private int N;
	private int M;
	private Key[] keys;
	private Value[] vals;
	
	/**
	 * Constructor
	 * @param m Estimated number of elements to be added to the table
	 */
	public HashST(int m){
		M = m*2;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	private void resize(int capacity){
		assert capacity >= N;
		Key[] tempk = (Key[]) new Object[capacity];
		Value[] tempv = (Value[]) new Object[capacity];
		for (int i = 0; i < N; i++){
			tempk[i] = keys[i];
			tempv[i] = vals[i];
		}
		vals = tempv;
		keys = tempk;
		
	}
	
	/**
	 * Adds a key, value pair to the table
	 * @param key Key to be hashed
	 * @param val value to be stored
	 */
	public void put(Key key, Value val){
		if(N >= M/2) resize(2*M);
		
		int i;
		for(i = hash(key); keys[i]!= null; i = (i+1)%M)
			if (keys[i].equals(key)) {vals[i] = val; return;}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	/**
	 * Accessor for the table
	 * @param key Key corresponding to the requested value
	 * @return Value needed from the given key
	 */
	public Value get(Key key){
		for (int i = hash(key); keys[i] != null; i = (i+1)%M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}
	/**
	 * Does the table contain the key
	 * @param key Key to be compared
	 * @return boolean true or false
	 */
	public boolean contains(Key key){
		for(Key testKey : keys)
			if(testKey!= null && testKey.equals(key)) return true;
		return false;
	}
	/**
	 * Deletes a Key-value pair from the table
	 * @param key key which relates to the value to be deleted
	 */
	public void delete(Key key){
		if (!contains(key)) return;
		int i = hash(key);
		while(!key.equals(keys[i]))
			i = (i+1)%M;
		keys[i] = null;
		vals[i] = null;
		i = (i+1) %M;
		while (keys[i] != null){
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo,valToRedo);
			i = (i+1)%M;
		}
		N--;
		if (N>0 || N== M/8) resize(M/2);
	}
}