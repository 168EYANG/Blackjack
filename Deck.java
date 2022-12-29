import java.util.*; //Imports all

public class Deck
{
    private static char cards[] = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T' ,'J' ,'Q' ,'K'}; //Creates array cards[] for different ranks
    private static char suits[] = {'C', 'D', 'H', 'S'}; //Creates array suits[] for all suits 

    public Card deck[] = new Card[52]; //Creates new array deck[] with 52 elements

    private int index = 0; 

    public int getDeckLength() //Method getDeckLength()
    {
        return deck.length; //Returns the length of deck[]
    }

    public void createDeck()
    {
        for ( int x = 0 ; x < 4 ; x++ ) //Loops 4 times for each suit
        {
                for ( int y = 0 ; y < 13 ; y++ ) //Loops 13 times for each rank
                {
                    deck[index] = new Card(cards[y], suits[x]); //Creates object Card and passes arguments cards[y] and suits[x] to it
                    index++; //Increases index
                }
        }

    }

    public void shuffle()
    {
        Random r = new Random(); //Creates Random object r

        for (int i = 0; i < 104; i++) //Shuffles deck 104 times since it loops through 104 times
        {
            int first = r.nextInt(deck.length - 1); //Generates random number from 0 to 51
            int second = r.nextInt(deck.length - 1); //Generates random number from 0 to 51

            Card temp = deck[first]; //Uses variable temp to store deck[first]

            deck[first] = deck[second]; //Swaps deck[first] and deck[second]
            deck[second] = temp; //Sets deck[second] to be temp
        }

        index = 0;
    }

    public Card deal()
    {
        return deck[index++]; //Deals the cards 
    }
}