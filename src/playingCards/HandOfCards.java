package playingCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * Implements the hand of cards class.
 * ________________________________________________ add more info
 *
 */
public class HandOfCards {
	/**
	 * Hand of cards, consisting in an array of Cards
	 */
	protected ArrayList<Card> hand;
	private LinkedHashSet<Integer> uniqueSuits;
	private LinkedHashSet<Integer> uniqueFaces;
	private int[] faceFrequencies;
	private int[] suitFrequencies;

	/**
	 * Constructor method. Initializes the hand of the cards, the ...
	 */
	public HandOfCards() {
		hand = new ArrayList<Card>();
		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		faceFrequencies = new int[15];
		suitFrequencies = new int[6];
	}

	/**
	 * Constructor method from an array of Cards.
	 * @param _hand an array of Cards containing the hand of the player
	 */
	public HandOfCards(ArrayList<Card> _hand) {
		hand = _hand;
		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		faceFrequencies = new int[15];
		suitFrequencies = new int[6];

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Constructor method from a string of cards
	 * @param cardString a string with the cards of the hand
	 * 
	 */
	public HandOfCards(String cardString) {
		hand = new ArrayList<Card>();

		String[] cards = cardString.split("\\s+", 0);
		for (String c : cards)
			hand.add(new Card(c));

		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		faceFrequencies = new int[15];
		suitFrequencies = new int[6];
		
		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Constructor method from an array of strings of cards
	 * @param cardString an array of strings with the cards of the hand
	 * 
	 */
	public HandOfCards(String[] cardString) {
		hand = new ArrayList<Card>(5);

		for (int i = 0; i < cardString.length; i++) {
			if (i >= 5) {
				System.out.println("Hand can't have more than 5 cards");
				break;
			}
			Card card = new Card(cardString[i]);
			hand.add(card);
		}

		uniqueSuits = new LinkedHashSet<Integer>(5);
		uniqueFaces = new LinkedHashSet<Integer>(5);
		faceFrequencies = new int[15];
		suitFrequencies = new int[6];

		countUniqueFacesSuits();
		countFrequencies();
	}


	/**
	 * Adds new cards to the hand.
	 * @param cards array of cards to be added to the hand 
	 */
	public void add(ArrayList<Card> cards) {
		hand.addAll(cards);

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Adds new card to the hand.
	 * @param card card to add to the hand 
	 */
	public void add(Card card) {
		hand.add(card);

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Counts the frequencies of the faces and suits of the cards in the hand.
	 */
	private void countFrequencies() {
		Arrays.fill(faceFrequencies, 0);
		Arrays.fill(suitFrequencies, 0);
		for (Card card : hand) {
			faceFrequencies[card.getFace()]++;
			suitFrequencies[card.getSuit()]++;
		}
	}

	/**
	 * adds the unique faces and suits to the linked hashed sets uniqueFaces and uniqueSuits 
	 * _______________________________________________verify this!
	 */
	private void countUniqueFacesSuits() {
		uniqueSuits.clear();
		uniqueFaces.clear();
		for (Card card : hand) {
			uniqueSuits.add(card.getSuit());
			uniqueFaces.add(card.getFace());
		}
	}

	/**
	 * Removes the card at the specified position from the hand.
	 * @param position of the card to discard
	 */
	public void discard(int position) {
		if (position >= this.size() || position < 0)
			throw new IllegalArgumentException("Position to discard out of bounds.");
		hand.remove(position);

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Removes the cards at the specified positions from the hand.
	 * @param positions array with the positions of the cards to discard
	 */
	public void discard(int[] positions) {
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] >= this.size() || positions[i] < 0)
				throw new IllegalArgumentException("Position to discard out of bounds.");
			hand.remove(positions[i]);
		}

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Gets the card at the position i in the hand
	 * @param i position of the desired card
	 * @return card at position i
	 */
	public Card get(int i) {
		return hand.get(i);
	}

	/**
	 * Gets the distance of the cards. __________________________complete this?
	 * @return the distance of the cards
	 */
	public int getCardDistances() {
		HandOfCards sortedHand = this.sorted();
		int distance = 0;
		for (int i = 1; i < hand.size(); i++) {
			distance += sortedHand.get(i).getFace() - sortedHand.get(i - 1).getFace();
		}
		return distance;
	}

	/**
	 * Gets the hand of cards of the player.
	 * @return the hand of cards.
	 */
	public ArrayList<Card> getCards() {
		return hand;
	}

	/**
	 * Gets an hand of cards of a specified suit.
	 * @param suit the desired suit
	 * @return an hand of cards with the specified suit
	 */
	public HandOfCards getCardsOfSuit(int suit) {
		HandOfCards suitedHand = new HandOfCards();
		for (Card card : hand)
			if (card.getSuit() == suit)
				suitedHand.add(card);

		return suitedHand;
	}

	/**
	 * Gets the frequencies of each face.
	 * @return an array containing the frequencies of each face
	 */
	public int[] getFrequencies() {
		return faceFrequencies;
	}

	/**
	 * Gets an array with the frequencies of each face.
	 * @return an array with the frequencies of each face
	 */
	public ArrayList<Integer> getFrequenciesArr() {
		ArrayList<Integer> frequencies = new ArrayList<Integer>(14);
		frequencies.add(faceFrequencies[14]);
		for (int i = 2; i <= 14; i++)
			frequencies.add(faceFrequencies[i]);
		return frequencies;
	}

	/**
	 * Gets the face of the highest card in the hand.
	 * @return the face of the highest card
	 */
	public int getHighCard() {
		HandOfCards sortedHand = this.sorted();
		return sortedHand.get(sortedHand.size() - 1).getFace();
	}

	/**
	 * Gets the face of the lowest card in the hand.
	 * @return the face of the lowest card
	 */
	public int getLowCard() {
		HandOfCards sortedHand = this.sorted();
		return sortedHand.get(0).getFace();
	}

	/**
	 * Returns the most common suit in the hand
	 * @return the suit (in int form) of the most common suit
	 */
	public int getMostCommonSuit() {
		int maxFreq = 0;
		int idx = 0;
		for (int i = 0; i < suitFrequencies.length; i++) {
			if (suitFrequencies[i] > maxFreq) {
				maxFreq = suitFrequencies[i];
				idx = i;
			}
		}
		return idx;
	}

	/**
	 * Counts the number of high cards in the hand.
	 * @return the number of high cards in the hand
	 */
	public int getNHighCards() {
		int nHighCards = 0;
		for (int i = 11; i <= 14; i++)
			nHighCards += faceFrequencies[i];

		return nHighCards;
	}

	/**
	 * Gets the number of unique faces in the hand.
	 * @return the number of unique faces in the hand
	 */
	public int getUniqueFaces() {
		return uniqueFaces.size();
	}

	/**
	 * Gets the number of unique suits in the hand.
	 * @return the number of unique suits in the hand
	 */
	public int getUniqueSuits() {
		return uniqueSuits.size();
	}

	/**
	 * _______________________________________________________________ write this mighty B.
	 * @param hand2
	 * @return
	 */
	public ArrayList<Integer> match(HandOfCards hand2) {
		ArrayList<Integer> positions = new ArrayList<Integer>(5);
		ArrayList<Card> cards2 = hand2.getCards();

		if (cards2.get(0).getSuit() == 5) { // Some
			ArrayList<Integer> newPositions = new ArrayList<Integer>(5);

			for (int suit = 0; suit < 4; suit++) {
				for (int i = 0; i < hand.size(); i++) {

					if (hand.get(i).getSuit() != suit)
						continue;

					for (int j = 0; j < cards2.size(); j++) {
						if (hand.get(i).equals(cards2.get(j))) {
							newPositions.add(i + 1);
							cards2.remove(j);
							break;
						}
					}
				}
				if (newPositions.size() > positions.size())
					positions = new ArrayList<Integer>(newPositions);
				newPositions.clear();
			}
		} else { // Any or specific suit
			for (int i = 0; i < hand.size(); i++) {
				for (int j = 0; j < cards2.size(); j++) {
					if (hand.get(i).equals(cards2.get(j))) {
						positions.add(i + 1);
						cards2.remove(j);
						break;
					}
				}
			}
		}

		return positions;
	}

	/**
	 * Prints the cards in the hand.
	 */
	public void print() {
		System.out.println(hand);
	}

	/**
	 * Replaces the cards at the specified positions in the hand with the new given cards.
	 * @param cards list of new cards to replace the discarded ones
	 * @param positions array with the positions of the cards to be replaced
	 * @throws IllegalArgumentException if the position of the card to replace is out of bonds
	 */
	public void replaceCards(ArrayList<Card> cards, ArrayList<Integer> positions) {
		if (this.size() == 0) {
			for (int i = 0; i < cards.size(); i++)
				hand.add(null);
		}
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i) >= this.size() || positions.get(i) < 0)
				throw new IllegalArgumentException("Position to replace out of bounds.");
			hand.set(positions.get(i), cards.get(i));
		}

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Replaces the cards at the specified positions in the hand with the new given cards.
	 * @param cards list of new cards to replace the discarded ones
	 * @param array with the positions of the cards to be replaced
	 * @throws IllegalArgumentException if the position of the card to replace is out of bonds
	 */
	public void replaceCards(ArrayList<Card> cards, int[] positions) {
		if (this.size() == 0) {
			for (int i = 0; i < cards.size(); i++)
				hand.add(null);
		}
		for (int position : positions) {
			if (position >= this.size() || position < 0)
				throw new IllegalArgumentException("Position to replace out of bounds.");
			hand.set(position, cards.get(position));
		}

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Replaces the card at the specified position in the hand with a new given card.
	 * @param card to be set on the hand
	 * @param position of the card to be replaced
	 * @throws IllegalArgumentException if the position of the card to replace is out of bonds
	 */
	public void replaceCards(Card card, int position) {
		if (position >= this.size() || position < 0)
			throw new IllegalArgumentException("Position to replace out of bounds.");
		hand.set(position, card);

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Gets the size of the hand.
	 * @return the size of the hand
	 */
	public int size() {
		return hand.size();
	}

	/**
	 * Sorts the cards in the hand.
	 */
	public void sortCards() {
		Collections.sort(hand);
	}

	/**
	 * Returns an hand of cards with the cards sorted.
	 * @return the hand sorted
	 */
	public HandOfCards sorted() {
		ArrayList<Card> newCards = new ArrayList<Card>(hand);
		HandOfCards sortedHand = new HandOfCards(newCards);
		sortedHand.sortCards();
		return sortedHand;
	}

	@Override
	public String toString() {
		return "" + hand;
	}
}