import java.util.*;

public class Adventure {

    // Main Program
    public static void main(String[] args) {

        // Start Initialization Timer
        long initStart = System.nanoTime();

        // Title Screen
        System.out.println();
        System.out.println("     T H E   Q U E S T   F O R");
        System.out.println("  ,_____,");
        System.out.println("  |_, ,_|  __ _   __   __   __ _");
        System.out.println("    | |   / _` |  \\ \\ / /  / _` |");
        System.out.println("    | |  | (_| |   \\ V /  | (_| |");
        System.out.println("   _/ |   \\__,_|    \\_/    \\__,_|");
        System.out.println("  |__/");
        System.out.println();

        // RNG Initialization
        InitStatus prayToGods = new InitStatus("Praying to the RNG gods");
        Random rand = new Random();
        prayToGods.finished();

        // Generals
        InitStatus spherizeGlobals = new InitStatus("Spherizing globals");
        boolean done = false;
        Scanner input = new Scanner(System.in);
        spherizeGlobals.finished();

        // Room Creation
        InitStatus constructDungeon = new InitStatus("Building the dungeon");
        Dungeon dungeon = new Dungeon();
        constructDungeon.finished();

        // Enemy Randomization
        InitStatus spawnHeathens = new InitStatus("Spawning heathens");
        int monsterCount = 3 + rand.nextInt(4);
        for (int i = 0; i < monsterCount; i++) {
            done = false; while (!done) {
                int x = rand.nextInt(5);
                int y = rand.nextInt(5);
                if (dungeon.getRoom(x,y).getObject().equals(" ") && x != 0 && y != 0) {
                    dungeon.setRoom(x,y,"$");
                    done = true;
                }
            }
        }
        spawnHeathens.finished();

        // Goal Randomization
        InitStatus placeTreasure = new InitStatus("Placing treasure");
        done = false; while (!done) {
            int x = rand.nextInt(5);
            int y = rand.nextInt(5);
            if (dungeon.getRoom(x,y).getObject().equals(" ") && x != 0 && y != 0) {
                dungeon.setRoom(x,y,"!");
                done = true;
            }
        }
        placeTreasure.finished();

        // Player Creation
        InitStatus birthPlayer = new InitStatus("Placing you in the Javaverse");
        Player you = new Player("Player");
        birthPlayer.finished();

        // Finish Initialization Timer
        long initFinish = System.nanoTime();
        long initTotal = (initFinish - initStart) / 1000000;
        System.out.println("Game ready! (" + initTotal + "ms)");

        // Get Name
        System.out.println();
        System.out.println("Greetings traveler!");
        System.out.print("What is your name?\n> ");
        String inputName = input.nextLine();
        if (inputName.length() > 0) {
            you.setName(inputName);
        } else {
            System.out.println("Oh, you don't have one? That's OK!");
        }

        // Start Gamer Timer
        long gameStart = System.nanoTime();

        // Game Loop
        Move lastMove = null;
        while (!you.isDead() && !you.isWinner()) {

            // Room Display
            System.out.println();
            dungeon.display(you);

            // Good Move
            if (lastMove != null) {

                // Message
                System.out.println(lastMove.getMessage());

                // Event Logic
                if (lastMove.isValid()) {

                    // Battle Time
                    if (lastMove.hasMonster()) {

                        // Initialize
                        Battle battle = new Battle();

                        // Prompt
                        System.out.println("The monster asks you a question.");
                        System.out.println("\"What is " + battle.getProblem() + "?\"");

                        // Get User Input
                        int lastAnswer = 0;
                        done = false; while (!done) {
                            System.out.print("> ");
                            try {
                                lastAnswer = input.nextInt();
                                done = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                done = false;
                            }
                            input.nextLine();
                        }

                        // Result
                        if (battle.result(lastAnswer)) {
                            dungeon.setRoom(you.getX(),you.getY()," ");
                            monsterCount--;
                        } else {
                            you.loseLife();
                            you.respawn();
                        }

                        // Redisplay
                        System.out.println();
                        dungeon.display(you);
                        if (battle.result(lastAnswer)) { System.out.println("You won the battle!"); }
                        else { System.out.println("You lost the battle."); }

                    }

                }

            // Greeting
            } else {

                System.out.println("Welcome, " + you.getName() + "!");

            }

            // Player Logic
            if (!you.isDead() && !you.isWinner()) {
                System.out.println("Lives: " + you.getLives() + ". Monsters: " + monsterCount + ".");
                System.out.print("Where to?\n> ");
                lastMove = you.goMove(input.nextLine(), dungeon, monsterCount);
            }

        }

        // Game End
        long gameFinish = System.nanoTime();
        System.out.println();
        dungeon.display(you);
        System.out.println();
        if (you.isDead() || you.isWinner()) {
            System.out.print("You have ");
            if (you.isDead()) {
                System.out.println("died, rest in piece.");
            } else if (you.isWinner()) {
                System.out.println("won, great job!");
            } else {
                System.out.println("broken the game, how did you do that?");
            }
        } else {
            System.out.println("You really have broken the game...");
        }
        int gameTotal = (int)( ((gameFinish-gameStart)/1000000000) + 0.5 );
        System.out.println("This round lasted " + gameTotal + " seconds.");
        System.out.println();

        // Quit
        input.close();
        return;

    }

}