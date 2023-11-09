package longong.atmapplication.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import longong.atmapplication.HelloApplication;
import longong.atmapplication.data.Customer;
import longong.atmapplication.data.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionHistoryController extends MainMenuController{
    @FXML
    private Label timeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, Date> dateColumn;

    @FXML
    private TableColumn<Transaction, String> typeColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, Double> balanceColumn;

    private Customer customer;

    private final String transactionLogFile = "src/main/java/longong/atmapplication/data/transactionLog.txt";

    private List<Transaction> transactionList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set time and date
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            String time = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            timeLabel.setText(time);
            dateLabel.setText(currentDate.toString());
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //set button property
        backButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

    }
    public void initData(Customer customer) {
        // Initialize table columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        this.customer = customer;
        // Load transaction history
        loadTransactionHistory();
    }

    private void loadTransactionHistory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(transactionLogFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                String customerName = parts[0];
                String cardNumber = parts[1];
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
                Date date = dateFormat.parse(parts[2]);
                Transaction.TransactionType type = Transaction.TransactionType.valueOf(parts[3]);
                double amount = Double.parseDouble(parts[4]);
                double balance = Double.parseDouble(parts[5]);

                if(cardNumber.equals(customer.getCardNumber()))
                {
                    Transaction transaction = new Transaction(date, type, amount, balance);
                    transactionList.add(transaction);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        setTableStyle();
        // Add transactions to table
        transactionTable.getItems().addAll(transactionList);

    }

    private void setTableStyle() {
        // Set header style
        transactionTable.getStyleClass().add("transaction-table");

        // Set amount column cell factory
        amountColumn.setCellFactory(column -> {
            return new TableCell<Transaction, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        // Get the current Transaction object
                        Transaction transaction = getTableView().getItems().get(getIndex());

                        // Set the text color based on the typeColumn value
                        if (transaction.getAmount() >= 0) {
                            setTextFill(Color.GREEN);
                            setText(String.format("$%.2f", item));
                        } else {
                            setTextFill(Color.RED);
                            setText(String.format("-$%.2f", -item));
                        }

                        // Set the amount text
                        //setText(String.format("$%.2f", item));
                    }
                }
            };
        });
    }
    public void goBack(ActionEvent event) {
        try {
            // Load the FXML file for the main menu window
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
            Parent root = loader.load();
            MainMenuController controller = loader.getController();
            controller.setAcceptedCustomer(customer);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the scene and show the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
