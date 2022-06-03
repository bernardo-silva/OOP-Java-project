/**
 * 
 */
package videopoker;

import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author bs
 *
 */
public class VideoPoker {
	Player player;
	DeckOfCards deck;
	
	private List<String> hands;
	private HashMap<String, String[]> handsParameters;
	private HashMap<String, Integer> payouts;
	private List<String> optimalStrat;
	
	/**
	 * @param handFile 
	 * 
	 */
	public VideoPoker(String stratFile, String handFile) {
		player = new Player();
		deck = new DeckOfCards();
		
		optimalStrat = stratFromFile(stratFile);
		handsParameters = new HashMap<String, String[]>();
		payouts = new HashMap<String, Integer>();
		handsFromFile(handFile);
		System.out.println(hands);
	}

	private List<String> stratFromFile(String stratFile) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void handsFromFile(String handFile) {
		File f = new File(handFile);
		Scanner scan;
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] parameters = line.split("#", 0)[0].split(" ",0);
				String name = line.split("#", 0)[1];

				hands.add(name);
				handsParameters.put(name, parameters);
				//TODO: Implement payouts
				payouts.put(name, 1);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Hand File Not Found");
			e.printStackTrace();
		}
	}

	public void payout() {
		
	}

	public int[] advice(){
		return null;
	}

	private String checkHand(){
		for(String hand : hands) {
			for(String par : handsParameters.get(hand)) {
				String rule = par.split(":", 0)[0];
				String value = par.split(":", 0)[1];

				switch (rule) {
				case "Nb":
					break;
				case "st":
					break;
				case "fl":
					break;
				case "hi":
					break;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {

		VideoPoker vp = new VideoPoker("", "hands.txt");
	}
}
