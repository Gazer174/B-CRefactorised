package tests;

import dao.GameDAO;
import dao.GameDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class GameDaoTest {
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

}
