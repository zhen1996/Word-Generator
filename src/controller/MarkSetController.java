/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import MyTool.LoaderFxml;
import MyTool.MarkManage;
import MyTool.PreMarkGet;
import MyTool.PreMarkSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;


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
    private Label infoShow;
    
    private MarkManage markManage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	PreMarkSet.setPreMark(desList,PreMarkGet.getDescriptions());
    	PreMarkSet.setPreMark(locList,PreMarkGet.getLocations());
    	PreMarkSet.setPreMark(numList,PreMarkGet.getNumbers());
    	markManage=new MarkManage(desList, locList, numList, desEditor, locEditor, numEditor);
    }
    
    
    @FXML
    private void save(MouseEvent event) throws IOException, TemplateException{
      markManage.save();
      infoShow.setText("已保存");
    }
    @FXML
    private void deleteChoosenDes(ActionEvent event) throws IOException, TemplateException{
      markManage.delete(1);
    }
    @FXML
    private void deleteChoosenLoc(ActionEvent event) throws IOException, TemplateException{
    	markManage.delete(2);
    }
    @FXML
    private void deleteChoosenNum(ActionEvent event) throws IOException, TemplateException{
    	markManage.delete(3);
    }
    @FXML
    private void addEditedDes(ActionEvent event) throws IOException, TemplateException{
      markManage.addNew(1);
    }@FXML
    private void addEditedLoc(ActionEvent event) throws IOException, TemplateException{
      markManage.addNew(2);
    }@FXML
    private void addEditedNum(ActionEvent event) throws IOException, TemplateException{
      markManage.addNew(3);
    }
    
    
    
    
    
    
    
   
 // 菜单事件
 	@FXML
 	private void setNewProject(ActionEvent event) throws IOException, TemplateException {
 		LoaderFxml.load("/fxml/NameSet.fxml", "项目命名");

 	}

 	@FXML
 	private void openProject(ActionEvent event) throws IOException, TemplateException {
 		throw new UnsupportedOperationException();
 	}

 	@FXML
 	private void manageProject(ActionEvent event) throws IOException, TemplateException {
 		throw new UnsupportedOperationException();

 	}

 	@FXML
 	private void manageMark(ActionEvent event) throws IOException {
 		LoaderFxml.load("/fxml/MarkSet.fxml", "标记管理界面");
 	}
 	// 菜单事件
    

  
    
}
