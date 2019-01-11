//package handvalue;

import java.util.*;
import java.util.stream.*;

/**Helper functions assisting in score calculation.
 * @author Karen Qu */

public class helperFunctions {
    /**Totals up the value of cards from each card combination and 
     *counts the number of 15s that result
     *@param cardsAndStart a multidimensional array of the Card object 
     *                     each subarray contains a card combination to be
     *                     totalled
     *@return the int count of 15s found in all the combinations */
    public static int get15s(Card[][] cardsAndStart) {
        return ((Long) IntStream.range(0, cardsAndStart.length)
                .map(cardArray -> Arrays.stream(cardsAndStart[cardArray])
                .mapToInt(card -> card.getValueFor15s())
                .sum())
                .filter(totalCardValue -> totalCardValue == 15)
                .count()).intValue();
    }
    
    /**Finds the frequency of each card rank.
     *@param cards   an array of Card objects to be processed (including the
     *              start card and the cards in the hands)
     *@return    a Map in the format <Rank, frequency of the Rank (as Long)>*/
    public static Map<Rank, Long> getFrequencyMap(Card[] cards) {
        Map frequencyMap = Arrays.stream(cards)
                        .collect(Collectors.groupingBy(card -> card.getRank(),
                        Collectors.counting()));
        return frequencyMap;
    }
    
    /**Removes ranks from the frequency map if the frequency is less than two
     * @param frequencyMap a Map in the format <Rank, frequency of the Rank
     *                     as Long>
     * @return processed frequency map */
    public static Map<Rank, Long> removeLessThanTwo(Map<Rank, Long> 
                            frequencyMap) {
        frequencyMap.values().removeIf(frequency -> frequency.intValue() < 2);
        return frequencyMap;
    }
    
    /**Finds the number of pairs present in the input cards
     * @param rankFrequency a Map in the format <Rank, frequency of the Rank
     *                     as Long>
     * @param cards an array of Card objects to be processed (including the
     *              start card and the cards in the hands)
     * @return the total number of pairs as an int*/
    public static int getPairs(Map<Rank, Long> rankFrequency, Card[] cards) {
        return rankFrequency.values()
                .stream()
                .mapToInt(frequency -> frequency.intValue())
                //4 of a kind = 6 pairs (1+2+3), 3 of a kind = 3 pairs (1+2) etc
                .map(frequency -> IntStream.range(1, frequency).sum())
                .sum();

    }

    /**Obtains the multiplication factor required to calculate the new number
     * of combinations based on how many cards have the same rank
     * @param rankFrequency a Map in the format <Rank, frequency of the Rank
     *                     as Long>
     * @param value the rank whose frequency we seek
     * @return int of the multiplication factor*/
    public static int getRankCombos(Map<Rank, Long> rankFrequency, Rank value) {
        return rankFrequency.get(value) != null ? rankFrequency.get(value)
                .intValue() * 1 : 1;
    }
    
    /**Finds the number of combinations that can result in a run and the length
     * of that run
     * @param rankFrequency a Map in the format <Rank, frequency of the Rank
     *                      as Long>
     * @param ranks         a sorted list of unique ranks from the start 
     *                      and the hand cards
     * @return int[] of size 2. The first value corresponds to how many
     * combinations result in the run (0 if the length of the run is
     * insufficient to score) and the second value corresponds to the length
     * of the run (0 if insufficient to score) */
    public static int[] getRuns(Map<Rank, Long> rankFrequency, 
                          List<Rank> ranks) {
        
        /**a list of ranks comprising the run*/
        List<Rank> run = new ArrayList<>();
        /**the number of combinations resulting in a run*/
        int comboMultiplier = getRankCombos(rankFrequency, ranks.get(0));
        //add the first rank to the list
        run.add(ranks.get(0));
        
        for (int i = 1; i <= ranks.size() - 1; i++) {
            /**current rank to be potentially added*/
            int currentValue = ranks.get(i).runValue();
            /**the last rank added*/
            int lastRunValue = run.get(run.size() - 1).runValue();
            
            //runs consist of values adjacent to one another
            if (lastRunValue + 1 == currentValue) {
                run.add(ranks.get(i));
                comboMultiplier *= getRankCombos(rankFrequency, ranks.get(i));
            //if the size of the run is insufficient and it's non adjacent
            } else if (run.size() < 3) {
                //try and make a new run comprising of the current rank
                run.clear();
                run.add(ranks.get(i));
                //possible run combinations from the current rank
                comboMultiplier = getRankCombos(rankFrequency, ranks.get(i));
            }

        }
        return new int[]{run.size() < 3 ? 0 : comboMultiplier,
            run.size() < 3 ? 0 : run.size()};
    }
    
    /**Checks if the cards are a flush (if they have the same suit)
     * @param cards set of cards to check. May be just the hand cards or the
     *              hands plus the start card
     * @return boolean true if is flush, false if is not*/
    public static boolean isFlush(Card[] cards) {
        Suit targetSuit = cards[0].getSuit();
        return Arrays.stream(cards)
                     .map(card -> card.getSuit())
                     .allMatch(cardSuit -> cardSuit == targetSuit);
    }
    
    /**Checks if the One for his nob condition is true (if the hand has a 
     * jack the same suit as the start card)
     * @param handCards Card array representing the cards in the hand
     * @param startCard the Card object representing the start card
     * @return boolean true if the nob condition is met, false if it is not */
    public static boolean isOneForHisNob(Card[] handCards, Card startCard) {
        Suit targetSuit= startCard.getSuit();
        return Arrays.stream(handCards)
                     .anyMatch(card -> 
                             card.equals(new Card("J"+targetSuit.toString()))); 
    }
    
    /**Calculates the cribbage score as an int from parameters
     * @param number15s                 int for the number of card combinations 
     *                                  which result in 15
     * @param pairs                     int for the number of possible pairs
     * @param runs                      int[] of size 2. 
     *                                      value 1 - number combos from the run 
     *                                      value 2 - run length
     *                                      both 0 if the run isn't long enough                      
     * @param isFlushIncludingStart     boolean if the start and hands are a
     *                                  flush
     * @param isFlushHands              boolean if the hands are a flush
     * @param isNob                     if there is a jack in the hands the 
     *                                  same suit as the start card
     * @return total score expressed as an int*/
    public static int getScore(int number15s, int pairs, int[] runs, 
        boolean isFlushIncludingStart, boolean isFlushHands, boolean isNob) {
            //stores the score received from flushes
            int flushScore = 0;
            if (isFlushIncludingStart == true) {
                flushScore=5;       
            }
            else if (isFlushHands == true) {
                flushScore=4; 
            }
            //one point if a nob exists
            int oneForHisNobScore = isNob == true ? 1 : 0;
            //score calculation
            return 2*number15s + pairs*2 + runs[1]*runs[0] + 
                    flushScore + oneForHisNobScore;
    }
}
