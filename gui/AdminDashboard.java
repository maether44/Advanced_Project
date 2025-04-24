package gui;

import database.DatabaseHelper;
import model.Scholarship;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("👨‍💼 Admin Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(63, 81, 181));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        // Eligible Students Button
        JButton eligibleBtn = createButton("View Eligible Students");
        eligibleBtn.addActionListener(e -> {
            List<Student> all = DatabaseHelper.getAllStudents();
            StringBuilder msg = new StringBuilder("🎓 Eligible Students:\n\n");
            for (Student s : all) {
                if (!DatabaseHelper.getEligibleScholarships(s).isEmpty()) {
                    msg.append("• ").append(s.getName()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, msg.toString(), "Eligible Students", JOptionPane.INFORMATION_MESSAGE);
        });

        // All Students Button
        JButton allBtn = createButton("View All Students");
        allBtn.addActionListener(e -> {
            List<Student> all = DatabaseHelper.getAllStudents();
            StringBuilder msg = new StringBuilder("👥 All Students:\n\n");
            for (Student s : all) {
                msg.append("• ").append(s.getName())
                        .append(" | GPA: ").append(s.getGpa())
                        .append(" | Income: ").append(s.getFamilyIncome())
                        .append(" | Age: ").append(s.getAge()).append("\n");
            }
            JOptionPane.showMessageDialog(this, msg.toString(), "All Students", JOptionPane.INFORMATION_MESSAGE);
        });

        // Add Scholarship Button
        JButton addBtn = createButton("Add a Scholarship");
        addBtn.addActionListener(e -> showAddScholarshipDialog());

        // Add all three buttons
        buttonPanel.add(eligibleBtn);
        buttonPanel.add(allBtn);
        buttonPanel.add(addBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Logout Button at the bottom
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(Color.GRAY);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        logoutBtn.addActionListener(e -> {
            new LandingPage();
            dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(logoutBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(33, 150, 243));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return btn;
    }

    private void showAddScholarshipDialog() {
        JTextField nameField = new JTextField();
        JTextField gpaField = new JTextField();
        JTextField incomeField = new JTextField();
        JTextField minAgeField = new JTextField();
        JTextField maxAgeField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Scholarship Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Min GPA:"));
        panel.add(gpaField);
        panel.add(new JLabel("Max Family Income:"));
        panel.add(incomeField);
        panel.add(new JLabel("Min Age:"));
        panel.add(minAgeField);
        panel.add(new JLabel("Max Age:"));
        panel.add(maxAgeField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Scholarship", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Scholarship s = new Scholarship(
                        nameField.getText().trim(),
                        Double.parseDouble(gpaField.getText().trim()),
                        Double.parseDouble(incomeField.getText().trim()),
                        Integer.parseInt(minAgeField.getText().trim()),
                        Integer.parseInt(maxAgeField.getText().trim())
                );
                DatabaseHelper.insertScholarship(s);
                JOptionPane.showMessageDialog(this, "✅ Scholarship added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid input. Please check all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
