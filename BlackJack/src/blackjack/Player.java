package blackjack;

public class Player {
    // Fields
    private final String name;
    private int playerScore = 0;
    public Card[] playerCards = new Card[11];
    private boolean gotBlackjack;
    private boolean busted;
    private int cardsDrawn = 0;

    // Constructors
    public Player(String name) {
        this.name = name;
    }

    // AddCard Func
    public void addCard(Card e) {
        // Add the new card to the player array
        playerCards[cardsDrawn] = new Card(e.getSuit(), e.getRank(), e.getValue());
        // Increase the index to add the next card into the next position
        cardsDrawn++;
        // Increase player score
        playerScore += e.getValue();
    }

    public void setGotBlackjack(boolean gotBlackjack) {
        this.gotBlackjack = gotBlackjack;
    }

    public void setBusted(boolean busted) {
        this.busted = busted;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public boolean GotBlackjack() {
        return gotBlackjack;
    }

    public boolean isBusted() {
        return busted;
    }

}
