package doubleBonusT7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import playingCards.Card;
import playingCards.HandOfCards;
import videopoker.Strategy;
import videopoker.pokerHands.Flush;
import videopoker.pokerHands.FourOfAKind;
import videopoker.pokerHands.FullHouse;
import videopoker.pokerHands.PokerHand;
import videopoker.pokerHands.RoyalFlush;
import videopoker.pokerHands.Straight;
import videopoker.pokerHands.StraightFlush;

public class DoubleBonusT7Strategy implements Strategy {

private enum StrategyCheckers {
	STRAT1(DoubleBonusT7Strategy::strat1),
	STRAT2(DoubleBonusT7Strategy::strat2),
	STRAT3(DoubleBonusT7Strategy::strat3),
	STRAT4(DoubleBonusT7Strategy::strat4),
	STRAT5(DoubleBonusT7Strategy::strat5),
	STRAT6(DoubleBonusT7Strategy::strat6),
	STRAT7(DoubleBonusT7Strategy::strat7),
	STRAT8(DoubleBonusT7Strategy::strat8),
	STRAT9(DoubleBonusT7Strategy::strat9),
	STRAT10(DoubleBonusT7Strategy::strat10),
	STRAT11(DoubleBonusT7Strategy::strat11),
	STRAT12(DoubleBonusT7Strategy::strat12),
	STRAT13(DoubleBonusT7Strategy::strat13),
	STRAT14(DoubleBonusT7Strategy::strat14),
	STRAT15(DoubleBonusT7Strategy::strat15),
	STRAT16(DoubleBonusT7Strategy::strat16),
	STRAT17(DoubleBonusT7Strategy::strat17),
	STRAT18(DoubleBonusT7Strategy::strat18),
	STRAT19(DoubleBonusT7Strategy::strat19),
	STRAT20(DoubleBonusT7Strategy::strat20),
	STRAT21(DoubleBonusT7Strategy::strat21),
	STRAT22(DoubleBonusT7Strategy::strat22),
	STRAT23(DoubleBonusT7Strategy::strat23),
	STRAT24(DoubleBonusT7Strategy::strat24),
	STRAT25(DoubleBonusT7Strategy::strat25),
	STRAT26(DoubleBonusT7Strategy::strat26),
	STRAT27(DoubleBonusT7Strategy::strat27),
	STRAT28(DoubleBonusT7Strategy::strat28),
	STRAT29(DoubleBonusT7Strategy::strat29),
	STRAT30(DoubleBonusT7Strategy::strat30),
	STRAT31(DoubleBonusT7Strategy::strat31),
	STRAT32(DoubleBonusT7Strategy::strat32),
	STRAT33(DoubleBonusT7Strategy::strat33);

	Function<HandOfCards, ArrayList<Integer>> checker;
	
	StrategyCheckers(Function<HandOfCards, ArrayList<Integer>> _checker){
		checker = _checker;
	}
	
	ArrayList<Integer> check(HandOfCards hand) {
		return checker.apply(hand);
	}

}
//	private final static ArrayList<Integer> allPositions = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));

	public DoubleBonusT7Strategy() { }

	@Override
	public ArrayList<Integer> getOptimalStrategy(HandOfCards hand){
		int counter = 1;
		ArrayList<Integer> positions;
		for(StrategyCheckers sc : StrategyCheckers.values()) {
			positions = sc.check(hand);
			if (positions != null) {
//				System.out.println("Matched " + counter);
				return positions;
			}
			counter++;
		}
		return null;
	}

	private static ArrayList<Integer> matchCards(HandOfCards hand, String cards, int amount){
		HandOfCards matchHand = new HandOfCards(cards);
		ArrayList<Integer> positions = hand.match(matchHand);

		if (positions.size() != amount)
			return null;
		return positions;
		
	}

	private static ArrayList<Integer> matchCards(HandOfCards hand, HandOfCards matchHand, int amount){
		ArrayList<Integer> positions = hand.match(matchHand);

		if (positions.size() != amount)
			return null;
		return positions;
		
	}
	public static ArrayList<Integer> strat1(HandOfCards hand) {
		//Straight flush, four of a kind, royal flush
		ArrayList<Integer> allPositions = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

		PokerHand straightFlush = new StraightFlush("SF", null);
		if (straightFlush.checkHand(hand))
			return allPositions;

		PokerHand royalFlush = new RoyalFlush("RF", null);
		if (royalFlush.checkHand(hand))
			return allPositions;

		PokerHand fourOfAKind = new FourOfAKind("4OAK", null);
		if (fourOfAKind.checkHand(hand)) {
			int face;
			int[] frequencies = hand.getFrequencies();
			for (int i = 0; i < 5; i++) {
				face = hand.getCards().get(i).getFace();
				if (frequencies[face] == 1) {
					allPositions.remove(i);
					return allPositions;
				}
			}
		}
		return null;
	}

