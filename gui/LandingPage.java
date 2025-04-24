package gui;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JFrame {

    public LandingPage() {
        setTitle("Scholarship Eligibility System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Welcome!", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(33, 150, 243));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton applyButton = new JButton("Apply for Scholarship");
        applyButton.setBackground(new Color(76, 175, 80));
        applyButton.setForeground(Color.WHITE);
        applyButton.setFocusPainted(false);
        applyButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        applyButton.addActionListener(e -> {
            new ApplyPage();
            dispose();
        });

        JButton adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setBackground(new Color(244, 67, 54));
        adminLoginButton.setForeground(Color.WHITE);
        adminLoginButton.setFocusPainted(false);
        adminLoginButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        adminLoginButton.addActionListener(e -> {
            new LoginPage();
            dispose();
        });

        buttonPanel.add(applyButton);
        buttonPanel.add(adminLoginButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
