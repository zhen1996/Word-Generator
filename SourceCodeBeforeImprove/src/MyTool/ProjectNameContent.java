package MyTool;

import MyBean.ProjectBean;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ProjectNameContent extends StackPane{
     private HBox singleProject;
     private ProjectBean projectBean;
	public HBox getSingleProject() {
		return singleProject;
	}
	public void setSingleProject(HBox singleProject) {
		this.singleProject = singleProject;
	}
	public ProjectBean getProjectBean() {
		return projectBean;
	}
	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}
	
}
