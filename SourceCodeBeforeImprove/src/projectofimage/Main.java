/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectofimage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import MyBean.ImageBean;
import MyBean.MarkBean;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author 振
 */
public class Main extends Application{
    
   @Override
    public void start(Stage stage) throws Exception {
	   /*FXMLLoader load=new FXMLLoader(getClass().getResource("ProjectManage.fxml"));
       Parent root=(Parent)load.load();
       ProjectManageController controller=load.getController();
       controller.setStage(stage);
        Scene scene=new Scene(root);
       stage.setTitle("test");
       stage.setScene(scene);
       stage.show();
	 */
        
      FXMLLoader load=new FXMLLoader(getClass().getResource("NameSet.fxml"));
        Parent root=(Parent)load.load();
        NameSetController controller=load.getController();
        controller.setStage(stage);
         Scene scene=new Scene(root);
        stage.setTitle("请设置设置项目名字");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) throws IOException {
        Application.launch(Main.class, args);       
    }
}

