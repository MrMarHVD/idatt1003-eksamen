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

  /*@BeforeEach
  void setUp() {
    this.departureOverview = new DepartureOverview(new TrainDeparture())
  }*/

  @Test
  void shouldCreateOverview() {
    DepartureOverview overview = new DepartureOverview(
        new TrainDeparture("L1", 1,
            "Oslo", LocalTime.of(12, 0)),
        new TrainDeparture("R2", 2,
            "Trondheim", LocalTime.of(12, 15)),
        new TrainDeparture("S3", 3,
            "Ålesund", LocalTime.of(13, 0)));
  }
}