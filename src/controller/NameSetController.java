/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MyTool.LoaderFxml;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 振
 */
public class NameSetController implements Initializable {
    @FXML
    private TextArea text;
    private Stage primaryStage=null;
    
    @FXML
    private void setProjectName(ActionEvent event) throws Exception{
    String name=text.getText().trim();//得到设置的项目名字
    LoaderFxml.load("/fxml/ProjectSet.fxml", name);
    Stage stage = (Stage) text.getScene().getWindow();
    stage.close();
    }
    

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   public void setStage(Stage stage) {
        this.primaryStage=stage;
    }
    
}
