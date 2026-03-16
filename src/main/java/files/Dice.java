package files;

import java.util.Random;

public class Dice {
    private static Dice instance = new Dice();

    public static Dice getInstance(){
        return instance;
    }

    private Random random;

    public Dice(){
        random = new Random();
    }

    public int roll(){
        return random.nextInt(1,7);
    }
}
