//A matrix holding all of the positions
/**
 * This class breaks down the game into basic data structures and primitive data
 * types. It uses a matrix holding all of the positions, 0 for empty, 1 for red,
 * 2 for yellow.
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/25/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public class CharMatrix
{
    private int[][] grid;

    int myRows;

    int myCols;

    BoardPanel myBoard;
    // [row][column]

    // 0 represents empty
    // 1 represents red
    // 2 represents yellow

    /**
     * constructor and initializes the fields in the game
     * 
     * @param rows  - row length of grid
     * @param cols  - column length of grid
     * @param board - the BoardPanel and carrier of the game
     */
    public CharMatrix(int rows, int cols, BoardPanel board)
    {
        // default value is 0ff
        grid = new int[rows][cols];
        myRows = rows;
        myCols = cols;
        myBoard = board;
    }

    /**
     * A second constructor for the very first run and the boardparameter isn't
     * passed through.
     * 
     * @param rows - row length of grid
     * @param cols - column length of grid
     */
    public CharMatrix(int rows, int cols)
    {
        // default value is 0ff
        grid = new int[rows][cols];
        myRows = rows;
        myCols = cols;
    }

    /**
     * myRows field getter
     * 
     * @return myRows
     */
    public int numRows()
    {
        return myRows;
    }

    /**
     * grid field getter
     * 
     * @return grid
     */
    public int[][] getGrid()
    {
        return grid;
    }

    /**
     * iterates through each value in newGrid, and sets it value to
     * corresponding location in grid.
     * 
     * @param newGrid - will replace current version of grid
     */
    public void setGrid(int[][] newGrid)
    {
        for (int i = 0; i < newGrid.length; i++)
        {
            for (int j = 0; j < newGrid[0].length; j++)
            {
                grid[i][j] = newGrid[i][j];
            }
        }
    }

    /**
     * myCols field getter
     * 
     * @return myCols
     */
    public int numCols()
    {
        return myCols;
    }

    /**
     * access value at given row and col on the grid
     * 
     * @param row - row value
     * @param col - column value
     * @return value at given location
     */
    public int getToken(int row, int col)
    {
        return grid[row][col];
    }

    /**
     * Main algorithm in the game. Sets the grid at given location to value.
     * Checks spots surrounding this location to determine whether the move
     * results in a win for either player. It uses 3 for loops. One for
     * verticals. Another for horizontals. A final loop for diagonals. If after
     * these loops go and there is a 4-in-a-row, then the winner corresponds the
     * value that was just inserted in position.
     * 
     * @param row   - row insertion value
     * @param col   - col insertion value
     * @param value - 1 for red, 2 for yellow
     * @return -1 if there's no winner, 1 if red wins, 2 if yellow wins
     */
    public int setToken(int row, int col, int value)
    {

        grid[row][col] = value;
        // System.out.println("spec: " + grid[0][0] + grid[1][0] + row + col);

        // test if game won
        int i = row;
        int j = col - 3;
        for (int k = 0; k < 4; k++)
        {
            if (inBounds(i, j + k) && inBounds(i, j + k + 3) && fourEqual(i,
                    j + k, i, j + k + 1, i, j + k + 2, i, j + k + 3))
            {
                return (grid[i][j + k]);
            }

        }
        i = row - 3;
        j = col;
        for (int k = 0; k < 4; k++)
        {
            if (inBounds(i + k, j) && inBounds(i + k + 3, j) && fourEqual(i + k,
                    j, i + k + 1, j, i + k + 2, j, i + k + 3, j))
            {
                return (grid[i + k][j]);
            }

        }
        i = row - 3;
        j = col - 3;
        for (int k = 0; k < 4; k++)
        {
            if (inBounds(i + k, j + k) && inBounds(i + k + 3, j + k + 3)
                    && fourEqual(i + k, j + k, i + k + 1, j + k + 1, i + k + 2,
                            j + k + 2, i + k + 3, j + k + 3))
            {
                return (grid[i + k][j + k]);
            }

        }
        i = row - 3;
        j = col + 3;
        for (int k = 0; k < 4; k++)
        {
            if (inBounds(i + k, j - k) && inBounds(i + k + 3, j - k - 3)
                    && fourEqual(i + k, j - k, i + k + 1, j - k - 1, i + k + 2,
                            j - k - 2, i + k + 3, j - k - 3))
            {
                return (grid[i + k][j - k]);
            }

        }

        // no winner
        return -1;
    }

    /**
     * checks if the given column has any available slots.
     * 
     * @param col - to be checked
     * @return true if available, false if not
     */
    public boolean colAvailable(int col)
    {
        return (grid[myRows - 1][col] == 0);
    }

    /**
     * get the lowest available row value for a given column
     * 
     * @param col - column intended
     * @return int row value where the coin will drop to if played in that
     *         column
     */
    public int getHighest(int col)
    {
        int i = 0;
        while (i < myRows && grid[i][col] != 0)
        {
            i += 1;
        }

        return i - 1;
    }

    /**
     * 
     * @param col
     * @param val
     * @param i
     */
    public void slowToken(int col, int val, int i)
    {
        grid[i][col] = val;
        if (i != myRows - 1)
        {
            grid[i + 1][col] = 0;
        }
    }

    /**
     * finds the row value associated with that column using helper methods, and
     * calls setToken once row value is found
     * 
     * @param col - intended column
     * @param val - 1 or 2 depending on who's turn it is
     * @return -2 if column isn't available, or the potential values of setToken
     */
    public int placeToken(int col, int val)
    {

        if (colAvailable(col))
        {
            return setToken(getHighest(col) + 1, col, val);
        }
        return -2;
    }

    /**
     * removes a given token by replacing the value at the indicated column with
     * 0.
     * 
     * @param col - intended column
     */
    public void removeToken(int col)
    {
        if (grid[0][col] != 0)
        {
            grid[getHighest(col)][col] = 0;
        }
    }

    /**
     * Determines if the 4 values at these 4 locations are all equal. This is
     * the method used to check for a win.
     * 
     * @param i1 - x of Loc1
     * @param j1 - y of Loc2
     * @param i2 - x of Loc2
     * @param j2 - y of Loc2
     * @param i3 - x of Loc3
     * @param j3 - y of Loc3
     * @param i4 - x of Loc4
     * @param j4 - y of Loc4
     * @return true if they are, false if not.
     */
    public boolean fourEqual(int i1, int j1, int i2, int j2, int i3, int j3,
            int i4, int j4)
    {
        return (grid[i1][j1] == grid[i2][j2] && grid[i2][j2] == grid[i3][j3]
                && grid[i3][j3] == grid[i4][j4] && grid[i1][j1] != 0);
    }

    /**
     * determines if given coordinates are within grid bounds.
     * 
     * @param i - row value
     * @param j - column value
     * @return true if it is, false if not
     */
    public boolean inBounds(int i, int j)
    {
        return (i >= 0 && i < myRows && j >= 0 && j < myCols);
    }

}
