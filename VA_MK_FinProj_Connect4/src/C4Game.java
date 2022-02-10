import java.util.concurrent.TimeUnit;
import java.awt.EventQueue;
import java.awt.*;

import java.awt.event.InvocationEvent;

/**
 * This class is a CharMatrix and inherits all of its methods. It is responsible
 * for taking care of the main functionality of the game, and the only class
 * that knows the actual rules of the game.
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/25/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public class C4Game extends CharMatrix
{
    private BoardPanel board;

    public int won;
    public int rowpos;
    public int top;
    public Connect4 myConnect4;

    /**
     * C4Game constructor which passes the rows and columns of b to its parent
     * constructor. Then it initializes the fields in the class.
     * 
     * @param b the BoardPanel which this game will follow.
     */
    public C4Game(BoardPanel b)
    {
        super(b.numRows(), b.numCols(), b);
        board = b;
        b.update(this);
        won = -1;
        rowpos = -1;
    }

    /**
     * The main game class that will implement the rules in this game.
     * 
     * @param connect a Connect4 object used to initialize myConnect4 field.
     */
    public void addConnect4(Connect4 connect)
    {
        myConnect4 = connect;
    }

    /**
     * a cloning constructor. This creates a completely new C4Game object based
     * off of the information from the parameter.
     * 
     * @param other the game information needed to clone
     */
    public C4Game(C4Game other) // clone constructor
    {
        super(other.board.numRows(), other.board.numCols());
        board = other.board;
        setGrid(other.getGrid());

        board.update(this);
        won = -1;
    }

    /**
     * getter method for won field
     * 
     * @return won a -1 if nobody, 1 if red is the winner, 2 if yellow is the
     *         winner
     */
    public int isWon()
    {
        return won;
    }

    /**
     * This method executes the current player's turn. It calls the setToken
     * method to update the game with the designated value (1, or 2). Then
     * creates a C4GameRunnable class to implement the animation of the coin
     * falling from the mouse y_location to the bottom-most available level.
     * 
     * @param col - the col to insert
     * @param val - either 1 or 2 depending on whether it is red's or yellow's turn
     */
    public void makeMove(int col, int val)
    {
        rowpos = (rowpos == -1 ? rowpos = numRows() - 1 : rowpos - 1);
        if (rowpos == numRows() - 1)
        {
            top = getHighest(col);
        }
        if (rowpos >= top + 1)
        {
            // System.out.println("1.makeMove2: rowpos=" + rowpos + " col=" +
            // col);
            // System.out.println("a1");
            setToken(rowpos, col, val);
            if (rowpos != numRows() - 1)
            {
                // System.out.println("b1");
                setToken(rowpos + 1, col, 0);
            }
            board.update(this);
            EventQueue queue = Toolkit.getDefaultToolkit()
                    .getSystemEventQueue();
            Runnable runnable = new C4GameRunnable(this, col, val);
            InvocationEvent event = new InvocationEvent(this, runnable);
            queue.postEvent(event);
            try
            {
                Thread.sleep(70);
            }
            catch (InterruptedException ex)
            {
                System.out.println("time fail");
                won = setToken(rowpos, col, val);
            }

        }
        else
        {
            // System.out.println("2.makeMove2: rowpos=" + rowpos + " col=" +
            // col);
            won = setToken(rowpos + 1, col, val);
            rowpos = -1;
            myConnect4.move();
        }
        // board.update(this);
    }

}
