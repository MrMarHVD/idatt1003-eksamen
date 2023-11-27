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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


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
   * JFrame object represents the entirety of the interface.
   */
  private JFrame frame; // Consider making some of these fields scoped to specific methods

  /**
   * JTable object represents the table itself.
   */
  private JTable table;
  /**
   * DefaultTableModel object defines the structure of the table.
   */
  private DefaultTableModel tableModel;

  /**
   * JLabel object for the current time.
   */
  private JLabel clockLabel;

  /**
   * Field where new time can be entered.
   */
  private JTextField clockField;

  /**
   * Button to press to update clock.
   */
  private JButton clockButton;

  /**
   * Search field for searching ID and destination of a given departure.
   */
  private JTextField searchField;

  /**
   * Button to press to perform search by ID or destination.
   */
  private JButton searchButton;

  /**
   * Button to register a new departure using the information entered.
   */
  private JButton registerButton;

  /**
   * Field to enter the line of the new departure to register.
   */
  private JTextField lineField;

  /**
   * Field to enter the destination of the new departure to register.
   */
  private JTextField destinationField;

  /**
   * Field to enter the time of departure of the new departure to register.
   */
  private JTextField timeField;

  /**
   * Field to enter the train ID of the new departure to register.
   */
  private JTextField idField;

  /**
   * Button to press to add delay to the selected departure.
   */
  private JButton delayButton;

  /**
   * Field to input the amount of delay to add to the selected departure, measured in minutes.
   */
  private JTextField delayField;

  /**
   * Button to press to change the track of the selected departure.
   */
  private JButton trackButton;

  /**
   * Field to enter the track to change the selected departure to.
   */
  private JTextField trackField;

  /**
   * Button to press to remove the currently selected departure.
   */
  private JButton removeButton;

  /**
   * LocalTime-object representing the current time of day according to the overview.
   */
  private LocalTime currentTime;

  /**
   * The departureOverview-which the GUI as a whole, tracks.
   */
  private final DepartureOverview overview;

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
    this.setupDelayActionListener();
    this.setupTrackActionListener();
    this.setupCancelActionListener();
    this.initializeScrollPane();
    this.setLookAndFeel();
  }

  /**
   * Start method, displays the GUI on the screen.
   */
  public void start() {
    this.frame.setVisible(true);
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
      String[] columnLabels = {"Departure time", "Line", "ID", "Destination",
          "Status", "Track"};

      this.tableModel = new DefaultTableModel(columnLabels, 0);
      this.table = new JTable(tableModel);
  }

  /**
   * Initialises the top panel, showing the search bar and the clock.
   */
  private void initializeTopPanel() {
    this.searchField = new JTextField(15);
    this.searchButton = new JButton("Search");

    // Create a JPanel with BoxLayout.
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));

    // Create a sub-panel for search components.
    JPanel searchPanel = new JPanel();
    searchPanel.add(new JLabel("Train ID/Destination"));
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
   * Initialises the lower panel, allowing the user to register new departures
   * as well as add delay or change track of a single departure.
   */
  private void initializeLowerPanel() {
    JPanel lowerPanel = new JPanel();
    lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));

    // Create sub-panels.
    JPanel registerPanel = new JPanel();
    JPanel delayTrackPanel = new JPanel();
    JPanel removePanel = new JPanel();

    // Set the layouts of the various sub-panels.
    registerPanel.setLayout(new FlowLayout());
    delayTrackPanel.setLayout(new FlowLayout());
    removePanel.setLayout(new FlowLayout());

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

    this.removeButton = new JButton("Remove departure");

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

    // Add register components to registerPanel.
    registerPanel.add(timePanel);;
    registerPanel.add(linePanel);
    registerPanel.add(destinationPanel);
    registerPanel.add(idPanel);
    registerPanel.add(this.registerButton);

    // Add delay components to delayTrackPanel.
    JLabel delayLabel = new JLabel("Delay:");
    this.delayButton = new JButton("Add delay");
    this.delayField = new JTextField(5);
    delayTrackPanel.add(delayLabel);
    delayTrackPanel.add(this.delayField);
    delayTrackPanel.add(delayButton);

    // Add removeButton to corresponding panel.
    removePanel.add(this.removeButton);

    // Add track components to delayTrackPanel.
    JLabel trackLabel = new JLabel("Track:");
    this.trackButton = new JButton("Set track");
    this.trackField = new JTextField(5);
    delayTrackPanel.add(trackLabel);
    delayTrackPanel.add(this.trackField);
    delayTrackPanel.add(this.trackButton);

    // Add the two sub-panels to the main panel.
    lowerPanel.add(registerPanel);
    lowerPanel.add(delayTrackPanel);
    lowerPanel.add(removePanel);

    // Set padding around the lowerPanel.
    lowerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    // Add the lowerPanel to the frame.
    frame.add(lowerPanel, BorderLayout.SOUTH);
  }


  /**
   * Initialises the scroll pane, allowing the user to scroll through departures.
   */
  private void initializeScrollPane() {
      JScrollPane scrollPane = new JScrollPane(table);
      frame.add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Sets the look and feel of the GUI to 'Nimbus' for better aesthetics.
   */
  private void setLookAndFeel() {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets up ActionListener for search, allowing the user to access the search functionality.
   */
  private void setupSearchActionListener() {
      // ActionListener object checks for search inputs in the field.
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
    // ActionListener object checks for new time in the clock field.
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
    // ActionListener object checks for inputs in the registration fields.
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
   * Set up ActionListener for adding delay.
   */
  private void setupDelayActionListener() {
    // ActionListener object checks for input in the delay field.
    this.delayButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String newDelay = delayField.getText();
        addDelay(newDelay);
      }
    });
  }

  /**
   * Set up ActionListener for setting track.
   */
  private void setupTrackActionListener() {
    // ActionListener object checks for input in the track field.
    this.trackButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String newTrack = trackField.getText();
        setTrack(newTrack);
      }
    });
  }

  /**
   * Set up ActionListener for removing tracks.
   */
  private void setupCancelActionListener() {
    // ActionListener object checks for
    this.removeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cancelDeparture();
      }
    });
  }

  /**
   * Populates the table with the attributes of a specific departure if
   * and only if it departs after current time.
   *
   * @param departure departure whose attributes should be added to the table.
   */
  private void populateTableRow(TrainDeparture departure) {
    String status = "";

    // Check whether departure is cancelled to determine what to display in 'status' column.
    if (departure.getCancelStatus()) {
      status = "Cancelled";
    }
    else {
      status = "New time: " + departure.getTime().plusMinutes(departure.getDelay()).toString();
    }

    if (departure.getTime().plusMinutes(departure.getDelay()).isAfter(this.currentTime)) {
      Object[] rowData = {
          departure.getTime(),
          departure.getLine(),
          departure.getTrainID(),
          departure.getDestination(),
          status.equals("New time: " + departure.getTime().toString()) ? "" : status,
          departure.getTrack() == 0 || departure.getTrack() == -1 ? "" : departure.getTrack()
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
    for (TrainDeparture departure : this.overview.getDepartures()) {
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

    // If the user inputs in one of the two valid formats (HH:MM and HH:MM:SS), proceed.
    try {
      // Throw exception if user inputs in invalid format.
      if (hoursMinutesSeconds.length < 2 || hoursMinutesSeconds.length > 3) {
        throw new IllegalArgumentException("Did you remember "
            + "colon between hours and minutes/seconds?");
      }
      if (hoursMinutesSeconds.length == 2) {
        int hours = Integer.parseInt(hoursMinutesSeconds[0]);
        int minutes = Integer.parseInt(hoursMinutesSeconds[1]);
        newTimeObject = LocalTime.of(hours, minutes);
      }
      else {
        int hours = Integer.parseInt(hoursMinutesSeconds[0]);
        int minutes = Integer.parseInt(hoursMinutesSeconds[1]);
        int seconds = Integer.parseInt(hoursMinutesSeconds[2]);
        newTimeObject = LocalTime.of(hours, minutes, seconds);
      }
      if (newTimeObject.isAfter(this.currentTime)) {
        this.currentTime = newTimeObject;
        this.clockLabel.setText(newTimeObject.toString());
      }
      else {
        throw new IllegalArgumentException("New time cannot be earlier than current time. ");
      }
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

    // Split hours, minutes and seconds using colon.
    String[] hoursMinutesSeconds = newTime.split(":");
    LocalTime newTimeObject = null;
    int newTrainIDObject = 0;

    // Ensure a train line has been provided before proceeding.
    try {
      if (Objects.equals(newLine, "")) {
        throw new IllegalArgumentException("No line provided.");
      }
    }
    catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this.frame, "Train line: " + e.getMessage(),
          "Input error.", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Ensure a destination has been provided before proceeding.
    try {
      if (Objects.equals(newDestination, "")) {
        throw new IllegalArgumentException("No destination provided. ");
      }
    }
    catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this.frame, "Train destination: " + e.getMessage(),
          "Input error.", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // If the user inputs in one of the two valid formats (HH:MM and HH:MM:SS), proceed.
    // Try to convert time input to LocalTime object. ´
    // If the time format is wrong or the values out of range, catch.
    try {
      if (hoursMinutesSeconds.length < 2 || hoursMinutesSeconds.length > 3) {
        throw new IllegalArgumentException("Invalid time format.");
      }

      else if (hoursMinutesSeconds.length == 2) {
        int hours = Integer.parseInt(hoursMinutesSeconds[0]);
        int minutes = Integer.parseInt(hoursMinutesSeconds[1]);
        newTimeObject = LocalTime.of(hours, minutes);
      }

      else {
        int hours = Integer.parseInt(hoursMinutesSeconds[0]);
        int minutes = Integer.parseInt(hoursMinutesSeconds[1]);
        int seconds = Integer.parseInt(hoursMinutesSeconds[2]);
        newTimeObject = LocalTime.of(hours, minutes, seconds);
      }
    }
    catch(NumberFormatException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time format.",
          "Input error.", JOptionPane.ERROR_MESSAGE);
      return;
    }
    catch(DateTimeException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time: values out of range",
          "Input error.", JOptionPane.ERROR_MESSAGE);
      return;
    }
    catch(IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid time format: " + e.getMessage(),
          "Input error.", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Try to parse the value of newTrainID to an integer.
    try {
      newTrainIDObject = Integer.parseInt(newTrainID);
    }
    catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this.frame, "Invalid train ID format.",
          "Input error.", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Ensure newTimeObject was successfully redefined before proceeding.
    try {
      if (newTimeObject != null) {
        this.overview.registerDeparture(new TrainDeparture(newLine, newTrainIDObject,
            newDestination, newTimeObject));
      }
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this.frame, "Train ID: " + e.getMessage(),
          "Input error.", JOptionPane.ERROR_MESSAGE);
      return;
    }

    this.populateTable();
  }

  /**
   * Add delay to the departure currently visible. Only works if a single departure is visible.
   *
   * @param delay delay to add.
   */
  private void addDelay(String delay) {
    int delayInt = Integer.parseInt(delay);

    // Finds the TrainDeparture object for which the current row is displaying data.
    int selectedRow = this.table.getSelectedRow();

    if (selectedRow != -1) {
      TrainDeparture current = this.overview.searchByID((int)
          this.tableModel.getValueAt(selectedRow, 2));
      current.addDelay(delayInt);
    }
    else {
      JOptionPane.showMessageDialog(this.frame, "No departure has been selected.",
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }
    this.populateTable();
  }

  /**
   * Set the track of the currently selected departure, display error otherwise.
   *
   * @param track new track.
   */
  private void setTrack(String track) {
    int trackInt = Integer.parseInt(track);

    int selectedRow = this.table.getSelectedRow();

    // If the user has selected a row, assign new track. Else, display error.
    if (selectedRow != -1) {
      TrainDeparture current = this.overview.searchByID((int)
          this.tableModel.getValueAt(selectedRow, 2));
      current.setTrack(trackInt);
    }
    else {
      JOptionPane.showMessageDialog(this.frame, "No departure has been selected.",
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }

    this.populateTable();
  }

  /**
   * Cancel the currently selected departure if there is one, display error otherwise.
   */
  private void cancelDeparture() {
    int selectedRow = this.table.getSelectedRow();

    if (selectedRow != -1) {
      TrainDeparture current = this.overview.searchByID((int)
          this.tableModel.getValueAt(selectedRow, 2));
      current.cancelDeparture();
    }
    else {
      JOptionPane.showMessageDialog(this.frame, "No departure has been selected.",
          "Input error.", JOptionPane.ERROR_MESSAGE);
    }

    this.populateTable();
  }
}



