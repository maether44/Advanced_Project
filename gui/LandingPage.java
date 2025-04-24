package gui;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JFrame {

    public LandingPage() {
        setTitle("🎓 Scholarship Eligibility System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JLabel title = new JLabel("Welcome!", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(new Color(33, 150, 243));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JButton applyButton = new JButton("Apply for Scholarship");
        styleButton(applyButton, new Color(76, 175, 80));
        applyButton.addActionListener(e -> {
            new ScholarshipInfoPage();
            dispose();
        });

        JButton adminLoginButton = new JButton("Admin Login");
        styleButton(adminLoginButton, new Color(244, 67, 54));
        adminLoginButton.addActionListener(e -> {
            new LoginPage();
            dispose();
        });

        buttonPanel.add(applyButton);
        buttonPanel.add(adminLoginButton);

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(200, 40));
    }
}
