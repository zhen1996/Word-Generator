/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import MyTool.ImageReduce;
import MyTool.ImportWord;
import MyTool.LoaderFxml;
import MyTool.PreMarkGet;
import MyTool.PreMarkSet;
import MyTool.WorkBeanIn;
import MyTool.WorkBeanList;
import freemarker.template.TemplateException;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author 振
 */
public class ProjectSetController implements Initializable {
	// 每个fxml文件里的id变量前必须加@FXML
	@FXML
	private HBox imageList;// 小图片展示区
	@FXML
	private Label label;// 项目名称
	@FXML
	private ImageView imageShow;// 大图片展示区
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

	private WorkBeanList list;// 项目信息类,对应操作时图片和标记信息之间的情况

	@Override
	public void initialize(URL url, ResourceBundle rb) {// 实例初始化会跳用此方法
		list = new WorkBeanList(imageList, imageShow, chooserOfDescription, chooserOfLocation, chooserOfNumber,
				setOwnDescriptionArea, setOwnLocationArea, setOwnNumberField, descriptionShow, numberShow, locationShow,
				informationShow);
			PreMarkSet.setPreMark(chooserOfDescription,PreMarkGet.getDescriptions());
			PreMarkSet.setPreMark(chooserOfLocation,PreMarkGet.getLocations());
			PreMarkSet.setPreMark(chooserOfNumber,PreMarkGet.getNumbers());
			// 获取预先设置的markBean并加载到工作区
	} // 实例初始化会跳用此方法

	@FXML
	private void imageInputUseDir(ActionEvent event) throws IOException { // 使用文件夹选择器添加图片
		DirectoryChooser dirChooser1 = new DirectoryChooser();
		dirChooser1.setTitle("请选择你储存图片的文件夹");
		// dirChooser1.setInitialDirectory(value);
		File file = dirChooser1.showDialog(new Stage());
		File[] f1 = file.listFiles();
		if (f1 != null) {
			for (File e : f1) {
				if (isImage(e)) {
					e=ImageReduce.of(e);//减少图片的大小
					list.insertImage(e);

				}
			}
		}

	}// 使用文件夹选择器添加图片

	@FXML // 每个动作处理方法前必须加@FXML
	private void imageInputUseFile(ActionEvent event) throws IOException { // 使用文件选择器添加图片
		FileChooser fileChooser1 = new FileChooser();
		fileChooser1.setTitle("请选择你要处理的图片");
		// filec.setInitialDirectory(value);
		fileChooser1.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("GIF", "*.gif"),
				new FileChooser.ExtensionFilter("BMP", "*.bmp"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		File e = fileChooser1.showOpenDialog(new Stage());
		if (e != null && isImage(e)) {
			 e=ImageReduce.of(e);//减少图片的大小
			list.insertImage(e);
		}

	}// 使用文件选择器添加图片

	@FXML
	private void nextImage(ActionEvent event) throws FileNotFoundException {
		list.nextImage();
	} // 下一张图片

	@FXML
	private void previousImage(ActionEvent event) throws FileNotFoundException {
		list.preImage();
	} // 上一张图片

	@FXML
	private void deletCurentImage(ActionEvent event) throws FileNotFoundException {
		list.deleteImage();
	} // 删除当前图片

	@FXML
	private void setPreDescripton(MouseEvent event) {
		String s = (String) chooserOfDescription.getSelectionModel().getSelectedItem();
		list.setDescription(s);
	}// 设置预先的描述

	@FXML
	private void setPreLocation(MouseEvent event) {
		String s = (String) chooserOfLocation.getSelectionModel().getSelectedItem();
		list.setLocation(s);
	}// 设置预先的位置

	@FXML
	private void setPreNumber(MouseEvent event) {
		String s = (String) chooserOfNumber.getSelectionModel().getSelectedItem();
		list.setNumber(s);
	}// 设置预先的number

	@FXML
	private void setOwnDescripton(MouseEvent event) {
		String s = setOwnDescriptionArea.getText();
		if (!s.isEmpty() && s.length() != 0) {
			list.setDescription(s);
		}
	}// 自定义描述

	@FXML
	private void setOwnLocation(MouseEvent event) {
		String s = setOwnLocationArea.getText();
		if (!s.isEmpty() && s.length() != 0) {
			list.setLocation(s);
		}

	}// 自定义位置

	@FXML
	private void setOwnNumber(MouseEvent event) {
		String s = setOwnNumberField.getText();
		if (!s.matches("\\d+")) {
			informationShow.setText("请输入有效的数字");
			return;
		}
		if (!s.isEmpty() && s.length() != 0) {
			informationShow.setText("数字正确");
			list.setNumber(s);
		}

	}// 自定义数量

	@FXML
	private void importWord(ActionEvent event) throws IOException, TemplateException {// 导出word文档
		if (list.isEmpty() || !list.isFull()) {
			informationShow.setText("项目为空\n或还有项目未完成编辑，\n请继续编辑");
			return;
		}
		ImportWord out = new ImportWord();
		out.importWord(list.getInformation());
		informationShow.setText("已完成导出");
		/*
		 * ImportWord out=new ImportWord(); out.setStage(stage);
		 * out.importWord(list.getList());
		 * 
		 * ImportProject project=new ImportProject();
		 * 
		 * project.importProject(list.getList());
		 * informationShow.setText("已完成导出doc文档，\n且保存了项目");
		 */

	}

	@FXML
	private void importProject(ActionEvent event) throws IOException, TemplateException {// 导出word文档
		if (list.isEmpty() || !list.isFull()) {
			informationShow.setText("项目为空\n或还有项目未完成编辑，\n请继续编辑");
			return;
		}
		List<WorkBeanIn> infos = list.getInformation();
		System.out.println(infos.get(0).getDesription());
		/*
		 * ImportProject project=new ImportProject();
		 * 
		 * project.importProject(list.getList());
		 * 
		 * informationShow.setText("已完成导出项目");
		 */
	}

	private boolean isImage(File file) throws IOException { // 判断目录里的文件是否为图片
		if (file.getCanonicalPath().matches(".+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.Png)$")) {
			return true;
		}
		;
		return false;
	}// //判断目录里的文件是否为图片

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
