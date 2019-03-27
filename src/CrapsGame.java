//Joseph Verdugo 23592803
public class CrapsGame
{
    private int point = 0;
   
    /**
    *  Calculates the result of the next dice roll in the Craps game.
    *  The parameter total is the sum of dots on two dice.
    *  point is set to the saved total, if the game continues,
    *  or 0, if the game has ended.
    *  Returns 1 if player won, -1 if player lost,
    *  0 if player continues rolling.
    */
    public int processRoll(int total)
    {
        if( point == 0 )
        {
            if( total == 7 || total == 11 ) {
                point = 0;
                return 1;
            }

            else if( total == 2 || total == 3 || total == 12 ) {
                point = 0;
                return -1;
            }
            point = total; // If the game was not lost or won on the first dice roll then set point equal to total
            return 0;
        }
        else
            {
            if (total == point) {
                point = 0;
                return 1;
            } else if (total == 7) {
                point = 0;
                return -1;
            } else
                return 0;
        }
    }
  
    /**
    *  Returns the saved point
    */
    public int getPoint()
    {
        return point;
    }
}