package Starter;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import MyTool.LoaderFxml;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author 振
 */
public class NameProjectStarter extends Application{
    
   @Override
    public void start(Stage stage)  {
	   LoaderFxml.load( "/fxml/NameSet.fxml","项目命名");
    }
    
    public static void main(String[] args) throws IOException {
        Application.launch(NameProjectStarter.class, args);       
    }
}

