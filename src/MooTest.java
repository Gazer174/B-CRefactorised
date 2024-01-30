import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MooTest {
    GameDAO gDao;
    MooGame mooGame;
    @BeforeEach
    void setUp(){
        mooGame = new MooGame(gDao);
    }

    @RepeatedTest(100)
    public void testGeneratedNumber(){

        String generatedNumber = mooGame.generateNumber();
        System.out.println(generatedNumber);
        assertEquals(4,generatedNumber.length());
        assertTrue(generatedNumber.matches("\\d+"));
        assertTrue(areAllDigitsUnique(generatedNumber));
        
    }
    @Test
    public void testGenerateFeedback(){
        String checkInput1 = mooGame.generateFeedback("8975","8957");
        assertEquals("BB,CC",checkInput1);
    }

    @Test
    public void testPlayAgain(){
        Status status = mooGame.playAgain(true);
        assertEquals(Status.CONTINUEGAME,status);
    }
    private boolean areAllDigitsUnique(String str) {
        for (int i = 0; i < str.length(); i++) {
            char currentDigit = str.charAt(i);
            if (str.substring(i + 1).contains(String.valueOf(currentDigit))) {
                return false;
            }
        }
        return true;
    }
    /*
    @Test
    public void testCheckUserVerified() {
        // Arrange
        GameDAO mockDAO = Mockito.mock(GameDAO.class);
        Mockito.when(mockDAO.getUserId("validInput")).thenReturn(1);

        MooGame mooGame = new MooGame(mockDAO);

        // Act
        Status result = mooGame.checkUser("validInput");

        // Assert
        assertEquals(Status.VERIFIED, result);
        assertEquals(1, mooGame.currentId);
        assertNotNull(mooGame.genNumber);
        assertNotNull(mooGame.currentFeedback);
    }

     */
}
