public class Dealer
{
    private int dealerSum = 0;
    private int playerSum = 0;

    public int dealerDecides( int d, int p , Deck deck1 )
    {
        dealerSum = d; //Sets dealerSum to be equal to d
        playerSum = p; //Sets playerSum to be equal to p
        if ( dealerSum >= playerSum ) //If the dealer's sum is greater than the player's sum
        {
            return dealerSum; //Dealer doesn't draw cards and returns the dealerSum
        }
        else if ( dealerSum <= 17 ) //If the dealer's sum is below 17
        {
            while ( dealerSum <= 17 ) //While the dealer's sum is below 17
            {
                Card w = deck1.deal(); //Draws a new card
                if ( w.getValue() == 'A' ) //If the dealer draws an ace
                {
                    if ( dealerSum <= 10 ) //If the dealer's sum is less than or equal to 10
                        dealerSum += 11; //Dealer sets ace to be 11 points
                    else //Else
                        dealerSum += 1; //Dealer sets ace to be 1 point
                }
                dealerSum += w.getSum(w.getValue()); //Calls method getSum and passes argument w.getValue() to it, adds result to dealerSum
                if ( dealerSum >= 21 ) //If the dealer's sum is 21 or above
                    return dealerSum; //Returns dealerSum
                else //Else
                    continue; //Continues the loop
            }
        }
        else if ( dealerSum > 17 ) //If the dealer starts off with sum being more than 17
        {
            return dealerSum; //Returns the sum without drawing any new cards
        }
        return dealerSum; //If dealer has between 17-21 points after going through the loop, returns dealerSum
    }
}