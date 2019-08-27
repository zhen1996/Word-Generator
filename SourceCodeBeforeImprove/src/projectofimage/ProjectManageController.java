package projectofimage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.collections.SortableList;


import MyBean.ProjectBean;
import MyTool.DeleteProject;
import MyTool.ImageViewWithIndex;
import MyTool.ProjectNameContent;
import MyTool.ReadProjects;
import freemarker.template.TemplateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.coobird.thumbnailator.name.Rename;

public class ProjectManageController implements Initializable{
	
	@FXML
	private VBox projectList;//项目展示区
	@FXML
	private Button delete;//
	@FXML
	private Button union;//
	@FXML
	private Button editer;//
	@FXML
	private Label hint;//提示区
	private int num=0;
	private Stage currentStage;
	
	

	private ArrayList<ProjectNameContent> selectedProjects;//选中的项目的集合
	
	private void addSingleProject(ProjectBean bean) throws FileNotFoundException { //添加一个项目
		
		//bean解析
		String string=bean.getProjectName();
		String spite[]=string.split("_");
	   String projectName=spite[0];
	   String projectDate=spite[1];
	   ArrayList<File> images=bean.getImages();
	 
		HBox singleProject=new HBox();//一个项目栏
		singleProject.setAlignment(Pos.CENTER_LEFT);
		singleProject.setPrefHeight(100);
		singleProject.setPrefWidth(1000);
		
		
		
		
		//项目名字
		ProjectNameContent projectNameContent=new ProjectNameContent();//单个项目控制器
		projectNameContent.setStyle("-fx-background-color: #999999;");
		projectNameContent.setPrefHeight(100);
		projectNameContent.setPrefWidth(200);
		projectNameContent.setSingleProject(singleProject);
		projectNameContent.setProjectBean(bean);
		projectNameContent.setCursor(Cursor.HAND);
		Label name=new Label();
		name.setText(projectName);
		projectNameContent.getChildren().add(name);
		projectNameContent.setOnMouseClicked(e->{//添加单击选择事件
			ProjectNameContent selected=(ProjectNameContent)e.getSource();
			//改变按钮的背景颜色
			if(!selectedProjects.contains(selected))
			{
				selectedProjects.add(selected);
				selected.setStyle("-fx-background-color: #36A9CE;");
				
				
			}
			else {
				selectedProjects.remove(selected);
				selected.setStyle("-fx-background-color: #999999;");
				
			}
			//改变按钮的鼠标事件的状态
			setButtonStatus();
			
			//改变提示区
			hint.setText("当前已选项目"+selectedProjects.size()+"/"+num);
		
			
			
			
			
		});
		//项目名字。。。（作为控制器）
		
		
		//项目日期
		StackPane data=new StackPane();
		data.setPrefHeight(100);
		data.setPrefWidth(200);
		Label dataLabel=new Label();
		dataLabel.setText(projectDate);
		data.getChildren().add(dataLabel);
		//项目日期
		
		
		//缩略图区
		ScrollPane scrollPane=new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setPrefHeight(100);
		scrollPane.setPrefWidth(600);
		scrollPane.setPannable(true);
		scrollPane.setCursor(Cursor.HAND);
		HBox hBox=new HBox();
		hBox.setPrefHeight(100);
		hBox.setPrefWidth(600);
		hBox.setSpacing(5);
		for(File e:images)
		{
			
			Image image=new Image(new FileInputStream(e));
			ImageView imageView=new ImageView(image);
			imageView.setFitHeight(90);
			imageView.setFitWidth(100);
			imageView.setImage(image);
			hBox.getChildren().add(imageView);
		}
		scrollPane.setContent(hBox);
	//缩略图区	
		
		singleProject.getChildren().addAll(projectNameContent,data,scrollPane);//将所有容器装载到一个项目栏
	
		projectList.getChildren().add(singleProject);

	}
	
	      private void setButtonStatus() {//设置button状态
		  if(selectedProjects.size()==0) {
				delete.setDisable(true);
				union.setDisable(true);
				editer.setDisable(true);
				delete.setStyle("-fx-background-color: #999999;");
				union.setStyle("-fx-background-color: #999999;");
				editer.setStyle("-fx-background-color: #999999;");
				
			}
			if(selectedProjects.size()>0) {
				delete.setDisable(false);
				delete.setStyle("-fx-background-color: #36A9CE;");
				if(selectedProjects.size()>1) {
					union.setDisable(false);
					union.setStyle("-fx-background-color: #36A9CE;");
					editer.setDisable(true);
					editer.setStyle("-fx-background-color: #999999;");
				}
				else{
					editer.setDisable(false);
					editer.setStyle("-fx-background-color: #36A9CE;");
					union.setDisable(true);
					union.setStyle("-fx-background-color: #999999;");
					
				}
				
			}
			  
		  
	  }
	  @FXML
	    private void projectDelet(ActionEvent event) throws IOException, TemplateException{
	      //在项目展示区和集合中中删除
		 for(ProjectNameContent e:selectedProjects )
		 {
			 DeleteProject.of(e.getProjectBean().getProjectName());//删除文件系统
			 projectList.getChildren().remove(e.getSingleProject());//从项目展示区删除	 
		 }
		 selectedProjects.removeAll(selectedProjects);//删除所有文件
		   setButtonStatus();
  
	    }
	  
	  @FXML
	    private void projectEdit(ActionEvent event) throws IOException, TemplateException{
		  //只有一个项目选中时才能执行
		  if(selectedProjects.size()>1) {
			  hint.setText("程序逻辑出错");
			  return;
		  }
		//导入到projectSet中
		  projectSet(); 
		  
		  //退出当前舞台
		  currentStage.close();
	    }
	
 
	  @FXML
	    private void projectUnion(ActionEvent event) throws IOException, TemplateException{
		  //多余一个项目选中时才能执行
		  if(selectedProjects.size()<1) {
			  hint.setText("程序逻辑出错");
			  return;
		  }
		//导入到projectSet中
		  
		  projectSet();
		  //退出当前舞台
		  currentStage.close();
	    }
	  
	  private void  projectSet() throws IOException {  //重命名界面并传递数据
		  
		  String[] projectNames=new String[selectedProjects.size()];
		  
		  for(int i=0;i<projectNames.length;i++) {
			  projectNames[i]=selectedProjects.get(i).getProjectBean().getProjectName();
			  
		  }
		  
		  FXMLLoader load=new FXMLLoader(getClass().getResource("RenameToProject.fxml"));
	        Parent root=(Parent)load.load();
	       RenameToProjectController controller=(RenameToProjectController)load.getController();
	        controller.setProjectNames(projectNames);
	       
	         Scene scene=new Scene(root);
	         Stage stage=new Stage();
	         controller.setStage(stage);
	        stage.setTitle("重命名您的项目");
	        stage.setScene(scene);
	        stage.show();
	  }

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ReadProjects readProjects =new ReadProjects();
		selectedProjects=new ArrayList<>();
		
		//读取项目集合
		try {
			 ArrayList<ProjectBean> projects=readProjects.readProjects();
			 if(projects!=null) {
			 num=projects.size();
			 for(ProjectBean bean:projects)//装载项目
					try {
						addSingleProject(bean);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			 else {
				 hint.setText("无历史项目");
				 return;
			 }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//初始化提示区的信息
		
		hint.setText("已选0/"+num);
		
		
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
     public void setStage(Stage stage) {
    	 currentStage=stage;
    	 
     }
}
