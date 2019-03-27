//Joseph Verdugo 23592803
// Represents the craps table with two rolling dice

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CrapsTable extends JPanel
    implements ActionListener
{
    private RollingDie die1, die2;
    private final int delay = 20;
    private Timer clock;
    private CrapsGame game;
    private DisplayPanel display;

    // Constructor
    public CrapsTable(DisplayPanel displ)
    {
      setBackground(Color.green);
      setBorder(new LineBorder(Color.ORANGE.darker(), 3));
      display = displ;
      game = new CrapsGame();
      die1 = new RollingDie();
      die2 = new RollingDie();
      clock = new Timer(delay, this);
    }

    // Rolls the dice (called when the "Roll" button
    // is clicked)
    public void rollDice()
    {
        // You write code here
            int creditAmount = Integer.parseInt( display.getCredit().getText() );
            int betAmount = Integer.parseInt( display.getBet().getText() );
            if( betAmount > 0 && betAmount <= creditAmount )
            {
                // The following should only run if there is a valid bet
                RollingDie.setBounds(3, getWidth() - 3, 3, getHeight() - 3);
                die1.roll();
                die2.roll();
                clock.start();
            }
            else if( creditAmount == 0 )
            {
                JOptionPane.showMessageDialog(null, "GAME OVER! YOU RAN OUT OF CREDITS");
            }
            else
            {
                // If there is NOT a valid bet, a message should give a range of a valid bet
                JOptionPane.showMessageDialog(null, "Invalid Bet: Please Enter A Bet Between 1 And " + creditAmount );
            }


        

       
    }

    // Processes timer events
    public void actionPerformed(ActionEvent e)
    {
        if (diceAreRolling())
        {
            if (!clock.isRunning())
                clock.restart();
            if (die1.isRolling())
                die1.avoidCollision(die2);
            else if (die2.isRolling())
                die2.avoidCollision(die1);
        }
        else
        {
            clock.stop();
            int total = die1.getNumDots() + die2.getNumDots();
            int result = game.processRoll(total);
            int point = game.getPoint();
            display.update(result, point);
        }
        repaint();
    }

    // returns true if dice are still rolling; otherwise
    // returns false
    public boolean diceAreRolling()
    {
        return die1.isRolling() || die2.isRolling();
    }

    // Called automatically after a repaint request
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        die1.draw(g);
        die2.draw(g);
    }
}


