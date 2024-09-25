package com.example.personalproj;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import java.time.LocalDate;

//Patient class to store a specific patients information
class patient{
    public String fName;
    public String lName;
    public LocalDate birthday;
    public String height;
    public String weight;
    public String allergies;
    public String conditions;
}
public class GuiController {
    //Variables for each of the objects pulled from the .fxml file
    @FXML
    private TabPane tabs;
    @FXML
    private Tab addTab;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    public Button sub;
    public Tab loginTab;
    public Button subPat;
    public TextField FName;
    public TextField LName;
    public DatePicker date;
    public Button logout;
    public Text incorrect;
    public Tab plookup;
    public TextField height;
    public TextField weight;
    public TextArea allergies;
    public TextArea conditions;
    public TextField FNameS;
    public TextField LNameS;
    public DatePicker dateS;
    public Label showName;
    public Label showBirthday;
    public Button searchPat;
    public Label curUser;
    public Label showWeight;
    public ListView<String> allergList = new ListView<>();
    public ListView<String> condList = new ListView<>();
    public Label showHeight;

    //Functions for altering state of the stage, or getting information from the GUI
    public void disableLog()
    {
        tabs.getTabs().remove(0);
    }
    public void enableTab(){
        addTab.setDisable(false);
        logout.setVisible(true);
        plookup.setDisable(false);
    }
    public void logout()
    {
        incorrect.setVisible(false);
        curUser.setText("");
        sub.setTranslateY(-10);
        logout.setVisible(false);
        addTab.setDisable(true);
        plookup.setDisable(true);
        tabs.getTabs().add(0, loginTab);
        tabs.getSelectionModel().select(loginTab);
        clearAdd();
        clearSearch();
    }
    public String getPassword() {
        String password = pass.getText();
        pass.clear();
        return password;
    }

    public void setIncorrect()
    {
        incorrect.setVisible(true);
        sub.setTranslateY(15);
    }
    public patient getPatient()
    {
        patient p = new patient();
        p.fName = FName.getText().toLowerCase();
        p.lName = LName.getText().toLowerCase();
        p.birthday = date.getConverter().fromString(date.getEditor().getText());
        p.height = height.getText();
        p.weight = weight.getText();
        p.allergies = allergies.getText();
        p.conditions = conditions.getText();
        return p;
    }
    public void clearSearch()
    {
        allergList.getItems().clear();
        condList.getItems().clear();
        showHeight.setText("");
        showBirthday.setText("");
        showName.setText("");
        showWeight.setText("");
        FNameS.clear();
        LNameS.clear();
        dateS.getEditor().clear();
    }
    public void clearAdd()
    {
        FName.clear();
        LName.clear();
        date.getEditor().clear();
        height.clear();
        weight.clear();
        allergies.clear();
        conditions.clear();
    }
    public patient searchPatientGet()
    {
        allergList.getItems().clear();
        condList.getItems().clear();
        showHeight.setText("");
        showBirthday.setText("");
        showName.setText("");
        showWeight.setText("");
        patient p = new patient();
        p.fName = FNameS.getText().toLowerCase();
        p.lName = LNameS.getText().toLowerCase();
        p.birthday = dateS.getConverter().fromString((dateS.getEditor().getText()));
        return p;
    }
    public String getUser()
    {
        String username = user.getText();
        user.clear();
        return username;
    }
}