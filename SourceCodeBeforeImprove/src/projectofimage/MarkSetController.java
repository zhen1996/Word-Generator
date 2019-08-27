/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectofimage;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author 振
 */
public class MarkSetController implements Initializable{
    @FXML
    private ListView desList;
    @FXML
    private ListView locList;
    @FXML
    private ListView numList;
    @FXML
    private TextArea desEditor;
    @FXML
    private TextArea locEditor;
    @FXML
    private TextArea numEditor;
    
    
    @FXML
    private void deleteChoosenDes(ActionEvent event) throws IOException, TemplateException{
      
    }
    @FXML
    private void deleteChoosenLoc(ActionEvent event) throws IOException, TemplateException{
      
    }
    @FXML
    private void deleteChoosenNum(ActionEvent event) throws IOException, TemplateException{
      
    }
    @FXML
    private void addEditedDes(ActionEvent event) throws IOException, TemplateException{
      
    }@FXML
    private void addEditedLoc(ActionEvent event) throws IOException, TemplateException{
      
    }@FXML
    private void addEditedNum(ActionEvent event) throws IOException, TemplateException{
      
    }
    
    
    
    
    
    
    
   
    //菜单事件
    @FXML
    private void setNewProject(ActionEvent event) throws IOException, TemplateException{
      System.out.print("cao2");
    }
     @FXML
    private void openProject(ActionEvent event) throws IOException, TemplateException{
      
    }
     @FXML
    private void manageProject(ActionEvent event) throws IOException, TemplateException{
      
    }
     @FXML
    private void manageMark(ActionEvent event) throws IOException, TemplateException{
        System.out.print("cao");
        
    }
 //菜单事件
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
}
