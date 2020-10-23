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
        System.out.println(" Done.");
    }

    // Status Getter
    public boolean isDone() {
        return done;
    }

}