	public static ArrayList<Integer> strat2(HandOfCards hand) {
		// 4 to royal flush
		if (hand.getUniqueSuits() > 2)
			return null;
		if (hand.getUniqueFaces() < 4)
			return null;

		return matchCards(hand, "TX JX QX KX AX", 4);
	}

	public static ArrayList<Integer> strat3(HandOfCards hand) {
		// Three aces
		ArrayList<Integer> positions = new ArrayList<Integer>(5);
		for(int i=0; i<hand.size(); i++)
			if(hand.get(i).getFace() == 14) positions.add(i+1);
		
		if(positions.size() == 3) return positions;
		return null;
	}

	public static ArrayList<Integer> strat4(HandOfCards hand) {
		//Straight, flush, full house
		ArrayList<Integer> allPositions = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

		PokerHand straight= new Straight("S", null);
		if(straight.checkHand(hand)) return allPositions;

		PokerHand flush = new Flush("F", null);
		if(flush.checkHand(hand)) return allPositions;

		PokerHand fullHouse = new FullHouse("SF", null);
		if(fullHouse.checkHand(hand)) return allPositions;
		
		return null;
	}

	public static ArrayList<Integer> strat5(HandOfCards hand) {
		//Three of a kind
		int[] frequencies = hand.getFrequencies();
		int face = 0;
		for(int i=0; i<frequencies.length; i++)
			if(frequencies[i] == 3) {
				face = i;
				break;
			}
		if(face == 0) return null;

		ArrayList<Integer> positions = new ArrayList<Integer>(5);
		for(int i=0; i<5; i++)
			if(hand.get(i).getFace() == face) positions.add(i+1);
		
		return positions;
	}
	public static ArrayList<Integer> strat6(HandOfCards hand) {
		//4 to straight flush
		if(hand.getUniqueSuits() > 2) return null; 
		if(hand.getUniqueFaces() < 4) return null; 
		
		int mostCommonSuit = hand.getMostCommonSuit();
		HandOfCards suitedHand = hand.getCardsOfSuit(mostCommonSuit);
		if(suitedHand.size() != 4) return null;

		suitedHand.sortCards();
		if(suitedHand.getCardDistances() > 4) return null;
		
		return matchCards(hand, suitedHand, suitedHand.size());
	}

	public static ArrayList<Integer> strat7(HandOfCards hand) {
		//Two pair
		int[] frequencies = hand.getFrequencies();
		int[] pairs = new int[2];
		int nPairs = 0;

		for(int i=2; i<frequencies.length; i++) {
			if(frequencies[i] > 2) return null;
			if(frequencies[i]==2) {
				pairs[nPairs] = i;
				nPairs++;
			}
		}
		if(nPairs != 2) return null;

		ArrayList<Integer> positions = new ArrayList<Integer>(5);
		int face;
		for(int i=0; i<5; i++) {
			face = hand.get(i).getFace() ; 
			if(face == pairs[0] || face == pairs[1]) positions.add(i+1);
		}
		return positions;
	}

	public static ArrayList<Integer> strat8(HandOfCards hand) {
		//High Pair
		int[] frequencies = hand.getFrequencies();
		int face = 0;
		
		for(int i=11; i<frequencies.length; i++) {
			if(frequencies[i]==2) {
				face = i;
				break;
			}
		}
		if(face == 0) return null; 

		ArrayList<Integer> positions = new ArrayList<Integer>(5);
		for(int i=0; i<5; i++)
			if(hand.get(i).getFace() == face) positions.add(i+1);

		return positions;
	}
	
	public static ArrayList<Integer> strat9(HandOfCards hand) {
		//4 to a flush
		if(hand.getUniqueSuits() > 2) return null; 
		
		int mostCommonSuit = hand.getMostCommonSuit();
		HandOfCards suitedHand = hand.getCardsOfSuit(mostCommonSuit);
		if(suitedHand.size() != 4) return null;

		
		return matchCards(hand, suitedHand, suitedHand.size());
	}

	public static ArrayList<Integer> strat10(HandOfCards hand) {
		//3 to Royal flush
		return matchCards(hand, "TX JX QX KX AX", 3);
	}

