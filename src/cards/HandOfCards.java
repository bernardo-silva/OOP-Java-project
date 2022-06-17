package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class HandOfCards {
	protected ArrayList<Card> hand;
	private LinkedHashSet<Integer> uniqueSuits;
	private LinkedHashSet<Integer> uniqueFaces;
	private int[] cardFrequencies;

	private int cardDistances;

	public HandOfCards(ArrayList<Card> _hand) {
//		hand = new ArrayList<Card>(_hand.size());
//		if(_hand.size() > 5) throw new IllegalArgumentException("The hand must have less than 5 cards");
		hand = _hand;
		uniqueSuits = new LinkedHashSet<Integer>(5);
		uniqueFaces = new LinkedHashSet<Integer>(5);
		cardFrequencies = new int[15];
		sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
	}

	public HandOfCards(int size) {
		hand = new ArrayList<Card>(size);
		for (int i = 0; i < size; i++) {
			hand.add(null);
		}
		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		cardFrequencies = new int[15];
	}

	public HandOfCards(String cardString){
		Scanner scanHand = new Scanner(cardString);
		hand = new ArrayList<Card>(5);
		scanHand.useDelimiter(" ");
		
		for (int i=0; scanHand.hasNext(); i++){
			if (i>=5){
				System.out.println("Hand can't have more than 5 cards");
				break;
			}
			Card card = new Card(scanHand.next());
			hand.add(card);
		}

		scanHand.close();	
		uniqueSuits = new LinkedHashSet<Integer>(5);
		uniqueFaces = new LinkedHashSet<Integer>(5);
		cardFrequencies = new int[15];
		sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
	}

	public HandOfCards(String[] cardString){
		hand = new ArrayList<Card>(5);
		
		for (int i=0; i < cardString.length; i++){
			if (i>=5){
				System.out.println("Hand can't have more than 5 cards");
				break;
			}
			Card card = new Card(cardString[i]);
			hand.add(card);
		}
	
		uniqueSuits = new LinkedHashSet<Integer>(5);
		uniqueFaces = new LinkedHashSet<Integer>(5);
		cardFrequencies = new int[15];
		sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
	}

	public void add(ArrayList<Card> cards) {
		if (hand.size() + cards.size() > 5)
			throw new IllegalArgumentException("The hand must have less than 5 cards");
		else {
			hand.addAll(cards);
			sortCards();
		}
	}

	public void add(Card card) {
		if (hand.size() + 1 > 5)
			throw new IllegalArgumentException("The hand must have less than 5 cards");
		else {
			hand.add(card);
			sortCards();
		}
	}

	private void countFrequencies() {
		Arrays.fill(cardFrequencies, 0);
		for (Card card : hand) {
			cardFrequencies[card.getFace()]++;
		}
	}

	private void countUniqueFacesSuits() {
		for (Card card : hand) {
			uniqueSuits.add(card.getSuit());
			uniqueFaces.add(card.getFace());
		}
	}

	public void discard(int position) {
		if (position > 4 || position < 0)
			throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
		hand.remove(position);
	}

	public void discard(int[] positions) {
		Arrays.sort(positions);
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] > 4 || positions[i] < 0)
				throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
			hand.remove(positions[i] - i);
		}
	}
	public Card get(int i) {
		return hand.get(i);
	}
	public int size() {
		return hand.size();
	}

	public int getCardDistances() {
		return cardDistances;
	}

	public int[] getFrequencies() {
		return cardFrequencies;
	}

	public int getLowCard() {
		return hand.get(0).getFace();
	}

	public int getUniqueFaces() {
		return uniqueFaces.size();
	}

	public int getUniqueSuits() {
		return uniqueSuits.size();
	}

	private int measureCardDistances() {
		int distance = 0;
		for (int i = 1; i < hand.size(); i++) {
			distance += hand.get(i).getFace() - hand.get(i - 1).getFace();
		}
		return distance;
	}

	public void print() {
		System.out.println(hand);
	}

	public void replaceCards(ArrayList<Card> cards, ArrayList<Integer> positions) {
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i) > 4 || positions.get(i) < 0)
				throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
			hand.set(positions.get(i), cards.get(i));
		}
		sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
	}

	public void replaceCards(ArrayList<Card> cards, int[] positions) {
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] > 4 || positions[i] < 0)
				throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
			hand.set(positions[i], cards.get(i));
		}
		sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
	}

	public void replaceCards(Card card, int position) {
		if (position > 4 || position < 0)
			throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
		hand.set(position, card);
		sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
	}

	public void sortCards() {
		Collections.sort(hand);
	}

	@Override
	public String toString() {
		return "" + hand;
	}

	public ArrayList<Card> getCards() {
		return hand;
	}

	public ArrayList<Integer> match(HandOfCards hand2) {
		if (hand2.getUniqueSuits() != 1)
			return null;

		ArrayList<Integer> positions = new ArrayList<Integer>(5);
		ArrayList<Card> cards2 = hand2.getCards();

		if (cards2.get(0).getSuit() == 4) { // Any
			System.out.println("Testing any.");
			for (int i = 0; i < hand.size(); i++) {
				for (int j = 0; j < cards2.size(); j++) {
					if (hand.get(i).getFace() == cards2.get(j).getFace()) {
						positions.add(i + 1);
						cards2.remove(j);
						break;
					}
				}
			}
		} else { // Some
			System.out.println("Testing some.");
			ArrayList<Integer> newPositions = new ArrayList<Integer>(5);

			for (int suit = 0; suit < 4; suit++) {
				for (int i = 0; i < hand.size(); i++) {

					if (hand.get(i).getSuit() != suit) continue;

					for (int j = 0; j < cards2.size(); j++) {
						System.out.println("Comparing " + hand.get(i) + " with " + cards2.get(j));
						if (hand.get(i).getFace() == cards2.get(j).getFace()) {
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
		}

		return positions;
	}
}
