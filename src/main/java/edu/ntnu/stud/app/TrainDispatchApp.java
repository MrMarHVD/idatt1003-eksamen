package edu.ntnu.stud.app;

import edu.ntnu.stud.backend.DepartureOverview;
import edu.ntnu.stud.backend.TrainDeparture;
import edu.ntnu.stud.gui.DepartureOverviewGui;
import java.time.LocalTime;
import javax.swing.SwingUtilities;

/**
 * This is the main class for the train dispatch application.
 * This is where a specific set of train departures are initialised alongside an
 * overview and the GUI.
 *
 * @author Håvard Daleng
 * @version 1.0, 2023-10-16
 */
public class TrainDispatchApp {

  /**
   * Main method.
   *
   * @param args input arguments.
   */
  public static void main(String[] args) {

    // Create some test departures.
    TrainDeparture departure1 = new TrainDeparture("A1", 1,
        "Trondheim", LocalTime.of(12, 0));
    TrainDeparture departure2 = new TrainDeparture("L2", 2,
        "Oslo", LocalTime.of(12, 15), 2);
    TrainDeparture departure3 = new TrainDeparture("R3", 3,
        "Ålesund", LocalTime.of(12, 30), 3);
    TrainDeparture departure4 = new TrainDeparture("C2", 4,
        "Stavanger", LocalTime.of(12, 45), 1);
    TrainDeparture departure5 = new TrainDeparture("A2", 5,
        "Tromsø", LocalTime.of(13, 0), 4);
    TrainDeparture departure6 = new TrainDeparture("A1", 6,
        "Trondheim", LocalTime.of(13, 15), 1);
    TrainDeparture departure7 = new TrainDeparture("L2", 7,
        "Oslo", LocalTime.of(13, 30), 2);
    TrainDeparture departure8 = new TrainDeparture("R3", 8,
        "Ålesund", LocalTime.of(13, 35), 3);
    TrainDeparture departure9 = new TrainDeparture("S9", 9,
        "Stockholm", LocalTime.of(13, 45), 4);
    TrainDeparture departure10 = new TrainDeparture("A1", 10,
        "Trondheim", LocalTime.of(14, 0), 1);

    // Initialise departure overview.
    DepartureOverview overview = new DepartureOverview(departure1, departure2, departure3,
        departure4, departure5, departure6, departure7, departure8, departure9, departure10);

    // Run the Gui, using SwingUtilities.invokeLater to avoid threading issues.
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        DepartureOverviewGui gui = new DepartureOverviewGui(overview); // Create your GUI here
        gui.start();
      }
    });
  }
}
