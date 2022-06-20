package videoPoker;

import java.util.ArrayList;

import playingCards.HandOfCards;
/**
 * This interface allows the use of different strategies without changing 
 * anything else in the program.
 * Defines the methods to be overridden for each game strategy/variant.
 *
 * @author Bernardo Silva 
 * @author Miguel Madeira
 * @author Vicente Silvestre
 *
 */
public interface Strategy {
	/**
         * Returns the positions of the cards that should be held given some
         * strategy.
         * @param hand a hand with cards
	 * @return the positions of the cards to hold
	 */
	public ArrayList<Integer> getOptimalStrategy(HandOfCards hand);

}
