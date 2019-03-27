public class Die
{
  private int numDots;

  // Sets numDots to a random integer from 1 to 6
  public void roll()
  {
    numDots = (int)(6 * Math.random()) + 1;
  }

  // Returns numDots
  public int getNumDots()
  {
    return numDots;
  }
}
