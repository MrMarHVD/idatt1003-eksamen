package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for TrainDeparture.
 *
 * @author Håvard Daleng.
 * @version 1.0, 2023-10-16.
 */
class TrainDepartureTest {
  private TrainDeparture trainDeparture;

  /**
   * Create a departure before each test for mutator methods.
   */
  @BeforeEach
  void setUp() {
    this.trainDeparture = new TrainDeparture("L1", 1,
        "Oslo", LocalTime.of(12, 0), 1);
  }

  /**
   * Checks whether the constructor instantiates TrainDeparture objects as intended.
   */
  @Test
  void shouldCreateDeparture() {
    // Instantiate departure here (and below).
    TrainDeparture trainDeparture = new TrainDeparture("L1", 1,
        "Oslo", LocalTime.of(12, 0), 1);

    Assertions.assertEquals("L1", trainDeparture.getLine());
    Assertions.assertEquals(1, trainDeparture.getTrainId());
    Assertions.assertEquals("Oslo", trainDeparture.getDestination());
    Assertions.assertEquals(LocalTime.of(12, 0), trainDeparture.getTime());
  }

  /**
   * Tests whether adding delay to departure works as intended.
   */
  @Test
  void shouldAddDelay() {
    trainDeparture.addDelay(5);
    Assertions.assertEquals(5, trainDeparture.getDelay());
  }

  /**
   * Tests whether adding several delays in a row works as intended.
   */
  @Test
  void shouldAddMultipleDelays() {
    trainDeparture.addDelay(5);
    trainDeparture.addDelay(9);
    Assertions.assertEquals(14, trainDeparture.getDelay());
  }

  /**
   * Tests whether adding a delay which makes departure time earlier than initial value
   * throws error.
   */
  @Test
  void shouldNotAllowNegativeDelay() {
    IllegalArgumentException thrown = assertThrows(
        IllegalArgumentException.class,
        () -> trainDeparture.addDelay(-5),
        "Expected addDelay() to throw, but it didn't."
    );

    assertTrue(thrown.getMessage().contains("The delay entered is invalid."));
  }

  /**
   * Tests whether setting the track using 'setTrack()'-method works properly.
   */
  @Test
  void shouldSetTrack() {
    trainDeparture.setTrack(2);
    assertEquals(2, trainDeparture.getTrack());
  }

  /**
   * Tests whether using the 'cancelDeparture()'-method cancels the departure.
   */
  @Test
  void shouldCancelDeparture() {
    trainDeparture.cancelDeparture();
    assertTrue(trainDeparture.getCancelStatus());
  }
}