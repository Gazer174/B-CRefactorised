/*  Hussein Adem 2024-01-26
    Hussein.adem@hotmail.com

    controller for Game. Adding and getting String from/to IO.
 */

package businesslogic;

import dao.GameDAOImpl;
import io.IO;

import java.sql.SQLException;

public class Controller implements Controllable {
    GameDAOImpl gd;
    Playable playable;
    IO io;

    public Controller(Playable playable, IO io, GameDAOImpl g) {
        this.playable = playable;
        this.io = io;
        this.gd = g;
    }

    @Override
    public void run() throws SQLException, InterruptedException {

        while (true) {
            Status status;
            if (playable.currentId() == 0){
                io.addString(playable.printIntroOutroText());
                String input = io.getString();
                status = playable.playGame(input);
            } else {
                String input = io.getString();
                status = playable.playGame(input);
            }


            switch (status){
                case VERIFIED, PLAYING_GAME -> io.addString(playable.printIntroOutroText());
                case OK -> {
                    io.addString(gd.getTopList().toString());
                    status = playable.playAgain(io.yesNo(playable.printIntroOutroText()));
                }
                case NO_USER_FOUND -> {
                    io.addString(playable.noUserFoundText());
                    Thread.sleep(5000L);
                    io.exit();
                }
            }
            switch (status){
                case EXIT -> io.exit();
                case CONTINUEGAME ->{
                    io.clear();
                    io.addString(playable.printIntroOutroText());
                }
            }
        }
    }
}



