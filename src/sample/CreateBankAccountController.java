package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class CreateBankAccountController implements Initializable {

    @FXML
    private ChoiceBox<String> accountTypeChoiceBox;

    @FXML
    private Button cancelButton;


    public void cancelButtonOnAction(ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountTypeChoiceBox.getItems().add("Saving Account");
        accountTypeChoiceBox.getItems().add("General Account");
        accountTypeChoiceBox.getItems().add("Student Account");
        accountTypeChoiceBox.getItems().add("Business Account");
        accountTypeChoiceBox.getItems().add("Joint Account");

        accountTypeChoiceBox.setValue("General Account");
    }

}