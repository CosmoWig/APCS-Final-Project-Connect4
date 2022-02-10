import java.awt.*;

/**
 * Class is designed to assist with the animation of the coins falling. It
 * implements the Runnable interface.
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/25/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public class C4GameRunnable implements Runnable
{
    C4Game m_game;
    int m_col;
    int m_value;

    /**
     * Constructor to initialize fields.
     * 
     * @param game - to init m_game field
     * @param col  - to init m_col field
     * @param val  - to init m_value field
     */
    public C4GameRunnable(C4Game game, int col, int val)
    {
        m_game = game;
        m_col = col;
        m_value = val;
    }

    @Override
    /**
     * run method, which calls the turn executer method: makeMove. Then helps
     * with the animations.
     */
    public void run()
    {
        m_game.makeMove(m_col, m_value);
    }

}
