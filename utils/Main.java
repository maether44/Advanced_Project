package utils;

import gui.ApplyPage;
import gui.LoginPage;
import model.Scholarship;
import model.Student;

import java.util.List;

import javax.swing.SwingUtilities;

import database.DatabaseHelper;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            new ApplyPage();
            new LoginPage();
            

            Student student = new Student("Malek", 3.8, 4000.0, 20);
            DatabaseHelper.insertStudent(student);
            Scholarship s1 = new Scholarship("Tech Leaders", 3.7, 5000, 18, 26);
            Scholarship s2 = new Scholarship("Women in STEM", 3.0, 6000, 18, 30);
            Student st1 = new Student("Aicha", 3.6, 3200, 21);

DatabaseHelper.insertStudent(st1);

            List<Scholarship> eligible = DatabaseHelper.getEligibleScholarships(student);
            System.out.println("Eligible Scholarships:");
            eligible.forEach(s -> System.out.println("✔ " + s.getName()));
            
        });
        
    }
}
