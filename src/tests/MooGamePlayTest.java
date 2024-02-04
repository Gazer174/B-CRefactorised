/*  Hussein Adem 2024-01-30
    Hussein.adem@hotmail.com

    testing metods in MooGame
 */

package tests;

import businesslogic.MooGamePlay;
import businesslogic.Status;
import dao.GameDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



import static org.junit.jupiter.api.Assertions.*;

public class MooGamePlayTest {
    //dependency på GameDAO
    GameDAOImpl gDao;
    MooGamePlay mooGamePlay;
    @BeforeEach
    void setUp(){
        //injiceras här
        mooGamePlay = new MooGamePlay(gDao);
    }

    @RepeatedTest(100)
    public void testGeneratedNumber(){

        String generatedNumber = mooGamePlay.generateNumber();
        assertEquals(4,generatedNumber.length());

        assertTrue(generatedNumber.matches("\\d+"));
        assertTrue(areAllDigitsUnique(generatedNumber));

        assertFalse(generatedNumber.matches(".*\\D.*"));
        assertFalse(areAllDigitsUnique(generatedNumber + generatedNumber));

    }
    @Test
    public void testGenerateFeedback(){
        String checkInput1 = mooGamePlay.generateFeedback("8975","8957");
        assertEquals("BB,CC",checkInput1);
        assertNotEquals("BC,BC",checkInput1);
    }

    @Test
    public void testPlayAgain(){
        Status status = mooGamePlay.playAgain(true);
        assertEquals(Status.CONTINUEGAME,status);
        assertNotEquals(Status.EXIT, status);


    }

    @Test
    public void testCheckUserVerified() {
        GameDAOImpl mockDAO = Mockito.mock(GameDAOImpl.class);
        Mockito.when(mockDAO.getUserId("validInput")).thenReturn(1);

        MooGamePlay mooGamePlay = new MooGamePlay(mockDAO);
        businesslogic.Status result = mooGamePlay.checkUser("validInput");

        assertEquals(businesslogic.Status.VERIFIED, result);
        assertEquals(1, mooGamePlay.currentId);
        assertNotNull(mooGamePlay.goalNumber);
        assertNotNull(mooGamePlay.currentFeedback);
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


}
