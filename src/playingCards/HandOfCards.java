package playingCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * This class implements a hand of cards, such as the one a player would hold.
 * <p>
 * It provides useful metrics and statistics that ease the checking of a given
 * hand for some type of patterns.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class HandOfCards {
	/**
	 * Hand of cards, consisting in an array of Cards
	 */
	protected ArrayList<Card> hand;
	/**
	 * Set of all suit values on the hand
	 */
	private LinkedHashSet<Integer> uniqueSuits;
	/**
	 * Set of all face values on the hand
	 */
	private LinkedHashSet<Integer> uniqueFaces;
	/**
	 * Array with the number of times each face appears on the hand
	 */
	private int[] faceFrequencies;
	/**
	 * Array with the number of times each suit appears on the hand
	 */
	private int[] suitFrequencies;

	/**
	 * Constructs an empty hand of cards.
	 */
	public HandOfCards() {
		hand = new ArrayList<Card>();
		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		faceFrequencies = new int[15];
		suitFrequencies = new int[6];
	}

	/**
	 * Constructs a hand of cards from a Collection of cards.
	 * @param _hand a Collection of Card objects.
	 */
	public HandOfCards(Collection<Card> _hand) {
		hand = new ArrayList<Card>(_hand);
		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		faceFrequencies = new int[15];
		suitFrequencies = new int[6];

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Constructs a hand of cards from a String with string representations of
	 * cards separated by whitespace.
	 * @param cardString a string with card representations separated by
	 * whitespace
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
	 * Constructs a hand of cards from an array of card string representations.
	 * @param cardString an array of strings representing cards
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
	 * Adds Cards from a Collection of Cards to the hand.
	 * @param cards Collection of cards to be added to the hand 
	 */
	public void add(Collection<Card> cards) {
		hand.addAll(cards);

		countUniqueFacesSuits();
		countFrequencies();
	}

	/**
	 * Adds a new Card to the hand.
	 * @param card Card to be added to the hand 
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
	 * Creates a set with the face and suit values that appear on the hand
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
	 * @param position of the card to be removed
         * @exception IllegalArgumentException if the position provided is out
         * of bounds.
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
	 * @param positions array with the positions of the cards to be discarded
         * @exception IllegalArgumentException if any of the positions provided are out
         * of bounds.
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
	 * Gets the Card at the specified index.
	 * @param i index of the Card to be returned
	 * @return the Card at the specified position
	 */
	public Card get(int i) {
		return hand.get(i);
	}

	/**
	 * Gets the total distance of the cards when sorted.
	 * <p>
	 * The distance between two cards is defined as the difference of their face
	 * values. 
	 * <p>
	 * This method starts by sorting a copy of the hand and then computes the
	 * distances.
	 * @return the total distance between the cards
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
	 * Gets the hand of cards of the player as an ArrayList.
	 * @return the hand of cards.
	 */
	public ArrayList<Card> getCards() {
		return hand;
	}

	/**
	 * Gets the cards that are of a specified suit.
	 * @param suit the desired suit
	 * @return an hand of cards of the specified suit
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
	 * <p>
	 * The returned array has two dummy elements at the beginning to allow using
	 * the face of a card as an index to this array.
	 * @return an array containing the frequencies of each face
	 */
	public int[] getFrequencies() {
		return faceFrequencies;
	}

	/**
	 * Gets a list with the frequencies of each face.
	 * <p>
	 * The resulting list has 14 elements, representing the frequency of each
	 * face with the Ace repeated at the first and last positions 
	 * (A 2 3 4 5 6 7 8 9 T J Q K A).
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
	 * Returns the most common suit in the hand.
	 * @return the most common suit
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
	 * <p>
	 * J, Q, K and A are considered to be high cards.
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
	 * One on one correspondence of the hand to another hand. Returns the
	 * positions of the matching cards.
	 * <p>
	 * This method tries to find the best one on one correspondence between two
	 * hands. 
	 * <p>
	 * Two cards match if their equality is true (see Card.equals). 
	 * It is assumed that if the second hand has a card of suit Some or Any, all
	 * cards have that same suit.
	 * <p>
	 * When the second card contains Cards with the Some suit, the method
	 * checks which suit value results in a greater number of matching cards,
	 * and returns them.
	 * @param hand2 a second hand of cards
	 * @return the positions of the cards that have a match on the second hand.
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
	 * @throws IllegalArgumentException if the position of the card to replace is out of bounds
	 */
	public void replaceCards(List<Card> cards, List<Integer> positions) {
		if(cards.size() != positions.size())
			throw new IllegalArgumentException("List of cards and positions do not have the same number of parameters.");
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
	 * @param positions array with the positions of the cards to be replaced
	 * @throws IllegalArgumentException if the position of the card to replace is out of bonds
	 */
	public void replaceCards(List<Card> cards, int[] positions) {
		if(cards.size() != positions.length)
			throw new IllegalArgumentException("List of cards and positions do not have the same number of parameters.");
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
	 * Gets the number of cards in the hand.
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
	 * Returns a copy of the hand of cards with the cards sorted.
	 * @return the hand sorted
	 */
	public HandOfCards sorted() {
		ArrayList<Card> newCards = new ArrayList<Card>(hand);
		HandOfCards sortedHand = new HandOfCards(newCards);
		sortedHand.sortCards();
		return sortedHand;
	}

	/**
	 * Returns a string representation of the hand.
	 * @return string with the cards in the hand
	 */
	@Override
	public String toString() {
		return "" + hand;
	}
}
