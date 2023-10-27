package edu.ntnu.stud;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

/**
 * This is the main class for the train dispatch application.
 */

public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.
    public static void main(String[] args) {

        // Create some departures
        TrainDeparture departure1 = new TrainDeparture("A1", 1, "Trondheim", LocalTime.of(12, 0), 1);
        TrainDeparture departure2 = new TrainDeparture("L2", 2, "Oslo", LocalTime.of(12, 15), 2);
        TrainDeparture departure3 = new TrainDeparture("R3", 2, "Ålesund", LocalTime.of(12, 30), 3);
        TrainDeparture departure4 = new TrainDeparture("C2", 2, "Stavanger", LocalTime.of(12, 45), 1);

        // Initialise departure overview
        DepartureOverview overview = new DepartureOverview(departure1, departure2, departure3, departure4);

        System.out.println(departure1.getTrainID());
        System.out.println(departure2.getDestination());
        System.out.println(overview.getDeparture(2).getLine());

        // Create GUI



    }
}
