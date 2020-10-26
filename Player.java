// THE QUEST FOR JAVA (Player Object)
// (C)2020 - Benjamin Sykes

public class Player {

    // Instance Variables
    private String name;
    private int lives;
    private boolean winner;
    private int x,y;

    // Constructor
    public Player(String theName) {
        name = formatName(theName);
        lives = 3;
        x = 0; y = 0;
    }

    // Name Formatter (Private)
    private String formatName(String theName) {
        int theLength = theName.length();
        if (theLength <= 0) {
            return "";
        } else if (theLength == 1) {
            return theName.toUpperCase();
        } else {
            return theName.substring(0,1).toUpperCase() + theName.substring(1).toLowerCase();
        }
    }

    // Name Getter
    public String getName() {
        return name;
    }

    // Name Setter
    public void setName(String newName) {
        name = formatName(newName);
    }

    // Life Getter
    public int getLives() {
        return lives;
    }

    // Life Loser
    public void loseLife() {
        lives--;
    }

    // Life Gainer
    public void gainLife() {
        lives++;
    }

    // Death Checker
    public boolean isDead() {
        return (lives <= 0);
    }

    // Winner Checker
    public boolean isWinner() {
        return winner;
    }

    // Postition Getters
    public int getX() { return x; }
    public int getY() { return y; }

    // Mover
    public Move goMove(String direction, Dungeon dungeon, int monsterCount) {

        // Initialize
        Move thisMove = null;
        direction = direction.toLowerCase();

        // Check Validity
        if ((direction.equals("up") || direction.equals("u")) && y > 0) { y--; }
        else if ((direction.equals("down") || direction.equals("d")) && y < 4) { y++; }
        else if ((direction.equals("left") || direction.equals("l")) && x > 0) { x--; }
        else if ((direction.equals("right") || direction.equals("r")) && x < 4) { x++; }
        else if (direction.equals("stay") || direction.equals("s")) { /* Do Nothing */ }
        else { thisMove = new Move(false); }

        // Execute Move
        if (thisMove == null) {
            thisMove = new Move(true, dungeon.getRoom(x, y));
        }

        // Update Player
        if (thisMove.hasGoal()) {
            if (monsterCount <= 0) {
                winner = true;
                thisMove.appendMessage("There are no monsters left!");
            } else {
                thisMove.appendMessage("There are still monsters left.");
            }
        }

        // Return
        return thisMove;
        
    }

    // Respawner
    public void respawn() {
        x = 0; y = 0;
    }
    
}