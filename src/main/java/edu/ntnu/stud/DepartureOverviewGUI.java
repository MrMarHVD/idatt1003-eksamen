package edu.ntnu.stud;

import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a DepartureOverviewGUI class. It is used to establish the GUI element.
 *
 * @author Håvard Daleng.
 * @version 1.0, 2023-10-16.
 */
/*
TODO: add comments for all fields and methods, add the ability to set delay and track.
*/

public class DepartureOverviewGUI {
    /**
     *
     */
    private JFrame frame; // Consider making some of these fields scoped to specific methods
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel clockLabel;
    private JTextField clockField;
    private JButton clockButton;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel topPanel;
    private JButton registerButton;
    private JTextField lineField;
    private JTextField destinationField;
    private JTextField timeField;
    private JTextField idField;
    private JPanel lowerPanel;
    private final DepartureOverview overview;
    private LocalTime currentTime;

  /**
   * Constructor, initialises the various GUI-components.
   *
   * @param overview TrainDepartureOverview the GUI will keep track of.
   */
  public DepartureOverviewGUI(DepartureOverview overview) {

      this.overview = overview;

      this.currentTime = LocalTime.of(12,0,0);

      this.initializeFrame();

      this.initializeTableModel();

      this.populateTable();

      this.initializeTopPanel();

      this.initializeLowerPanel();

      this.setupSearchActionListener();

      this.setupClockActionListener();

      this.setupRegisterActionListener();

      this.initializeScrollPane();
  }

  /**
   * Start method, displays the GUI.
   */
  public void start() {
      frame.setVisible(true);
  }

  /**
   * Initialises the GUI frame.
   */
  private void initializeFrame() {
      frame = new JFrame("Departure Overview");
      frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
      frame.setSize(600, 600);

      frame.setLayout(new BorderLayout());
  }

  /**
   * Initialises the GUI table model.
   */
  private void initializeTableModel() {
      String[] columnLabels = {"Departure time", "Line", "ID", "Destination", "Delay", "Track"};

      this.tableModel = new DefaultTableModel(columnLabels, 0);
      table = new JTable(tableModel);
  }

