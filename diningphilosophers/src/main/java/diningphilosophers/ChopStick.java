package diningphilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

    private static int stickCount = 0;
    private boolean iAmFree = true;
    private final int myNumber;

    public ChopStick() {
        myNumber = ++stickCount;
    }

    public boolean tryTake(int delay) throws InterruptedException {
        if (!iAmFree) {
            boolean locked = lock.tryLock(300, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (!locked) // Toujours pas libre, on abandonne
            {
                return false; // Echec
            }
        }
        iAmFree = false;
        // Pas utile de faire notifyAll ici, personne n'attend qu'elle soit occupée
        return true; // Succès
    }

    synchronized public void release() {
        try{
            iAmFree = true;
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
