import javax.swing.*;
import java.awt.*;

public class UserPanel extends JFrame {
    private Account user;

    public UserPanel(Account user) {
        this.user = user;
        setTitle("User Panel - Account No: " + user.getAccountNumber());
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));
        setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");
        JButton balanceBtn = new JButton("Check Balance");
        JButton resetBtn = new JButton("Reset Password");
        JButton exitBtn = new JButton("Logout");

        depositBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        withdrawBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        transferBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        balanceBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        resetBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        exitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        add(depositBtn);
        add(withdrawBtn);
        add(transferBtn);
        add(balanceBtn);
        add(resetBtn);
        add(exitBtn);

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            if (input != null) {
                double amount = Double.parseDouble(input);
                user.deposit(amount);
                AccountManager.saveAccounts();
                JOptionPane.showMessageDialog(this, "Deposited successfully.");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String pass = JOptionPane.showInputDialog(this, "Enter password:");
            if (pass != null && pass.equals(user.getPassword())) {
                String input = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
                if (input != null) {
                    double amount = Double.parseDouble(input);
                    if (user.withdraw(amount)) {
                        AccountManager.saveAccounts();
                        JOptionPane.showMessageDialog(this, "Withdrawn successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient balance.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong password.");
            }
        });

        transferBtn.addActionListener(e -> {
            String pass = JOptionPane.showInputDialog(this, "Enter password:");
            if (pass != null && pass.equals(user.getPassword())) {
                String accStr = JOptionPane.showInputDialog(this, "Enter receiver account number:");
                String amtStr = JOptionPane.showInputDialog(this, "Enter amount:");
                if (accStr != null && amtStr != null) {
                    int accNo = Integer.parseInt(accStr);
                    double amount = Double.parseDouble(amtStr);
                    Account receiver = null;
                    for (Account a : AccountManager.getAccounts()) {
                        if (a.getAccountNumber() == accNo) {
                            receiver = a;
                            break;
                        }
                    }
                    if (receiver != null && user.transferTo(receiver, amount)) {
                        AccountManager.saveAccounts();
                        JOptionPane.showMessageDialog(this, "Transferred successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Transfer failed.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong password.");
            }
        });

        balanceBtn.addActionListener(e -> {
            String pass = JOptionPane.showInputDialog(this, "Enter password:");
            if (pass != null && pass.equals(user.getPassword())) {
                JOptionPane.showMessageDialog(this, "Balance: " + user.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Wrong password.");
            }
        });

        resetBtn.addActionListener(e -> {
            String oldPass = JOptionPane.showInputDialog(this, "Enter old password:");
            if (oldPass != null && oldPass.equals(user.getPassword())) {
                String newPass = JOptionPane.showInputDialog(this, "Enter new password:");
                if (newPass != null) {
                    user.setPassword(newPass);
                    AccountManager.saveAccounts();
                    JOptionPane.showMessageDialog(this, "Password changed.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong password.");
            }
        });

        exitBtn.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }
}
