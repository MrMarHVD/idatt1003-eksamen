package edu.ntnu.stud;

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

public class DepartureOverviewGUI {
    /**
     *
     */
    private JFrame frame; // Consider making some of these fields scoped to specific methods
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;
    private DepartureOverview overview;

  /**
   * Constructor, initialises the various GUI-components.
   *
   * @param overview TrainDepartureOverview the GUI will keep track of.
   */
  public DepartureOverviewGUI(DepartureOverview overview) {

      this.overview = overview;

      this.initializeFrame();

      this.initializeTableModel();

      this.populateTable();

      this.initializeSearchPanel();

      this.setupActionListener();

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
      String[] columnLabels = {"Line", "Departure time", "Delay", "Track"};

      this.tableModel = new DefaultTableModel(columnLabels, 0);
      table = new JTable(tableModel);

  }

  /**
   * Initialises the search panel objects.
   */
  private void initializeSearchPanel() {
      // Initialise search field objects.
      this.searchField = new JTextField(15);
      this.searchButton = new JButton("Search");
      this.searchPanel = new JPanel();

      searchPanel.add(new JLabel("Train ID"));
      searchPanel.add(searchField);
      searchPanel.add(searchButton);
      frame.add(searchPanel, BorderLayout.NORTH);
  }

  /**
   * Initialises the scroll panel, allowing the user to scroll through departures.
   */
  private void initializeScrollPane() {
      JScrollPane scrollPane = new JScrollPane(table);
      frame.add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Sets up ActionListener, allowing allowing the user to access the search functionality.
   */
  private void setupActionListener() {
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
   * Populates the table with the attributes of a specific departure. Makes code more readable.
   *
   * @param departure departure whose attributes should be added to the table.
   */
  private void populateTableRow(TrainDeparture departure) {
      Object[] rowData = {
          departure.getLine(),
          departure.getTime(),
          departure.getDelay() == 0 ? "" : departure.getDelay(),
          departure.getTrack() == 0 ? "" : departure.getTrack()
      };
      tableModel.addRow(rowData);
  }

  private void populateTable() {
    // Populates the table with all departures.
    for (TrainDeparture departure : overview.getDepartures()) {
      this.populateTableRow(departure);
    }
  }

  /**
   * General search method: first tries to convert input to integer and find departure with
   * correspondent ID, searches by destination if int-conversion fails.
   * Reloads table with all departures if both searches fail.
   *
   * @param input ID inputted in the search field.
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
}

