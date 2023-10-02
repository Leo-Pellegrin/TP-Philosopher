package diningphilosophers;

import java.io.IOException;

public class Table {

    public static void main(String[] args) {
        //Use Clear Variable Name
        ChopStick stick1 = new ChopStick();
        ChopStick stick2 = new ChopStick();
        ChopStick stick3 = new ChopStick();
        Philosopher rigaud = new Philosopher("Rigaud", stick1, stick2);
        Philosopher conchon = new Philosopher("Conchon", stick2, stick3);
        Philosopher bastide = new Philosopher("Bastide", stick3, stick1);
        rigaud.start();
        conchon.start();
        bastide.start();
        // attendre la frappe d’une touche dans la console
        try {
            System.in.read();
        } catch (IOException ex) {
        }
        rigaud.leaveTable();
        conchon.leaveTable();
        bastide.leaveTable();

    }
}
