import java.awt.event.*;
import javax.swing.Timer;

/**
 * Implements a computer player. Also implements the Player and ActionListener
 * interfaces. Uses the Connect4Strat class to make its decision.
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/25/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public class ComputerPlayer implements Player, ActionListener
{
    private Connect4 program;
    private C4Game game;
    private BoardPanel board;
    private Timer clock;
    private int clockCount;
    private int moveRow, moveCol;
    private Connect4Strat strategy;
    private int myIdentity;

    /**
     * Constructor to initialize the game fields (Connect4, C4Game, BoardPanel)
     * 
     * @param p - Connect4 param
     * @param g - C4Game param
     * @param b - BP param
     */
    public ComputerPlayer(Connect4 p, C4Game g, BoardPanel b, int identity)
    {
        program = p;
        game = g;
        board = b;
        clock = new Timer(250, this); // need to look into this some more
        myIdentity = identity;
    }

    /**
     * strategy field setter
     * 
     * @param s - strategy to set
     */
    public void setStrategy(Connect4Strat s)
    {
        strategy = s;
    }

    /**
     * outputs to console when its computer's turn
     */
    public String getPrompt()
    {
        return "My turn...";
    }

    /**
     * outputs to console when computer wins
     */
    public String getWinMessage()
    {
        if (myIdentity == 1)
        {
            return "Red Wins!";
        }
        else
        {
            return "Yellow Wins!";
        }
    }

    /**
     * called when it is computer's turn to play. Uses methods available in
     * Strategy interface to decide which column to play in. Then calls game's
     * makeMove method once column is found.
     */
    public void makeMove()
    {
        strategy = new Connect4Strat();
        int col = strategy.findBestMove(game);
        if (col == -1)
        {
            moveCol = 0; // fix this later
        }
        else
        {
            moveCol = col;
        }

        // clockCount = 5; //will change based off of how long it takes to play
        // clock.restart();
        game.makeMove(moveCol, 2);
    }

    /**
     * 
     */
    public void actionPerformed(ActionEvent e)
    {
        if (clockCount > 0)
        {
            // board.update(game);
            clockCount--;
        }
        else
        {
            clock.stop();

        }
    }
}