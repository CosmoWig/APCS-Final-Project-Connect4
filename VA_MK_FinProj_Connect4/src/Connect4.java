import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

/**
 * Main class. Simulates the whole game through the main method. Takes care of
 * GUI with BoardPanel's help.
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/25/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public class Connect4 extends JFrame
{
    private C4Game game; // C4Game is the charMatrix
    private JTextField display;
    private Player players[];
    private int currentPlayer;
    private static int choice;

    /**
     * Constructor to initialize all fields, set up the BoardPanel display, and
     * initialize the players. Uses the scanner input from the main method to to
     * decide if the game is pvp or pvc.
     */
    public Connect4()
    {

        Container c = getContentPane();
        display = new JTextField(20);
        display.setBackground(Color.WHITE);
        display.setEditable(false);
        c.add(display, BorderLayout.NORTH /* can change */);

        BoardPanel board = new BoardPanel();
        c.add(board, BorderLayout.CENTER); // can change

        game = new C4Game(board);
        game.addConnect4(this);

        HumanPlayer h = new HumanPlayer(this, game, board, 1);
        ComputerPlayer comp;
        HumanPlayer h2;
        players = new Player[2];
        players[0] = h;
        // players[1] = comp;
        if (choice == 1)
        {
            comp = new ComputerPlayer(this, game, board, 2);
            players[1] = comp;
        }
        else if (choice == 2)
        {
            h2 = new HumanPlayer(this, game, board, 2);
            players[1] = h2;
        }
        players[0].makeMove();

    }

    /**
     * move method for game. Changes currentPlayer field to other player. Checks
     * if the game is over. Otherwise continues with the game.
     */
    public void move()
    {
        currentPlayer = (currentPlayer + 1) % 2;
        Player thisPlayer = players[currentPlayer];

        if (game.isWon() != -1)
        {
            // game won
            System.out.println(players[game.isWon() - 1].getWinMessage());

        }
        else
        {
            thisPlayer.makeMove();
        }
    }

    /**
     * only main method in the whole class. Creates a scanner for pvp or pvc
     * input. Starts the game by creating a Connect4Object.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("For 1 player, enter 1.\nFor 2 players, enter 2");
        int c = in.nextInt();
        choice = c;
        in.close();

        Connect4 window = new Connect4();
        window.setTitle("Connect4");
        window.setBounds(200, 200, 288, 292);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);

    }
}