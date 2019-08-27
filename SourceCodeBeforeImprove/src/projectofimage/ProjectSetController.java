/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectofimage;

import MyBean.ImageBean;
import MyBean.ImageBeanWithIndex;
import MyTool.ImageReduce;
import MyTool.ImageViewWithIndex;
import MyTool.ImportProject;
import MyTool.ImportWord;
import MyTool.PreMark;
import MyTool.ReadSingleProject;
import MyTool.WorkBeansList;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author 振
 */
public class ProjectSetController implements Initializable {
    //每个fxml文件里的id变量前必须加@FXML
    @FXML
    private HBox imageList;//小图片展示区
    @FXML
    private Label label;//项目名称
    @FXML
    private ImageView imageShow;//大图片展示区
    @FXML
    private ListView chooserOfDescription;
    @FXML
    private ListView chooserOfLocation;
    @FXML
    private ListView chooserOfNumber;
    @FXML
    private TextArea setOwnDescriptionArea;
    @FXML
    private TextArea setOwnLocationArea;
    @FXML
    private TextField setOwnNumberField;

    @FXML
    private Label informationShow;
    @FXML
    private Label descriptionShow;
    @FXML
    private Label numberShow;
    @FXML
    private Label locationShow;

    private WorkBeansList list;//项目信息类
    private ImageBeanWithIndex currentBean;//当前工作的图像bean
    private int init = 0;//设置初始化的标志位
    private String projectName;
    private Stage stage;

    @FXML
    public void imageInputUseDir(ActionEvent event) throws IOException {  //使用文件夹选择器添加图片
        DirectoryChooser dirChooser1 = new DirectoryChooser();
        dirChooser1.setTitle("请选择你储存图片的文件夹");
        //dirChooser1.setInitialDirectory(value);
        File file = dirChooser1.showDialog(stage);
        File[] f1 = file.listFiles();
        if(f1!=null) {
        for (File e : f1) {
            if (isImage(e)) {
               // e=ImageReduce.of(e);//减少图片的大小
                Image i = new Image(new FileInputStream(e));
                ImageViewWithIndex iv = new ImageViewWithIndex();
                iv.setFitHeight(100);
                iv.setFitWidth(150);
                iv.setImage(i);
                list.addImageBean(e, iv);//添加图片信息到项目组中
                setIcon(iv);//添加小图片到小图片展示区
                setHandle(e, iv);//为每个小图片添加事件
                if (init == 0) {
                    currentBean = list.getFirstBean();
                    setImageShow(currentBean.getImageBean().getImageFile());
                    setText();
                    clearOwnArea();
                    clearPreItemSelected();
                    init++;

                }//初始化界面
            }
        }
        }
    }//使用文件夹选择器添加图片

    @FXML //每个动作处理方法前必须加@FXML
    private void imageInputUseFile(ActionEvent event) throws IOException {   //使用文件选择器添加图片
        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("请选择你要处理的图片");
        // filec.setInitialDirectory(value);
        fileChooser1.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File e = fileChooser1.showOpenDialog(stage);
        if(e!=null){
        if(isImage(e)){
        e=ImageReduce.of(e);//减少图片的大小
        Image i = new Image(new FileInputStream(e));
        ImageViewWithIndex iv = new ImageViewWithIndex();
        iv.setFitHeight(90);
        iv.setFitWidth(150);
        iv.setImage(i);

        list.addImageBean(e, iv);//添加图片信息到项目组中
        setIcon(iv);//添加小图片到小图片展示区
        setHandle(e, iv);//为每个小图片添加事件   
        if (init == 0) {
            currentBean = list.getFirstBean();
            setImageShow(currentBean.getImageBean().getImageFile());
            setText();
            clearOwnArea();
            clearPreItemSelected();
            init++;

        }//初始化界面
        }
        }
    }//使用文件选择器添加图片

    @FXML
    private void nextImage(ActionEvent event) throws FileNotFoundException {
        if (currentBean != null) {
            currentBean = list.next(currentBean);
            setImageShow(currentBean.getImageBean().getImageFile());
            setText();
            clearOwnArea();//清理自定义区
            clearPreItemSelected();
        }
    }  //下一张图片

    @FXML
    private void previousImage(ActionEvent event) throws FileNotFoundException {
        if (currentBean != null) {
            currentBean = list.previous(currentBean);
            setImageShow(currentBean.getImageBean().getImageFile());
            setText();
            clearOwnArea();
            clearPreItemSelected();
        }
    }  //上一张图片

    @FXML
    private void deletCurentImage(ActionEvent event) throws FileNotFoundException {
        if (currentBean != null) {
            imageList.getChildren().remove(currentBean.getIndexImageView());//移除小图片展示区对应的小图片
            currentBean = list.delete(currentBean);
            if (currentBean != null) {
                setImageShow(currentBean.getImageBean().getImageFile());
                setText();
            } else {
                init = 0;
                imageShow.setImage(null);
                informationShow.setText("请添加图片进行编辑");
            }
            clearOwnArea();
            clearPreItemSelected();
        }

    }  //删除当前图片

