
public class MooGame implements Game {
    GameDAO connector;
    public int guesses = 1;
    public int currentId = 0;
    public String genNumber = "";
    public String currentFeedback = "";

    public MooGame(GameDAO g) {
        this.connector = g;
    }
    public Status checkUser(String input) {
        Status status;
        try {
            int id;
            id = connector.getUserId(input);
            if (id != 0) {
                currentId = id;
                generateNumber();
                status = Status.VERIFIED;
            } else if (input.equals("exit")) {
                status = Status.EXIT;
            } else {
                status = Status.NO_USER_FOUND;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return status;
    }
    public Status playGame(String input) {
        String checkInput = generateFeedback(goalNumber(), input);
        return checkOutcome(checkInput, currentId());
    }
    public Status playAgain(boolean b) {
        if (b){
            guesses = 1;
            generateNumber();
            return Status.CONTINUEGAME;

        } else {
            return Status.EXIT;
        }
    }
    public String printStartText(){
        if (currentId == 0){
            return "Enter your user name:\n";
        } else {
            return "New game:\nFor practice, number is: \n";
        }

    }
    /*
    public String newGameText(){
        generateNumber();
        return "New game:\nFor practice, number is: " + goalNumber() + "\n";
    }

     */

    public Status checkOutcome(String generatedOutcome, int id) {
        Status status;
        int nGuess = guesses;

        if (!generatedOutcome.equals("BBBB,")) {
            currentFeedback += "\n" + generatedOutcome;
            nGuess++;
            guesses = nGuess;
            status = Status.PLAYING_GAME;
        } else {
            status = Status.OK;
        }
        connector.saveResult(nGuess,id);
        return status;
    }

    public int nGuesses() {
        return guesses;
    }

    public String goalNumber() {
        return genNumber;
    }
    public int currentId(){
        return currentId;
    }
    public String currentFeedback(){
        return currentFeedback;
    }


    public String generateNumber() {
        String goal = "";
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            String randomDigit = "" + random;
            while (goal.contains(randomDigit)) {
                random = (int) (Math.random() * 10);
                randomDigit = "" + random;
            }
            goal = goal + randomDigit;
        }
        genNumber = goal;
        return goal;
    }
    public String generateFeedback(String goal, String userGuess) {
        userGuess += "    ";
        int cows = 0, bulls = 0;

        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (goal.charAt(i) == userGuess.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        currentFeedback = userGuess;
        resultBuilder.append("B".repeat(bulls)).append(",").append("C".repeat(cows));

        return resultBuilder.toString();
    }
}
