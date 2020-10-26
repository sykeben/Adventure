import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

public class Adventure {

    // Screen Clear Function
    // Sourced from: https://stackoverflow.com/a/38365871/11018374
    // I am aware this is not recommended.
    public static void clrScr(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println();
        }
    }

    // Main Program
    public static void main(String[] args) {

        // I/O Initialization
        Scanner input = new Scanner(System.in);

        // Title Screen
        clrScr();
        System.out.println("              .");
        System.out.println("               )  ,");
        System.out.println("              (    )");
        System.out.println("            ___)__(_____,");
        System.out.println("            \\__________/ \\");
        System.out.println("             \\________/__/");
        System.out.println("           .__\\______/__.");
        System.out.println("+----------(____________)-----------+");
        System.out.println("|                                   |");
        System.out.println("|     T H E   Q U E S T   F O R     |");
        System.out.println("|  ,_____,                          |");
        System.out.println("|  |_, ,_|  __ _   __   __   __ _   |");
        System.out.println("|    | |   / _` |  \\ \\ / /  / _` |  |");
        System.out.println("|    | |  | (_| |   \\ V /  | (_| |  |");
        System.out.println("|   _/ |   \\__,_|    \\_/    \\__,_|  |");
        System.out.println("|  |__/                             |");
        System.out.println("|                                   |");
        System.out.println("+-----------------------------------+");
        System.out.println(" A mini text adventure by Ben Sykes.");

        // Get Name
        System.out.println();
        System.out.println("Greetings traveler!");
        System.out.print("What is your name?\n> ");
        String playerName = input.nextLine();
        if (playerName.length() <= 0) {
            playerName = "Player";
        }
        clrScr();

        // Full Program Loop
        boolean again = true;
        while (again) {

            // Start Initialization Timer
            long initStart = System.nanoTime();

            // RNG Initialization
            InitStatus prayToGods = new InitStatus("Praying to the RNG gods");
            Random rand = new Random();
            prayToGods.finished();

            // Generals
            InitStatus spherizeGlobals = new InitStatus("Spherizing globals");
            boolean done = false;
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
            Player you = new Player(playerName);
            birthPlayer.finished();

            // Finish Initialization Timer
            long initFinish = System.nanoTime();
            long initTotal = (initFinish - initStart) / 1000000;
            System.out.println("Game ready! (" + initTotal + "ms)");

            // Start Game Timer
            long gameStart = System.nanoTime();

            // Game Loop
            Move lastMove = null;
            while (!you.isDead() && !you.isWinner()) {

                // Room Display
                clrScr();
                dungeon.display(you);
                System.out.println();

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
                            clrScr();
                            dungeon.display(you);
                            System.out.println();
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
            clrScr();
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

            // Prompt Retry
            System.out.println("Would you like to play again?");
            done = false; while (!done) {
                System.out.print("> ");
                String againInput = input.nextLine();
                againInput = againInput.toLowerCase();
                if (againInput.equals("y") || againInput.equals("yes")) {
                    done = true; again = true;
                } else if (againInput.equals("n") || againInput.equals("no")) {
                    done = true; again = false;
                } else {
                    System.out.println("Please respond yes or no.");
                }
            }

        }

        // Quit
        clrScr();
        InitStatus cleanup = new InitStatus("Closing up shop");
        input.close();
        cleanup.finished();
        clrScr();
        System.out.println("Goodbye, thank you for playing!");
        System.out.println();
        return;

    }

}