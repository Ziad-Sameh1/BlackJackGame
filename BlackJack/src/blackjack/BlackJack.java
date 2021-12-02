package blackjack;

import java.util.Scanner;

public class BlackJack {
    static Game ourGame = new Game();
    static GUI gui = new GUI();

    public static void main(String[] args) {
        ourGame.generateCardDeck();
        ourGame.setPlayersInfo();
        gui.runGUI(ourGame.cards, ourGame.players[0].playerCards, ourGame.players[1].playerCards, ourGame.players[2].playerCards, ourGame.players[3].playerCards);
        startingTheGame();
        findTheWinner();
    }

    static void startingTheGame() {
        Scanner input = new Scanner(System.in);
        int choice;
        Card drawenCard;
        for (int i = 0; i < 4; i++) {
            if (i < 3) {
                System.out.println("----- Player " + (i + 1) + " turn -----");
                while (true) {
                    System.out.print("-Hit(press 1) -Stand(press 2): ");
                    choice = input.nextInt();
                    while (choice != 1 && choice != 2) {
                        System.out.println("\nPlease Enter a Correct Answer: ");
                        choice = input.nextInt();
                    }
                    if (choice == 1) {
                        drawenCard = ourGame.drawRandomCard();
                        ourGame.players[i].addCard(drawenCard);
                        ourGame.updateMaxScore();
                        gui.updatePlayerHand(drawenCard, i);
                        checkIfBusted(ourGame.players[i]);
                        checkIfBlackjack(ourGame.players[i]);
                        System.out.println("Your Score is: " + ourGame.players[i].getPlayerScore());
                        System.out.println(ourGame.highestValidScore);
                        if (ourGame.players[i].GotBlackjack()) {
                            System.out.println("You Got a Blackjack");
                            break;
                        }
                    } else {
                        break;
                    }
                    if (ourGame.players[i].isBusted()) {
                        System.out.println("Player " + (i + 1) + " Busted!");
                        ourGame.players[i].setPlayerScore(0);
                        ourGame.updateMaxScore();
                        break;
                    }
                }
            } else if (i == 3) {
                if (ourGame.players[0].isBusted() == true && ourGame.players[1].isBusted() == true && ourGame.players[2].isBusted() == true) {
                    System.out.println("----- Dealer's turn -----");
                } else {
                    System.out.println("----- Dealer's turn -----");
                    while (true) {
                        if (ourGame.players[i].GotBlackjack()) {
                            System.out.println("Dealer Got a Blackjack");
                            break;
                        } else if (ourGame.players[i].isBusted()) {
                            System.out.println("Dealer Busted!");
                            break;
                        }
                        // if the dealer has the highest score and the no player else has the same score
                        else if (ourGame.players[0].getPlayerScore() < ourGame.highestValidScore && ourGame.players[1].getPlayerScore() < ourGame.highestValidScore && ourGame.players[2].getPlayerScore() < ourGame.highestValidScore && ourGame.players[i].getPlayerScore() == ourGame.highestValidScore) {
                            break;
                        }
                        drawenCard = ourGame.drawRandomCard();
                        ourGame.players[i].addCard(drawenCard);
                        ourGame.updateMaxScore();
                        gui.updateDealerHand(drawenCard, ourGame.cards);
                        checkIfBusted(ourGame.players[i]);
                        checkIfBlackjack(ourGame.players[i]);
                        System.out.println("Dealer Score is: " + ourGame.players[i].getPlayerScore());

                    }
                }
            }
        }
    }

    static void checkIfBusted(Player player) {
        if (player.getPlayerScore() > 21) {
            player.setBusted(true);
        }
    }

    static void checkIfBlackjack(Player player) {
        if (player.getPlayerScore() == 21) {
            player.setGotBlackjack(true);
        }
    }

    static void findTheWinner() {
        int highestScorePosition = 0;
        int numberOfPlayersGotSameHighScore = 0;
        for (int i = 0; i < 4; i++) {
            if (ourGame.players[i].getPlayerScore() == ourGame.highestValidScore) {
                highestScorePosition = i;
                numberOfPlayersGotSameHighScore++;
            }
        }
        if (numberOfPlayersGotSameHighScore == 1) {
            if (highestScorePosition == 3) {
                System.out.println("Dealer won!");
                System.out.println("------ Game End:) ------ ");
            } else {
                System.out.println("Player " + (highestScorePosition + 1) + " won!");
                System.out.println("------ Game End:) ------ ");
            }
        } else if (numberOfPlayersGotSameHighScore > 1) {
            System.out.println("PUSH");
            System.out.println("------ Game End:) ------ ");
        }
    }
}
