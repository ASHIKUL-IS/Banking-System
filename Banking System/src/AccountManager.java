import java.io.*;
import java.util.ArrayList;

public class AccountManager {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static final String FILE_NAME = "accounts.dat";
    private static int nextAccountNumber = 1001;

    static {
        loadAccounts();
        updateNextAccountNumber();
    }

    private static void updateNextAccountNumber() {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() >= nextAccountNumber) {
                nextAccountNumber = acc.getAccountNumber() + 1;
            }
        }
    }

    public static Account createAccount(String name, String father, String mother, String gender, int age, String mobile, String password) {
        Account acc = new Account(nextAccountNumber, name, father, mother, gender, age, mobile, password);
        accounts.add(acc);
        saveAccounts();
        nextAccountNumber++;
        return acc;
    }

    public static Account login(int accNo, String password) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo && acc.getPassword().equals(password)) {
                return acc;
            }
        }
        return null;
    }

    public static void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace(); // Log to console
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (ArrayList<Account>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            accounts = new ArrayList<>(); // fallback
        }
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }
}
