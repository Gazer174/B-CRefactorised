package tests;

import dao.GameDAO;
import dao.GameDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class GameDaoImplTest {
    GameDAOImpl gDao;
    @BeforeEach
    void setUp(){
        gDao = new GameDAO();
    }
    
    @Test
    public void testGetUserId(){
        int result = gDao.getUserId("nonExistingUser");
        assertEquals(0,result);
    }
    /*
    @Test
    public void saveResult(){
        assertThrows(SQLException.class, () -> gDao.saveResult(-1,-1));
    }

     */
    
}
