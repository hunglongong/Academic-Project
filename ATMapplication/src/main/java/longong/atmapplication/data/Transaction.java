package longong.atmapplication.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Transaction {

    public enum TransactionType {
        WITHDRAW, DEPOSIT, ETRANSFER
    }

    private String customerNamer;
    private String cardNumber;
    private LocalDateTime timestamp;
    private Date date;
    private TransactionType type;
    private double amount;
    private double balance;

    public Transaction(String customerName, String cardNumber, TransactionType type, double amount, double balance) {
        this.timestamp = LocalDateTime.now();
        this.customerNamer = customerName;
        this.cardNumber = cardNumber;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public Transaction(Date date, TransactionType type, double amount, double balance) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void writeToTransactionLog() {
        String fileName = "src/main/java/longong/atmapplication/data/transactionLog.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String logEntry = customerNamer+ "," + cardNumber + "," + timestamp + "," + type + "," + amount + "," + balance;
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing transaction log: " + e.getMessage());
        }
    }

    public void updateBalance(String cardNumber, double newBalance) throws IOException {
        // Read the contents of the file
        List<String> lines = Files.readAllLines(Paths.get("src/main/java/longong/atmapplication/data/bankAccounts"));

        // Loop through each line and split values into an array
        for (int i = 0; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");
            if (values[1].equals(cardNumber)) {
                // Update the balance value in the array
                values[3] = String.format("%.2f", newBalance);

                // Update the line in the list of lines
                lines.set(i, String.join(",", values));

                // Exit the loop since we found the target account
                break;
            }
        }

        // Write the updated contents to the file, overwriting the previous contents
        Files.write(Paths.get("src/main/java/longong/atmapplication/data/bankAccounts"), lines, StandardOpenOption.TRUNCATE_EXISTING);
    }


}
