package gui;

import model.Scholarship;
import database.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScholarshipInfoPage extends JFrame {

    public ScholarshipInfoPage() {
        setTitle("Available Scholarships");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Available Scholarships", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(63, 81, 181));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        JTextArea scholarshipsArea = new JTextArea();
        scholarshipsArea.setEditable(false);
        scholarshipsArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        List<Scholarship> scholarships = DatabaseHelper.getAllScholarships();
        for (Scholarship s : scholarships) {
            scholarshipsArea.append("🎓 " + s.getName() + "\n");
            scholarshipsArea.append("   - Min GPA: " + s.getMinGPA() + "\n");
            scholarshipsArea.append("   - Max Income: $" + s.getMaxIncome() + "\n");
            scholarshipsArea.append("   - Age Range: " + s.getMinAge() + " - " + s.getMaxAge() + "\n\n");
        }

        JScrollPane scrollPane = new JScrollPane(scholarshipsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        JButton nextButton = new JButton("Proceed to Apply");
        nextButton.setBackground(new Color(76, 175, 80));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        nextButton.setFocusPainted(false);
        nextButton.addActionListener(e -> {
            new ApplyPage(() -> {
                new LandingPage();
            });
            dispose();
        });

        JPanel bottom = new JPanel();
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        bottom.add(nextButton);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}
