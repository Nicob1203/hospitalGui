package com.example.personalproj;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.*;
import java.sql.Date;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Application extends javafx.application.Application {
    static String password = "";
    static String username = "";
    static Connection connection;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();
        GuiController control = loader.getController();
        Scene scene = new Scene(root, 600, 300);
            EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    password = control.getPassword();
                    username = control.getUser();
                    //String jdbcUrl = "jdbc:postgresql://34.162.25.191:5432/Hospital?user=postgres&password=password";

                    //String jdbcUrl = "jdbc:postgresql://localhost:6969/nicobartello";
                    try {
                        Class.forName("org.postgresql.Driver");
                        connection = DriverManager.getConnection("jdbc:postgresql://74.98.248.154:5432/hospital?user=" + username + "&password=" + password);
                        if(connection==null)
                            connection = DriverManager.getConnection("jdbc:postgresql://192.168.68.62:5432/hospital?user=" + username + "&password=" + password);
                        if (connection != null) {
                            control.enableTab();
                            control.disableLog();
                            control.curUser.setText("User: " + username);
                        }
                        else
                            control.setIncorrect();

                    } catch (Exception ex) {
                        control.setIncorrect();
                        System.err.println(ex.getMessage());
                    }
                }
            };
            control.sub.setOnMouseClicked(handler);
            control.subPat.setOnMouseClicked(e -> addPatient(control.getPatient()));
            control.searchPat.setOnMouseClicked(e -> searchPatient(control.searchPatientGet(), control));
            control.logout.setOnMouseClicked(e ->logout(connection, control));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setWidth(600);
            stage.setHeight(500);
            stage.show();
            stage.toFront();
            stage.requestFocus();
        }

        public void logout(Connection c, GuiController g)
        {
            try {
                g.logout();
                c.close();
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }
        }

    public void searchPatient(patient p, GuiController control)
    {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            if(p.lName!=null && p.fName!=null && p.birthday!=null){
                String sql = "SELECT height, weight, allergies, conditions FROM patients WHERE firstname = ? AND lastname = ? AND birthday = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, p.fName);
                ps.setString(2, p.lName);
                ps.setDate(3, Date.valueOf(p.birthday));
                rs = ps.executeQuery();
                if(rs.next())
                {
                  p.height = rs.getString("height");
                  p.weight = rs.getString("weight");
                  p.allergies = rs.getString("allergies");
                  p.conditions = rs.getString("conditions");
                }
                control.showName.setText(p.lName.substring(0,1).toUpperCase() + p.lName.substring(1) + ", " + p.fName.substring(0,1).toUpperCase() + p.fName.substring(1));
                control.showBirthday.setText(p.birthday.toString());
                if(p.height!=null)
                    control.showHeight.setText(p.height);
                if(p.weight!=null)
                    control.showWeight.setText(p.weight);
                if(p.allergies!=null){
                    String[] allergyList = p.allergies.split(",");
                    for(String s: allergyList)
                        control.allergList.getItems().add(s.trim());
                }
                if(p.conditions!=null){
                    String[] conditionList = p.conditions.split(",");
                    for(String s: conditionList)
                        control.condList.getItems().add(s.trim());
                }

            }

        }
        catch(Exception e){
            System.err.println(e.getMessage());}
    }
    public void addPatient(patient p)
    {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            if(p.lName!=null && p.fName!=null && p.birthday!=null){
                String sql = "INSERT INTO patients(patientid, firstname, lastname, birthday, height, weight, allergies, conditions) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
                ps = connection.prepareStatement(sql);
                ps.setString(1, p.fName);
                ps.setString(2, p.lName);
                ps.setDate(3, Date.valueOf(p.birthday));
                if(p.height!=null)
                    ps.setString(4, p.height);
                if(p.weight!=null)
                    ps.setDouble(5, Double.parseDouble(p.weight));
                if(p.allergies!=null)
                    ps.setString(6, p.allergies);
                if(p.conditions!=null)
                    ps.setString(7, p.conditions);
                rs = ps.executeQuery();
            }

        }
        catch(Exception e){
            System.err.println(e.getMessage());}
    }

    public static void main(String[] args) {
        launch();
        try{
        connection.close();}
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}