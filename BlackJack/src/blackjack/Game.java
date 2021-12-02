package blackjack;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner input = new Scanner(System.in);
    public Player[] players = new Player[4];
    public Card[] cards = new Card[52];
    public int highestValidScore = 0;

    public void generateCardDeck() {

        int cardValue = 1;
        int cardRank = 0;

        for (int i = 0; i < 52; i++) {
            if (i < 13) {
                if (cardValue > 10) {
                    cardValue = 10;
                }
                cards[i] = new Card(0, cardRank, cardValue);
                cardRank++;
                cardValue++;
            } else if (i < 26) {
                if (i == 13) {
                    cardValue = 1;
                    cardRank = 0;
                }
                if (cardValue > 10) {
                    cardValue = 10;
                }
                cards[i] = new Card(1, cardRank, cardValue);
                cardRank++;
                cardValue++;
            } else if (i < 39) {
                if (i == 26) {
                    cardValue = 1;
                    cardRank = 0;
                }
                if (cardValue > 10) {
                    cardValue = 10;
                }
                cards[i] = new Card(2, cardRank, cardValue);
                cardRank++;
                cardValue++;
            } else if (i < 52) {
                if (i == 39) {
                    cardValue = 1;
                    cardRank = 0;
                }
                if (cardValue > 10) {
                    cardValue = 10;
                }
                cards[i] = new Card(3, cardRank, cardValue);
                cardRank++;
                cardValue++;
            }
        }
    }

    public Card drawRandomCard() {
        Random rand = new Random();
        int randomChoice = rand.nextInt(52);
        while (cards[randomChoice] == null) {
            randomChoice = rand.nextInt(52);
        }
        Card drawnCard = new Card(cards[randomChoice]);
        cards[randomChoice] = null;
        return drawnCard;
    }

    public void setPlayersInfo() {
        String playerName;
        for (int i = 0; i < 3; i++) {
            // Set player name
            System.out.print("Enter player " + (i + 1) + " name: ");
            playerName = input.next();
            players[i] = new Player(playerName);
            // Add two card to player array
            players[i].addCard(drawRandomCard());
            updateMaxScore();
            players[i].addCard(drawRandomCard());
            updateMaxScore();
            System.out.println(players[i].getName() + "'s Initial Score: " + players[i].getPlayerScore());
        }
        players[3] = new Player("Dealer");
        players[3].addCard(drawRandomCard());
        updateMaxScore();
        players[3].addCard(drawRandomCard());
        updateMaxScore();
        System.out.println(players[3].getName() + "'s Initial Score: " + players[3].getPlayerScore());
    }

    public void updateMaxScore() {
        int[] scores = new int[4];
        if (players[0] != null && players[0].getPlayerScore() <= 21) {
            scores[0] = players[0].getPlayerScore();
        } else {
            scores[0] = 0;
        }

        if (players[1] != null && players[1].getPlayerScore() <= 21) {
            scores[1] = players[1].getPlayerScore();
        } else {
            scores[1] = 0;
        }
        if (players[2] != null && players[2].getPlayerScore() <= 21) {
            scores[2] = players[2].getPlayerScore();
        } else {
            scores[2] = 0;
        }
        if (players[3] != null && players[3].getPlayerScore() <= 21) {
            scores[3] = players[3].getPlayerScore();
        } else {
            scores[3] = 0;
        }
        Arrays.sort(scores);
        highestValidScore = scores[3];
    }
}