    private void setHandle(File e, ImageViewWithIndex iv) throws FileNotFoundException {//为每个小图片添加事件

        /* iv.setOnMousePressed(h->{
        imageView.setBackground(new Background(
                new BackgroundImage(i,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,
                        new BackgroundSize(500,300,false,false,false,false)))); 
        });*/
        iv.setOnMouseClicked(h -> {

            try {
                setImageShow(e);
            } catch (FileNotFoundException ex) {

            }
            currentBean = list.random(iv);
            if (currentBean != null) {
                setText();
                clearOwnArea();
                clearPreItemSelected();
            }
        });
    }//为每个小图片添加事件||随机图片

    
    
    
    @FXML
    private void setPreDescripton(MouseEvent event) {
        String s = (String) chooserOfDescription.getSelectionModel().getSelectedItem();
        if (currentBean != null) {
            descriptionShow.setText(s);
            setDescriptionOfCurrentBean(s);
        }

    }//设置预先的描述

    @FXML
    private void setPreLocation(MouseEvent event) {
        String s = (String) chooserOfLocation.getSelectionModel().getSelectedItem();
        if (currentBean != null) {
            locationShow.setText(s);
            setLocationOfCurrentBean(s);
        }

    }//设置预先的位置

    @FXML
    private void setPreNumber(MouseEvent event) {
        String s = (String) chooserOfNumber.getSelectionModel().getSelectedItem();
        if (currentBean != null) {
            numberShow.setText(s);
            setNumberOfCurrentBean(s);
        }

    }//设置预先的number

    @FXML
    private void setOwnDescripton(MouseEvent event) {
        String s = setOwnDescriptionArea.getText();
        if (!s.isEmpty() && currentBean != null) {
            descriptionShow.setText(s);
            setDescriptionOfCurrentBean(s);
        }

    }//自定义描述

    @FXML
    private void setOwnLocation(MouseEvent event) {
        String s = setOwnLocationArea.getText();
        if (!s.isEmpty() && currentBean != null) {
            locationShow.setText(s);
            setLocationOfCurrentBean(s);
        }

    }//自定义位置

    @FXML
    private void setOwnNumber(MouseEvent event) {
        String s = setOwnNumberField.getText();
                if(!s.matches("\\d+")){
           informationShow.setText("请输入有效的数字");
           return ;
        }
        if (!s.isEmpty() && currentBean != null) {
            informationShow.setText("数字正确");
            numberShow.setText(s);
            setNumberOfCurrentBean(s);
        }

    }//自定义数量
    @FXML
    private void importWord(ActionEvent event) throws IOException, TemplateException{//导出word文档
        if(!list.getList().isEmpty()){
        if(!list.isFull()){
              informationShow.setText("还有项目未完成编辑，\n请继续编辑");
         }
        else{
       ImportWord out=new ImportWord();
       out.setStage(stage);
       out.importWord(list.getList());
     
        ImportProject project=new ImportProject();
       
        project.importProject(list.getList(), projectName);
        informationShow.setText("已完成导出doc文档，\n且保存了项目");
        }
    }
        else{
        informationShow.setText("项目为空，无法完成导出");
        }
    }
    @FXML
    private void importProject(ActionEvent event) throws IOException, TemplateException{//导出word文档
        if(!list.getList().isEmpty()){
        if(!list.isFull()){
              informationShow.setText("还有项目未完成编辑，\n请继续编辑");
         }
        else{
        	ImportProject project=new ImportProject();
        	
            project.importProject(list.getList(), projectName);
       
        informationShow.setText("已完成导出项目");
     
        }
    }
        else{
        informationShow.setText("项目为空，无法完成导出");
        }
    }
    
    
    
    
    //菜单事件
    @FXML
    private void setNewProject(ActionEvent event) throws IOException, TemplateException{
        Stage stage2=new Stage();
        FXMLLoader load=new FXMLLoader(getClass().getResource("NameSet.fxml"));
        Parent root=(Parent)load.load();
        NameSetController controller=load.getController();
        controller.setStage(stage2);
         Scene scene=new Scene(root);
        stage2.setTitle("设置项目名字");
        stage2.setScene(scene);
        stage2.show();
         
    }
     @FXML
    private void openProject(ActionEvent event) throws IOException, TemplateException{
    	 Stage stage2=new Stage();
         FXMLLoader load=new FXMLLoader(getClass().getResource("NameSet.fxml"));
         Parent root=(Parent)load.load();
         NameSetController controller=load.getController();
         controller.setStage(stage2);
          Scene scene=new Scene(root);
         stage2.setTitle("设置项目名字");
         stage2.setScene(scene);
         stage2.show();
    }
     @FXML
    private void manageProject(ActionEvent event) throws IOException, TemplateException{
    	 Stage stage2=new Stage();
         FXMLLoader load=new FXMLLoader(getClass().getResource("ProjectManage.fxml"));
         Parent root=(Parent)load.load();
         ProjectManageController controller=(ProjectManageController)load.getController();
         controller.setStage(stage2);
          Scene scene=new Scene(root);
         stage2.setScene(scene);
         stage2.show();
    }
     @FXML
    private void manageMark(ActionEvent event) throws IOException{
    	 
         FXMLLoader load=new FXMLLoader(getClass().getResource("MarkSet.fxml"));
        Parent root=(Parent)load.load();
         Scene scene=new Scene(root);
         Stage stage1=new Stage();
        stage1.setTitle("标记管理界面");
        stage1.setScene(scene);
        stage1.show();
        
    }
 //菜单事件
    
    

