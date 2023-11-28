package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import java.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for DepartureOverview.
 *
 * @author Håvard Daleng.
 * @version 1.0, 2023-11-09.
 */
class DepartureOverviewTest {
  private DepartureOverview departureOverview;

  /**
   * Initialize an overview with one departure.
   */
  @BeforeEach
  void setUp() {
    this.departureOverview = new DepartureOverview(new TrainDeparture("L1", 1,
        "Oslo", LocalTime.of(12, 0, 0)));
  }

  /**
   * Tests whether an overview with the correct parameters was created.
   */
  @Test
  void shouldCreateOverview() {
    DepartureOverview overview = new DepartureOverview(
        new TrainDeparture("L1", 1,
            "Oslo", LocalTime.of(12, 0)),
        new TrainDeparture("R2", 2,
            "Trondheim", LocalTime.of(12, 15)),
        new TrainDeparture("S3", 3,
            "Ålesund", LocalTime.of(13, 0)));

    Assertions.assertEquals("L1", overview.getDeparture(0).getLine());
    Assertions.assertEquals("Trondheim", overview.getDeparture(1).getDestination());
    Assertions.assertEquals(LocalTime.of(13, 0),
        overview.getDeparture(2).getTime());
  }

  /**
   * Tests whether initializing an overview with departures having duplicate IDs results in
   * exception.
   */
  @Test
  void shouldCauseExceptionWithDuplicateIds() {
    TrainDeparture td1 = new TrainDeparture("L1", 1,
        "Oslo", LocalTime.of(12, 0));
    TrainDeparture td2 = new TrainDeparture("R1", 1,
        "Trondheim", LocalTime.of(13,0));

    assertThrows(IllegalArgumentException.class, () -> {
      new DepartureOverview(td1, td2);
    });
  }

  /**
   * Tests whether 'getDeparture()' works by comparing the trainID of a TrainDeparture
   * in the overview is equal to what it was registered with.
   */
  @Test
  void shouldGetDeparture() {
    assertEquals(1,
        departureOverview.getDeparture(0).getTrainId());
  }

  /**
   * Tests whether 'getDepartures()' method yields a list with the right number
   * of elements.
   */
  @Test
  void shouldGetDepartures() {
    TrainDeparture td1 = new TrainDeparture("S1", 2,
        "Trondheim", LocalTime.of(12, 30));
    TrainDeparture td2 = new TrainDeparture("R2", 3,
        "Ålesund", LocalTime.of(14, 0));
    TrainDeparture td3 = new TrainDeparture("L2", 4,
        "Gjøvik", LocalTime.of(11, 25));
    this.departureOverview.registerDeparture(td1);
    this.departureOverview.registerDeparture(td2);
    this.departureOverview.registerDeparture(td3);

    assertEquals(4, this.departureOverview.getDepartures().size());
  }

  /**
   * Tests whether a new departure registered has the correct fields, in other words
   * that it's been registered correctly.
   */
  @Test
  void shouldRegisterDeparture() {
    TrainDeparture td1 = new TrainDeparture("L2", 2,
        "Trondheim", LocalTime.of(12, 15, 0));
    this.departureOverview.registerDeparture(td1);

    assertEquals(2, this.departureOverview.getDeparture(1).getTrainId());
    assertEquals("L2", this.departureOverview.getDeparture(1).getLine());
    assertEquals(LocalTime.of(12, 15),
        this.departureOverview.getDeparture(1).getTime());
  }

  /**
   * Tests whether attempting to register null departure yields an error.
   */
  @Test
  void shouldNotAddNullDeparture() {
    assertThrows(IllegalArgumentException.class,
        () -> {this.departureOverview.registerDeparture(null);});
  }

