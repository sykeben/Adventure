public class Move {

    // Instance Variables
    private boolean valid, isMonster, isGoal;
    private Room room;
    private String resultMessage;

    // Constructor: Validity Only
    public Move(boolean isValid) {
        valid = isValid;
        calcResult();
    }

    // Constructor: Type & Room
    public Move(boolean isValid, Room theRoom) {
        valid = isValid;
        room = theRoom;
        calcResult();
    }

    // Result Calculator
    private void calcResult() {
        if (valid) {
            if (room.getObject().equals("$")) {
                resultMessage = "You encountered a monster!";
                isMonster = true; isGoal = false;
            } else if (room.getObject().equals("!")) {
                resultMessage = "You reached the goal!";
                isMonster = false; isGoal = true;
            } else {
                resultMessage = "You moved successfully.";
                isMonster = false; isGoal = false;
            }
        } else {
            resultMessage = "Invalid move.";
            isMonster = false; isGoal = false;
        }
    }

    // Validity Getter
    public boolean isValid() {
        return valid;
    }

    // Message Getter
    public String getMessage() {
        return resultMessage;
    }

    // Message Appender
    public void appendMessage(String newText) {
        resultMessage += "\n" + newText;
    }

    // Monster Checker
    public boolean hasMonster() {
        return isMonster;
    }

    // Goal Checker
    public boolean hasGoal() {
        return isGoal;
    }
    
}