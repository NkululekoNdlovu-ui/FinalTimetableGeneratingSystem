//
//
//package za.ac.cput.timetableproject.gui;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumnModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import za.ac.cput.timetableproject.dao.*;
//import za.ac.cput.timetableproject.domain.Group;
//import za.ac.cput.timetableproject.domain.Subject;
//import za.ac.cput.timetableproject.domain.TimeTable;
//import za.ac.cput.timetableproject.domain.Venue;
//import za.ac.cput.timetableproject.domain.Lecture;
//import za.ac.cput.timetableproject.domain.Slot;
//
//public class ViewTable extends JPanel {
//
//    private JTextArea timetableTextArea;  // Text area for displaying timetable
//    private JComboBox<Group> groupComboBox; // Combo box for groups
//    GroupsDao gDao = new GroupsDao();
//    SubjectDao sDao;
//    VenueDao vDao;
//    LectureDao lDao;
//    SlotDao slotDao;
//    TimeTableDao tDao = new TimeTableDao();
//
//    public ViewTable() throws SQLException {
//        setLayout(new BorderLayout());
//        sDao = new SubjectDao();
//        vDao = new VenueDao();
//        lDao = new LectureDao();
//        slotDao = new SlotDao();
//
//        // Add a combo box for group selection
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JLabel groupLabel = new JLabel("Select Group:");
//        groupComboBox = new JComboBox<>();
//        topPanel.add(groupLabel);
//        topPanel.add(groupComboBox);
//        // Add top panel to the north of the BorderLayout
//        add(topPanel, BorderLayout.NORTH);
//
//        // Create a JTextArea for displaying the timetable
//        timetableTextArea = new JTextArea();
//        timetableTextArea.setEditable(false); // Make the text area read-only
//        JScrollPane scrollPane = new JScrollPane(timetableTextArea);
//        add(scrollPane, BorderLayout.CENTER);
//
//        populateGroups();
//
//        // Populate timetable when a group is selected
//        groupComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Group selectedGroup = (Group) groupComboBox.getSelectedItem();
//                if (selectedGroup != null) {
//                    populateTimetable(selectedGroup.getGroupId());
//                }
//            }
//        });
//    }
//
//    private void populateTimetable(int groupId) {
//        ArrayList<TimeTable> timetableList = null;
//
//        try {
//            timetableList = tDao.selectedGroups(groupId);
//            // Clear the timetable before populating new data
//            //clearTimetable();
//
//            StringBuilder timetableOutput = new StringBuilder();
//            timetableOutput.append("Timetable for Group ID: ").append(groupId).append("\n\n");
//
//            if (timetableList != null) {
//                for (TimeTable timetable : timetableList) {
//                    // Retrieve necessary data
//                    Subject subject = sDao.selectedSubject(timetable.getSubjectCode());
//                    Venue venue = vDao.selectedVenue(timetable.getVenueId());
//                    Lecture lecture = lDao.getLectureById(timetable.getLectureId());
//                    Slot slot = slotDao.getSlotById(timetable.getSlotId());
//
//                    // Get the day and time from Slot
//                    String day = slot.getDayOfWeek();  // Assuming slot has a getDay() method
//                    String time = slot.getStartTime();  // Assuming slot has a getTime() method
//
//                    // Append formatted string to timetableOutput
//                    timetableOutput.append(String.format("%s at %s: %s (Venue: %s, Lecture: %s)\n",
//                            day, time, subject.getDescription(), venue.getDescription(), lecture.getLectureName()));
//                }
//            }
//
//            // Display the constructed timetable output
//            timetableTextArea.setText(timetableOutput.toString());
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void populateGroups() throws SQLException {
//        ArrayList<Group> groups = gDao.readGroups();
//        for (Group group : groups) {
//            groupComboBox.addItem(group);
//        }
//    }
//
//    // Clear the timetable before populating new data
//    
//}


//package za.ac.cput.timetableproject.gui;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.table.TableColumnModel;
//import za.ac.cput.timetableproject.dao.*;
//import za.ac.cput.timetableproject.domain.*;
//
//public class ViewTable extends JPanel {
//
//    private JTable timetableTable; // Table for displaying timetable
//    private JComboBox<Group> groupComboBox; // Combo box for groups
//    private DefaultTableModel tableModel; // Table model to manage table data
//    GroupsDao gDao = new GroupsDao();
//    SubjectDao sDao;
//    VenueDao vDao;
//    LectureDao lDao;
//    SlotDao slotDao;
//    TimeTableDao tDao = new TimeTableDao();
//
//    public ViewTable() throws SQLException {
//        setLayout(new BorderLayout());
//        sDao = new SubjectDao();
//        vDao = new VenueDao();
//        lDao = new LectureDao();
//        slotDao = new SlotDao();
//
//        // Add a combo box for group selection
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JLabel groupLabel = new JLabel("Select Group:");
//        groupComboBox = new JComboBox<>();
//        topPanel.add(groupLabel);
//        topPanel.add(groupComboBox);
//        // Add top panel to the north of the BorderLayout
//        add(topPanel, BorderLayout.NORTH);
//
//        // Create a table model with columns for the timetable
//        tableModel = new DefaultTableModel(new Object[]{"Day", "Time", "Subject", "Venue", "Lecture"}, 0);
//        timetableTable = new JTable(tableModel);
//
//        // Set column widths
//        TableColumnModel columnModel = timetableTable.getColumnModel();
//        columnModel.getColumn(0).setPreferredWidth(100); // Day
//        columnModel.getColumn(1).setPreferredWidth(100); // Time
//        columnModel.getColumn(2).setPreferredWidth(150); // Subject
//        columnModel.getColumn(3).setPreferredWidth(150); // Venue
//        columnModel.getColumn(4).setPreferredWidth(150); // Lecture
//
//        // Center-align the table cells
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//        timetableTable.setDefaultRenderer(Object.class, centerRenderer);
//
//        // Add the table to a scroll pane and then to the center of the panel
//        JScrollPane scrollPane = new JScrollPane(timetableTable);
//        add(scrollPane, BorderLayout.CENTER);
//
//        populateGroups();
//
//        // Populate timetable when a group is selected
//        groupComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Group selectedGroup = (Group) groupComboBox.getSelectedItem();
//                if (selectedGroup != null) {
//                    populateTimetable(selectedGroup.getGroupId());
//                }
//            }
//        });
//    }
//
//    // Populate timetable for the selected group
//    private void populateTimetable(int groupId) {
//        ArrayList<TimeTable> timetableList = null;
//
//        try {
//            timetableList = tDao.selectedGroups(groupId);
//            // Clear the table before populating new data
//            tableModel.setRowCount(0);
//
//            if (timetableList != null) {
//                for (TimeTable timetable : timetableList) {
//                    // Retrieve necessary data
//                    Subject subject = sDao.selectedSubject(timetable.getSubjectCode());
//                    Venue venue = vDao.selectedVenue(timetable.getVenueId());
//                    Lecture lecture = lDao.getLectureById(timetable.getLectureId());
//                    Slot slot = slotDao.getSlotById(timetable.getSlotId());
//
//                    // Get the day and time from Slot
//                    String day = slot.getDayOfWeek();  // Assuming slot has a getDay() method
//                    String time = slot.getStartTime();  // Assuming slot has a getTime() method
//
//                    // Add a row to the table
//                    tableModel.addRow(new Object[]{
//                            day,
//                            time,
//                            subject.getDescription(),
//                            venue.getDescription(),
//                            lecture.getLectureName()
//                    });
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    // Populate group combo box with available groups
//    private void populateGroups() throws SQLException {
//        ArrayList<Group> groups = gDao.readGroups();
//        for (Group group : groups) {
//            groupComboBox.addItem(group);
//        }
//    }
//}


package za.ac.cput.timetableproject.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.cput.timetableproject.dao.*;
import za.ac.cput.timetableproject.domain.*;

public class ViewTable extends JPanel {

    private JTable timetableTable;
    private JComboBox<Group> groupComboBox;
    private DefaultTableModel tableModel;
    private GroupsDao gDao = new GroupsDao();
    private SubjectDao sDao;
    private VenueDao vDao;
    private LectureDao lDao;
    private SlotDao slotDao;
    private TimeTableDao tDao = new TimeTableDao();

    public ViewTable() throws SQLException {
        setLayout(new BorderLayout());
        sDao = new SubjectDao();
        vDao = new VenueDao();
        lDao = new LectureDao();
        slotDao = new SlotDao();

        // Top panel for group selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel groupLabel = new JLabel("Select Group:");
        groupComboBox = new JComboBox<>();
        topPanel.add(groupLabel);
        topPanel.add(groupComboBox);
        add(topPanel, BorderLayout.NORTH);

        // Improved table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Day", "Time", "Subject", "Venue", "Lecture"}, 0);
        timetableTable = new JTable(tableModel);

        // Styling the table
        timetableTable.setRowHeight(30);
        timetableTable.setFont(new Font("Arial", Font.PLAIN, 14));
        timetableTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        timetableTable.getTableHeader().setBackground(new Color(70, 130, 180)); // SteelBlue
        timetableTable.getTableHeader().setForeground(Color.WHITE);

        // Set column widths dynamically
        TableColumnModel columnModel = timetableTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100); // Day
        columnModel.getColumn(1).setPreferredWidth(100); // Time
        columnModel.getColumn(2).setPreferredWidth(200); // Subject
        columnModel.getColumn(3).setPreferredWidth(150); // Venue
        columnModel.getColumn(4).setPreferredWidth(150); // Lecture

        // Center-align the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        timetableTable.setDefaultRenderer(Object.class, centerRenderer);

        // Add alternating row colors for readability
        timetableTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(240, 255, 255) : Color.WHITE); // LightCyan color for alternate rows
                }
                return c;
            }
        });

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(timetableTable);
        add(scrollPane, BorderLayout.CENTER);

        populateGroups();

        // Action listener for group selection
        groupComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Group selectedGroup = (Group) groupComboBox.getSelectedItem();
                if (selectedGroup != null) {
                    populateTimetable(selectedGroup.getGroupId());
                }
            }
        });
    }

    // Populate timetable for the selected group
    private void populateTimetable(int groupId) {
        ArrayList<TimeTable> timetableList = null;

        try {
            timetableList = tDao.selectedGroups(groupId);
            tableModel.setRowCount(0); // Clear the table before populating new data

            if (timetableList != null && !timetableList.isEmpty()) {
                for (TimeTable timetable : timetableList) {
                    Subject subject = sDao.selectedSubject(timetable.getSubjectCode());
                    Venue venue = vDao.selectedVenue(timetable.getVenueId());
                    Lecture lecture = lDao.getLectureById(timetable.getLectureId());
                    Slot slot = slotDao.getSlotById(timetable.getSlotId());

                    String day = slot.getDayOfWeek();
                    String time = slot.getStartTime(); // Format time, e.g., "10:00 AM - 12:00 PM"

                    // Add a row to the table
                    tableModel.addRow(new Object[]{
                            day,
                            time,
                            subject.getDescription(),
                            venue.getDescription(),
                            lecture.getLectureName()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "No timetable data found for the selected group.", "No Data", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error retrieving timetable data.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Populate group combo box with available groups
    private void populateGroups() throws SQLException {
        ArrayList<Group> groups = gDao.readGroups();
        if (groups != null && !groups.isEmpty()) {
            for (Group group : groups) {
                groupComboBox.addItem(group);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No groups found in the database.", "No Data", JOptionPane.WARNING_MESSAGE);
        }
    }
}

