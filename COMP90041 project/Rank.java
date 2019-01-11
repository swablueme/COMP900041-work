//package handvalue;
import java.util.*;

/**
* Rank implementation for playing cards in a cribbage game
* Supports the ability to return the value for 15s calculation and a method
* to return a value to return values used for run calculations as well as
* methods for comparison.
* @author Karen Qu 
*/

public class Rank implements Comparable<Rank> {

    /**list of  all possible ranks*/
    private final ArrayList<String> possibleRanks = new ArrayList(Arrays.asList
        ("A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"));
    
    /**Index of rank as string*/
    private final int indexValue;   
    
    /*Rank as string abbreviation*/
    private final String rankString;
    
    /**Constructs a rank
     *@param    rank    a string of length 1 to be used to construct a rank*/
    Rank(String rank) {
        this.indexValue = possibleRanks.indexOf(rank);
        this.rankString = rank;
    }

    /**@return int value of card used in counting the number of 15s
     *for example A = 1, K, Q, J = 10*/
    public int valueFor15s() {
        return Math.min(this.indexValue + 1, 10);
    }

    /**@return card's value as an int index*/
    public int runValue() {
        return this.indexValue + 1;
    }
    
    /**@return string representation of a card rank*/
    @Override
    public String toString() {
        return this.rankString;
    }
    
    /**@return a boolean expressing whether or not two Rank objects are equal
     *@param    obj    the rank to compare the current rank with*/
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rank) {
            if ((this.toString()).equals(obj.toString())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    /**@return int value used in rank equality decisions*/
    @Override
    public int hashCode() {
        return this.indexValue;
    }
    
    /**@return int comparing the two ranks
     *@param    other   the other rank taking part in the comparison*/
    @Override
    public int compareTo(Rank other) {
        return this.indexValue - other.indexValue;
    }
}
