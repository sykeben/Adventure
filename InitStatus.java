// THE QUEST FOR JAVA (Initialization Status Object)
// (C)2020 - Benjamin Sykes

public class InitStatus {

    // Instance Variables
    private boolean done;

    // Constructor
    public InitStatus(String message) {
        done = false;
        System.out.print(message + "...");
    }

    // Finisher
    public void finished() {
        done = true;
        System.out.println(ANSI.grn + " Done." + ANSI.rst);
    }

    // Status Getter
    public boolean isDone() {
        return done;
    }

}