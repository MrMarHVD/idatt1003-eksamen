/**
 * This is a DepartureOverviewGUI class. It is used to establish the GUI element.
 *
 * @author Håvard Daleng
 * @version 1.0
 * @date 2023-10-16
 */

package edu.ntnu.stud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartureOverviewGUI {
    /**
     *
     */
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public DepartureOverviewGUI(DepartureOverview overview) {
        frame = new JFrame("Departure Overview");
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setSize(600, 600);

        frame.setLayout(new BorderLayout());

        String[] columnLabels = {"Line", "Departure time", "Delay"};
    }


}