    private boolean isImage(File file) throws IOException {  //判断目录里的文件是否为图片
        if (file.getCanonicalPath().matches(".+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.Png)$")) {
            return true;
        };
        return false;
    }// //判断目录里的文件是否为图片

    public void setProjectName(String name) {
    	projectName=name;
        label.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {//实例初始化会跳用此方法
        // TODO
        list = new WorkBeansList();
        try {
            setPreMark();
        } catch (IOException ex) {
            Logger.getLogger(ProjectSetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } //实例初始化会跳用此方法

    private void setPreMark() throws IOException {//获取预先设置的markBean并加载到工作区
        PreMark pm = new PreMark();
        String de[] = pm.getDescriptions();
        String lo[] = pm.getLocations();
        String nu[] = pm.getNumbers();

        ObservableList num = FXCollections.observableArrayList(nu);

        chooserOfNumber.setItems(num);

        ObservableList des = FXCollections.observableArrayList(de);
        chooserOfDescription.setItems(des);

        ObservableList loc = FXCollections.observableArrayList(lo);
        chooserOfLocation.setItems(loc);
    }//获取预先设置的markBean并加载到工作区

    private void setImageShow(File e) throws FileNotFoundException {
        Image i = new Image(new FileInputStream(e));
        imageShow.setImage(i);

    }

    private void setIcon(ImageViewWithIndex iv) {//添加小图片到小图片展示区
        imageList.getChildren().add(iv);
    }//添加小图片到小图片展示区

    private void setText() {

        descriptionShow.setText(currentBean.getImageBean().getMarkbean().getDescription());
        locationShow.setText(currentBean.getImageBean().getMarkbean().getLocation());
        numberShow.setText(currentBean.getImageBean().getMarkbean().getNumber());

    }//修改工作区状态

    private void setDescriptionOfCurrentBean(String s) {
        currentBean.getImageBean().getMarkbean().setDescription(s);
    }

    private void setLocationOfCurrentBean(String s) {
        currentBean.getImageBean().getMarkbean().setLocation(s);
    }

    private void setNumberOfCurrentBean(String s) {
        currentBean.getImageBean().getMarkbean().setNumber(s);
    }

    private void clearOwnArea() {
        setOwnDescriptionArea.setText("");
        setOwnLocationArea.setText("");
        setOwnNumberField.setText("");

    }//清理自定义区

    private void clearPreItemSelected() {
        chooserOfDescription.getSelectionModel().clearSelection();
        chooserOfLocation.getSelectionModel().clearSelection();
        chooserOfNumber.getSelectionModel().clearSelection();

    }//重置选择项
    
    public void setStage(Stage stage) {
    	this.stage=stage;
    }
    
    
    public void setSingleProject(String projectName) throws IOException {//传入单个project重新编辑	
    	//读取项目集
    	ReadSingleProject readSingleProject=new ReadSingleProject();
    	ArrayList<ImageBean> imageBeans=readSingleProject.readSingleProject(projectName);
    	//将项目集加载到界面并绑定对应的ImageViewWithIndex，并将项目集导入list
        for (ImageBean e : imageBeans) {
                  
                Image i = new Image(new FileInputStream(e.getImageFile()));
               
           
                ImageViewWithIndex iv = new ImageViewWithIndex();
               
                iv.setFitHeight(100);
                iv.setFitWidth(150);
              
                iv.setImage(i);
            
                ImageBeanWithIndex imageBeanWithIndex=new ImageBeanWithIndex(); 
               
                imageBeanWithIndex.setImageBean(e);
             
                imageBeanWithIndex.setIndexImageView(iv);//绑定
              
                list.addProjectBean(imageBeanWithIndex);//将项目导入list
            
                setIcon(iv);//添加小图片到小图片展示区
              
                setHandle(e.getImageFile(), iv);//为每个小图片添加事件
                
                if (init == 0) {
                    currentBean = list.getFirstBean();
                    setImageShow(currentBean.getImageBean().getImageFile());
                    setText();
                    clearOwnArea();
                    clearPreItemSelected();
                    init++;

                }//初始化界面
          
        }
    	//将项目集加载到界面并绑定对应的ImageViewWithIndex，并将项目集导入list

    
    }
    
    

}
