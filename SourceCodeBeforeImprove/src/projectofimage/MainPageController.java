/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectofimage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author 振
 */
public class MainPageController implements Initializable {
    @FXML
    ImageView image1;
     @FXML
    ImageView image2;
      @FXML
    ImageView image3;
       @FXML
    ImageView image4;
       File i1,i2,i3,i4,i11,i22,i33,i44;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 初始化图片文件
    }    
    @FXML
    private void moving(ActionEvent e) throws FileNotFoundException{
          subMove((ImageView)e.getSource());
    }
    @FXML
    private void out(ActionEvent e) throws FileNotFoundException{
         subOut((ImageView)e.getSource());
    }
    @FXML
    private void clicked(ActionEvent e){
    subClick((ImageView)e.getSource());
    }

    private void subMove(ImageView i) throws FileNotFoundException {
       if(i==image1){
          changeImage(image1,i11);
       }
        if(i==image2){
        changeImage(image1,i22);
        }
        if(i==image3){
        changeImage(image1,i33);
        }
        if(i==image4){
        changeImage(image1,i44);
        }    
    }

    private void subOut(ImageView i) throws FileNotFoundException {
        if(i==image1){
         changeImage(image1,i1);
       }
        if(i==image2){
        changeImage(image1,i2);
        }
        if(i==image3){
        changeImage(image1,i3);
        }
        if(i==image4){
        changeImage(image1,i4);
        }
    }

    private void subClick(ImageView i) {
       if(i==image1){  //新建项目
         
       }
        if(i==image2){//打开项目
        
        }
        if(i==image3){//管理项目
        
        }
        if(i==image4){//标记管理
        
        }
    }

    private void changeImage(ImageView i, File e) throws FileNotFoundException {
       Image image=new Image(new FileInputStream(e));
       i.setImage(image);
    }

   
}
