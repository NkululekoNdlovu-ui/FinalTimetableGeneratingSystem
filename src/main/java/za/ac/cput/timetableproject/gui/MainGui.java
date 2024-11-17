//
//package za.ac.cput.timetableproject.gui;
//
//import java.awt.*;
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;
//import java.sql.SQLException;
//import javax.swing.*;
//
//public class MainGui extends JFrame {
//
//    private JButton generate, venue, subject, lecture, groups, viewTable, viewLectureTime; // Add the new button
//    private JPanel contentPanel;
//    private CardLayout cardLayout;
//    private JLabel imageLabel; // Make the imageLabel a field to access it in the resize event
//    private ImageIcon imageIcon; // Hold the original image to resize when needed
//
//    public MainGui() throws SQLException {
//        // Initialize components
//        generate = new JButton("Generate");
//        lecture = new JButton("Lecture");
//        venue = new JButton("Venue");
//        subject = new JButton("Subject");
//        groups = new JButton("Groups");
//        viewTable = new JButton("View Table");
//        viewLectureTime = new JButton("View Lecture Time"); // Initialize the new button
//
//        // Set up the GUI
//        setGui();
//    }
//
//    private void setGui() throws SQLException {
//        // Set up the frame
//        setTitle("Main GUI");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Create a panel for buttons and set its layout
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(8, 1)); // Adjust to 8 rows to include the new button
//
//        // Add buttons to the panel
//        buttonPanel.add(generate);
//        buttonPanel.add(lecture);
//        buttonPanel.add(venue);
//        buttonPanel.add(subject);
//        buttonPanel.add(groups);
//        buttonPanel.add(viewTable);
//        buttonPanel.add(viewLectureTime); // Add the new button to the panel
//
//        // Add the button panel to the west side of the frame
//        add(buttonPanel, BorderLayout.WEST);
//
//        // Create a panel to hold the content
//        contentPanel = new JPanel();
//        contentPanel.setLayout(new BorderLayout());
//
//        // Create a panel to hold VenueGui, LectureGui, GroupsGui, GenerateGui, SubjectGui, and ViewTable
//        JPanel contentHolder = new JPanel(new CardLayout());
//
//        // Initial image panel
//        JPanel imagePanel = new JPanel(new BorderLayout());
//
//        // Load the image icon once and keep it in memory
//        imageIcon = new ImageIcon("C:\\Users\\DELL\\Desktop\\ProjectFinalAssignment\\src\\menuwallpaper.jpg"); // Ensure the path is correct
//
//        // Set up the label with the image, but don't scale yet
//        imageLabel = new JLabel();
//        imagePanel.add(imageLabel, BorderLayout.CENTER);
//
//        // Add the imagePanel to contentHolder
//        contentHolder.add(imagePanel, "Image");
//
//        // Create instances of GUI panels
//        VenueGui venueGui = new VenueGui();
//        LectureGui lectureGui = new LectureGui();
//        GroupsGui groupsGui = new GroupsGui();
//        GenerateGui generateGui = new GenerateGui();
//        SubjectGui subjectGui = new SubjectGui();
//        ViewTable viewTableGui = new ViewTable();
//        ViewLectureTime  viewLectureTimeGui = new ViewLectureTime (); // Add the ViewLectureTime GUI
//
//        // Add other GUI panels to the contentHolder
//        contentHolder.add(venueGui, "Venue");
//        contentHolder.add(lectureGui, "Lecture");
//        contentHolder.add(groupsGui, "Groups");
//        contentHolder.add(generateGui, "Generate");
//        contentHolder.add(subjectGui, "Subject");
//        contentHolder.add(viewTableGui, "ViewTable");
//        contentHolder.add(viewLectureTimeGui, "ViewLectureTime"); // Add the ViewLectureTime panel
//
//        contentPanel.add(contentHolder, BorderLayout.CENTER);
//
//        // Add content panel to the center of the frame
//        add(contentPanel, BorderLayout.CENTER);
//
//        // Initialize CardLayout
//        cardLayout = (CardLayout) contentHolder.getLayout();
//        cardLayout.show(contentHolder, "Image");
//
//        // Add action listeners for buttons
//        lecture.addActionListener(e -> cardLayout.show(contentHolder, "Lecture"));
//        venue.addActionListener(e -> cardLayout.show(contentHolder, "Venue"));
//        groups.addActionListener(e -> cardLayout.show(contentHolder, "Groups"));
//        generate.addActionListener(e -> cardLayout.show(contentHolder, "Generate"));
//        subject.addActionListener(e -> cardLayout.show(contentHolder, "Subject"));
//        viewTable.addActionListener(e -> cardLayout.show(contentHolder, "ViewTable"));
//        viewLectureTime.addActionListener(e -> cardLayout.show(contentHolder, "ViewLectureTime")); // Add listener for new button
//
//        // Make the frame visible
//        setVisible(true);
//
//        // Add listener to dynamically resize the image when the window is resized
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                resizeImage();
//            }
//        });
//
//        // Initial image scaling
//        resizeImage();
//    }
//
//    // Resize the image based on the current size of the content panel
//    private void resizeImage() {
//        // Get the current width and height of the content panel or use a default size
//        int panelWidth = contentPanel.getWidth();
//        int panelHeight = contentPanel.getHeight();
//
//        if (panelWidth > 0 && panelHeight > 0) {
//            // Scale the image to fit the content panel while maintaining aspect ratio
//            Image scaledImage = imageIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
//            imageLabel.setIcon(new ImageIcon(scaledImage));
//        }
//    }
//}

