package playingCards;

import java.util.Objects;


/**
 * This class implements a regular playing card with face and suit values.
 * <p>
 * It implements the Comparable interface in order to compare cards by their
 * face value.
 * <p>
 * Two extra suit values are also provided, Any and Some, to provide functionality when
 * comparing the equality of cards.
 */
public class Card implements Comparable<Card>{
	/**
	 * Possible suit types (Clubs, Spades, Hearts, Diamonds, Any and Some)
	 *
	 */
	enum Suit{
		C,
		S,
		H,
		D,
		ANY,
		SOME
	}
	
	/**
	 * Possible face values
	 */
	static final char[] allFaces = {' ',' ','2','3','4','5','6','7','8','9','T','J','Q','K','A'};
	/**
	 * Value of the card's face
	 */
	private final int cardFace;
	/**
	 * Value of the card's suit
	 */
	private final Suit cardSuit;


	/**
	 * Constructor method of the Card, with its face and suit represented as integers
	 * Constructs a card with the values given as integers.
	 * <p>
	 * The face value should be the corresponding number for numbered cards and
	 * 11 for Jacks, 12 for Queens, 13 for Kings and 14 for aces.
	 * <p>
	 * The suit value should be in the correspondence: 0 = Clubs, 1 = Spades,
	 * 2 = Hearts, 3 = Diamonds, 4 = Any, 5 = Some
	 *
	 * @param face Card's face value
	 * @param suit Card's suit value
	 */
	public Card(int face, int suit){
		if(face>14 || face<2) 
			throw new IllegalArgumentException("Card face '" + face + "' is not valid.");

		if(suit>5 || suit<0)
			throw new IllegalArgumentException("Card suit '" + suit + "' is not valid.");

		cardFace = face;
		cardSuit = Suit.values()[suit]; 
	}

	
	/**
	 * Constructs a Card from a string with at most two characters.
	 * <p>
	 * A card face can be represented by a number from 2 to 9 or 
	 * T (ten), J (Jack), Q (Queen), K (King), A (Ace).
	 * <p>
	 * A card suit can be represented by the initial of the suit (C, S, H or D),
	 * or by an "X" corresponding to the Some suit or a "*" corresponding to the Any
	 * suit. In case of omission, the suit is considered to be Any.
	 *
	 * @param string representation of a card with at most 2 characters
	 */
	public Card(String card)
	{
		if(card.length() > 2 || card.length() < 0)
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

	/**
	 * Returns the face of the card as an integer.
	 * @return the face of the card
	 */
	public int getFace(){ 
		return cardFace; 
	}
	
	/**
	 * Returns the suit of the card as an integer
	 * @return the suit of the card
	 */
	public int getSuit(){ 
		return cardSuit.ordinal(); 
	}
	
	/**
	 * Returns a string representation of the card.
	 * @return card string representation
	 */
	public String toString(){ 
		return "" + allFaces[cardFace] + cardSuit;
	}

	/**
	 * Compares the card to another card by face value
	 * @return 1 if it is greater, 0 if they have same face and -1 if it is smaller
	 */
	public int compareTo(Card other) {
			if(cardFace > other.getFace()) return 1;
			else if (cardFace == other.getFace()) return 0;
			else return -1;
	}

	/**
	 * Returns hash code
	 * @return hash code value
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cardFace, cardSuit);
	}

	/**
	 * Checks whether two cards have equal face and compares the suit.
	 * <p>
	 * Two cards are equal whenever they have the same face and the same suit,
	 * or when they have the same face and at least one of them has suit Some or
	 * Any.
	 * @return logic value of euqality
	 */
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
