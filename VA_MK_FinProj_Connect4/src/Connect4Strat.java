import java.util.ArrayList;

/**
 * Strategy class for ComputerPlayer. Helps computer make a decision.
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/25/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public class Connect4Strat
{

    /**
     * empty constructor. Useful for making clones if necessary.
     */
    public Connect4Strat()
    {

    }

    /**
     * This is the 3-in-a-row blocker and 3-in-a-row completer/winner. The
     * method creates 2 copies of the main game (hence the need for a cloning
     * constructor in C4Game). A for loop iterates through each column, and
     * pretends to place its own token for the first copy, and an opponents
     * token in the 2nd copy. Based off of the way placeToken and setToken work,
     * if a win value is returned for the first copy, the computer will play
     * there. Else if a win value is returned for the opponent, the computer
     * will return that value to block the human. If not it will play randomly.
     * 
     * @param game - game to be cloned and simulated
     * @return column value which either wins the game, blocks opponent, or
     *         random.
     */
    public int findBestMove(C4Game game)
    {
        C4Game compSim = new C4Game(game);
        C4Game playerSim = new C4Game(game);
        for (int y = 0; y < game.numCols(); y++)
        {
            // int value = sim.placeToken(y, 2);
            if (compSim.placeToken(y, 2) == 2)
            {
                return y;
            }
            if (playerSim.placeToken(y, 1) == 1)
            {
                return y;
            }

        }

        return findRandomMove(game);

    }

    /**
     * Gets a random column value. Checks if available. Keeps returning random
     * values until an available column is found and then plays it there.
     * 
     * @param game - current game awaiting a column value.
     * @return column - random column to play
     */
    public int findRandomMove(C4Game game)
    {
        int j = (int) (7 * Math.random());

        while (!game.colAvailable(j))
        {
            j = (int) (7 * Math.random());
        }

        return j;
    }
}
