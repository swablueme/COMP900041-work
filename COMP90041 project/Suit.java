//package handvalue;
/**
* Suit implementation for playing cards in a cribbage game
* @author Karen Qu 
*/

public enum Suit {
    /*Suits expressed as enums*/
    
    C, D, H, S;
    /*Returns card rank as a string*/
    public String toString() {
        return this.name();
    }
}
