/**
 * This is a DepartureOverview class. It is used to store a list of train departures.
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


public class DepartureOverview {

    private ArrayList<TrainDeparture> departures;

    /**
     * Default constructor for the DepartureOverview class
     */
    public DepartureOverview() {
        this.departures = new ArrayList<>();
    }

    /**
     * Parameterised constructor for the DepartureOverview class with arbitrary number of initial departures
     * @param departures the initial departures
     */
    public DepartureOverview(TrainDeparture... departures) {
        this.departures = new ArrayList<>(Arrays.asList(departures));
    }

    /**
     * Register a new departure and re-sort the list of departures by departure time
     */
    public void registerDeparture(TrainDeparture departure) {
        departures.add(departure);
    }

    /**
     * Get a departure from the overview from index in departures list
     */
    public TrainDeparture getDeparture(int index) {
        return departures.get(index);
    }

    /**
     * This method sorts the list of departures by true departure time using a lambda expression
     */
    public void sortDepartures() {

        departures.sort((TrainDeparture o1, TrainDeparture o2) ->
        {
            LocalTime newTime1 = o1.getTime().plusMinutes(o1.getDelay());
            LocalTime newTime2 = o2.getTime().plusMinutes(o2.getDelay());
            return newTime1.compareTo(newTime2);
        });
    }
}
