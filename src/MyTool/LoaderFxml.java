/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTool;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author 振
 */
public class LoaderFxml {
    public  static void load(String fxmlLocation,String stageName) {
    	 FXMLLoader load=new FXMLLoader(LoaderFxml.class.getResource(fxmlLocation));
         Parent root=null;
 		try {
 			root = (Parent)load.load();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
          Scene scene=new Scene(root);
          Stage stage=new Stage();
          stage.setTitle(stageName);
          stage.getIcons().add(new Image(("images/icon.jpg")));
         stage.setScene(scene);
         stage.show();
    }

}
