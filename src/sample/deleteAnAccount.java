package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class deleteAnAccount {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();
    Statement statement;


    @FXML
    TextField accountNumber, reAccountNumber;

    @FXML
    Label warning;

    @FXML
    Button exit;



    public void deleteOnAction(ActionEvent event) {

        if (accountNumber.getText().equals(reAccountNumber.getText())){
            warning.setText("");
            if (isExecute()){
                warning.setText(" Deleting Success !!!");
            }else {
                warning.setText(" Deleting Un success !!!");
            }
        }else {
            warning.setText(" Account Number Must be same !!!");
        }

    }


    public boolean isExecute()  {
        Connection connection = createConnectionDemo.createConnection();
        try{
            statement = connection.createStatement();
            statement.execute(" DELETE from abcd_bank.users where Account_Number = '"+ accountNumber.getText() +"'    ");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }



    public void exitOnAction(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }


}
