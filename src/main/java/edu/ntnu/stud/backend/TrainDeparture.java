

package edu.ntnu.stud.backend;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is a TrainDeparture class. Objects of this class represent train departures
 * and the various variables associated with them including time of departure,
 * line, delay, ID, destination and track.
 *
 * @author Håvard Daleng
 * @version 1.0, 2023-10-16
 */

public class TrainDeparture {

  /**
   * Stores the time of departure.
   */
  private final LocalTime time;

  /**
   * Stores the train line, consisting of a string  containing a letter and a number in that order.
   */
  private final String line;

  /**
   * Stores the specific ID of this departure in the form of an integer, serving as an identifier.
   */
  private final int trainId;

  /**
   * Stores a string representing the destination of the train departing.
   */
  private final String destination;

  /**
   * This field stores a string representing the track the train will arrive to / depart from.
   */
  private int track;

  /**
   * This field stores the delay time of a specific departure.
   */
  private int delay;

  /**
   * Stores a boolean representing whether the departure is cancelled (true) or not (false).
   */
  private boolean cancel;

  /**
   * Parameterized constructor which does not assign track.
   *
   * @param line the line.
   * @param trainId the ID.
   * @param destination the destination.
   * @param time the time of departure.
   */
  public TrainDeparture(String line, int trainId, String destination, LocalTime time) {
    this.line = line;
    this.trainId = trainId;
    this.destination = destination;
    this.time = time;
    this.delay = 0;
    this.track = -1;
    this.cancel = false;
  }

  /**
   * Parameterized constructor which does assign track.
   *
   * @param line the line.
   * @param trainId the ID.
   * @param destination the destination.
   * @param time the time of departure.
   * @param track the track of departure.
   */
  public TrainDeparture(String line, int trainId, String destination, LocalTime time, int track) {
    this.line = line;
    this.trainId = trainId;
    this.destination = destination;
    this.time = time;
    this.track = track;
    this.delay = 0;
    this.cancel = false;
  }

  /**
   * Getter for the time of departure.
   *
   * @return the time of departure.
   */
  public LocalTime getTime() {
    return time;
  }

  /**
   * Getter for the line.
   *
   * @return the line of departure.
   */
  public String getLine() {
    return line;
  }

  /**
   * Getter for the ID.
   *
   * @return the ID of departure.
   */

  public int getTrainId() {
    return trainId;
  }

  /**
   * Getter method for the destination.
   *
   * @return the destination of the train.
   */

  public String getDestination() {
    return destination;
  }

  /**
   * Getter method for the track.
   *
   * @return the track from which the train departs.
   */
  public int getTrack() {
    return track;
  }

  /**
   * Getter method for the delay.
   *
   * @return the delay of the train.
   */
  public int getDelay() {
    return this.delay;
  }

  /**
   * Getter for the status of the departure, cancelled = true or cancelled = false.
   *
   * @return boolean representing status of the departure.
   */
  public boolean getCancelStatus() {
    return this.cancel;
  }

  /**
   * Setter for the track of the departure.
   *
   * @param track new track.
   */
  public void setTrack(int track) throws NumberFormatException {

    if (track > 0) {
      this.track = track;
    } else {
      throw new IllegalArgumentException("The track entered is less than 1.");
    }
  }

  /**
   * Cancel-method allowing the user to cancel this departure.
   */
  public void cancelDeparture() {
    // Departure cannot be 'un-cancelled' as this would likely not occur in a practical situation.
    this.cancel = true;
  }

  /**
   * This method adds a delay to the departure.
   *
   * @param minutes number of minutes delayed.
   */
  public void addDelay(int minutes) {
    if (minutes <= 0) {
      // Throw exception if delay input is negative.
      throw new IllegalArgumentException("The delay entered is invalid.");
    } else {
      this.delay += minutes;
    }
  }
}
