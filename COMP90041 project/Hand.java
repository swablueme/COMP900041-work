//package handvalue;
import java.util.*;
/** Class for storing the player's cards. Provides methods for returning cards 
 * with or without the start card and can produce card combinations
 * @author Karen Qu*/

public class Hand {
    /**Array to store all cards relevant to the player including the start
     card*/
    private final Card[] cards = new Card[5];
    
    /*constructor creating cards from a string array*/
    Hand(String[] cards) {
        for (int i = 0; i < cards.length; i++) {
            this.cards[i] = new Card(cards[i]);
        }
    }

    /**@return cards comprising the hand*/
    public Card[] getHandCards() {
        return Arrays.copyOfRange(cards, 0, 4);
    }

    /**@return the start card*/
    public Card getStartCard() {
        return cards[4];
    }

    /**@return all cards including the hand and start cards*/
    public Card[] getAllCards() {
        return cards.clone();
    }

    /**@return all card combinations from the hand and the start card
     * except the empty combination*/
    public Card[][] getAllCombos() {
        Card[][] combos = Combinations.combinations(getAllCards());
        return combos[0].length == 0
                ? Arrays.copyOfRange(combos, 1, combos.length) : combos;
    }

    /**@return string representation of all cards held*/
    @Override
    public String toString() {
        return "Hand cards: " + Arrays.deepToString(getHandCards())
                + " | Start card: " + getStartCard().toString();
    }
}
