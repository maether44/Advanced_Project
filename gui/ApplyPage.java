package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.Student;
import database.DatabaseHelper;

public class ApplyPage extends JFrame {

    public ApplyPage() {
        setTitle("🎓 Scholarship Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Main container panel
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Apply for a Scholarship");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        container.add(title);

        // Fields
        JTextField nameField = addField(container, "Full Name");
        JTextField gpaField = addField(container, "GPA");
        JTextField incomeField = addField(container, "Family Income");
        JTextField ageField = addField(container, "Age");

        // Submit Button
        JButton submitBtn = new JButton("Apply");
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setBackground(new Color(70, 130, 180));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setPreferredSize(new Dimension(100, 35));
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(submitBtn);

        add(container);
        setVisible(true);

        // Action Listener
        submitBtn.addActionListener((ActionEvent e) -> {
            try {
                String name = nameField.getText().trim();
                double gpa = Double.parseDouble(gpaField.getText().trim());
                double income = Double.parseDouble(incomeField.getText().trim());
                int age = Integer.parseInt(ageField.getText().trim());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Student student = new Student(name, gpa, income, age);
                DatabaseHelper.insertStudent(student);

                JOptionPane.showMessageDialog(this,
                        "✅ Application submitted successfully for: " + name,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                nameField.setText("");
                gpaField.setText("");
                incomeField.setText("");
                ageField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for GPA, Income, and Age.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again.",
                        "Submission Failed", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    private JTextField addField(JPanel parent, String labelText) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);

        parent.add(label);
        parent.add(field);
        parent.add(Box.createRigidArea(new Dimension(0, 10)));
        return field;
    }
}
