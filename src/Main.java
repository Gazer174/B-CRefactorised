/*  Hussein Adem 2024-01-25
    Hussein.adem@hotmail.com

    implements Dependency injection ONLY!
 */

import businesslogic.Controllable;
import businesslogic.Controller;
import businesslogic.Playable;
import businesslogic.MooGame;
import dao.GameDAOImpl;
import io.IO;
import io.InputOutput;
import io.SimpleWindow;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        IO gw = new SimpleWindow("Moo");
        GameDAOImpl gameDAOImpl = new GameDAOImpl();

        Playable moo = new MooGame(gameDAOImpl);
        IO io = new InputOutput(gw);

        Controllable controller = new Controller(moo, io, gameDAOImpl);
        controller.run();
    }

}