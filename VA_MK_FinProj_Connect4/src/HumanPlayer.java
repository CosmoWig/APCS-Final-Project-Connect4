import java.awt.*;
import java.util.concurrent.TimeUnit;

import java.awt.event.*;
/**
 * Class implementation for a human player. Impelements the player and MouseListener interfaces.
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/25/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public class HumanPlayer implements Player, MouseListener
{
    private Connect4 program;
    private C4Game game;
    private BoardPanel board;
    private boolean myTurn;
    private int myIdentity;

    /**
     * Class constructor to initialize main game fields
     * @param p - Connect4 object
     * @param g - C4Game object
     * @param b - BP object
     * @param identity - this if for pvp. A 1 corresponds to red, 2 corresponds to yellow
     */
    public HumanPlayer(Connect4 p, C4Game g, BoardPanel b, int identity)
    {
        program = p;
        game = g;
        board = b;
        b.addMouseListener(this);
        myIdentity = identity;
    }

    /**
     * outputs to console when it is human's turn
     */
    public String getPrompt()
    {
        if (myIdentity == 1)
        {
            return "Red's turn";
        }
        else
        {
            return "Yellow's turn";
        }
    }

    /**
     * outputs to console when human wins.
     */
    public String getWinMessage()
    {
        if (myIdentity == 1)
        {
            return "red wins!";
        }
        else
        {
            return "yellow wins!";
        }
    }

    /**
     * makeMove accessor method. sets myTurn to true so that mouseReleased can be called
     */
    public void makeMove()
    {
        myTurn = true;
    }

    /**
     * called when user clicks on the screen and then updates the game
     */
    public void mouseReleased(MouseEvent e)
    {
        if (!myTurn)
            return;

        int col = e.getX() / 40;
        game.makeMove(col, myIdentity);
        myTurn = false;
    }

    /**
     * method to keep the game moving forward. Give the turn to the opposing player
     */
    public void doMove()
    {
        program.move();
    }

    /**
     * Not used but required by the MouseListener interface spec:
     */
    public void mouseClicked(MouseEvent e)
    {
    }
    /**
     * Not used but required by the MouseListener interface spec:
     */
    public void mousePressed(MouseEvent e)
    {
    }
    /**
     * Not used but required by the MouseListener interface spec:
     */
    public void mouseEntered(MouseEvent e)
    {
    }
    /**
     * Not used but required by the MouseListener interface spec:
     */
    public void mouseExited(MouseEvent e)
    {
    }
}