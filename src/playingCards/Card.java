package playingCards;

import java.util.Objects;

/**
 * @author bs
 *
 */
public class Card implements Comparable<Card>{
	enum Suit{
		C,
		S,
		H,
		D,
		ANY,
		SOME
	}
	static final char[] allFaces = {' ',' ','2','3','4','5','6','7','8','9','T','J','Q','K','A'};

	private final int cardFace;
	private final Suit cardSuit;

//constructors
	/**
	 * @param face
	 * @param suit
	 */
	public Card(int face, int suit){//using ints
		if(face>14 || face<2) 
			throw new IllegalArgumentException("Card face '" + face + "' is not valid.");

		if(suit>5 || suit<0)
			throw new IllegalArgumentException("Card suit '" + suit + "' is not valid.");

		cardFace = face;
		cardSuit = Suit.values()[suit]; 
	}

	public Card(String card)
	{
		if(card.length() > 2)
			throw new IllegalArgumentException("Card '" + card + "' is not valid. Expected String with at most two characters.");

		int face = -1;
		for (int i=2; i<allFaces.length;i++) {
			if(allFaces[i] == card.charAt(0)) {
				face = i;
				break;
			}
		}

		if(face==-1)
			throw new IllegalArgumentException("Card '" + card + "' is not valid. Invalid face");
		
		cardFace = face;
		
		if(card.length() == 1) {
			cardSuit = Suit.ANY;
			return;
		}

		if(card.charAt(1) == '*')
			cardSuit = Suit.ANY;
		else if(card.charAt(1) == 'X')
			cardSuit = Suit.SOME;
		else {
			try {
				cardSuit = Suit.valueOf(card.substring(1,2));
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Card '" + card + "' is not valid. Invalid suit.");
			}
		}
	}

	public int getFace(){ 
		return cardFace; 
	}
	
	public int getSuit(){ 
		return cardSuit.ordinal(); 
	}
	
	public String toString(){ 
		return "" + allFaces[cardFace] + cardSuit;
	}

	public int compareTo(Card other) {
			if(cardFace > other.getFace()) return 1;
			else if (cardFace == other.getFace()) return 0;
			else return -1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardFace, cardSuit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		
		if(cardFace != other.cardFace) return false;
		if(cardSuit == Suit.ANY || cardSuit == Suit.SOME) return true;
		if(other.cardSuit == Suit.ANY || other.cardSuit == Suit.SOME) return true;
		return cardSuit == other.cardSuit;
	}
}