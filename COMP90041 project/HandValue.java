//package handvalue;
import java.util.*;

/** Reads cards in as a string array argument and scores them for Cribbage.
 *@author Karen Qu*/

public class HandValue {
    /**
     * Main method which scores command line card input
     * @param   args    string array containing input cards expressed in a 
     *                  string consisting of 2 characters. 
     *                      Character 1- Rank: uppercase A for Ace, 
     *                      K for King, Q for Queen, J for Jack, T for Ten or 
     *                      digits between 2-9 for ranks 2-9
     *                      Character 2- Suit: C for Clubs, D for Diamonds, 
     *                      H for Hearts or S for Spades
     */
    public static void main(String[] args) {
        
        /**Processes player relevant cards*/
        Hand cards = new Hand(args);  
        /**generates combinations from all player relevant cards*/
        Card[][] cardCombinations = cards.getAllCombos();
        /**generates a frequency map for all ranks*/
        Map<Rank, Long> rankFrequency = helperFunctions
                .getFrequencyMap(cards.getAllCards());
        
        /**generates a list of all unique ranks*/
        List<Rank> ranksList = new ArrayList<>(rankFrequency.keySet());
        Collections.sort(ranksList);
        /**Removes ranks with a frequency of less than 2*/
        helperFunctions.removeLessThanTwo(rankFrequency);
        
        /*gets the number of 15s*/
        int number15s = helperFunctions.get15s(cardCombinations);
        /*gets the number of pairs obtainable*/
        int pairs = helperFunctions.getPairs(rankFrequency,
                cards.getAllCards());
        /*gets the number of combinations resulting in a run and the length
        of the run*/
        int[] runs = helperFunctions.getRuns(rankFrequency, ranksList);
        /**Determines if the start card and hand results in a flush*/
        boolean isFlushWithStart = helperFunctions.isFlush(cards.getAllCards());
        /**Determines if the hand results in a flush*/
        boolean isFlushWithHand = helperFunctions.isFlush(cards.getHandCards());
        /**Determines if there is a Jack in the hand the same suit as the start
         * card*/
        boolean isNob = helperFunctions.isOneForHisNob(cards.getHandCards(),
                cards.getStartCard());
        
        /**Calculates the Cribbage score*/
        int score = helperFunctions.getScore(number15s, pairs, runs,
                isFlushWithStart, isFlushWithHand, isNob);
        
        System.out.println(score);
        

    }
}
