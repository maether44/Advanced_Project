package gui;

import database.DatabaseHelper;
import file.ReportGenerator;
import model.Student;
import model.Scholarship;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminPage extends JFrame {

    public AdminPage() {
        setTitle("🎓 Admin Panel - Scholarship Eligibility");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set a modern UI look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Title Label
        JLabel titleLabel = new JLabel("Eligible Students for Scholarships");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Text area
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setMargin(new Insets(10, 10, 10, 10));
        displayArea.setBackground(new Color(245, 245, 245));
        displayArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        footerPanel.add(closeButton);
        add(footerPanel, BorderLayout.SOUTH);

        // Fetch and display data
        List<Student> allStudents = DatabaseHelper.getAllStudents();
        StringBuilder eligibleStudents = new StringBuilder();

        for (Student s : allStudents) {
            List<Scholarship> eligible = DatabaseHelper.getEligibleScholarships(s);
            if (!eligible.isEmpty()) {
                eligibleStudents.append("Student: ").append(s.getName()).append("\n");
                for (Scholarship sch : eligible) {
                    eligibleStudents.append("  - ").append(sch.getName()).append("\n");
                }
                eligibleStudents.append("\n");
            }
        }

        if (eligibleStudents.length() == 0) {
            eligibleStudents.append("No eligible students found.");
        }

        displayArea.setText(eligibleStudents.toString());

        setVisible(true);
    }
}
