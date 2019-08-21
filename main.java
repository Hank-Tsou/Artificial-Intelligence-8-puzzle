import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Start 8-puzzle Game");
		System.out.println("1. Enter a specific 8-puzzle configuration");
		System.out.println("2. Randomly Generated 8-puzzle problem");
		System.out.println("3. Read File (input file name)");
		System.out.println();
		
		int choice = sc.nextInt();
		sc.nextLine();
		
		switch(choice)
		{
			case 1: 
				System.out.println("Please enter 8-puzzle configuration: ");
				String userInput = sc.nextLine();		
				sc.close();
				
				AStar(userInput);
				break;
				
			case 2:
				String rndState = randomGenerator();
				System.out.println("Random State: " + rndState);
				
				AStar(rndState);
				break;
				
			case 3: 
				
				System.out.println("Please enter file name (with .txt): ");
				String filename = sc.nextLine();		
				sc.close();

				Readfile(filename);
				break;
		}
	}
	
	public static void AStar(String line) {
		char[] temp = null;

		temp = line.replaceAll(" ", "").toCharArray();
		int zero = new String(temp).indexOf("0");

		if(isSovable(line)) {
			long startTime;
			long endTime;

			Board initial = new Board(temp, zero);
			
			startTime = System.currentTimeMillis();
			Solver Heuristic_Function_1 = new Solver(initial);
			endTime   = System.currentTimeMillis();
			System.out.println("Run Time: " + (endTime - startTime) +" ms");
			
			startTime = System.currentTimeMillis();
			Solver2 Heuristic_Function_2 = new Solver2(initial);
			endTime   = System.currentTimeMillis();
			System.out.println("Run Time: " + (endTime - startTime) +" ms");
		}

		else {
			System.out.println("Unsolvable puzzle");
		}
	}
	
	public static String Readfile(String filename) {
		String line = null;
		
		try {
			FileReader fileReader = 
			new FileReader(filename);
			
			BufferedReader bufferedReader = 
			new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				AStar(line);
			}
			
			bufferedReader.close();         
		}
		
		catch(FileNotFoundException ex) {
			System.out.println(
			"Unable to open file '" + 
			filename + "'");                
		}
		
		catch(IOException ex) {
			System.out.println(
			"Error reading file '" 
			+ filename + "'");                  
		}
		return filename;
	}
	
	public static boolean isSovable(String input) {
		
		int check = 0;
		char[] temp2 = null;
		temp2 = input.replaceAll(" |0", "").toCharArray();
		
		for(int i=0; i<7; i++) {
			for(int j = i; j<8; j++) {
				if(temp2[i] > temp2[j]) {
    				check++;
    				}
			}
		}
		
		if(check % 2 == 0) {
			return true;
		}
		return false;
	}
	
	public static String randomGenerator()
	{
		ArrayList<Integer> rndNumbers = new ArrayList<Integer>();
		int stringSize = 9;
			
		for (int i=0; i<stringSize; i++) 
		{
			rndNumbers.add(i);
		}
			
		Collections.shuffle(rndNumbers);
		
		String rndString = "";
		
		for(Integer s: rndNumbers)
		{
			rndString += s;
		}
		return rndString;
	}	
}
