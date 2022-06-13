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

import videopoker.old.DistanceToFlush;
import videopoker.old.DistanceToStraight;
import videopoker.old.HandRule;
import videopoker.old.InRank;
import videopoker.old.LowCard;
import videopoker.old.NumberRank;
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
	
	private boolean shuffle = true;
	
	public VideoPoker(String handFile, String stratFile, String deckFile, boolean shuffle, Player player) {
		this.player = player;
		this.shuffle = shuffle;

		deck = new DeckOfCards(deckFile);
		
		optimalStrat = stratFromFile(stratFile);
		hands= new ArrayList<PokerHand>();

		handsFromFile(handFile);
		System.out.println(hands);
	}

	public VideoPoker(String handFile, String stratFile, Player player) {
		this.player = player;
		deck = new DeckOfCards();
		
		optimalStrat = stratFromFile(stratFile);
		hands= new ArrayList<PokerHand>();

		handsFromFile(handFile);
		System.out.println(hands);
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
				hands.add(scanHand(scan));
			}
			System.out.println("Coiso");
			scan.close();

		} catch (Exception e) {
			System.out.println("Bad Hand File");
			e.printStackTrace();
		}
	}

	private PokerHand scanHand(Scanner scan) {
		PokerHand hand;
		String[] params;
		int[] payouts;

		String line = scan.nextLine();
		params = line.split(" ",0);
		String name = params[0];
		
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
			Card minCard, maxCard;
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
			Card minCard, maxCard;
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
			Card minCard, maxCard;
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
	
	private int[] stringArrToIntArr(String[] split) {
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
	public int payout(int bet) {
		PokerHand highestHand = checkHand();
		if(highestHand == null) return 0;
		return highestHand.getPayout(bet);
	}

	public int[] advice(){
		return null;
	}

	private PokerHand checkPlayerHand(){
		boolean satisfies = true;
		for(PokerHand hand : hands) {
			if (hand.checkHand(player.hand)) return hand;
		}
		return null;
	}

	public static void main(String[] args) {
		VideoPoker vp = new VideoPoker("", "hands3.txt");
	}

	public void playGame() {
		int bet;
		Action action;
		while(true) {
			bet = player.askBet();
			if(bet < 1 || bet > 5) {
				System.out.println("Invalid bet amount");
				break;
			}

			player.setHand(deck.deal(5));
			action = player.askAction();
			performAction(action);
			payout(bet);
		}
		
	}
}
