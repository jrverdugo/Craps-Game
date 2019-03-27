//Joseph Verdugo 23592803
// Represents a display panel for a Craps table

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class DisplayPanel extends JPanel
{
    private JTextField wonText, lostText, pointText, betText, creditText;
    private int wonCount, lostCount, bet, credit;

    // Constructor
    public DisplayPanel()
    {
        super(new GridLayout(3, 2, 10, 0));
        setBorder(new EmptyBorder(0, 0, 5, 0));
        Font displayFont = new Font("Monospaced", Font.BOLD, 16);

        add(new JLabel("    Bet:"));
        betText = new JTextField("0", 5);
        betText.setFont(displayFont);
        betText.setEditable(true);
        betText.setBackground(Color.WHITE);
        add(betText);
        add(new JLabel("    Credit:"));

        creditText = new JTextField("1000", 5);
        creditText.setFont(displayFont);
        creditText.setEditable(false);
        creditText.setBackground(Color.WHITE);
        add(creditText);

        add(new JLabel("    Won:"));
        add(new JLabel("    Lost:"));
        add(new JLabel("    Point:"));
        add(new JLabel(""));



        wonText = new JTextField("  0", 5);
        wonText.setFont(displayFont);
        wonText.setEditable(false);
        wonText.setBackground(Color.WHITE);
        add(wonText);

        lostText = new JTextField("  0", 5);
        lostText.setFont(displayFont);
        lostText.setEditable(false);
        lostText.setBackground(Color.WHITE);
        add(lostText);

        pointText = new JTextField(5);
        pointText.setFont(displayFont);
        pointText.setEditable(false);
        pointText.setBackground(Color.DARK_GRAY);
        add(pointText);
    }
  
    public JTextField getBet()
    {
        return betText;
    }
  
    public JTextField getCredit()
    {
        return creditText;
    }

    // Updates this display, based on the result and
    // "point" in the previous roll
    @SuppressWarnings("Duplicates")
    public void update(int result, int point)
    {
        credit = Integer.parseInt( getCredit().getText() );
        bet = Integer.parseInt( getBet().getText() );

        if( result == 0 )
        {
            betText.setEditable( false );
            pointText.setText( Integer.toString( point ) );
            pointText.setBackground( Color.yellow );
        }
        else if( result == 1 )
        {
            //Increment the win text
            wonCount = Integer.parseInt( wonText.getText().replaceAll( "\\s+", "" ) );
            wonCount++;
            wonText.setText( Integer.toString( wonCount ) );
            int newCredit = bet + credit;
            creditText.setText( Integer.toString( newCredit ) );
            //Does not say whether or not bet should be set back to 0, so I am assuming the bet will remain
//            bet = 0;
//            betText.setText( Integer.toString( bet ) );
            betText.setEditable( true );
            //Set point back to nothing with Dark Gray Background
            pointText.setBackground( Color.DARK_GRAY );
            pointText.setText("");

        }
        else if( result == -1 )
        {
            //Increment the lost text
            lostCount = Integer.parseInt( lostText.getText().replaceAll( "\\s+", "" ) );
            lostCount++;
            lostText.setText( Integer.toString( lostCount ) );
            int newCredit = credit - bet;
            creditText.setText( Integer.toString( newCredit ) );
            //Does not say whether or not bet should be set back to 0, so I am assuming the bet will remain
//            bet = 0;
//            betText.setText( Integer.toString( bet ) );
            betText.setEditable( true );
            //Set point back to nothing with Dark Gray Background
            pointText.setBackground( Color.DARK_GRAY );
            pointText.setText("");

            if( newCredit == 0 )
            {
                bet = 0;
                betText.setText(Integer.toString(bet));
                betText.setEditable(false);

                JOptionPane.showMessageDialog(null, "GAME OVER! YOU RAN OUT OF CREDITS");
            }
        }
    }
}
