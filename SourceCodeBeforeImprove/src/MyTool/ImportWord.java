/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTool;

import MyBean.ImageBean;
import MyBean.ImageBeanWithIndex;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.misc.BASE64Encoder;

/**
 *
 * @author 振
 */
public class ImportWord {
	private Stage stage;
	
	
	public void setStage(Stage stage) {
		this.stage=stage;
		
	}
	
    public  String importWord(ArrayList<ImageBeanWithIndex> l){//不成功会返回false
    	   FileChooser fileChooser1 = new FileChooser();
           fileChooser1.setTitle("储存文件");
           // filec.setInitialDirectory(value);
           fileChooser1.getExtensionFilters().addAll(
                   new FileChooser.ExtensionFilter("doc", "*.doc")
           );
           File e = fileChooser1.showSaveDialog(stage);
           //启动线程开始文件操作
           if(e!=null) {
      
           ImportWordThread importWordThread=new ImportWordThread(e, l);
           importWordThread.start();
           return "文件操作中，请等待片刻";
           }
           else {
			return "您取消了保存";
		}
           
           
           
        
      
}
    
}
