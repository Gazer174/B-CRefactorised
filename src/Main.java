/*  Hussein Adem 2024-01-25
    Hussein.adem@hotmail.com

    implements Dependency injection ONLY!
 */

import businesslogic.Controller;
import businesslogic.Game;
import businesslogic.MooGame;
import dao.GameDAOImpl;
import io.InputOutput;
import io.SimpleWindow;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        SimpleWindow gw = new SimpleWindow("Moo");
        GameDAOImpl gameDAOImpl = new GameDAOImpl();

        Game moo = new MooGame(gameDAOImpl);
        InputOutput io = new InputOutput(gw);
        Controller controller = new Controller(moo, io, gameDAOImpl);
        controller.run();
    }

}