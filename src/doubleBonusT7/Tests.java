package doubleBonusT7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import playingCards.HandOfCards;
import videoPoker.Strategy;

public class Tests {

	public static void testHandsFromFile(String filename) {
		Strategy strat = new DoubleBonusT7Strategy();
        try{        
            File testFile = new File(filename);
            Scanner scanLine = new Scanner(testFile);
			int count = 0;
			boolean success = true;

            while (scanLine.hasNextLine()){
				count++;
				String subStrings[]= scanLine.nextLine().split(":",0);
//				System.out.println(subStrings[0]);
				HandOfCards testHand = new HandOfCards(subStrings[0]);
				ArrayList<Integer> testPositions = new ArrayList<Integer>();
				String[] temp = subStrings[1].split("\\s+");
				for (int i = 0; i < temp.length; i++) {
					testPositions.add(Integer.parseInt(temp[i]));
				}

				ArrayList<Integer> stratPositions = new ArrayList<Integer>();
				stratPositions = strat.getOptimalStrategy(testHand);

				if(!testPositions.equals(stratPositions)){
					success = false;
					System.out.println();
					System.out.println("Strategy incorrect on line " + count + " with hand " + testHand);
					System.out.println("Expected: " + testPositions + " Got: " + stratPositions);
					System.out.println();
				}
//				tests.add(testPositions ==  strat.getOptimalStrategy(testHand));			
			}
			if(success)
				System.out.println("All tests passed");
            scanLine.close();
		}
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
	}
	public static void main(String[] args) {
		testHandsFromFile("TESTS/strategy-hands-tests.txt");
	}

}
