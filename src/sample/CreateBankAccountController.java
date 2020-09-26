package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class  CreateBankAccountController implements Initializable {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();
    Statement statement;

    @FXML
    private ChoiceBox<String> accountTypeChoiceBox,countryChoiceBox,branchChoiceBox,genderChoiceBox;

    @FXML
    private TextField firstName,lastName,email,phone,accountNumber,initialAmount,nidPassportNo;

    @FXML
    private TextArea addressArea;

    @FXML
    private Button cancelButton,saveButton,imageLoadButton;

    @FXML
    private DatePicker birthDate;

    @FXML
    private Label warning;


public void saveButtonOnAction(ActionEvent event) throws SQLException {

    if (!accountTypeChoiceBox.getValue().isEmpty() && !countryChoiceBox.getValue().isEmpty()
            && !branchChoiceBox.getValue().isEmpty() && !genderChoiceBox.getValue().isEmpty()
            && !firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !email.getText().isEmpty()
            && !phone.getText().isEmpty() && !accountNumber.getText().isEmpty() && !initialAmount.getText().isEmpty()
            && !nidPassportNo.getText().isEmpty() && !addressArea.getText().toString().isEmpty()){

        Connection connection = createConnectionDemo.createConnection();

                if (accountNumberValidation()) {
                                if (isExecute()) {
                                    System.out.println("Registration  Success...");

                                    Stage stage = (Stage) saveButton.getScene().getWindow();
                                    stage.close();

                                    TrayNotification t = new TrayNotification();
                                    //AnimationType a = AnimationType.FADE;//AnimationType a = AnimationType.SLIDE;
                                    AnimationType a = AnimationType.POPUP;
                                    t.setAnimationType(a);
                                    t.setTitle("Success !!!");
                                    t.setMessage("Registration Success.");
                                    //t.setNotificationType(NotificationType.ERROR);//t.setNotificationType(NotificationType.NOTICE);  //t.setNotificationType(NotificationType.WARNING);
                                    t.setNotificationType(NotificationType.SUCCESS);
                                    t.showAndDismiss(Duration.millis(4000));


                                    try{
                                        Parent root = FXMLLoader.load(getClass().getResource("Photo&Signature.fxml"));
                                        Stage primaryStage = new Stage();
                                        primaryStage.setTitle("Upload Photo And Signature..");
                                        Scene scene = new Scene(root, 600, 601); //"/image/login.png"
                                        scene.getStylesheets().add("/Style/style.css");
                                        primaryStage.resizableProperty().setValue(false);
                                        primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
                                        primaryStage.setScene(scene);
                                        primaryStage.show();
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }

                                }else {
                                    warning.setText("Try A Different AccountNumber or Email or Phone or NidPassportNo");
                                    System.out.println("Try A Different AccountNumber or Email or Phone or NidPassportNo");
                                    TrayNotification t = new TrayNotification();
                                    //AnimationType a = AnimationType.FADE;//AnimationType a = AnimationType.SLIDE;
                                    AnimationType a = AnimationType.POPUP;
                                    t.setAnimationType(a);
                                    t.setTitle("Error !!!");
                                    t.setMessage("Try Another AccountNumber or Email or Phone or NidPassportNo....");
                                    //t.setNotificationType(NotificationType.ERROR);//t.setNotificationType(NotificationType.NOTICE);  //t.setNotificationType(NotificationType.WARNING);
                                    t.setNotificationType(NotificationType.ERROR);
                                    t.showAndDismiss(Duration.millis(4000));
                                }
                }else {
                    System.out.println("Account Number Must be 16 Digit");
                    warning.setText("Account Number Must be 16 Digit");

                    TrayNotification t = new TrayNotification();
                    //AnimationType a = AnimationType.FADE;//AnimationType a = AnimationType.SLIDE;
                    AnimationType a = AnimationType.POPUP;
                    t.setAnimationType(a);
                    t.setTitle("Error !!!");
                    t.setMessage("Error with account Number.Must be 16 Digit...");
                    //t.setNotificationType(NotificationType.ERROR);//t.setNotificationType(NotificationType.NOTICE);  //t.setNotificationType(NotificationType.WARNING);
                    t.setNotificationType(NotificationType.ERROR);
                    t.showAndDismiss(Duration.millis(4000));
                }


    }else {
        warning.setText("Please Fill up All Fields.");
        TrayNotification t = new TrayNotification();
        //AnimationType a = AnimationType.FADE;//AnimationType a = AnimationType.SLIDE;
        AnimationType a = AnimationType.POPUP;
        t.setAnimationType(a);
        t.setTitle("Error !!!");
        t.setMessage("Please Fill up All Fields.");
        //t.setNotificationType(NotificationType.ERROR);//t.setNotificationType(NotificationType.NOTICE);  //t.setNotificationType(NotificationType.WARNING);
        t.setNotificationType(NotificationType.ERROR);
        t.showAndDismiss(Duration.millis(4000));
    }
}


// SELECT DATE_FORMAT('2007-10-04', '%d  %m  %Y'); -- Ans= 04 10 2007

    public boolean accountNumberValidation() throws SQLException {

    if (accountNumber.getText().length() == 16){
                return true;
        }else {
            return false;
        }
    }



    public boolean emailValidation(String email) {
        if (email.length() == 10) {
            return true;}
        else
            return false;
    }


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

        genderChoiceBox.getItems().addAll("Male","Female","Other");
        genderChoiceBox.setValue("Male");

        countryChoiceBox.getItems().addAll("Algeria","Algeria","Andorra","Andorra","Angola","Antarctica","Antigua and Barbuda","Argentina","Armenia","Armenia","Aruba","Austria","Azerbaijan","Azerbaijan","Bangladesh","Belarus","Belarus","Belgium","Botswana","Botswana","Bouvet","Island","Brazil","British","Indian","Ocean","Territory","Burundi","Cambodia","Cameroon","Cayman","Islands","Cayman","Islands Chad","Chad Chile","China","Congo","Brazzaville","Costa Rica","Cuba","Cuba Cyprus","Cyprus","Côte","D'Ivoire","Iv ory","Coast Côte","D'Ivoire","Ivory","Coast","","Dominica","Eritrea","Eritrea","Eritrea","Estonia","Ethiopia","Falkland","Islands","Falkland","Islands","Faroe","Islands","French","Polynesia","Gambia","Ghana","Greece","Greece Greenland","Grenada","Grenada","Guam","Guam","Guam","Guernsey","Guernsey","Guinea-Bissau","Guinea-Bissau","Guinea-Bissau","Haiti","Holy","See","Vatican","City","State","","Honduras","Iceland","Iceland","India","Indonesia","Indonesia","Indonesia","Iran","Iraq","Iraq","Isle","of","Man","Isle","of","Man","Isle","of","Man","Israel","Italy","Jersey","Jersey","Jersey","Jersey","Jersey","Kenya","Korea","North","Korea","South","Korea","South","Lebanon","Lesotho","Libya","Lithuania","Luxembourg","Luxembourg","Luxembourg","Macao","Macedonia","Malawi","Malaysia","Malaysia","Maldives","Maldives","Mali","Marshall","Islands","Martinique","Mauritania","Mayotte","Mexico","Micronesia","Moldova","Moldova","Montenegro","Montenegro","Montserrat","Montserrat","Mozambique","Mozambique","Myanmar","Netherlands","New","Caledonia","Nicaragua","Niger","Norfolk","Island","Norway","Pakistan","Pakistan","Palau","Palau","Palau","Paraguay","Peru","Portugal","Portugal","Puerto","Rico","Qatar","Romania","Russian","Federation","Rwanda","Saint","Barthélemy","Saint","Barthélemy","Saint","Kitts","and","Nevis","Saint","Lucia","Saint","Martin","Saint","Martin","Saint","Vincent","and","The","Grenadines","Samoa","Samoa","San","Marino","San","Marino","Saudi","Arabia","Senegal","Serbia","Seychelles","Slovakia","Slovakia","Slovenia","Solomon","Islands","Solomon","Islands","Solomon","Islands","South","Africa","South","Georgia","and","The","South","Sandwich","Islands","Spain","Spain","Spain","Spain","Sudan","Sudan","Sudan","Swaziland","Sweden","Switzerland","Taiwan","Taiwan","Tanzania","Thailand","Timor-Leste","Togo","Trinidad","and","Tobago","Trinidad","and","Tobago","Tunisia","Tuvalu","Ukraine","United","Arab","Emirates","Uzbekistan","Uzbekistan","Vanuatu","Vanuatu","Viet","Nam","Virgin","Islands","British","Virgin","Islands","British","Wallis","and","Futuna","Yemen","Zambia","Zambia","Zimbabwe","Åland","Islands");
        countryChoiceBox.setValue("Bangladesh");

        branchChoiceBox.getItems().addAll("Ghorashal","Madhabdi","Manohardi","Narsingdi SME");
        branchChoiceBox.setValue("Ghorashal");


    }


    public boolean isExecute()  {
        Connection connection = createConnectionDemo.createConnection();

            try{
                statement = connection.createStatement();
                statement.execute("INSERT INTO users(Account_TypeChoice,First_Name,Last_Name,Email,Phone,Account_Number,Country_Choice,Branch_Choice,Birth_Date,Initial_Amount,Gender_Choice,Nid_Passport_No,Address) VALUES(   '"+ accountTypeChoiceBox.getValue() +"','"+ firstName.getText() +"','"+ lastName.getText() +"','"+ email.getText() +"','"+ phone.getText() +"','"+ accountNumber.getText() +"','"+ countryChoiceBox.getValue() +"','"+ branchChoiceBox.getValue() +"','"+ birthDate.getValue() +"','"+ initialAmount.getText() +"','"+ genderChoiceBox.getValue() +"','"+ nidPassportNo.getText() +"','"+ addressArea.getText() +"'      )");
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }

    }




}