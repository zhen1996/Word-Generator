package Starter;

import java.io.IOException;

import MyTool.LoaderFxml;
import javafx.application.Application;
import javafx.stage.Stage;

public class MarkManageStarter extends Application{
    
   @Override
    public void start(Stage stage)  {
	   LoaderFxml.load( "/fxml/MarkSet.fxml","项目命名");
    }
    
    public static void main(String[] args) throws IOException {
        Application.launch(NameProjectStarter.class, args);       
    }
}
