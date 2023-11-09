package longong.atmapplication.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import longong.atmapplication.HelloApplication;
import longong.atmapplication.data.Customer;
import longong.atmapplication.data.Transaction;
import longong.atmapplication.data.Transaction.TransactionType;

public class WithdrawController extends MainMenuController{

    private Customer customer;
    private double balance;
    private double amount;

    @FXML
    private Label balanceLabel;
    @FXML
    private TextField amountField;
    @FXML
    private Label errorMessage;
    @FXML
    private Label timeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button quickWithdrawButton1;
    @FXML
    private Button quickWithdrawButton2;
    @FXML
    private Button quickWithdrawButton3;
    @FXML
    private Button quickWithdrawButton4;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button0;
    @FXML
    private Button backspaceButton;
    @FXML
    private Button dotButton;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        // Add a listener to the amount field's focus property
        amountField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Set the focus to the pin field
                amountField.requestFocus();

                // Set the action for each number button to append the corresponding text to the pin field
                button1.setOnAction(event -> amountField.appendText(button1.getText()));
                button2.setOnAction(event -> amountField.appendText(button2.getText()));
                button3.setOnAction(event -> amountField.appendText(button3.getText()));
                button4.setOnAction(event -> amountField.appendText(button4.getText()));
                button5.setOnAction(event -> amountField.appendText(button5.getText()));
                button6.setOnAction(event -> amountField.appendText(button6.getText()));
                button7.setOnAction(event -> amountField.appendText(button7.getText()));
                button8.setOnAction(event -> amountField.appendText(button8.getText()));
                button9.setOnAction(event -> amountField.appendText(button9.getText()));
                button0.setOnAction(event -> amountField.appendText(button0.getText()));
                dotButton.setOnAction(event -> amountField.appendText(dotButton.getText()));
                backspaceButton.setOnAction(event -> {
                    // Get the current text in the amount field
                    String currentText = amountField.getText();

                    // If the current text is not empty, remove the last character
                    if (!currentText.isEmpty()) {
                        amountField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                });
            }
        });

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

        quickWithdrawButton1.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        quickWithdrawButton1.setOnMouseEntered(e -> quickWithdrawButton1.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        quickWithdrawButton1.setOnMouseExited(e -> quickWithdrawButton1.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

        quickWithdrawButton2.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        quickWithdrawButton2.setOnMouseEntered(e -> quickWithdrawButton2.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        quickWithdrawButton2.setOnMouseExited(e -> quickWithdrawButton2.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

        quickWithdrawButton3.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        quickWithdrawButton3.setOnMouseEntered(e -> quickWithdrawButton3.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        quickWithdrawButton3.setOnMouseExited(e -> quickWithdrawButton3.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

        quickWithdrawButton4.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        quickWithdrawButton4.setOnMouseEntered(e -> quickWithdrawButton4.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        quickWithdrawButton4.setOnMouseExited(e -> quickWithdrawButton4.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));
    }

    public void initData(Customer customer) {
        this.customer = customer;
        balanceLabel.setText(String.format("$%.2f", customer.getBalance()));
    }
    @FXML
    void withdraw(ActionEvent event) {
        try {
            balance = customer.getBalance();
            // parse the amount to withdraw from the amountField
            amount = Double.parseDouble(amountField.getText());

            // check if the amount is less than or equal to the balance
            if (amount <= balance  && amount >0) {
                // update the balance and balanceLabel
                balance -= amount;
                customer.setBalance(balance);
                balanceLabel.setText(String.format("$%.2f", balance));

                //Create transaction history
                Transaction transaction = new Transaction(customer.getName(), customer.getCardNumber(), TransactionType.WITHDRAW, -amount, balance);
                transaction.writeToTransactionLog();
                transaction.updateBalance(customer.getCardNumber(), balance);

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Withdrawal of " + String.format("$%.2f", amount) + " successful.");
                alert.showAndWait();

                // clear the amountField
                amountField.clear();
                amount = 0;
            }
            else if(amount <= 0){
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("* Please enter amount bigger than 0");
            }else {
                // show an error message if the amount is greater than the balance
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("* You don't have enough money to withdraw");
            }
        } catch (NumberFormatException e) {
            // show an error message if the amountField does not contain a valid number
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Error: invalid input.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void quickWithdraw(ActionEvent event) {
        // check which button was clicked and set the amountField accordingly
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            String buttonText = button.getText();
            if (buttonText.equals("$100")) {
                amountField.setText("100");
            } else if (buttonText.equals("$200")) {
                amountField.setText("200");
            } else if (buttonText.equals("$300")) {
                amountField.setText("300");
            } else if (buttonText.equals("$400")) {
                amountField.setText("400");
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) {
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
        balance = customer.getBalance();
        balanceLabel.setText("Balance: " + String.format("$%.2f", balance));
    }

    @FXML
    void quickWithdraw1(ActionEvent event) {
        amountField.setText("100");
    }

    @FXML
    void quickWithdraw2(ActionEvent event) {
        amountField.setText("200");
    }

    @FXML
    void quickWithdraw3(ActionEvent event) {
        amountField.setText("300");
    }

    @FXML
    void quickWithdraw4(ActionEvent event) {
        amountField.setText("400");
    }

    public void clearWithdraw(ActionEvent actionEvent) {
        amountField.clear();
        amount = 0;
        errorMessage.setText("");

    }
}
