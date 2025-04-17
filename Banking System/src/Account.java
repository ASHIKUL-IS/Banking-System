import java.io.Serializable;

public class Account implements Serializable {
    private int accountNumber;
    private String name;
    private String father;
    private String mother;
    private String gender;
    private int age;
    private String mobile;
    private String password;
    private double balance;

    public Account(int accountNumber, String name, String father, String mother, String gender, int age, String mobile, String password) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.father = father;
        this.mother = mother;
        this.gender = gender;
        this.age = age;
        this.mobile = mobile;
        this.password = password;
        this.balance = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void setPassword(String newPass) {
        password = newPass;
    }

    public boolean transferTo(Account receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            receiver.deposit(amount);
            return true;
        }
        return false;
    }
}
