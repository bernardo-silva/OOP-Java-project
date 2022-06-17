package videopoker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Function;

import playingCards.Card;
import playingCards.HandOfCards;

public class Strategy {
//	private final static ArrayList<Integer> allPositions = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));

	public Strategy() {
	}

	private static int[] asArray(ArrayList<Integer> arr) {
		int[] result = new int[arr.size()];
		for (int i = 0; i < arr.size(); i++)
			result[i] = arr.get(i);

		return result;
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

		ArrayList<Card> cards = new ArrayList<Card>(5);
		cards.add(new Card("TX"));
		cards.add(new Card("JX"));
		cards.add(new Card("QX"));
		cards.add(new Card("KX"));
		cards.add(new Card("AX"));

		ArrayList<Integer> positions = hand.match(new HandOfCards(cards));
		if (positions.size() < 4)
			return null;
		return positions;
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
		//
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
		if(hand.getUniqueSuits() > 2) return null; 
		if(hand.getUniqueFaces() < 4) return null; 
		
		return null;
//		int[] frequencies = hand.getFrequencies();
//		int i = 0;
//		while(true) {
//			for(int j=0; j<4; j++) {
//				if(frequencies[i+j]==0) {
//					
//				}
//
//				
//			}
//		}
	}
	public static ArrayList<Integer> strat7(HandOfCards hand) {
		int[] frequencies = hand.getFrequencies();
		int[] pairs = new int[2];
		int nPairs = 0;

		for(int i=2; i<frequencies.length; i++) {
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
		int[] frequencies = hand.getFrequencies();
		int face = 0;
		for(int i=10; i<frequencies.length; i++) {
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
		if(hand.getUniqueSuits() != 2) return null;

		int commonSuit = 0;
		for(int i=1; i<5; i++) {
			if(hand.get(i-1).getSuit() == hand.get(i).getSuit()) {
				commonSuit = hand.get(i).getSuit();
				break;
			}
		}

		ArrayList<Integer> positions = new ArrayList<Integer>(5);

		for(int i=0; i<5; i++)
			if(hand.get(i).getSuit() == commonSuit) positions.add(i+1);

		if(positions.size() == 4) return positions;
		return null;
	}
	public static ArrayList<Integer> strat10(HandOfCards hand) {
		ArrayList<Card> cards = new ArrayList<Card>(5);
		cards.add(new Card("TX"));
		cards.add(new Card("JX"));
		cards.add(new Card("QX"));
		cards.add(new Card("KX"));
		cards.add(new Card("AX"));

		ArrayList<Integer> positions = hand.match(new HandOfCards(cards));
		if (positions.size() != 3)
			return null;
		return positions;
	}
	public static ArrayList<Integer> strat11(HandOfCards hand) {
		// 4 to outside straight
		int[] frequencies = hand.getFrequencies();
		int lowCard = 0;

		for(int i=3; i<=10; i++) {
			if(frequencies[i] == frequencies[i+1])
			if(frequencies[i+1] == frequencies[i+2])
			if(frequencies[i+2] == frequencies[i+3])
				lowCard = i;
		}
		if(lowCard == 0) return null;

		ArrayList<Integer> positions = new ArrayList<Integer>(4);
		for(int i=0; i<5; i++) {
			if(hand.get(i).getFace() == lowCard + positions.size())
				positions.add(i+1);
		}
		return positions;
	}
	public static ArrayList<Integer> strat11(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat12(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat13(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat14(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat15(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat16(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat17(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat18(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat19(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat20(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat21(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat22(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat23(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat24(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat25(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat26(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat27(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat28(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat29(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat30(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat31(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat32(HandOfCards hand) {
        }
	public static ArrayList<Integer> strat33(HandOfCards hand) {
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
		
		
		ArrayList<Integer> positions = Strategy.strat2(hand);
		System.out.println(positions);

//		StrategyCheckers sc = StrategyCheckers.STRAT1;
//		for(StrategyCheckers s : StrategyCheckers.values()) {
//			System.out.println(s.check(null));
//		}
//		for(StrategyCheckers s : StrategyCheckers.values()) {
//			System.out.println(s.check(null).length);
//		}

	}
}
