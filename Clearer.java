// THE QUEST FOR JAVA (Clearer)
// (C)2020 - Benjamin Sykes

import java.io.IOException;

public class Clearer {

    // Screen Clear Function
    // Sourced from: https://stackoverflow.com/a/38365871/11018374
    // I am aware this is not recommended.
    public static void go(){

        // Attempt Clear
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println();
        }

        // Reset ANSI
        System.out.print(ANSI.rst);

    }
    
}