	public static ArrayList<Integer> strat11(HandOfCards hand) {
		// 4 to outside straight
		ArrayList<Integer> freqArr = hand.getFrequenciesArr();
		freqArr.replaceAll(e -> (e>0)?1:0);

		List<Integer> pattern = Arrays.asList(0,1,1,1,1,0);
		int subArrIdx = Collections.indexOfSubList(freqArr, pattern);
		if(subArrIdx == -1) return null;

		HandOfCards sequenceCards = new HandOfCards();

		for(int i=0; i<4; i++)
			sequenceCards.add(new Card(subArrIdx + 2 + i, 4));

		
		return matchCards(hand, sequenceCards, 4);
		
		
		
//		int[] frequencies = hand.getFrequencies();
//		int lowCard = 0;
//
//		for(int i=3; i<=10; i++) {
//			if(frequencies[i] == frequencies[i+1])
//			if(frequencies[i+1] == frequencies[i+2])
//			if(frequencies[i+2] == frequencies[i+3])
//				lowCard = i;
//		}
//		if(lowCard == 0) return null;
//
//		ArrayList<Integer> positions = new ArrayList<Integer>(4);
//		for(int i=0; i<5; i++) {
//			if(hand.get(i).getFace() == lowCard + positions.size())
//				positions.add(i+1);
//		}
//		return positions;
	}

	public static ArrayList<Integer> strat12(HandOfCards hand) {
		//Low Pair
		int[] frequencies = hand.getFrequencies();
		int face = 0;
		
		for(int i=2; i<=10; i++) {
			if(frequencies[i]==2) {
				face = i;
				break;
			}
		}
		if(face == 0) return null; 

		HandOfCards pairOfCards = new HandOfCards();
		pairOfCards.add(new Card(face, 4));
		pairOfCards.add(new Card(face, 4));
		
		return matchCards(hand, pairOfCards, 2);

//		ArrayList<Integer> positions = new ArrayList<Integer>(5);
//		for(int i=0; i<5; i++)
//			if(hand.get(i).getFace() == face) positions.add(i+1);
//
//		return positions;
	}

	public static ArrayList<Integer> strat13(HandOfCards hand) {
		//AKQJ unsuited
		return matchCards(hand, "A* K* Q* J*", 4);
	}

	public static ArrayList<Integer> strat14(HandOfCards hand) {
		//3 to straight flush (type 1)
		int mostCommonSuit = hand.getMostCommonSuit();
		HandOfCards suitedHand = hand.getCardsOfSuit(mostCommonSuit);
		if(suitedHand.size() != 3) return null;
		
		switch(suitedHand.getNHighCards()) {
		case 0:
			if(suitedHand.getLowCard() == 2) return null;
			if(suitedHand.getCardDistances() != 2) return null; 
			break;
		case 1:
			if(suitedHand.getCardDistances() > 3) return null; 
			break;
		default: //2 or 3
			if(suitedHand.getCardDistances() > 4) return null; 
			break;
		}
			
		return matchCards(hand, suitedHand, 3);
	}

	public static ArrayList<Integer> strat15(HandOfCards hand) {
		//4 to an inside straight with 3 high cards
		if(hand.getNHighCards() < 3) return null;
		if(hand.getHighCard() == 14) return matchCards(hand, "T* J* Q* K* A*", 4);
		return matchCards(hand, "9* T* J* Q* K*", 4);
	}

	public static ArrayList<Integer> strat16(HandOfCards hand) {
		//QJ suited
		return matchCards(hand, "QX JX", 2);
	}

	public static ArrayList<Integer> strat17(HandOfCards hand) {
		//3 to a flush with 2 high cards
		if(hand.getNHighCards() < 2) return null;
		int mostCommonSuit = hand.getMostCommonSuit();
		HandOfCards suitedHand = hand.getCardsOfSuit(mostCommonSuit);
		if(suitedHand.size() < 3 || suitedHand.getNHighCards() < 2) return null;
		
		return matchCards(hand, suitedHand, 3);
	}

