package videopoker;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import cards.Card;

public class VideoPokerUtil {
	public static ArrayList<PokerHand> stratFromFile(String stratFile) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ArrayList<PokerHand> handsFromFile(String handFile) {
		ArrayList<PokerHand> hands = new ArrayList<PokerHand>(); 
		File f = new File(handFile);
		Scanner scan;
		
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				hands.add(scanHand(scan));
			}
			System.out.println("Coiso");
			scan.close();

		} catch (Exception e) {
			System.out.println("Bad Hand File");
			e.printStackTrace();
			System.exit(0);
		}
		return hands;
	}

	private static PokerHand scanHand(Scanner scan) {
		PokerHand hand = null;
		String[] params;
		int[] payouts;

		String line = scan.nextLine();
		params = line.split("\\s+",0);
		String name = params[0];
		Card minCard, maxCard;
		
		System.out.println("Parsing " + name);
		
		switch(name) {
		case "RoyalFlush":
			payouts = stringArrToIntArr(params[1].split(",",0));
			hand = new RoyalFlush(name, payouts);
			break;
		case "StraightFlush":
			payouts = stringArrToIntArr(params[1].split(",",0));
			hand = new StraightFlush(name, payouts);
			break;
		case "FourOfAKind":
			System.out.println("FourOfAKind " + params[1]);
			String[] cards = params[1].split("-",2);
			minCard = new Card(params[1].split("-",2)[0]);
			if (cards.length == 2)
				maxCard = new Card(params[1].split("-",2)[1]);
			else
				maxCard = minCard;

			payouts = stringArrToIntArr(params[2].split(",",0));
			hand = new FourOfAKind(name, payouts, minCard, maxCard);
			break;
		case "FullHouse":
			payouts = stringArrToIntArr(params[1].split(",",0));
			hand = new FullHouse(name, payouts);
			break;
		case "Flush":
			payouts = stringArrToIntArr(params[1].split(",",0));
			hand = new Flush(name, payouts);
			break;
		case "Straight":
			payouts = stringArrToIntArr(params[1].split(",",0));
			hand = new Straight(name, payouts);
			break;
		case "ThreeOfAKind":
			cards = params[1].split("-",2);
			minCard = new Card(cards[0]);
			if (cards.length == 2)
				maxCard = new Card(cards[1]);
			else
				maxCard = minCard;

			payouts = stringArrToIntArr(params[2].split(",",0));
			hand = new ThreeOfAKind(name, payouts, minCard, maxCard);
			break;
		case "NPairOf":
			int N = Integer.parseInt(params[1]);
			cards = params[2].split("-",2);
			minCard = new Card(cards[0]);
			if (cards.length == 2)
				maxCard = new Card(cards[1]);
			else
				maxCard = minCard;

			payouts = stringArrToIntArr(params[3].split(",",0));
			hand = new NPairOf(name, payouts, N, minCard, maxCard);
			break;
		}

		return hand;
	}
	
	private static int[] stringArrToIntArr(String[] split) {
		for(String s : split)
			System.out.print(s);
		System.out.println();
		int[] payouts = new int[split.length];

		for(int i=0;i<split.length;i++) {
			payouts[i] = Integer.parseInt(split[i]);
		}
		return payouts;
	}

	/*
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
*/
}
