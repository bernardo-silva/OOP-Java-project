package playingCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

public class HandOfCards {
	protected ArrayList<Card> hand;
	private LinkedHashSet<Integer> uniqueSuits;
	private LinkedHashSet<Integer> uniqueFaces;
	private int[] faceFrequencies;
	private int[] suitFrequencies;

	public HandOfCards() {
		hand = new ArrayList<Card>();
		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		faceFrequencies = new int[15];
		suitFrequencies = new int[6];
	}

	public HandOfCards(ArrayList<Card> _hand) {
		hand = _hand;
		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		faceFrequencies = new int[15];
		suitFrequencies = new int[6];

		countUniqueFacesSuits();
		countFrequencies();
	}

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

	public void add(ArrayList<Card> cards) {
		hand.addAll(cards);

		countUniqueFacesSuits();
		countFrequencies();
	}

	public void add(Card card) {
		hand.add(card);

		countUniqueFacesSuits();
		countFrequencies();
	}

	private void countFrequencies() {
		Arrays.fill(faceFrequencies, 0);
		for (Card card : hand) {
			faceFrequencies[card.getFace()]++;
			suitFrequencies[card.getSuit()]++;
		}
	}

	private void countUniqueFacesSuits() {
		uniqueSuits.clear();
		uniqueFaces.clear();
		for (Card card : hand) {
			uniqueSuits.add(card.getSuit());
			uniqueFaces.add(card.getFace());
		}
	}

	public void discard(int position) {
		if (position >= this.size() || position < 0)
			throw new IllegalArgumentException("Position to discard out of bounds.");
		hand.remove(position);

		countUniqueFacesSuits();
		countFrequencies();
	}

	public void discard(int[] positions) {
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] >= this.size() || positions[i] < 0)
				throw new IllegalArgumentException("Position to discard out of bounds.");
			hand.remove(positions[i]);
		}

		countUniqueFacesSuits();
		countFrequencies();
	}

	public Card get(int i) {
		return hand.get(i);
	}

	public int getCardDistances() {
		HandOfCards sortedHand = this.sorted();
		int distance = 0;
		for (int i = 1; i < hand.size(); i++) {
			distance += sortedHand.get(i).getFace() - sortedHand.get(i - 1).getFace();
		}
		return distance;
	}

	public ArrayList<Card> getCards() {
		return hand;
	}

	public HandOfCards getCardsOfSuit(int suit) {
		HandOfCards suitedHand = new HandOfCards();
		for (Card card : hand)
			if (card.getSuit() == suit)
				suitedHand.add(card);

		return suitedHand;
	}

	public int[] getFrequencies() {
		return faceFrequencies;
	}

	public ArrayList<Integer> getFrequenciesArr() {
		ArrayList<Integer> frequencies = new ArrayList<Integer>(14);
		frequencies.add(faceFrequencies[14]);
		for (int i = 2; i <= 14; i++)
			frequencies.add(faceFrequencies[i]);
		return frequencies;
	}

	public int getHighCard() {
		HandOfCards sortedHand = this.sorted();
		return sortedHand.get(sortedHand.size() - 1).getFace();
	}

	public int getLowCard() {
		HandOfCards sortedHand = this.sorted();
		return sortedHand.get(0).getFace();
	}

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

	public int getNHighCards() {
		int nHighCards = 0;
		for (int i = 11; i <= 14; i++)
			nHighCards += faceFrequencies[i];

		return nHighCards;
	}

	public int getUniqueFaces() {
		return uniqueFaces.size();
	}

	public int getUniqueSuits() {
		return uniqueSuits.size();
	}

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

	public void print() {
		System.out.println(hand);
	}

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

	public void replaceCards(Card card, int position) {
		if (position >= this.size() || position < 0)
			throw new IllegalArgumentException("Position to replace out of bounds.");
		hand.set(position, card);

		countUniqueFacesSuits();
		countFrequencies();
	}

	public int size() {
		return hand.size();
	}

	public void sortCards() {
		Collections.sort(hand);
	}

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