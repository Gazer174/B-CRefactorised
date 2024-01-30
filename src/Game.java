import java.sql.SQLException;

public interface Game {
    Status checkUser(String input);
    int nGuesses();
    String printStartText();
    Status playGame(String input) throws SQLException;
    String currentFeedback();
    Status playAgain(boolean b);
    int currentId();



}
