import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * This class prepares the whole board for the game. It takes care of the
 * background, mouse actions. This class implements JPanel and
 * MouseMotionListener.
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/25/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public class BoardPanel extends JPanel implements MouseMotionListener
{
    private int rows = 6;
    private int cols = 7;
    private Color yellow = new Color(255, 255, 0);
    private Color red = new Color(255, 0, 0);
    private Color grey = new Color(224, 224, 224);
    private Color lgrey = new Color(248, 248, 249);
    private int tempR, tempC;
    private final int CELLSIZE = 40;
    private int mouseX;
    private int mouseY;

    /**
     * Constructor of the board. Sets the window dimensions, backgroudn colors,
     * and initializes the mouse listeners.
     */
    public BoardPanel()
    {
        setPreferredSize(new Dimension(cols, rows));
        setBackground(Color.BLUE);
        this.addMouseMotionListener(this);
    }

    /**
     * getter method for rows
     * 
     * @return rows
     */
    public int numRows()
    {
        return rows;
    }

    /**
     * getter method for columns
     * 
     * @return cols
     */
    public int numCols()
    {
        return cols;
    }

    private C4Game game;

    /**
     * updates the game field to match the parameter. calls repaint to update
     * the board gui.
     * 
     * @param g - the latest state of the game
     */
    public void update(C4Game g)
    {
        game = g;
        repaint();
    }

    /**
     * Location where all of the painting code is placed. Invoked each time
     * computer needs to paint. Updates the color of the board based off of the
     * fact that 1 corresponds to red, and 2 corresopnds to yellow in the
     * CharMatrix.
     */
    public void paintComponent(Graphics g)
    {
        // System.out.println("painting");

        super.paintComponent(g);

        if (game == null)
            return;

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                Color color = Color.BLUE;

                if (game.getToken(r, c) == 1)
                {
                    color = red;
                }
                else if (game.getToken(r, c) == 2)
                {
                    color = yellow;
                }
                else if (rows - 1 - r == mouseY && c == mouseX)
                {
                    color = lgrey;
                }
                else
                {
                    color = grey;
                }
                g.setColor(color);
                int x = c * CELLSIZE;
                int y = (rows - 1 - r) * CELLSIZE;
                g.fillOval(x, y, CELLSIZE, CELLSIZE);
                g.setColor(Color.BLUE);
                g.drawRect(x, y, CELLSIZE, CELLSIZE);
                g.drawRect(x + 1, y + 1, CELLSIZE - 2, CELLSIZE - 2);
            }
        }
    }

    /**
     * accesses the mouse location divides the coordinates by the total pixel
     * count / the number of columns. then repaints the board.
     */
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX() / 40;
        mouseY = e.getY() / 40;
        repaint();
    }

    /**
     * required method by the interface. plays no part in the game.
     */
    public void mouseDragged(MouseEvent e)
    {

    }
}