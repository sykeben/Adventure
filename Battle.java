import java.util.Random;

public class Battle {

    // Instance Variables
    private int numA, numB, numC;
    private boolean add;

    // Constructor
    public Battle() {
        Random rand = new Random();
        numA = rand.nextInt(10);
        numB = rand.nextInt(10);
        add = rand.nextBoolean();
        if (add) { numC = numA + numB; }
        else { numC = numA - numB; }
    }

    // Problem Getter
    public String getProblem() {
        return numA + " " + (add ? "+" : "-") + " " + numB;
    }

    // Correct Getter
    public int getCorrect() {
        return numC;
    }

    // Result Checker
    public boolean result(int answer) {
        return (answer == numC);
    }
    
}