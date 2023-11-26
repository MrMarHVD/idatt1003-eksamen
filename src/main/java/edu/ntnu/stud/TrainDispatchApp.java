package edu.ntnu.stud;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Map;
import java.util.HashMap;


/**
 * This is the main class for the train dispatch application.
 * This is where a specific set of train departures are initialised alongside an
 * overview and the GUI.
 *
 * @author Håvard Daleng
 * @version 1.0, 2023-10-16
 */

public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.
  public static void main(String[] args) {

    // Create some departures
    TrainDeparture departure1 = new TrainDeparture("A1", 1, "Trondheim", LocalTime.of(12, 0));
    TrainDeparture departure2 = new TrainDeparture("L2", 2, "Oslo", LocalTime.of(12, 15), 2);
    TrainDeparture departure3 = new TrainDeparture("R3", 3, "Ålesund", LocalTime.of(12, 30), 3);
    TrainDeparture departure4 = new TrainDeparture("C2", 4, "Stavanger", LocalTime.of(12, 45), 1);
    TrainDeparture departure5 = new TrainDeparture("A2", 5, "Tromsø", LocalTime.of(13, 0), 4);
    TrainDeparture departure6 = new TrainDeparture("A1", 6, "Trondheim", LocalTime.of(13, 15), 1);
    TrainDeparture departure7 = new TrainDeparture("L2", 7, "Oslo", LocalTime.of(13, 30), 2);
    TrainDeparture departure8 = new TrainDeparture("R3", 8, "Ålesund", LocalTime.of(13, 35), 3);
    TrainDeparture departure9 = new TrainDeparture("S9", 9, "Stockholm", LocalTime.of(13, 45), 4);
    TrainDeparture departure10 = new TrainDeparture("A1", 10, "Trondheim", LocalTime.of(14, 0), 1);

    Map<Integer, TrainDeparture> departures = new HashMap<>();

    /*
    String[] lineList = {"A1", "L2", "R3", "C2", "A2", "E3", "C3"};
    int[] trackList = {1, 2, 3, 4, 5};
    String[] destinationList = {"Trondheim", "Oslo", "Ålesund", "Stavanger", "Tromsø", "Hamar", "Kristiansand"};

    // Train departure generator
    for (int i = 2; i <= 100; i++) {
      int randomNum1 = ThreadLocalRandom.current().nextInt(0, 8);
      int randomNum2 = ThreadLocalRandom.current().nextInt(0, 8);
      int randomNum3 = ThreadLocalRandom.current().nextInt(0, 6);
      TrainDeparture newDeparture = new TrainDeparture(lineList[randomNum2], i, destinationList[randomNum1], )
    }*/

    // Initialise departure overview
    DepartureOverview overview = new DepartureOverview(departure1, departure2, departure3,
        departure4, departure5, departure6, departure7, departure8
        , departure9, departure10);

    //LocalTime time = LocalTime.of(14, 0);
    //overview.removeDeparturesAfter(time);
    //System.out.println(overview.getDepartures());
    /*
    System.out.println(overview.getDepartures());
    overview.removeDeparturesAfter(LocalTime.of(10, 21));
    System.out.println(overview.getDepartures());*/


    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        DepartureOverviewGUI GUI = new DepartureOverviewGUI(overview); // Create your GUI here
        GUI.start();
      }
    });


    // Create GUI
    //DepartureOverviewGUI GUI = new DepartureOverviewGUI(overview);
    //GUI.start();

  }
}
