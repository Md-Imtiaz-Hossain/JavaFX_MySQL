package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController  {



    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();


    @FXML
    private Button cancelButton,nextButton;

    @FXML
    private Label warning,warning1,warning2;

    @FXML
    private TextField accountNumber,phone;


    public void logInOnAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("CreateBankAccount.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create A Bank Account");
            Scene scene = new Scene(root, 550, 900); //"/image/login.png"
            scene.getStylesheets().add("/Style/CreateBankAccount.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //get a handle to the stage
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            //do what you have to do
            stage.close();
        }
    }


    public void cancelButtonOnAction(ActionEvent event){
         //get a handle to the stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
         //do what you have to do
        stage.close();
    }


    public void nextButtonOnAction(ActionEvent event) {

        Connection connection = createConnectionDemo.createConnection();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("  SELECT count(1) FROM abcd_bank.users where Account_Number = '"+ accountNumber.getText() +"' and Phone = '"+ phone.getText() +"';  ");

            while (resultSet.next()){

                if (accountNumber.getText().isEmpty() && phone.getText().isEmpty()){
                    warning2.setText("");
                    warning1.setText("");
                    warning.setText("Enter Account Number And Phone");
                    System.out.println("Please Enter Username And Password");
                }else if (accountNumber.getText().isEmpty()){
                    warning.setText("");
                    warning2.setText("");
                    warning1.setText("Enter Account Number");
                    System.out.println("Please Enter Account Number");
                }else if(phone.getText().isEmpty()){
                    warning.setText("");
                    warning1.setText("");
                    warning2.setText("Enter Account Phone");
                    System.out.println("Please Enter Account Phone");
                } else {
                            if (resultSet.getInt(1) == 1){

                                        try{
                                            Parent root = FXMLLoader.load(getClass().getResource("CreateInternetBankingAccount.fxml"));
                                            Stage primaryStage = new Stage();
                                            primaryStage.setTitle("Create A Bank Account");
                                            Scene scene = new Scene(root, 338, 463); //"/image/login.png"
                                            scene.getStylesheets().add("/Style/CreateBankAccount.css");
                                            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
                                            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
                                            primaryStage.setScene(scene);

                                            primaryStage.show();
                                        }catch (IOException e){
                                            e.printStackTrace();
                                        }finally {
                                            //get a handle to the stage
                                            Stage stage = (Stage) cancelButton.getScene().getWindow();
                                            //do what you have to do
                                            stage.close();
                                        }
                            }else {
                                warning2.setText("");
                                warning1.setText("");
                                warning.setText("Invalid!Need An Account? Please-->");
                                System.out.println("Invalid UserName or Password!!!");
                            }
                }
            }
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
