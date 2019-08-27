package MyBean;

import java.io.File;
import java.util.ArrayList;

public class ProjectBean {
private String projectName;
private ArrayList<File> images;
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}
public ArrayList<File> getImages() {
	return images;
}
public void setImages(ArrayList<File> images) {
	this.images = images;
}

}