package za.ac.cput.timetableproject.gui;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.*;

public class MainGui extends JFrame {

    private JButton generate, venue, subject, lecture, groups, viewTable, viewLectureTime;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JLabel imageLabel;
    private ImageIcon imageIcon;
    private JButton lastClickedButton; // To track the last clicked button

    public MainGui() throws SQLException {
        generate = new JButton("Generate");
        lecture = new JButton("Lecture");
        venue = new JButton("Venue");
        subject = new JButton("Subject");
        groups = new JButton("Groups");
        viewTable = new JButton("View Table");
        viewLectureTime = new JButton("View Lecture Time");

        // Add hover and click effect to buttons
        addHoverAndClickEffect(generate);
        addHoverAndClickEffect(lecture);
        addHoverAndClickEffect(venue);
        addHoverAndClickEffect(subject);
        addHoverAndClickEffect(groups);
        addHoverAndClickEffect(viewTable);
        addHoverAndClickEffect(viewLectureTime);

        setGui();
    }

    private void setGui() throws SQLException {
        setTitle("Main GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 1));

        buttonPanel.add(generate);
        buttonPanel.add(lecture);
        buttonPanel.add(venue);
        buttonPanel.add(subject);
        buttonPanel.add(groups);
        buttonPanel.add(viewTable);
        buttonPanel.add(viewLectureTime);

        add(buttonPanel, BorderLayout.WEST);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        JPanel contentHolder = new JPanel(new CardLayout());
        JPanel imagePanel = new JPanel(new BorderLayout());

        imageIcon = new ImageIcon("C:\\Users\\DELL\\Desktop\\ProjectFinalAssignment\\src\\menuwallpaper.jpg");

        imageLabel = new JLabel();
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        contentHolder.add(imagePanel, "Image");

        VenueGui venueGui = new VenueGui();
        LectureGui lectureGui = new LectureGui();
        GroupsGui groupsGui = new GroupsGui();
        GenerateGui generateGui = new GenerateGui();
        SubjectGui subjectGui = new SubjectGui();
        ViewTable viewTableGui = new ViewTable();
        ViewLectureTime viewLectureTimeGui = new ViewLectureTime();

        contentHolder.add(venueGui, "Venue");
        contentHolder.add(lectureGui, "Lecture");
        contentHolder.add(groupsGui, "Groups");
        contentHolder.add(generateGui, "Generate");
        contentHolder.add(subjectGui, "Subject");
        contentHolder.add(viewTableGui, "ViewTable");
        contentHolder.add(viewLectureTimeGui, "ViewLectureTime");

        contentPanel.add(contentHolder, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        cardLayout = (CardLayout) contentHolder.getLayout();
        cardLayout.show(contentHolder, "Image");

        lecture.addActionListener(e -> cardLayout.show(contentHolder, "Lecture"));
        venue.addActionListener(e -> cardLayout.show(contentHolder, "Venue"));
        groups.addActionListener(e -> cardLayout.show(contentHolder, "Groups"));
        generate.addActionListener(e -> cardLayout.show(contentHolder, "Generate"));
        subject.addActionListener(e -> cardLayout.show(contentHolder, "Subject"));
        viewTable.addActionListener(e -> cardLayout.show(contentHolder, "ViewTable"));
        viewLectureTime.addActionListener(e -> cardLayout.show(contentHolder, "ViewLectureTime"));

        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImage();
            }
        });

        resizeImage();
    }

    // Add hover and click effect method
    private void addHoverAndClickEffect(JButton button) {
        Color originalColor = button.getBackground();
        button.setOpaque(true);
        button.setBorderPainted(false);

        // Mouse hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != lastClickedButton) {
                    button.setBackground(Color.GREEN); // Set background to green on hover
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != lastClickedButton) {
                    button.setBackground(originalColor); // Restore original background on hover exit
                }
            }
        });

        // Button click action
        button.addActionListener(e -> {
            if (lastClickedButton != null) {
                lastClickedButton.setBackground(originalColor); // Reset last clicked button's background
            }
            button.setBackground(Color.lightGray); // Set clicked button to red
            lastClickedButton = button; // Update the last clicked button
        });
    }

    private void resizeImage() {
        int panelWidth = contentPanel.getWidth();
        int panelHeight = contentPanel.getHeight();

        if (panelWidth > 0 && panelHeight > 0) {
            Image scaledImage = imageIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }
}
