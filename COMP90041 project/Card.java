//package handvalue;

/** Class for card creation. A card has rank and a suit. Provides methods
 * for returning a card suit, a card rank and the value for 15s calculation.
 * @author Karen Qu*/

public class Card {
    /**stores the card's rank*/
    private final Rank rank;
    /**stores the card's suit*/
    private final Suit suit;
    
    /**Constructs a card. A card has a rank and suit.
     *@param    card   a string representation of a card of length 2
                       containing the desired rank and suit value*/
    Card(String card) {
        this.rank = new Rank(card.substring(0, 1));
        this.suit = Suit.valueOf(card.substring(1, 2));
    }
    /**@return rank of the card as Rank*/
    public Rank getRank() {
        return this.rank;
    }
    
    /**@see Rank#valueFor15s()*/
    public int getValueFor15s() {
        return this.rank.valueFor15s();
    }
    /**@return Suit as enum*/ 
    public Suit getSuit() {
        return this.suit;
    }
    
    /**@return (boolean) whether or not two cards are equal (they are equal
     * if their ranks and suits are equal)
     *@param    obj    a card object*/
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            if (this.rank.equals(((Card) obj).rank)
                    && this.suit.equals(((Card) obj).suit)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    /**@return rank and suit of the card as string*/
    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}
