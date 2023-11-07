
/**
 * Represents an overview of train departures.
 *
 * @author Håvard Daleng
 * @version 1.0
 * @date 2023-10-16
 */

package edu.ntnu.stud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.time.LocalTime;
import java.util.Objects;

/**
 * This class structures a number of departures into an overview which is used
 * to instantiate the DepartureOverviewGUI class, used to generate the UI. It also provides
 * functionality which allows us to organise and sort departures within the context of the overview.
 *
 * @author Håvard Daleng
 * @version 1.0, 2023-10-16
 */


public class DepartureOverview {

  /**
   * Contains the list of departures the overview keeps track of.
   */
  private final ArrayList<TrainDeparture> departures;

  /**
   * Default constructor for the DepartureOverview class. Generates empty overview.
   */
  public DepartureOverview() {
      this.departures = new ArrayList<>();
  }

  /**
   * Parameterised constructor with arbitrary number of initial departures.
   *
   * @param departures the initial departures.
   */
  public DepartureOverview(TrainDeparture... departures) {
    this.departures = new ArrayList<>(Arrays.asList(departures));
  }

  /**
   * Register a new departure and re-sort the list of departures by departure time.
   */
  public void registerDeparture(TrainDeparture departure) {
    for (TrainDeparture departure1 : this.departures) {
      if (departure.getTrainID() == departure1.getTrainID()) {
        throw new IllegalArgumentException("The register already contains departure with ID"
            + departure.getTrainID());
      }
    }
    this.departures.add(departure);
    this.sortDepartures();
  }

  /**
   * Get a departure from the overview using index in departures list,
   * return null if out of bounds.
   *
   * @return the departure with the given index.
   */
  public TrainDeparture getDeparture(int index) {
    TrainDeparture result = null;
    if (this.departures.size() > index) {
      result = departures.get(index);
    }
    return result;
  }

  /**
   * Get entire list of departures in this overview.
   *
   * @return list of departures.
   */
  public ArrayList<TrainDeparture> getDepartures() {
    return this.departures;
  }

  /**
   * Sort the list of departures by true departure time (include delay) using a lambda expression.
   */
  public void sortDepartures() {

    departures.sort((TrainDeparture o1, TrainDeparture o2) ->
    {
      LocalTime newTime1 = o1.getTime().plusMinutes(o1.getDelay());
      LocalTime newTime2 = o2.getTime().plusMinutes(o2.getDelay());
      return newTime1.compareTo(newTime2);
    });
  }

  /**
   * Searches the overview for a departure with a specific ID.
   *
   * @param input ID to search with.
   * @return TrainDeparture with ID in question, if any.
   */
  public TrainDeparture searchByID(int input) {
    TrainDeparture result = null;

    for (TrainDeparture departure : this.getDepartures()) {
      if (departure.getTrainID() == input) {
        result = departure;
      }
    }
    return result;
  }

  /**
   * Searches the overview for departures headed to specific destination.
   *
   * @param inputDestination destination to search for.
   * @return list of departures due for destination entered.
   */
  public ArrayList<TrainDeparture> searchByDestination(String inputDestination) {
    ArrayList<TrainDeparture> results = new ArrayList<TrainDeparture>();

    for (TrainDeparture departure : this.departures) {
      if (Objects.equals(departure.getDestination(), inputDestination)) {
        results.add(departure);
      }
    }
    return results;
  }
}
