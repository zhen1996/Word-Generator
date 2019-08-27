/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectofimage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MyTool.ProjectNameContent;
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
public class RenameToProjectController implements Initializable {

	 @FXML
	    private TextArea text;
	    private Stage primarystage;
	    
	    private String[] projectNames;
	    
	    @FXML
	    private void setProjectName(ActionEvent event) throws IOException{
	    String name=text.getText().trim();
	    FXMLLoader load=new FXMLLoader(getClass().getResource("ProjectSet.fxml"));
	        Parent root=(Parent)load.load();
	        ProjectSetController controller=load.getController();
	        controller.setProjectName(name);
	        for(String projectName:projectNames)
	        	controller.setSingleProject(projectName);
	         Scene scene=new Scene(root);
	         Stage stage=new Stage();
	        stage.setTitle("项目再编辑");
	        stage.setScene(scene);
	        stage.show();
	        primarystage.close();
	    
	    }
	    

	   
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	        // TODO
	    }    
         public void setProjectNames(String projectNames[]) {
        	 this.projectNames=projectNames;
        	 
         }
	   public void setStage(Stage stage) {
	        this.primarystage=stage;
	    }
    
}
