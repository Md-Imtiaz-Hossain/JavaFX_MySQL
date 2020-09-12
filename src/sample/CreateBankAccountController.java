package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class  CreateBankAccountController implements Initializable {

    @FXML
    private ChoiceBox<String> branchChoiceBox,accountTypeChoiceBox,genderChoiceBox,countryChoiceBox;

    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;


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

        genderChoiceBox.getItems().addAll("Male","Female");
        genderChoiceBox.setValue("Male");

        countryChoiceBox.getItems().addAll("Algeria","Algeria","Andorra","Andorra","Angola","Antarctica","Antarctica","Antigua","and","Barbuda","Argentina","Armenia","Armenia","Aruba","Austria","Azerbaijan","Azerbaijan","Bangladesh","Belarus","Belarus","Belgium","Botswana","Botswana","Bouvet","Island","Brazil","British","Indian","Ocean","Territory","Burundi","Cambodia","Cameroon","Cayman","Islands","Cayman","Islands","Chad","Chad","Chile","China","Congo","Brazzaville","","Costa","Rica","Cuba","Cuba","Cyprus","Cyprus","Côte","D'Ivoire","Ivory","Coast","","Côte","D'Ivoire","Ivory","Coast","","Dominica","Eritrea","Eritrea","Eritrea","Estonia","Ethiopia","Falkland","Islands","Falkland","Islands","Faroe","Islands","French","Polynesia","Gambia","Ghana","Greece","Greece","Greenland","Grenada","Grenada","Guam","Guam","Guam","Guernsey","Guernsey","Guinea-Bissau","Guinea-Bissau","Guinea-Bissau","Haiti","Holy","See","Vatican","City","State","","Honduras","Iceland","Iceland","India","Indonesia","Indonesia","Indonesia","Iran","Iraq","Iraq","Isle","of","Man","Isle","of","Man","Isle","of","Man","Israel","Italy","Jersey","Jersey","Jersey","Jersey","Jersey","Kenya","Korea","North","Korea","South","Korea","South","Lebanon","Lesotho","Libya","Lithuania","Luxembourg","Luxembourg","Luxembourg","Macao","Macedonia","Malawi","Malaysia","Malaysia","Maldives","Maldives","Mali","Marshall","Islands","Martinique","Mauritania","Mayotte","Mexico","Micronesia","Moldova","Moldova","Montenegro","Montenegro","Montserrat","Montserrat","Mozambique","Mozambique","Myanmar","Netherlands","New","Caledonia","Nicaragua","Niger","Norfolk","Island","Norway","Pakistan","Pakistan","Palau","Palau","Palau","Paraguay","Peru","Portugal","Portugal","Puerto","Rico","Qatar","Romania","Russian","Federation","Rwanda","Saint","Barthélemy","Saint","Barthélemy","Saint","Kitts","and","Nevis","Saint","Lucia","Saint","Martin","Saint","Martin","Saint","Vincent","and","The","Grenadines","Samoa","Samoa","San","Marino","San","Marino","Saudi","Arabia","Senegal","Serbia","Seychelles","Slovakia","Slovakia","Slovenia","Solomon","Islands","Solomon","Islands","Solomon","Islands","South","Africa","South","Georgia","and","The","South","Sandwich","Islands","Spain","Spain","Spain","Spain","Sudan","Sudan","Sudan","Swaziland","Sweden","Switzerland","Taiwan","Taiwan","Tanzania","Thailand","Timor-Leste","Togo","Trinidad","and","Tobago","Trinidad","and","Tobago","Tunisia","Tuvalu","Ukraine","United","Arab","Emirates","Uzbekistan","Uzbekistan","Vanuatu","Vanuatu","Viet","Nam","Virgin","Islands","British","Virgin","Islands","British","Wallis","and","Futuna","Yemen","Zambia","Zambia","Zimbabwe","Åland","Islands");
        countryChoiceBox.setValue("Bangladesh");

        branchChoiceBox.getItems().addAll("Ghorashal","Madhabdi","Manohardi","Narsingdi SME");
        branchChoiceBox.setValue("Ghorashal");


    }




    public void saveButtonOnAction(ActionEvent event) {
        String s =(String) accountTypeChoiceBox.getValue();
        System.out.println("Account Type- "+ s);

        String s1 =(String) genderChoiceBox.getValue();
        System.out.println("Gender- "+ s1);

        String s2 =(String) branchChoiceBox.getValue();
        System.out.println("Branch- "+ s2);

        String s3 =(String) countryChoiceBox.getValue();
        System.out.println("Country- "+ s3);
    }
}