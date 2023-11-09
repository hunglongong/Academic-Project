package longong.atmapplication.data;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String cardNumber;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public Customer(){};
    public Customer(String name, String cardNumber, String pin, double balance) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void addToTransactionHistory(String transaction) {
        transactionHistory.add(transaction);
    }

    public void addTransaction(Transaction transaction) {
    }
}
