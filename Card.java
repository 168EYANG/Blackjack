public class Card
{
    private char suit;
    private char value;

    public Card( char v, char s )
    {
        suit = s; //Sets card's suit as s 
        value = v; //Sets card's value as v
    }

    public char getSuit()
    {
        return suit; //Returns the suit of the card
    }

    public char getValue()
    {
        return value; //Returns the rank/value of the card
    }

    public int getSum( char rank ) 
    {
        int Sum = 0; //Defines and initializes Sum as 0
        switch ( rank ) //Switch if rank is 
        {
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9': //If rank is 2-9
            Sum += Character.getNumericValue( rank ); //Adds to the sum the numeric value of the char
            break; //Break
        case 'T':
        case 'J':
        case 'Q':
        case 'K': //If the rank is a T, J, Q, or K
            Sum += 10; //Adds ten to the sum
            break; //Break
        }  
        return Sum; //Returns the Sum
    }
}