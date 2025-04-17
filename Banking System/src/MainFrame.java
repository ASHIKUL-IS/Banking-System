import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Banking Management System");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JButton createBtn = new JButton("Create Account");
        JButton loginBtn = new JButton("User Login");
        JButton adminBtn = new JButton("Admin Login");
        JButton exitBtn = new JButton("Exit");

        createBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        loginBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        adminBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        exitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        add(createBtn);
        add(loginBtn);
        add(adminBtn);
        add(exitBtn);

        createBtn.addActionListener(e -> {
            JTextField name = new JTextField();
            JTextField father = new JTextField();
            JTextField mother = new JTextField();
            JTextField gender = new JTextField();
            JTextField age = new JTextField();
            JTextField mobile = new JTextField();
            JPasswordField pass = new JPasswordField();

            JPanel panel = new JPanel(new GridLayout(7, 2));
            panel.add(new JLabel("Name:")); panel.add(name);
            panel.add(new JLabel("Father's Name:")); panel.add(father);
            panel.add(new JLabel("Mother's Name:")); panel.add(mother);
            panel.add(new JLabel("Gender:")); panel.add(gender);
            panel.add(new JLabel("Age:")); panel.add(age);
            panel.add(new JLabel("Mobile:")); panel.add(mobile);
            panel.add(new JLabel("Password:")); panel.add(pass);

            int result = JOptionPane.showConfirmDialog(this, panel, "Create Account", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                Account acc = AccountManager.createAccount(
                        name.getText(),
                        father.getText(),
                        mother.getText(),
                        gender.getText(),
                        Integer.parseInt(age.getText()),
                        mobile.getText(),
                        new String(pass.getPassword())
                );
                JOptionPane.showMessageDialog(this, "Account created. Your Account Number is: " + acc.getAccountNumber());
            }
        });

        loginBtn.addActionListener(e -> {
            String accStr = JOptionPane.showInputDialog(this, "Enter Account Number:");
            JPasswordField pass = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(this, pass, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int accNo = Integer.parseInt(accStr);
                String password = new String(pass.getPassword());
                Account acc = AccountManager.login(accNo, password);
                if (acc != null) {
                    new UserPanel(acc);
                } else {
                    JOptionPane.showMessageDialog(this, "Login failed.");
                }
            }
        });

        adminBtn.addActionListener(e -> {
            JPasswordField pass = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(this, pass, "Admin Password", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String password = new String(pass.getPassword());
                if (password.equals("admin123")) {
                    new AdminPanel();
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong admin password.");
                }
            }
        });

        exitBtn.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