  /**
   * Initialises the top panel, showing the search bar and the clock.
   */
  private void initializeTopPanel() {
    this.searchField = new JTextField(15);
    this.searchButton = new JButton("Search");

    // Create a JPanel with BoxLayout.
    this.topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));

    // Create a sub-panel for search components.
    JPanel searchPanel = new JPanel();
    searchPanel.add(new JLabel("Train ID"));
    searchPanel.add(searchField);
    searchPanel.add(searchButton);

    // Create sub-panel for clock components.
    this.clockLabel = new JLabel(this.currentTime.toString(), SwingConstants.LEFT);
    this.clockLabel.setFont(new Font("Arial", Font.BOLD, 16));
    this.clockField = new JTextField(15);
    this.clockButton = new JButton("Update clock");
    JPanel clockPanel = new JPanel();
    clockPanel.add(this.clockLabel);
    clockPanel.add(this.clockField);
    clockPanel.add(this.clockButton);

    // Add the clock label to the top panel.
    topPanel.add(clockPanel);

    // Add the search panel to the top panel.
    topPanel.add(searchPanel);

    // Add padding around the top panel.
    topPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    // Add the top panel to the frame.
    frame.add(topPanel, BorderLayout.NORTH);
  }

  /**
   * Initialises the lower panel, allowing the user to register new departures.
   */
  private void initializeLowerPanel() {
    this.lowerPanel = new JPanel();
    lowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    // Create labels for each field.
    JLabel lineLabel = new JLabel("Line:");
    JLabel destinationLabel = new JLabel("Destination:");
    JLabel timeLabel = new JLabel("Time:");
    JLabel idLabel = new JLabel("Train ID:");

    // Initialize the text fields.
    this.lineField = new JTextField(5);
    this.destinationField = new JTextField(5);
    this.timeField = new JTextField(5);
    this.idField = new JTextField(5);
    this.registerButton = new JButton("Register");

    // Group each label and text field in its own panel.
    JPanel linePanel = new JPanel();
    linePanel.add(lineLabel);
    linePanel.add(this.lineField);

    JPanel destinationPanel = new JPanel();
    destinationPanel.add(destinationLabel);
    destinationPanel.add(this.destinationField);

    JPanel timePanel = new JPanel();
    timePanel.add(timeLabel);
    timePanel.add(this.timeField);

    JPanel idPanel = new JPanel();
    idPanel.add(idLabel);
    idPanel.add(this.idField);

    // Add the grouped panels to the lowerPanel.
    this.lowerPanel.add(timePanel);
    this.lowerPanel.add(linePanel);
    this.lowerPanel.add(destinationPanel);
    this.lowerPanel.add(idPanel);
    this.lowerPanel.add(this.registerButton);

    // Set padding around the lowerPanel.
    lowerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    // Add the lowerPanel to the frame.
    frame.add(lowerPanel, BorderLayout.SOUTH);
  }


  /**
   * Initialises the scroll panel, allowing the user to scroll through departures.
   */
  private void initializeScrollPane() {
      JScrollPane scrollPane = new JScrollPane(table);
      frame.add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Sets up ActionListener for search, allowing the user to access the search functionality.
   */
  private void setupSearchActionListener() {
      // ActionListener object checks for search inputs in the field
      searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String searchQuery = searchField.getText();
          search(searchQuery);
        }
      });
  }

  /**
   * Set up ActionListener for clock, allowing the user to set the time.
   */
  private void setupClockActionListener() {
    // ActionListener object checks for search inputs in the field
    this.clockButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String newTime = clockField.getText();
        updateClock(newTime);
      }
    });
  }

  /**
   * Set up ActionListener for registering new departures.
   */
  private void setupRegisterActionListener() {
    // ActionListener object checks for search inputs in the field
    this.registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String newTime = timeField.getText();
        String newLine = lineField.getText();
        String newDestination = destinationField.getText();
        String newID = idField.getText();
        registerDeparture(newTime, newLine, newDestination, newID);
      }
    });
  }

  /**
   * Populates the table with the attributes of a specific departure if
   * it departs after current time.
   *
   * @param departure departure whose attributes should be added to the table.
   */
  private void populateTableRow(TrainDeparture departure) {
    if (departure.getTime().isAfter(this.currentTime)) {
      Object[] rowData = {
          departure.getTime(),
          departure.getLine(),
          departure.getTrainID(),
          departure.getDestination(),
          departure.getDelay() == 0 ? "" : departure.getDelay(),
          departure.getTrack() == 0 ? "" : departure.getTrack()
      };
      tableModel.addRow(rowData);
    }
  }

  /**
   * Populates the whole table with all departures after current time.
   */
  private void populateTable() {
    // Populates the table with all departures.
    tableModel.setRowCount(0);
    for (TrainDeparture departure : overview.getDepartures()) {
      this.populateTableRow(departure);
    }
  }

  /**
   * General search method: first tries to convert input to integer and find departure with
   * correspondent ID, searches by destination if int-conversion fails.
   * Reloads table with all departures if both searches fail.
   *
   * @param input ID / destination inputted in the search field.
   */
  private void search(String input) {
    tableModel.setRowCount(0);
    int inputID;

    if (!Objects.equals(input, "")) {
      try {
        inputID = Integer.parseInt(input);
        this.searchByID(inputID);
      }

      // Search by destination instead if the input is a non-empty string.
      catch (NumberFormatException e) {
        this.searchByDestination(input);
      }
    }

    // Get original overview if input is empty string.
    else {
      this.populateTable();
    }
  }

  /**
   * Searches overview by train ID and populates table if any departures are found.
   *
   * @param input input ID to search for.
   */
  private void searchByID(int input) {
    TrainDeparture result = this.overview.searchByID(input);

    // Populates the table with resulting departure if it exists.
    if (input != 0 && this.overview.getDepartures().contains(result)) {
      this.populateTableRow(this.overview.searchByID(input));
    }

    // Get original overview if inputID = 0.
    else {
      this.populateTable();
    }
  }

  /**
   * Searches overview by destination and populates table if any departures are found.
   *
   * @param input destination to search for.
   */
  private void searchByDestination(String input) {
    ArrayList<TrainDeparture> results = this.overview.searchByDestination(input);

    if (!results.isEmpty()) {
      for (TrainDeparture departure : results) {
        this.populateTableRow(departure);
      }
    }
    else {
      this.populateTable();
    }
  }

  /**
   * Update the clock label and currentTime with the new time, then update table.
   *
   * @param newTime new time to update to.
   */
  private void updateClock(String newTime) {
    String[] hoursMinutesSeconds = newTime.split(":");
    LocalTime newTimeObject;

    try {
      if (hoursMinutesSeconds.length != 3) {
        throw new IllegalArgumentException("Invalid time format.");
      }
      int hours = Integer.parseInt(hoursMinutesSeconds[0]);
      int minutes = Integer.parseInt(hoursMinutesSeconds[1]);
      int seconds = Integer.parseInt(hoursMinutesSeconds[2]);
      newTimeObject = LocalTime.of(hours, minutes, seconds);
      this.currentTime = newTimeObject;
      this.clockLabel.setText(newTimeObject.toString());
      this.populateTable();
    }
    catch(NumberFormatException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time format.",
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }
    catch(DateTimeException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time: values out of range",
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }
    catch(IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time format: " + e.getMessage(),
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Register new departure.
   *
   * @param newTime time of departure.
   * @param newLine train line.
   * @param newDestination destination.
   * @param newTrainID ID of the train departure.
   */
  private void registerDeparture(String newTime, String newLine,
      String newDestination, String newTrainID) {

    String[] hoursMinutesSeconds = newTime.split(":");
    LocalTime newTimeObject = null;
    int newTrainIDObject = 0;

    // Try to convert time input to LocalTime object.
    try {
      if (hoursMinutesSeconds.length != 3) {
        throw new IllegalArgumentException("Invalid time format.");
      }
      int hours = Integer.parseInt(hoursMinutesSeconds[0]);
      int minutes = Integer.parseInt(hoursMinutesSeconds[1]);
      int seconds = Integer.parseInt(hoursMinutesSeconds[2]);
      newTimeObject = LocalTime.of(hours, minutes, seconds);
    }
    catch(NumberFormatException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time format.",
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }
    catch(DateTimeException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time: values out of range",
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }
    catch(IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time format: " + e.getMessage(),
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }

    try {
      newTrainIDObject = Integer.parseInt(newTrainID);
    }
    catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid train ID format.",
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }
    // Register departure if all inputs are valid.
    try {
      this.overview.registerDeparture(new TrainDeparture(newLine, newTrainIDObject, newDestination,
          newTimeObject));
    }

    // Get error from DepartureOverview class.
    catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this.frame, "Train ID: " + e.getMessage(),
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }
    this.populateTable();
  }

}



