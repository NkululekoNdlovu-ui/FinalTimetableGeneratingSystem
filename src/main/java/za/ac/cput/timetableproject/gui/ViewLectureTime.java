//package za.ac.cput.timetableproject.gui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import za.ac.cput.timetableproject.dao.*;
//import za.ac.cput.timetableproject.domain.Group;
//import za.ac.cput.timetableproject.domain.Lecture;
//import za.ac.cput.timetableproject.domain.Slot;
//import za.ac.cput.timetableproject.domain.TimeTable;
//
//public class ViewLectureTime extends JPanel {
//
//    private JTextArea lectureTimeTextArea;  // Text area for displaying lecture time
//    private JComboBox<Group> LectureComboBox; // Combo box for groups
//    GroupsDao gDao = new GroupsDao();
//    LectureDao lDao;
//    SlotDao slotDao;
//    TimeTableDao tDao = new TimeTableDao();
//
//    public ViewLectureTime() throws SQLException {
//        setLayout(new BorderLayout());
//        lDao = new LectureDao();
//        slotDao = new SlotDao();
//
//        // Add a combo box for group selection
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JLabel groupLabel = new JLabel("Select Select:");
//        LectureComboBox = new JComboBox<>();
//        topPanel.add(groupLabel);
//        topPanel.add(LectureComboBox);
//        // Add top panel to the north of the BorderLayout
//        add(topPanel, BorderLayout.NORTH);
//
//        // Create a JTextArea for displaying the lecture times
//        lectureTimeTextArea = new JTextArea();
//        lectureTimeTextArea.setEditable(false); // Make the text area read-only
//        JScrollPane scrollPane = new JScrollPane(lectureTimeTextArea);
//        add(scrollPane, BorderLayout.CENTER);
//
//        populateGroups();
//
//        // Populate lecture times when a group is selected
//        LectureComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Group selectedGroup = (Group) LectureComboBox.getSelectedItem();
//                if (selectedGroup != null) {
//                    populateLectureTimes(selectedGroup.getGroupId());
//                }
//            }
//        });
//    }
//
//    private void populateLectureTimes(int lectureID) {
//        ArrayList<TimeTable> timetableList = null;
//
//        try {
//            timetableList = tDao.selectedGroups(lectureID);
//            StringBuilder lectureTimeOutput = new StringBuilder();
//            lectureTimeOutput.append("Lecture Times for lECTURE ID: ").append(lectureID).append("\n\n");
//
//            if (timetableList != null) {
//                for (TimeTable timetable : timetableList) {
//                    // Retrieve necessary data
//                    Lecture lecture = lDao.getLectureById(timetable.getLectureId());
//                    Slot slot = slotDao.getSlotById(timetable.getSlotId());
//
//                    // Get the day and time from Slot
//                    String day = slot.getDayOfWeek();
//                    String time = slot.getStartTime();
//
//                    // Append formatted string to lectureTimeOutput
//                    lectureTimeOutput.append(String.format("%s at %s: Lecture: %s\n",
//                            day, time, lecture.getLectureName()));
//                }
//            }
//
//            // Display the constructed lecture time output
//            lectureTimeTextArea.setText(lectureTimeOutput.toString());
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewLectureTime.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void populateGroups() throws SQLException {
//        ArrayList<Lecture> groups = lDao.readLectures();
//        for (Lecture lecture : groups) {
//            LectureComboBox.addItem(lecture);
//        }
//    }
//}

//package za.ac.cput.timetableproject.gui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import za.ac.cput.timetableproject.dao.*;
//import za.ac.cput.timetableproject.domain.Group;
//import za.ac.cput.timetableproject.domain.Lecture;
//import za.ac.cput.timetableproject.domain.Slot;
//import za.ac.cput.timetableproject.domain.Subject;
//import za.ac.cput.timetableproject.domain.TimeTable;
//import za.ac.cput.timetableproject.domain.Venue;
//
//public class ViewLectureTime extends JPanel {
//
//    private JTextArea lectureTimeTextArea;  // Text area for displaying lecture time
//    private JComboBox<Lecture> lectureComboBox; // Combo box for lectures
//    GroupsDao gDao = new GroupsDao();
//    LectureDao lDao;
//    SlotDao slotDao;
//    TimeTableDao tDao = new TimeTableDao();
//
//    public ViewLectureTime() throws SQLException {
//        setLayout(new BorderLayout());
//        lDao = new LectureDao();
//        slotDao = new SlotDao();
//
//        // Add a combo box for lecture selection
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JLabel lectureLabel = new JLabel("Select Lecture:");
//        lectureComboBox = new JComboBox<>();
//        topPanel.add(lectureLabel);
//        topPanel.add(lectureComboBox);
//        // Add top panel to the north of the BorderLayout
//        add(topPanel, BorderLayout.NORTH);
//
//        // Create a JTextArea for displaying the lecture times
//        lectureTimeTextArea = new JTextArea();
//        lectureTimeTextArea.setEditable(false); // Make the text area read-only
//        JScrollPane scrollPane = new JScrollPane(lectureTimeTextArea);
//        add(scrollPane, BorderLayout.CENTER);
//
//        populateLectures();
//
//        // Populate lecture times when a lecture is selected
//        lectureComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Lecture selectedLecture = (Lecture) lectureComboBox.getSelectedItem();
//                if (selectedLecture != null) {
//                    populateLectureTimes(selectedLecture.getLectureID()); // Use lectureId to fetch timetable
//                }
//            }
//        });
//    }
//
//    private void populateLectureTimes(int lectureID) {
//        ArrayList<TimeTable> timetableList = null;
//
//        try {
//            timetableList = tDao.retrieveTimeTablesByLectureId(lectureID);
//            StringBuilder lectureTimeOutput = new StringBuilder();
//            lectureTimeOutput.append("Lecture Times for LECTURE ID: ").append(lectureID).append("\n\n");
//
//           if (timetableList != null && !timetableList.isEmpty()) {
//            for (TimeTable timetable : timetableList) {
//                // Retrieve necessary data
//                Subject subject = sDao.selectedSubject(timetable.getSubjectCode());
//                Venue venue = vDao.selectedVenue(timetable.getVenueId());
//                Lecture lecture = lDao.getLectureById(timetable.getLectureId());
//                Slot slot = slotDao.getSlotById(timetable.getSlotId());
//                Group group = gDao.getGroupById(timetable.getGroupId()); // Ensure you have this method
//
//                // Get the day and time from Slot
//                String day = slot.getDayOfWeek(); // Assuming slot has a getDayOfWeek() method
//                String time = slot.getStartTime(); // Assuming slot has a getStartTime() method
//
//                // Append formatted string to lectureTimeOutput
//                lectureTimeOutput.append(String.format("%s at %s: %s (Venue: %s, Lecture: %s, Group: %s)\n",
//                        day, time, subject.getDescription(), venue.getDescription(), lecture.getLectureName(), group.getGroupName())); // Assuming group has a getGroupName() method
//            }
//            } else {
//                lectureTimeOutput.append("No lecture times found for this lecture.");
//            }
//
//            // Display the constructed lecture time output
//            lectureTimeTextArea.setText(lectureTimeOutput.toString());
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewLectureTime.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, "Error retrieving lecture times: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void populateLectures() throws SQLException {
//        ArrayList<Lecture> lectures = lDao.readLectures(); // Fetch lectures from the database
//        for (Lecture lecture : lectures) {
//            lectureComboBox.addItem(lecture); // Populate the combo box with lecture objects
//        }
//    }
//}

//package za.ac.cput.timetableproject.gui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import za.ac.cput.timetableproject.dao.*;
//import za.ac.cput.timetableproject.domain.Group;
//import za.ac.cput.timetableproject.domain.Lecture;
//import za.ac.cput.timetableproject.domain.Slot;
//import za.ac.cput.timetableproject.domain.Subject;
//import za.ac.cput.timetableproject.domain.TimeTable;
//import za.ac.cput.timetableproject.domain.Venue;
//
//public class ViewLectureTime extends JPanel {
//
//    private JTextArea lectureTimeTextArea;  // Text area for displaying lecture time
//    private JComboBox<Lecture> lectureComboBox; // Combo box for lectures
//    GroupsDao gDao = new GroupsDao();
//    LectureDao lDao;
//    SlotDao slotDao;
//    TimeTableDao tDao = new TimeTableDao();
//    SubjectDao sDao = new SubjectDao(); // Assuming you have a SubjectDao
//    VenueDao vDao = new VenueDao(); // Assuming you have a VenueDao
//
//    public ViewLectureTime() throws SQLException {
//        setLayout(new BorderLayout());
//        lDao = new LectureDao();
//        slotDao = new SlotDao();
//
//        // Add a combo box for lecture selection
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JLabel lectureLabel = new JLabel("Select Lecture:");
//        lectureComboBox = new JComboBox<>();
//        topPanel.add(lectureLabel);
//        topPanel.add(lectureComboBox);
//        // Add top panel to the north of the BorderLayout
//        add(topPanel, BorderLayout.NORTH);
//
//        // Create a JTextArea for displaying the lecture times
//        lectureTimeTextArea = new JTextArea();
//        lectureTimeTextArea.setEditable(false); // Make the text area read-only
//        JScrollPane scrollPane = new JScrollPane(lectureTimeTextArea);
//        add(scrollPane, BorderLayout.CENTER);
//
//        populateLectures();
//
//        // Populate lecture times when a lecture is selected
//        lectureComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Lecture selectedLecture = (Lecture) lectureComboBox.getSelectedItem();
//                if (selectedLecture != null) {
//                    populateLectureTimes(selectedLecture.getLectureID()); // Use lectureId to fetch timetable
//                }
//            }
//        });
//    }
//
//    private void populateLectureTimes(int lectureID) {
//        ArrayList<TimeTable> timetableList = null;
//
//        try {
//            timetableList = tDao.retrieveTimeTablesByLectureId(lectureID);
//            StringBuilder lectureTimeOutput = new StringBuilder();
//            lectureTimeOutput.append("Lecture Times for LECTURE ID: ").append(lectureID).append("\n\n");
//
//            if (timetableList != null && !timetableList.isEmpty()) {
//                for (TimeTable timetable : timetableList) {
//                    // Retrieve necessary data
//                    Subject subject = sDao.selectedSubject(timetable.getSubjectCode());
//                    Venue venue = vDao.selectedVenue(timetable.getVenueId());
//                    Lecture lecture = lDao.getLectureById(timetable.getLectureId());
//                    Slot slot = slotDao.getSlotById(timetable.getSlotId());
//                    //Group group = gDao.getGroupById(timetable.getGroupId()); // Ensure you have this method
//
//                    // Check for null values
//                    if (subject == null || venue == null || lecture == null || slot == null) {
//                        continue; // Skip if any required data is missing
//                    }
//
//                    // Get the day and time from Slot
//                    String day = slot.getDayOfWeek(); // Assuming slot has a getDayOfWeek() method
//                    String time = slot.getStartTime(); // Assuming slot has a getStartTime() method
//
//                    // Append formatted string to lectureTimeOutput
//                    lectureTimeOutput.append(String.format("%s at %s: %s (Venue: %s, Lecture: %s)\n",
//                            day, time, subject.getDescription(), venue.getDescription(), lecture.getLectureName()));
//                }
//            } else {
//                lectureTimeOutput.append("No lecture times found for this lecture.");
//            }
//
//            // Display the constructed lecture time output
//            lectureTimeTextArea.setText(lectureTimeOutput.toString());
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewLectureTime.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, "Error retrieving lecture times: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void populateLectures() throws SQLException {
//        ArrayList<Lecture> lectures = lDao.readLectures(); // Fetch lectures from the database
//        for (Lecture lecture : lectures) {
//            lectureComboBox.addItem(lecture); // Populate the combo box with lecture objects
//        }
//    }
//}

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
//import za.ac.cput.timetableproject.dao.*;
//import za.ac.cput.timetableproject.domain.*;
//
//public class ViewLectureTime extends JPanel {
//
//    private JTable lectureTimeTable;  // Table for displaying lecture times
//    private DefaultTableModel tableModel;  // Table model for managing rows
//    private JComboBox<Lecture> lectureComboBox; // Combo box for lectures
//
//    GroupsDao gDao = new GroupsDao();
//    LectureDao lDao;
//    SlotDao slotDao;
//    TimeTableDao tDao = new TimeTableDao();
//    SubjectDao sDao = new SubjectDao(); 
//    VenueDao vDao = new VenueDao();
//    
//    // Define colors for alternating rows
//    private static final Color EVEN_ROW_COLOR = new Color(240, 240, 240); // Light gray
//    private static final Color ODD_ROW_COLOR = Color.WHITE; // White
//
//    public ViewLectureTime() throws SQLException {
//        setLayout(new BorderLayout(10, 10)); // Padding for consistency
//        lDao = new LectureDao();
//        slotDao = new SlotDao();
//
//        // Create top panel with lecture selection combo box
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        JLabel lectureLabel = new JLabel("Select Lecture:");
//        lectureLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Matching font style
//        lectureComboBox = new JComboBox<>();
//        lectureComboBox.setPreferredSize(new Dimension(250, 25)); // Wider combo box
//        topPanel.add(lectureLabel);
//        topPanel.add(lectureComboBox);
//        add(topPanel, BorderLayout.NORTH);
//
//        // Create JTable with matching columns to ViewTimetable
//        String[] columnNames = {"Day", "Time", "Subject", "Venue", "Lecture"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//        lectureTimeTable = new JTable(tableModel);
//
//        // Set table properties to match ViewTimetable style
//        lectureTimeTable.setFillsViewportHeight(true);
//        lectureTimeTable.setRowHeight(25); // Consistent row height
//        lectureTimeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12)); // Header font
//        lectureTimeTable.setFont(new Font("Arial", Font.PLAIN, 12)); // Table content font
//        lectureTimeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Single row selection
//
//        // Customize row colors
//        lectureTimeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                
//                // Set background color based on row index (alternating colors)
//                if (isSelected) {
//                    cell.setBackground(table.getSelectionBackground());
//                    cell.setForeground(table.getSelectionForeground());
//                } else {
//                    cell.setBackground(row % 2 == 0 ? EVEN_ROW_COLOR : ODD_ROW_COLOR);
//                    cell.setForeground(Color.BLACK); // Set the text color for cells
//                }
//                
//                return cell;
//            }
//        });
//
//        // Set up scroll pane with border for table
//        JScrollPane scrollPane = new JScrollPane(lectureTimeTable);
//        scrollPane.setBorder(BorderFactory.createTitledBorder("Lecture Times"));
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Populate combo box with lectures
//        populateLectures();
//
//        // Action listener for combo box to load lecture times
//        lectureComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Lecture selectedLecture = (Lecture) lectureComboBox.getSelectedItem();
//                if (selectedLecture != null) {
//                    populateLectureTimes(selectedLecture.getLectureID());
//                }
//            }
//        });
//    }
//
//    // Populate lecture times based on selected lecture ID
//    private void populateLectureTimes(int lectureID) {
//        ArrayList<TimeTable> timetableList = null;
//
//        try {
//            timetableList = tDao.retrieveTimeTablesByLectureId(lectureID);
//
//            // Clear table for new data
//            tableModel.setRowCount(0);
//
//            if (timetableList != null && !timetableList.isEmpty()) {
//                for (TimeTable timetable : timetableList) {
//                    // Retrieve necessary data for the timetable
//                    Subject subject = sDao.selectedSubject(timetable.getSubjectCode());
//                    Venue venue = vDao.selectedVenue(timetable.getVenueId());
//                    Lecture lecture = lDao.getLectureById(timetable.getLectureId());
//                    Slot slot = slotDao.getSlotById(timetable.getSlotId());
//
//                    // Skip rows if data is incomplete
//                    if (subject == null || venue == null || lecture == null || slot == null) {
//                        continue;
//                    }
//
//                    // Add a row to the table matching ViewTimetable style
//                    Object[] row = {
//                            slot.getDayOfWeek(),
//                            slot.getStartTime(),
//                            subject.getDescription(),
//                            venue.getDescription(),
//                            lecture.getLectureName()
//                    };
//                    tableModel.addRow(row);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "No lecture times found for this lecture.", "Information", JOptionPane.INFORMATION_MESSAGE);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewLectureTime.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, "Error retrieving lecture times: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    // Populate combo box with lecture data
//    private void populateLectures() throws SQLException {
//        ArrayList<Lecture> lectures = lDao.readLectures();
//        if (lectures != null && !lectures.isEmpty()) {
//            for (Lecture lecture : lectures) {
//                lectureComboBox.addItem(lecture);
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "No lectures found in the system.", "Information", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//}
//
//













package za.ac.cput.timetableproject.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.cput.timetableproject.dao.*;
import za.ac.cput.timetableproject.domain.*;

public class ViewLectureTime extends JPanel {

    private JTable lectureTimeTable;  // Table for displaying lecture times
    private DefaultTableModel tableModel;  // Table model for managing rows
    private JComboBox<Lecture> lectureComboBox; // Combo box for lectures

    GroupsDao gDao = new GroupsDao();
    LectureDao lDao;
    SlotDao slotDao;
    TimeTableDao tDao = new TimeTableDao();
    SubjectDao sDao = new SubjectDao(); 
    VenueDao vDao = new VenueDao();
    
    // Define colors for alternating rows
    private static final Color EVEN_ROW_COLOR = new Color(240, 255, 255); // LightCyan
    private static final Color ODD_ROW_COLOR = Color.WHITE; // White

    public ViewLectureTime() throws SQLException {
        setLayout(new BorderLayout(10, 10)); // Padding for consistency
        lDao = new LectureDao();
        slotDao = new SlotDao();

        // Create top panel with lecture selection combo box
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel lectureLabel = new JLabel("Select Lecture:");
        lectureLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Matching font style
        lectureComboBox = new JComboBox<>();
        lectureComboBox.setPreferredSize(new Dimension(250, 25)); // Wider combo box
        topPanel.add(lectureLabel);
        topPanel.add(lectureComboBox);
        add(topPanel, BorderLayout.NORTH);

        // Create JTable with matching columns to ViewTable
        String[] columnNames = {"Day", "Time", "Subject", "Venue", "Lecture"};
        tableModel = new DefaultTableModel(columnNames, 0);
        lectureTimeTable = new JTable(tableModel);

        // Set table properties to match ViewTable style
        lectureTimeTable.setFillsViewportHeight(true);
        lectureTimeTable.setRowHeight(30); // Consistent row height
        lectureTimeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Header font
        lectureTimeTable.setFont(new Font("Arial", Font.PLAIN, 14)); // Table content font
        lectureTimeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Single row selection

        // Customize row colors
        lectureTimeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Set background color based on row index (alternating colors)
                if (isSelected) {
                    cell.setBackground(table.getSelectionBackground());
                    cell.setForeground(table.getSelectionForeground());
                } else {
                    cell.setBackground(row % 2 == 0 ? EVEN_ROW_COLOR : ODD_ROW_COLOR);
                    cell.setForeground(Color.BLACK); // Set the text color for cells
                }
                
                return cell;
            }
        });

        // Set up scroll pane with border for table
        JScrollPane scrollPane = new JScrollPane(lectureTimeTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lecture Times"));
        add(scrollPane, BorderLayout.CENTER);

        // Populate combo box with lectures
        populateLectures();

        // Action listener for combo box to load lecture times
        lectureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lecture selectedLecture = (Lecture) lectureComboBox.getSelectedItem();
                if (selectedLecture != null) {
                    populateLectureTimes(selectedLecture.getLectureID());
                }
            }
        });
    }

    // Populate lecture times based on selected lecture ID
    private void populateLectureTimes(int lectureID) {
        ArrayList<TimeTable> timetableList = null;

        try {
            timetableList = tDao.retrieveTimeTablesByLectureId(lectureID);

            // Clear table for new data
            tableModel.setRowCount(0);

            if (timetableList != null && !timetableList.isEmpty()) {
                for (TimeTable timetable : timetableList) {
                    // Retrieve necessary data for the timetable
                    Subject subject = sDao.selectedSubject(timetable.getSubjectCode());
                    Venue venue = vDao.selectedVenue(timetable.getVenueId());
                    Lecture lecture = lDao.getLectureById(timetable.getLectureId());
                    Slot slot = slotDao.getSlotById(timetable.getSlotId());

                    // Skip rows if data is incomplete
                    if (subject == null || venue == null || lecture == null || slot == null) {
                        continue;
                    }

                    // Add a row to the table matching ViewTable style
                    Object[] row = {
                            slot.getDayOfWeek(),
                            slot.getStartTime(),
                            subject.getDescription(),
                            venue.getDescription(),
                            lecture.getLectureName()
                    };
                    tableModel.addRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No lecture times found for this lecture.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewLectureTime.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error retrieving lecture times: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Populate combo box with lecture data
    private void populateLectures() throws SQLException {
        ArrayList<Lecture> lectures = lDao.readLectures();
        if (lectures != null && !lectures.isEmpty()) {
            for (Lecture lecture : lectures) {
                lectureComboBox.addItem(lecture);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No lectures found in the system.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}














//package za.ac.cput.timetableproject.gui;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import za.ac.cput.timetableproject.dao.*;
//import za.ac.cput.timetableproject.domain.Group;
//import za.ac.cput.timetableproject.domain.Lecture;
//import za.ac.cput.timetableproject.domain.Slot;
//import za.ac.cput.timetableproject.domain.Subject;
//import za.ac.cput.timetableproject.domain.TimeTable;
//import za.ac.cput.timetableproject.domain.Venue;
//
//public class ViewLectureTime extends JPanel {
//
//    private JTable lectureTimeTable;  // Table for displaying lecture times
//    private DefaultTableModel tableModel;  // Table model for managing rows
//    private JComboBox<Lecture> lectureComboBox; // Combo box for lectures
//
//    GroupsDao gDao = new GroupsDao();
//    LectureDao lDao;
//    SlotDao slotDao;
//    TimeTableDao tDao = new TimeTableDao();
//    SubjectDao sDao = new SubjectDao(); 
//    VenueDao vDao = new VenueDao();
//
//    public ViewLectureTime() throws SQLException {
//        setLayout(new BorderLayout());
//        lDao = new LectureDao();
//        slotDao = new SlotDao();
//
//        // Add a combo box for lecture selection
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JLabel lectureLabel = new JLabel("Select Lecture:");
//        lectureComboBox = new JComboBox<>();
//        topPanel.add(lectureLabel);
//        topPanel.add(lectureComboBox);
//        add(topPanel, BorderLayout.NORTH);
//
//        // Create a JTable for displaying the lecture times
//        String[] columnNames = {"Day", "Time", "Subject", "Venue", "Lecture"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//        lectureTimeTable = new JTable(tableModel);
//
//        JScrollPane scrollPane = new JScrollPane(lectureTimeTable);
//        add(scrollPane, BorderLayout.CENTER);
//
//        populateLectures();
//
//        // Populate lecture times when a lecture is selected
//        lectureComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Lecture selectedLecture = (Lecture) lectureComboBox.getSelectedItem();
//                if (selectedLecture != null) {
//                    populateLectureTimes(selectedLecture.getLectureID());
//                }
//            }
//        });
//    }
//
//    private void populateLectureTimes(int lectureID) {
//        ArrayList<TimeTable> timetableList = null;
//
//        try {
//            timetableList = tDao.retrieveTimeTablesByLectureId(lectureID);
//
//            // Clear the table before adding new data
//            tableModel.setRowCount(0);
//
//            if (timetableList != null && !timetableList.isEmpty()) {
//                for (TimeTable timetable : timetableList) {
//                    // Retrieve necessary data
//                    Subject subject = sDao.selectedSubject(timetable.getSubjectCode());
//                    Venue venue = vDao.selectedVenue(timetable.getVenueId());
//                    Lecture lecture = lDao.getLectureById(timetable.getLectureId());
//                    Slot slot = slotDao.getSlotById(timetable.getSlotId());
//
//                    // Check for null values
//                    if (subject == null || venue == null || lecture == null || slot == null) {
//                        continue; // Skip if any required data is missing
//                    }
//
//                    // Get the day and time from Slot
//                    String day = slot.getDayOfWeek();
//                    String time = slot.getStartTime();
//
//                    // Add a row to the table
//                    Object[] row = {
//                            day,
//                            time,
//                            subject.getDescription(),
//                            venue.getDescription(),
//                            lecture.getLectureName()
//                    };
//                    tableModel.addRow(row);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "No lecture times found for this lecture.");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewLectureTime.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, "Error retrieving lecture times: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void populateLectures() throws SQLException {
//        ArrayList<Lecture> lectures = lDao.readLectures(); 
//        for (Lecture lecture : lectures) {
//            lectureComboBox.addItem(lecture);
//        }
//    }
//}
