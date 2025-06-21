package reg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExhibitionRegistrationForm extends JFrame {

    // Form components
    JTextField txtRegID, txtName, txtFaculty, txtProjectTitle, txtContact, txtEmail, txtImagePath;
    JLabel imageLabel;
    JButton btnRegister, btnSearch, btnUpdate, btnDelete, btnClear, btnExit, btnBrowse;

    public ExhibitionRegistrationForm() {
        setTitle("VU Exhibition Registration System");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Participant Details"));

        // Labels & Text Fields
        txtRegID = new JTextField();
        txtName = new JTextField();
        txtFaculty = new JTextField();
        txtProjectTitle = new JTextField();
        txtContact = new JTextField();
        txtEmail = new JTextField();
        txtImagePath = new JTextField();
        txtImagePath.setEditable(false);

        inputPanel.add(new JLabel("Registration ID:"));
        inputPanel.add(txtRegID);

        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Faculty:"));
        inputPanel.add(txtFaculty);

        inputPanel.add(new JLabel("Project Title:"));
        inputPanel.add(txtProjectTitle);

        inputPanel.add(new JLabel("Contact Number:"));
        inputPanel.add(txtContact);

        inputPanel.add(new JLabel("Email Address:"));
        inputPanel.add(txtEmail);

        inputPanel.add(new JLabel("Project Image Path:"));
        inputPanel.add(txtImagePath);

        // Browse Image Button
        btnBrowse = new JButton("Browse");
        btnBrowse.addActionListener(e -> browseImage());

        inputPanel.add(new JLabel("Upload Image:"));
        inputPanel.add(btnBrowse);

        // Panel for image display
        JPanel imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createTitledBorder("Project Prototype Image"));
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imagePanel.add(imageLabel);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        btnRegister = new JButton("Register");
        btnSearch = new JButton("Search");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        // Add to button panel
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Events
        btnClear.addActionListener(e -> clearFields());
        btnExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // Browse image
    private void browseImage() {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            txtImagePath.setText(path);
            ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            imageLabel.setIcon(icon);
        }
    }

    // Clear fields
    private void clearFields() {
        txtRegID.setText("");
        txtName.setText("");
        txtFaculty.setText("");
        txtProjectTitle.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtImagePath.setText("");
        imageLabel.setIcon(null);
    }

    // Main method to run GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExhibitionRegistrationForm());
    }
}
