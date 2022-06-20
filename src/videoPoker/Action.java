package videoPoker;

import java.util.ArrayList;

/**
 * @author Bernardo Silva 
 * @author Miguel Madeira
 * @author Vicente Silvestre
 *
 */
public class Action {

	/**
	 * Identifier of the type of action (first character)
	 */
	private char action;
	/**
	 * Amount of money to bet
	 */
	private int bet = 0;
	/**
	 * List of the indexes of the cards to hold
	 */
	private ArrayList<Integer> positions = null;

	/**
	 * Constructor method that assigns the action type, and initializes the array of positions of cards for the case of hold action
	 * @param _action char referring to the type of action (first letter)
	 */
	public Action(char _action) {
		if(_action != 'a' && _action != 'b' && _action !='d' &&
				_action != 'h' && _action != '$' && _action != 's')
			throw new IllegalArgumentException("Action " + _action + " is not valid.");
		action = _action;

		if (action == 'h')
			positions = new ArrayList<Integer>();
	}

	/**
	 * Adds the index of a card to hold in the array positions
	 * @param position index of the card to hold
	 */
	public void addPosition(int position) {
                if(position < 1 || position > 5)
                        throw new IllegalArgumentException("Invalid hold position " + position + ".");
		positions.add(position);
	}

	/**
	 * Gets the type of action
	 * @return char referring to the type of action
	 */
	public char getAction() {
		return action;
	}

	/**
	 * Gets the amount of money that is being betted
	 * @return amount of money that is being betted
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * Gets the list of the indexes of the cards to hold
	 * @return list of the indexes of the cards to hold
	 */
	public ArrayList<Integer> getPositions() {
		return positions;
	}

	/**
	 * Sets the amount of money to bet.
	 * @param _bet amount of money to bet
	 */
	public void setBet(int _bet) {
		bet = _bet;
	}

	/**
	 * Sets the list of the indexes of the cards to hold.
	 * @param positions list of the indexes of the cards to hold
	 */
	public void setPositions(ArrayList<Integer> positions) {
		this.positions = positions;
	}

	/**
	 * Returns a string representation of the object.
	 * @return string representation of the object
	 */
	public String toString() {
		String out = "" + action;
		if(bet != 0) out += " " + bet;
		if(positions != null)
			if(!positions.isEmpty()) 
				out += " " + positions;
		return out;
	}

}
