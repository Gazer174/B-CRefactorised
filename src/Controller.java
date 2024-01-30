import java.sql.SQLException;

public class Controller {
    GameDAO gd;
    Game game;
    IO io;

    public Controller(Game game, IO io, GameDAO g) {
        this.game = game;
        this.io = io;
        this.gd = g;
    }

    public void run() throws SQLException, InterruptedException {

        while (true) {
            Status status;
            if (game.currentId() == 0){
                io.addString(game.printIntroOutroText());
                String input = io.getString();
                status = game.checkUser(input);
            } else {
                String input = io.getString();
                status = game.playGame(input);
            }
            switch (status){
                case VERIFIED, PLAYING_GAME -> io.addString(game.printIntroOutroText());
                case OK -> {
                    io.addString(gd.getTopList().toString());
                    status = game.playAgain(io.yesNo(game.printIntroOutroText()));
                }
                case NO_USER_FOUND -> {
                    io.addString(game.noUserFoundText());
                    Thread.sleep(5000L);
                    io.exit();
                }
            }
            switch (status){
                case EXIT -> io.exit();
                case CONTINUEGAME ->{
                    io.clear();
                    io.addString(game.printIntroOutroText());
                }
            }
        }
    }
}



