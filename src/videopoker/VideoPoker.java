/**
 * 
 */
package videopoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * @author bs
 *
 */
public class VideoPoker {
	Player player;
	DeckOfCards deck;
	
//	private List<String> hands;
	private ArrayList<PokerHand> hands;
	private ArrayList<PokerHand> optimalStrat;
	
	public VideoPoker(String stratFile, String handFile) {
		player = new Player();
		deck = new DeckOfCards();
		
		optimalStrat = stratFromFile(stratFile);
		hands= new ArrayList<PokerHand>();

		handsFromFile(handFile);
		Collections.sort(hands);
	}

	private ArrayList<PokerHand> stratFromFile(String stratFile) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void handsFromFile(String handFile) {
		File f = new File(handFile);
		Scanner scan;
		
		String line, name;
		int payout;
		
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				System.out.println(line);

				name = line.split(":",2)[0];
				payout = Integer.parseInt(line.split(":",2)[1]);

				hands.add(new PokerHand(name, payout, scanHandRules(scan)));
			}
			System.out.println("COiso");
			scan.close();

		} catch (FileNotFoundException e) {
			System.out.println("Bad Hand File");
			e.printStackTrace();
		}
	}
	
	private ArrayList<HandRule> scanHandRules(Scanner scan) {
		ArrayList<HandRule> rules = new ArrayList<>();

		String line = scan.nextLine();
		
		String ruleName, ruleValues;
		String[] params;

		int value;
		
		while(!line.isBlank()) {
			params = line.split("=",2);

			ruleName = params[0];
			System.out.println(ruleName);
			ruleValues = params[1];

			switch (ruleName) {
			case "numberRank":
				String[] ranks = ruleValues.split(";",2)[0].split(",",0);
				value = Integer.parseInt(ruleValues.split(";",2)[1]);
				rules.add(new NumberRank(ranks, value));
				break;
			case "inRank":
				String[] valuesStrings = ruleValues.split(",",0);
				rules.add(new InRank(valuesStrings));
				break;
			case "distanceToFlush":
				value = Integer.parseInt(ruleValues);
				rules.add(new DistanceToFlush(value));
				break;
			case "distanceToStraight":
				value = Integer.parseInt(ruleValues);
				rules.add(new DistanceToStraight(value));
				break;
			case "lowCard":
				String card = ruleValues;
				rules.add(new LowCard(card));
				break;
			}
			if(!scan.hasNextLine()) break;
			line = scan.nextLine();
		}
		return rules;
	}

	public int payout() {
		PokerHand highestHand = checkHand();
		if(highestHand == null) return 0;
		return highestHand.getPayout();
	}

	public int[] advice(){
		return null;
	}

	private PokerHand checkHand(){
		boolean satisfies = true;
		for(PokerHand hand : hands) {
			if (hand.checkHand(player.hand)) return hand;
		}
		return null;
	}

	public static void main(String[] args) {
		VideoPoker vp = new VideoPoker("", "hands2.txt");
	}
}
