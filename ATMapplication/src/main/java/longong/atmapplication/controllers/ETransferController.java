package longong.atmapplication.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import longong.atmapplication.HelloApplication;
import longong.atmapplication.data.Customer;
import longong.atmapplication.data.Transaction;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ETransferController extends MainMenuController implements Initializable {

    @FXML
    private TextField senderField;
    @FXML
    private ImageView etransferImage;
    @FXML
    private ImageView etransferLogo;
    @FXML
    private TextField receiverField;

    @FXML
    private TextField amountField;
    @FXML
    private Button backButton;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label errorMessage;
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
    private Customer senderAccount;
    private Customer receiverAccount;

    private final String bankAccountFile = "src/main/java/longong/atmapplication/data/bankAccounts";
    private final String transactionLogFile = "src/main/java/longong/atmapplication/data/transactionLog.txt";
    private double amount;
    boolean isValid;
    boolean collisionAccount;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        // Add a listener to the receiver field's focus property
        receiverField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Set the focus to the pin field
                receiverField.requestFocus();

                // Set the action for each number button to append the corresponding text to the pin field
                button1.setOnAction(event -> receiverField.appendText(button1.getText()));
                button2.setOnAction(event -> receiverField.appendText(button2.getText()));
                button3.setOnAction(event -> receiverField.appendText(button3.getText()));
                button4.setOnAction(event -> receiverField.appendText(button4.getText()));
                button5.setOnAction(event -> receiverField.appendText(button5.getText()));
                button6.setOnAction(event -> receiverField.appendText(button6.getText()));
                button7.setOnAction(event -> receiverField.appendText(button7.getText()));
                button8.setOnAction(event -> receiverField.appendText(button8.getText()));
                button9.setOnAction(event -> receiverField.appendText(button9.getText()));
                button0.setOnAction(event -> receiverField.appendText(button0.getText()));
                dotButton.setOnAction(event -> receiverField.appendText(dotButton.getText()));
                backspaceButton.setOnAction(event -> {
                    // Get the current text in the amount field
                    String currentText = receiverField.getText();

                    // If the current text is not empty, remove the last character
                    if (!currentText.isEmpty()) {
                        receiverField.setText(currentText.substring(0, currentText.length() - 1));
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

        // get the image file from resources folder
        File file = new File("src/main/java/longong/atmapplication/image/etransfer.jpg");
        Image image = new Image(file.toURI().toString());
        etransferImage.setImage(image);

        File file2 = new File("src/main/java/longong/atmapplication/image/interac-email-transfer-logo.png");
        Image image2 = new Image(file2.toURI().toString());
        etransferLogo.setImage(image2);
    }
    public void initData(Customer customer) {
        // Initialize UI components
        this.senderAccount = customer;
        balanceLabel.setText(String.format("$%.2f", customer.getBalance()));
    }

    @FXML
    private Customer retrieveReceiverAccount(String cardNumberReceiver) {
        try (BufferedReader br = new BufferedReader(new FileReader(bankAccountFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String customerName = parts[0];
                String cardNumber = parts[1];
                String pin = parts[2];
                double balance = Double.parseDouble(parts[3]);

                //create receiver account if cardNumber matching
                if(cardNumberReceiver.equals(cardNumber) && !cardNumberReceiver.equals(senderAccount.getCardNumber()))
                {
                    isValid = true;
                    receiverAccount = new Customer(customerName, cardNumber, pin, balance);
                }
                else if(cardNumberReceiver.equals(senderAccount.getCardNumber()) && cardNumberReceiver.equals(cardNumber)){
                    collisionAccount = true;
                }
            }
            //check if no valid receiver account
            if(isValid == false && collisionAccount == false){
                // Show fail message
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("* " + cardNumberReceiver + " is not a valid account");
                isValid = true;
            }
            else if(collisionAccount == true){
                // Show fail message
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("* Can not send to your own account");
                collisionAccount = false;
            }
        } catch (IOException e) {
            System.err.println("Error reading bankAccounts.txt file: " + e.getMessage());
        }
        return receiverAccount;
    }

    @FXML
    private void transfer() {
        try {
            //set data for receiver account
            receiverAccount = retrieveReceiverAccount(String.valueOf(receiverField.getText()));

            if (receiverAccount != null)
            {
                amount = Double.parseDouble(amountField.getText());
                // Check if sender has enough balance
                if (senderAccount != null && senderAccount.getBalance() >= amount) {
                    // Update sender's balance
                    double senderBalance = senderAccount.getBalance() - amount;
                    senderAccount.setBalance(senderBalance);
                    // Store transaction log for sender
                    Transaction senderTransaction = new Transaction(senderAccount.getName(), senderAccount.getCardNumber(),Transaction.TransactionType.ETRANSFER, -amount, senderAccount.getBalance());
                    senderTransaction.writeToTransactionLog();
                    senderTransaction.updateBalance(senderAccount.getCardNumber(), senderAccount.getBalance());

                    // Update receiver's balance
                    double receiverBalance = receiverAccount.getBalance() + amount;
                    receiverAccount.setBalance(receiverBalance);

                    //update sender balances label
                    balanceLabel.setText(String.format("$%.2f", senderAccount.getBalance()));

                    // Store transaction log for sender
                    Transaction receiverTransaction = new Transaction(receiverAccount.getName(), receiverAccount.getCardNumber(),Transaction.TransactionType.ETRANSFER, amount, receiverAccount.getBalance());
                    receiverTransaction.writeToTransactionLog();
                    receiverTransaction.updateBalance(receiverAccount.getCardNumber(), receiverAccount.getBalance());

                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Transfer successful for $" + amount + " to " + receiverAccount.getName());
                    alert.showAndWait();

                    receiverField.clear();
                    amountField.clear();
                    amount = 0;
                    //receiverAccount = null;
                } else {
                    // Show error message
                    errorMessage.setTextFill(Color.RED);
                    errorMessage.setText("* Not enough balances");

                }
            }else{
                //catch empty field error
                if(receiverField.getText() == null)
                {
                    // Show error message
                    errorMessage.setTextFill(Color.RED);
                    errorMessage.setText("* Receiver field can not null");
                }
                else if(amountField.getText() == null)
                {
                    // Show error message
                    errorMessage.setTextFill(Color.RED);
                    errorMessage.setText("* Amount field can not null");
                }
            }
        } catch (NumberFormatException e) {
            // Show error message
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("* Invalid amount");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goBack(ActionEvent event) {
        try {
            // Load the FXML file for the main menu window
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
            Parent root = loader.load();
            MainMenuController controller = loader.getController();
            controller.setAcceptedCustomer(senderAccount);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the scene and show the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearETransfer(ActionEvent actionEvent) {
        receiverField.clear();
        amountField.clear();
        errorMessage.setText("");
        receiverAccount = null;
    }
}