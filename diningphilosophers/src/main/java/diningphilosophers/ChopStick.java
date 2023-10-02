package diningphilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

    private static int stickCount = 0;
    private boolean isFree = true;
    private final int myNumber;

    public ChopStick() {
        myNumber = ++stickCount;
    }

    public boolean tryTake(int delay) throws InterruptedException {
        if (isFree) {
            isFree = false;
            // Pas utile de faire notifyAll ici, personne n'attend qu'elle soit occupée
            return true; // Succès
        }
        //Better Way to write your if statement
        boolean locked = lock.tryLock(300, java.util.concurrent.TimeUnit.MILLISECONDS);
       return locked;
    }

    synchronized public void release() {
        try{
            isFree = true;
            System.out.println("Stick " + myNumber + " Released");
        }
        finally{
            lock.unlock();
        }        
    }

    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
