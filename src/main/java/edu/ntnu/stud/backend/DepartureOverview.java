package edu.ntnu.stud.backend;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
   * I used ArrayList to allow for an indefinite number of departures.
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
  public DepartureOverview(TrainDeparture... departures) throws IllegalArgumentException {

    // Use HashSet to ensure there are no duplicate IDs, as the HashSet doesn't allow duplicates.
    Set<Integer> uniqueIds = new HashSet<>();
    ArrayList<TrainDeparture> temp = new ArrayList<>();

    // Throw exception when adding a duplicate ID to the HashSet is attempted.
    for (TrainDeparture departure : departures) {
      if (!uniqueIds.add(departure.getTrainId())) {

        throw new IllegalArgumentException("Duplicate train ID found: " + departure.getTrainId());
      }
      temp.add(departure);
    }

    this.departures = temp;
  }

  /**
   * Register a new departure and re-sort the list of departures by departure time.
   * Throws error if departure with same ID already exists.
   *
   * @param departure the departure to register.
   */
  public void registerDeparture(TrainDeparture departure) throws IllegalArgumentException {

    // Throw exception if departure to add is null.
    if (departure == null) {
      throw new IllegalArgumentException("Cannot add null departure. ");
    }

    // Ensure the register doesn't contain departures with duplicate ID.
    for (TrainDeparture departure1 : this.departures) {
      if (departure.getTrainId() == departure1.getTrainId()) {
        throw new IllegalArgumentException("The register already contains departure with ID. "
            + departure.getTrainId());
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

    departures.sort((TrainDeparture o1, TrainDeparture o2) -> {
      LocalTime newTime1 = o1.getTime().plusMinutes(o1.getDelay());
      LocalTime newTime2 = o2.getTime().plusMinutes(o2.getDelay());
      return newTime1.compareTo(newTime2);
    });
  }

  /**
   * Sort the list of departures by nominal departure time only using lambda expression.
   */
  public void sortDeparturesNoDelay() {
    departures.sort((TrainDeparture o1, TrainDeparture o2) -> {
      LocalTime newTime1 = o1.getTime();
      LocalTime newTime2 = o2.getTime();
      return newTime1.compareTo(newTime2);
    });
  }

  /**
   * Removes all departures after a certain time.
   *
   * @param time the time after which all departures will be removed.
   */
  public void removeDeparturesBefore(LocalTime time) {

    this.departures.removeIf(
        departure -> departure.getTime().plusMinutes(departure.getDelay()).isBefore(time));
  }

  /**
   * Searches the overview for a departure with a specific ID.
   *
   * @param input ID to search with.
   * @return TrainDeparture with ID in question, if any.
   */
  public TrainDeparture searchById(int input) {
    TrainDeparture result = null;

    for (TrainDeparture departure : this.getDepartures()) {
      if (departure.getTrainId() == input) {
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
