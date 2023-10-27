/**
 * This is a TrainDeparture class. Objects of this class represent train departures.
 *
 * @author Håvard Daleng
 * @version 1.0
 * @date 2023-10-11
 */

package edu.ntnu.stud;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * TrainDeparture class, storing information related to train departures.
 */
public class TrainDeparture {

    /**
     * This field stores the time of departure.
     */
    private LocalTime time;

    /**
     * Stores the train line, consisting of a string  containing a letter and a number in that order.
     */
    private final String line;

    /**
     * Stores the specific ID of this departure in the form of an integer, serving as an identifier.
     */
    private final int trainID;

    /**
     * Stores a string representing the destination of the train departing
     */
    private final String destination;

    /**
     * This field stores a string representing the track the train will arrive to / depart from
     */
    private int track;

    /**
     * This field stores the delay time of a specific departure
     */

    private int delay;

    /**
     * Default constructor initialises a member of the class
     *
     * @param line the line
     * @param trainID the ID
     * @param destination the destination
     * @param time the time of departure
     * @param track the track of departure
     */
    public TrainDeparture(String line, int trainID, String destination, LocalTime time, int track) {
        this.line = line;
        this.trainID = trainID;
        this.destination = destination;
        this.time = time;
        this.track = track;
        this.delay = 0;
    }

    /**
     * Getter for the time of departure
     * @return the time of departure
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Getter for the line
     * @return the line of departure
     */
    public String getLine() {
        return line;
    }

    /**
     * Getter for the ID
     * @return the ID of departure
     */

    public int getTrainID() {
        return trainID;
    }

    /**
     * Getter method for the destination
     * @return the destination of the train
     */

    public String getDestination() {
        return destination;
    }

    /**
     * Getter method for the track
     * @return the track from which the train departs
     */
    public int getTrack() {
        return track;
    }

    /**
     * This method alters the delay
     * @param minutes of delay
     */

    /**
     * Getter method for the delay
     * @return the delay of the train
     */
    public int getDelay() {
        return delay;
    }

    /**
     * This method adds a delay to the departure
     * @param minutes number of minutes delayed
     */
    public void addDelay(int minutes) {
        this.delay += minutes;
    }


}
