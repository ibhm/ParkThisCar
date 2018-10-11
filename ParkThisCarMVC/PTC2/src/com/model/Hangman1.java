package com.model;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Hangman1 {

	public static void main(String[] args) throws IOException {
        File myFile = new File("Street_Network_Database.txt");
        System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());

        Scanner input = new Scanner(myFile);
        String in = "";
        in = input.nextLine();
    }

}