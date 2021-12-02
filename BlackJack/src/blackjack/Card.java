package blackjack;

public class Card {
    // All the Attributes
     private final int suit;
     private final int rank;
     private final int value;

    // Constructor to set the values
    public Card(int suit, int rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    // Copy Constructor
    public Card(Card e) {
        this.suit = e.suit;
        this.rank = e.rank;
        this.value = e.value;
    }

    // Getters
    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }


}
