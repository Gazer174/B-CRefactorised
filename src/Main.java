/*  Hussein Adem 2024-01-25
    Hussein.adem@hotmail.com

    implements Dependency injection ONLY!
 */

import businesslogic.Controllable;
import businesslogic.Controller;
import businesslogic.Playable;
import businesslogic.MooGamePlay;
import dao.GameDAO;
import dao.GameDAOImpl;
import io.IO;
import io.InputOutput;
import io.SimpleWindow;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        IO gw = new SimpleWindow("Moo");
        GameDAOImpl gameDAO = new GameDAO();

        Playable moo = new MooGamePlay(gameDAO);
        IO io = new InputOutput(gw);

        Controllable controller = new Controller(moo, io, gameDAO);
        controller.run();
    }

}