  /**
   * Tests whether 'sortDepartures()' work by adding departures in the wrong order,
   * then sorting and checking whether time of departure is in ascending order.
   * Since departures are sorted after each registration, invoking the method directly here
   * is unnecessary.
   */
  @Test
  void shouldSortDepartures() {
    TrainDeparture td1 = new TrainDeparture("S1", 2,
        "Trondheim", LocalTime.of(12, 30));
    TrainDeparture td2 = new TrainDeparture("R2", 3,
        "Ålesund", LocalTime.of(14, 0));
    TrainDeparture td3 = new TrainDeparture("L2", 4,
        "Gjøvik", LocalTime.of(11, 25));
    this.departureOverview.registerDeparture(td1);
    this.departureOverview.registerDeparture(td2);
    this.departureOverview.registerDeparture(td3);

    assertEquals(LocalTime.of(11, 25),
        this.departureOverview.getDeparture(0).getTime());
    assertEquals(LocalTime.of(12, 0),
        this.departureOverview.getDeparture(1).getTime());
    assertEquals(LocalTime.of(12, 30),
        this.departureOverview.getDeparture(2).getTime());
    assertEquals(LocalTime.of(14, 0),
        this.departureOverview.getDeparture(3).getTime());
  }

  /**
   * Tests whether 'removeDeparturesBefore()' works by removing all departures except the
   * latest one, and checking whether it is in index 0 of the list of departures.
   */
  @Test
  void shouldRemoveDeparturesBefore() {
    TrainDeparture td1 = new TrainDeparture("S1", 2,
        "Trondheim", LocalTime.of(12, 30));
    TrainDeparture td2 = new TrainDeparture("R2", 3,
        "Ålesund", LocalTime.of(14, 0));
    TrainDeparture td3 = new TrainDeparture("L2", 4,
        "Gjøvik", LocalTime.of(11, 25));
    this.departureOverview.registerDeparture(td1);
    this.departureOverview.registerDeparture(td2);
    this.departureOverview.registerDeparture(td3);
    this.departureOverview.removeDeparturesBefore(LocalTime.of(13, 55));

    assertEquals(LocalTime.of(14, 0),
        this.departureOverview.getDeparture(0).getTime());
  }

  /**
   * Ensures that the overview doesn't contain departures with departure
   * time before which all departures were to be removed.
   */
  @Test
  void shouldNotRemoveDeparturesAfter() {
    TrainDeparture td1 = new TrainDeparture("S1", 2,
        "Trondheim", LocalTime.of(12, 30));
    TrainDeparture td2 = new TrainDeparture("R2", 3,
        "Ålesund", LocalTime.of(14, 0));
    TrainDeparture td3 = new TrainDeparture("L2", 4,
        "Gjøvik", LocalTime.of(11, 25));
    this.departureOverview.registerDeparture(td1);
    this.departureOverview.registerDeparture(td2);
    this.departureOverview.registerDeparture(td3);
    this.departureOverview.removeDeparturesBefore(LocalTime.of(13, 55));
    assertFalse(this.departureOverview.getDepartures().contains(td1));
    assertFalse(this.departureOverview.getDepartures().contains(td3));
  }

  /**
   * Tests whether searching for train ID works by adding a few different
   * departures and then calling the method.
   */
  @Test
  void shouldFindDepartureWithRightId() {
    TrainDeparture td1 = new TrainDeparture("S1", 2,
        "Trondheim", LocalTime.of(12, 30));
    TrainDeparture td2 = new TrainDeparture("R2", 3,
        "Ålesund", LocalTime.of(14, 0));
    TrainDeparture td3 = new TrainDeparture("L2", 4,
        "Gjøvik", LocalTime.of(11, 25));
    this.departureOverview.registerDeparture(td1);
    this.departureOverview.registerDeparture(td2);
    this.departureOverview.registerDeparture(td3);

    assertEquals(4, this.departureOverview.searchById(4).getTrainId());
  }

  /**
   * Checks whether searching by destination works by ensuring the first departure in the
   * returned list has the right destination, and that it was the only departure found due
   * for that destination.
   */
  @Test
  void shouldFindDepartureWithRightDestination() {
    TrainDeparture td1 = new TrainDeparture("S1", 2,
        "Trondheim", LocalTime.of(12, 30));
    TrainDeparture td2 = new TrainDeparture("R2", 3,
        "Ålesund", LocalTime.of(14, 0));
    TrainDeparture td3 = new TrainDeparture("L2", 4,
        "Gjøvik", LocalTime.of(11, 25));
    this.departureOverview.registerDeparture(td1);
    this.departureOverview.registerDeparture(td2);
    this.departureOverview.registerDeparture(td3);

    assertEquals("Ålesund",
        this.departureOverview.searchByDestination(
            "Ålesund").get(0).getDestination());
    assertEquals(1, this.departureOverview.searchByDestination(
        "Ålesund").size()); 
  }
}