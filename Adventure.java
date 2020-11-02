// THE QUEST FOR JAVA (Main Class)
// (C)2020 - Benjamin Sykes

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Adventure {

    // Main Program
    public static void main(String[] args) {

        // I/O Initialization
        Scanner input = new Scanner(System.in);

        // Title Screen
        Clearer.go();
        System.out.println(ANSI.rst + ANSI.bld + ANSI.wht + "              .");
        System.out.println("               )  ,");
        System.out.println("              (    )");
        System.out.println(ANSI.yel + "            ___" + ANSI.wht + ")" + ANSI.yel + "__" + ANSI.wht + "(" + ANSI.yel + "_____,");
        System.out.println("            \\__________/ \\");
        System.out.println("             \\________/__/");
        System.out.println("            __\\______/__");
        System.out.println(ANSI.cyn + "+----------" + ANSI.yel + "(____________)" + ANSI.cyn + "-----------+");
        System.out.println("|                                   |");
        System.out.println("|     " + ANSI.wht + "T H E   Q U E S T   F O R" + ANSI.cyn + "     |");
        System.out.println("|  " + ANSI.wht + "._____." + ANSI.cyn + "                          |");
        System.out.println("|  " + ANSI.wht + "|_. ._|  __ _   __   __   __ _" + ANSI.cyn + "   |");
        System.out.println("|  " + ANSI.wht + "  | |   / _` |  \\ \\ / /  / _` |" + ANSI.cyn + "  |");
        System.out.println("|  " + ANSI.wht + "  | |  | (_| |   \\ V /  | (_| |" + ANSI.cyn + "  |");
        System.out.println("|  " + ANSI.wht + " _/ |   \\__,_|    \\_/    \\__,_|" + ANSI.cyn + "  |");
        System.out.println("|  " + ANSI.wht + "|__/" + ANSI.cyn + "                             |");
        System.out.println("|                              " + ANSI.wht + "v1.1" + ANSI.cyn + " |");
        System.out.println("+-----------------------------------+");
        System.out.println(ANSI.cyn + " A mini text adventure by Ben Sykes." + ANSI.rst);

        // Get Name
        System.out.println();
        System.out.println("Greetings traveler!");
        System.out.print("What is your name?\n" + ANSI.bld + "> ");
        String playerName = input.nextLine();
        if (playerName.length() <= 0) {
            playerName = "Player";
        }
        System.out.print(ANSI.rst);
        Clearer.go();

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
            System.out.println(ANSI.bld + ANSI.grn + "Game ready! (" + initTotal + "ms)" + ANSI.rst);

            // Start Game Timer
            long gameStart = System.nanoTime();

            // Game Loop
            Move lastMove = null;
            while (!you.isDead() && !you.isWinner()) {

                // Room Display
                Clearer.go();
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
                                System.out.print(ANSI.bld + "> ");
                                try {
                                    lastAnswer = input.nextInt();
                                    done = true;
                                } catch (InputMismatchException e) {
                                    System.out.println(ANSI.rst + "Please enter an integer.");
                                    done = false;
                                }
                                System.out.print(ANSI.rst);
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
                            Clearer.go();
                            dungeon.display(you);
                            System.out.println();
                            if (battle.result(lastAnswer)) { System.out.println(ANSI.grn + "You won the battle!" + ANSI.rst); }
                            else { System.out.println(ANSI.red + "You lost the battle." + ANSI.rst); }

                        }

                    }

                // Greeting
                } else {

                    System.out.println("Welcome, " + you.getName() + "!");

                }

                // Player Logic
                if (!you.isDead() && !you.isWinner()) {
                    System.out.println("Lives: " + you.getLives() + ". Monsters: " + monsterCount + ".");
                    System.out.print("Where to?\n" + ANSI.bld + "> ");
                    lastMove = you.goMove(input.nextLine(), dungeon, monsterCount);
                    System.out.print(ANSI.rst);
                }

            }

            // Game End
            long gameFinish = System.nanoTime();
            Clearer.go();
            dungeon.display(you);
            System.out.println();
            if (you.isDead()) { System.out.println(ANSI.red + "You have died, rest in piece." + ANSI.rst);
            } else if (you.isWinner()) { System.out.println(ANSI.grn + "You have won, great job!" + ANSI.rst);
            } else { System.out.println(ANSI.yel + "You managed to break the game, how did you do that?" + ANSI.rst); }
            int gameTotal = (int)( ((gameFinish-gameStart)/1000000000) + 0.5 );
            System.out.println("This round lasted " + gameTotal + " seconds.");

            // Prompt Retry
            System.out.println("Would you like to play again?");
            done = false; while (!done) {
                System.out.print(ANSI.bld + "> ");
                String againInput = input.nextLine();
                System.out.print(ANSI.rst);
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
        Clearer.go();
        InitStatus cleanup = new InitStatus("Closing up shop");
        input.close();
        cleanup.finished();
        Clearer.go();
        System.out.println(ANSI.bld + "Goodbye, thank you for playing!" + ANSI.rst);
        System.out.println();
        return;

    }

}