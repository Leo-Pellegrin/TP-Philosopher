package diningphilosophers;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher
        extends Thread {

    private static int seed = 1;
    private final Random random = new Random(System.currentTimeMillis() + seed++);
    private final static int DELAY = 1000;
    private final String name;
    private final ChopStick leftStick;
    private final ChopStick rightStick;
    private boolean isContinuing = true; // do not mix english and french it's a bad practice

    public Philosopher(String name, ChopStick leftStick, ChopStick rightStick) {
        //Another way to write constructor. More Clear and easy to use
        this.name = name;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
    }

    @Override
    public void run() {
        while (isContinuing) {
            try {
                think();
                // 2-Step locking protocol
                // 1st step : try to get resources

                //If block inside each other are a bad practice try making your code clear and easy to read
                if (!tryTakeStick(leftStick)) {
                    continue;    
                }
                if (!tryTakeStick(rightStick)) {
                    // success : process
                     releaseStick(leftStick);
                     continue;
                } 
                
                eat();
                // release resources
                releaseStick(leftStick);
                releaseStick(rightStick);
                // try again
            } catch (InterruptedException ex) {
                Logger.getLogger("Table").log(Level.SEVERE, "{0} Interrupted", this.getName());
            }
        }
        System.out.println(name + " leaves table");

    }

    public void leaveTable() {
        isContinuing = false;
    }

    private boolean tryTakeStick(ChopStick stick) throws InterruptedException {
        int delay = random.nextInt(100 + DELAY);
        boolean result = stick.tryTake(delay);
        if (result) {
            System.out.println(name + " took " + stick + " before " + delay + " ms");
        } else {
            System.out.println(name + " could not take " + stick + " before " + delay + " ms");
        }
        return result;
    }

    private void releaseStick(ChopStick stick) {
        stick.release();
        System.out.println(name + " releases " + stick);
    }

    private void think() {
        int delay = random.nextInt(100 + DELAY);
        System.out.println(name + " Starts Thinking for: " + delay + " ms");
        try {
            sleep(delay);
        } catch (InterruptedException ex) {
        }
        System.out.println(name + " Stops Thinking");
    }

    private void eat() {
        int delay = random.nextInt(100 + DELAY);
        System.out.println(name + " Starts Eating for:" + delay + " ms");
        try {
            sleep(delay);
        } catch (InterruptedException ex) {
            //Empty Catch Block is a bad practice try rethrowing the exception 
        }
        System.out.println(name + " Stops Eating");
    }
}