	public static ArrayList<Integer> strat18(HandOfCards hand) {
		//2 suited high cards
		return matchCards(hand, "QX JX KX AX", 2);
	}
	public static ArrayList<Integer> strat19(HandOfCards hand) {
		//4 to an inside straight with 2 high cards
		if(hand.getNHighCards() < 2) return null;
		if(hand.getHighCard() == 13) return matchCards(hand, "9* T* J* Q* K*", 4);
		return matchCards(hand, "8* 9* T* J* Q*", 4);
	}
	public static ArrayList<Integer> strat20(HandOfCards hand) {
		//3 to a straight flush (type 2)
		int mostCommonSuit = hand.getMostCommonSuit();
		HandOfCards suitedHand = hand.getCardsOfSuit(mostCommonSuit);
		if(suitedHand.size() != 3) return null;
		
		switch(suitedHand.getNHighCards()) {
		case 0:
			if(suitedHand.getCardDistances() > 3) return null; 
			break;
		case 1:
			if(suitedHand.getHighCard() == 14) return matchCards(hand, "A* 2* 3* 4* 5*", 3);
			if(suitedHand.getCardDistances() > 4) return null; 
			break;
		}
		return matchCards(hand, suitedHand, 3);
	}
	public static ArrayList<Integer> strat21(HandOfCards hand) {
		// 4 to an inside straight with 1 high card
		if(hand.getNHighCards() != 1) return null;
		int highCard = hand.getHighCard();
		if(highCard == 12) return matchCards(hand, "8* 9* T* J* Q*", 4);
		return matchCards(hand, "7* 8* 9* T* J*", 4);
	}
	public static ArrayList<Integer> strat22(HandOfCards hand) {
		// KQJ unsuited
		return matchCards(hand, "Q* J* K*", 3);
	}
	public static ArrayList<Integer> strat23(HandOfCards hand) {
		// JT suited
		return matchCards(hand, "JX TX", 2);
	}
	public static ArrayList<Integer> strat24(HandOfCards hand) {
		// QJ unsuited
		return matchCards(hand, "J* Q*", 2);
	}
	public static ArrayList<Integer> strat25(HandOfCards hand) {
		// 3 to a flush with 1 high card
		int mostCommonSuit = hand.getMostCommonSuit();
		HandOfCards suitedHand = hand.getCardsOfSuit(mostCommonSuit);
		if(suitedHand.size() != 3) return null;
		if(suitedHand.getNHighCards() != 1) return null;

		return matchCards(hand, suitedHand, 3);
	}

	public static ArrayList<Integer> strat26(HandOfCards hand) {
		// QT suited
		return matchCards(hand, "TX QX", 2);
	}

	public static ArrayList<Integer> strat27(HandOfCards hand) {
		// 3 to a straight flush (type 3)
		int mostCommonSuit = hand.getMostCommonSuit();
		HandOfCards suitedHand = hand.getCardsOfSuit(mostCommonSuit);
		if(suitedHand.size() != 3) return null;
		
		if(suitedHand.getCardDistances() > 4) return null; 

		return matchCards(hand, suitedHand, 3);
	}

	public static ArrayList<Integer> strat28(HandOfCards hand) {
		// KQ, KJ unsuited
		ArrayList<Integer> positions = matchCards(hand, "Q* K*", 2);
		if(positions != null && !positions.isEmpty()) return positions;

		return matchCards(hand, "J* K*", 2);
	}

	public static ArrayList<Integer> strat29(HandOfCards hand) {
		// Ace
		return matchCards(hand, "A*", 1);
	}
	public static ArrayList<Integer> strat30(HandOfCards hand) {
		// KT suited
		return matchCards(hand, "TX KX", 2);
	}
	public static ArrayList<Integer> strat31(HandOfCards hand) {
		//J, Q, K
		return matchCards(hand, "J* Q* K*", 1);
	}
	public static ArrayList<Integer> strat32(HandOfCards hand) {
		// 4 to an inside straight with no high cards
		if(hand.getNHighCards() != 0) return null;
		ArrayList<Integer> freqArr = hand.getFrequenciesArr();
		freqArr.replaceAll(e -> (e>0)?1:0);

		int subArrIdx = -1;
		List<Integer> pattern = Arrays.asList(1,0,1,1,1);

		for(int i=0; i<3; i++) {
			Collections.rotate(pattern, i);
			subArrIdx = Collections.indexOfSubList(freqArr, pattern);
			if(subArrIdx != -1) break;
		}
		if(subArrIdx == -1) return null;

		HandOfCards sequenceCards = new HandOfCards();
		for(int i=0; i<5; i++)
			if(pattern.get(i) != 0)
				sequenceCards.add(new Card(subArrIdx + 1 + i, 4));

		return matchCards(hand, sequenceCards, 4);
	}
	public static ArrayList<Integer> strat33(HandOfCards hand) {
		//  3 to a flush with no high cards
		int mostCommonSuit = hand.getMostCommonSuit();
		HandOfCards suitedHand = hand.getCardsOfSuit(mostCommonSuit);
		if(suitedHand.size() != 3) return null;

		return matchCards(hand, suitedHand, 3);
	}

	public static void main(String[] args) {
		ArrayList<Card> cards = new ArrayList<Card>(5);
		cards.add(new Card("2H"));
		cards.add(new Card("TC"));
		cards.add(new Card("QH"));
		cards.add(new Card("KC"));
		cards.add(new Card("AC"));
		
		HandOfCards hand = new HandOfCards(cards);
		System.out.println(hand);
		
		
		ArrayList<Integer> positions = DoubleBonusT7Strategy.strat2(hand);
		System.out.println(positions);

	}
}
