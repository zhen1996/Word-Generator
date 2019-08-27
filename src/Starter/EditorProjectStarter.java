package Starter;

import java.io.IOException;

import MyTool.LoaderFxml;
import javafx.application.Application;
import javafx.stage.Stage;

public class EditorProjectStarter extends Application{
    
   @Override
    public void start(Stage stage) {
	   LoaderFxml.load( "/fxml/ProjectSet.fxml","编辑项目");
    }
    
    public static void main(String[] args) throws IOException {
        Application.launch(NameProjectStarter.class, args);       
    }
}
