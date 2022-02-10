/**
 * This interface defines what all a Player needs in the game
 * 
 * @author Vivek Alumootil, Mihir Kelkar
 * @version 5/22/2020
 * 
 * @author Period - 4
 * @author Assignment - APCS Final Project Connect 4
 * 
 * @author Sources - None
 */
public interface Player
{
    String getPrompt();
    String getWinMessage();
    void makeMove();
}
