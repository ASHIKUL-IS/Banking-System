import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JFrame {
    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll, BorderLayout.CENTER);

        ArrayList<Account> accounts = AccountManager.getAccounts();
        StringBuilder sb = new StringBuilder();
        for (Account acc : accounts) {
            sb.append("Account No: ").append(acc.getAccountNumber()).append("\n");
            sb.append("Name: ").append(acc.getName()).append("\n");
            sb.append("Balance: ").append(acc.getBalance()).append("\n");
            sb.append("---------------------------\n");
        }
        textArea.setText(sb.toString());

        setVisible(true);
    }
}
