/**
 *
 * @author Ethan
 * @version 1.0
 *
 */
import java.util.Scanner; //Imports Scanner class

public class Blackjack  {
    /**
     * main method
     * @param args from user input
     */
    public static void main(String[] args) {
        String r = "y";

        Scanner input = new Scanner(System.in); //Creates Scanner object input
        Scanner input2 = new Scanner(System.in); //Creates Scanner object input2
        Scanner input3 = new Scanner(System.in); //Creates Scanner object input3

        int playerSum; //Defines and initializes variable playerSum as 0
        int dealerSum; //Defines and initializes variable dealersum as 0

        int index;
        int index1;

        Deck deck = new Deck(); //Creates Deck object deck
        Dealer dealer = new Dealer(); //Creates Dealer object dealer

        deck.createDeck(); //Calls method createDeck in Deck class
        System.out.println(); //Generates linefeed

        do {
            playerSum = 0;
            dealerSum = 0;
            index = 0;
            index1 = 0;

            deck.shuffle(); //Calls method shuffle in Deck class
            System.out.println(); //Generates linefeed
            
            System.out.print( "Welcome to the Blackjack table. ");  //Welcomes user to the game
            System.out.print( "\nYour first cards are " ); //Generates linefeed and then prints 

            for (int i = 0; i < 2; i++) {
                Card c = deck.deal(); //Gives player a card
                System.out.print( Character.toString(c.getSuit()) + "-" + Character.toString(c.getValue()) + " " ); //Prints the card's suit and then its rank by using methods getSuit and getValue in Card class
                if (c.getValue() == 'A') {
                    System.out.println( "\nYou have an ace, would you like it to be worth 1 or 11" ); //Asks user if they would like the value of the ace to be 1 or 11
                    playerSum += input.nextInt(); //Stores user's input as variable input and adds it to the playerSum
                }
                playerSum += c.getSum(c.getValue()); //If card's rank isn't an Ace, it calls method getSum of the Card class and sends the response to calling the method getValue in Card class
            }
            if ( playerSum == 21 ) {
                System.out.println( "The player has blackjack and has won. Congratulations "); //Generates print saying the player has blackjack and has won
            } else {
                System.out.println(); //Generates linefeed

                for (int l = 0; l < 2; l++) {
                    Card d = deck.deal(); //Deals the dealer a new card
                    if (d.getValue() == 'A') //If the dealer's card rank is an ace
                        dealerSum += 1; //Adds only one to the dealer's sum
                    dealerSum += d.getSum(d.getValue()); //Adds the value of the new card to the dealer
                    if (dealerSum == 21) {
                        System.out.println( "The dealer has blackjack and has won. Game over" ); //Prints that the dealer has reached blackjack and has won
                        break;
                    }
                    if (l == 1) //Designed to give the dealer two cards but only show one
                        break; //Breaks out of this loop
                    System.out.print( "The dealer's first card is " + Character.toString(d.getSuit()) + "-" + Character.toString(d.getValue()) + " " ); //Should only print the dealer's first card
                }         
                
                System.out.print( "\nWould you like to hit or stay? " ); //Prompts user if they would like to either hit or stay
                String x = input2.nextLine(); //Stores user's input as variable x
                if (x.equals("hit")) {
                    for (int o = 0; o < 48; o++) {
                        Card a = deck.deal(); //Deals a new card
                        System.out.println( "Your new card is " + Character.toString(a.getSuit()) + "-" + Character.toString(a.getValue()) + " " ); //Tells player their new card
                        playerSum += a.getSum(a.getValue()); //Adds card's value to playerSum
                        if (a.getValue() == 'A') {
                            System.out.println( "\nYou have an ace, would you like it to be worth 1 or 11" ); //Prompts player for if they want to make the ace worth 1 or 11 points
                            playerSum += input.nextInt(); //Adds user's response to playerSum
                        }
                        if (playerSum == 21) {
                            System.out.println( "The player has blackjack and has won. Congratulations "); //Prints saying the player has blackjack and has won
                            break;
                        } else if (playerSum > 21) {
                            System.out.println( "The player has busted. The dealer has won."); //Prints message saying the player has busted and the dealer has won
                            break;
                        }

                        System.out.print( "Would you like to hit or stay? " ); //Prompts user if they would like to hit or stay 
                        String w = input3.nextLine(); //Stores user response as String w

                        if (w.equals("hit")) //If the user inputs hit
                            continue; //Continues with the loop

                        else if (w.equals("stay")) {
                                index = dealer.dealerDecides( dealerSum , playerSum, deck ); //Passes arguments dealerSum, playerSum, and deck to method dealerDecides in Dealer class and sets index as the result
                                gameResults( index , playerSum ); //Passes arguments index (dealerSum) and playerSum to method gameResults
                                break; //Breaks out of the loop
                            }
                    }
                }
                else if (x.equals("stay")) {
                    index1 = dealer.dealerDecides( dealerSum , playerSum, deck ); //Passes arguments dealerSum, playerSum, and deck to method dealerDecides in Dealer class and sets index1 as the result
                    gameResults( index1 , playerSum ); //Passes argumnts index1 (dealerSum) and playerSum to method gameResults
                }
            }

            r = continuePlaying(); //Calls method continuePlaying()
        } while (r.equals("yes")); //Keep looping while the player enters "yes"
    }

    /**
     * gameResults method
     * @param i from main method
     * @param p from main method
     */
    public static void gameResults(int i, int p) {
        int dealerValues = i; 
        int playerValues = p;
        if ( dealerValues > 21 ) //If the dealer goes over 21
            System.out.println( "The dealer had " + dealerValues + " points and has busted. The player has won. "); //Prints message saying the dealer has busted and the player has won
        else if ( dealerValues > playerValues ) //If the dealer has a higher score than the player
            System.out.println( "The dealer has " + dealerValues + " points and the player had " + playerValues + " points. The dealer has won." ); //Prints both dealer's and player's score and says the dealer has won
        else if ( dealerValues < playerValues ) //If the dealer has a lower score than the player
            System.out.println( "The dealer has " + dealerValues + " points and the player had " + playerValues + " points. The player has won." ); //Prints both dealer's and player's score and says the player has won
        else if ( dealerValues == playerValues ) //If the dealer has the same score as the player
            System.out.println( "The dealer has " + dealerValues + " points and the player had " + playerValues + " points. The player and dealer have tied." ); //Prints both dealer's and player's score and says they have tied
    }
    
    /**
     * continuePLaying method
     * @return String
     */
    public static String continuePlaying() {
        Scanner sc = new Scanner(System.in); //Creates Scanner object sc

        System.out.println( "Does the user want to play again? yes/no "); //Prompts user for typing either "yes" or "no"
        String x = sc.nextLine(); //Saves user's response as x
        return x; //Returns x
    }
}
