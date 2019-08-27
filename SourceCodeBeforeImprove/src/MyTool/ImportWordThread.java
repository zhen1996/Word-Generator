package MyTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

import MyBean.ImageBean;
import MyBean.ImageBeanWithIndex;
import freemarker.template.Configuration;
import freemarker.template.Template;
import javafx.stage.FileChooser;
import sun.misc.BASE64Encoder;

public class ImportWordThread extends Thread{
	private File e;
	private int bodyFlag;
	private ArrayList<ImageBeanWithIndex> l;
	
	
	public ImportWordThread(File e,ArrayList<ImageBeanWithIndex> l) {
		this.e=e;
		this.l=l;	
	}
	
public void run() {
	try {
		System.out.println(importWord());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
private  String importWord() throws InterruptedException{//不成功会返回false

	bodyFlag=0;
 for(int i=0;i<l.size();i=i+2)
 {   
	
	 if(i<l.size()-1) {
		 importBody(l.get(i).getImageBean(),l.get(i+1).getImageBean());
		 //String importBodyReturnValue=importBody(l.get(i).getImageBean(),l.get(i+1).getImageBean());
      	//if(!"true".equals(importBodyReturnValue))
      		//return importBodyReturnValue;
 }
 else {importBody(l.get(i).getImageBean(),null);
	 //String importBodyReturnValue=importBody(l.get(i).getImageBean(),null);
	 	//if(!"true".equals(importBodyReturnValue))
	 		//return importBodyReturnValue;
 }
 }
 
	
	
	//每隔两个元素导出一个暂时文件。

if(!appendInfo(e, new File("mark/head.xml"),0))//追加首部信息
	{
	   

	   return "body文件连接出现问题";
	   }
 File bodys[]=new File("body").listFiles();
 for(File body:bodys) {//追加body信息
 if(!appendInfo(e,body,1))  
	 {
 	return "body文件连接出现问题";
	 }
 }
 if(!appendInfo(e, new File("mark/foot.xml"),1))//追加尾部信息
 	{
	 return "尾部文件连接出现问题";
 	}

	//将首+暂时文件+尾部文件连接导出一个doc文件
	if(!deleteBody())
		return "body文件删除出现问题，但文件已导出成功，请将body文件夹的文件删除或练习作者";
	return "成功导出";

}

private boolean appendInfo(File target,File source,int flag) {//无误
	try {
  BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(source),"utf-8"));
 	
 	BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target,true),"utf-8"));
 	String info="";
 	while((info=reader.readLine())!=null) {
 		
 		
 		writer.write(info);
 		writer.newLine();
 		
 	}
 	writer.flush();
 	writer.close();
 	reader.close();
 	return true;	
	}
	catch (Exception e) {
		e.printStackTrace();
	return false;
	}
	           
	
}
private boolean deleteBody() {
	File file=new File("body");
	File[] files=file.listFiles();
	for(File file2:files)
	{
		if(!file2.delete())
			return false;
		
	}
	return true;
}



private String importBody(ImageBean imageBean1,ImageBean imageBean2) {//导出中间文件
	File importDir=new File("body");
	if(!importDir.exists())
		importDir.mkdirs();
	File bodyFile=new File("body/"+bodyFlag);

	//得到freemark所需list
	
 Map<String,Object> m=new HashMap<>();
 String image1Code=getImageData(imageBean1.getImageFile());
 
  try {
	m.put("imageName1",imageBean1.getImageFile().getCanonicalPath());
	System.out.println(imageBean1.getImageFile().getCanonicalPath());
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  m.put("image1",image1Code);
  m.put("description1",imageBean1.getMarkbean().getDescription());
  m.put("location1",imageBean1.getMarkbean().getLocation()+" 数量："+imageBean1.getMarkbean().getNumber());
  
  if(imageBean2!=null){
	  try {
			m.put("imageName2",imageBean2.getImageFile().getCanonicalPath());
			System.out.println(imageBean2.getImageFile().getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	 String image2Code=getImageData(imageBean2.getImageFile());
      if(image2Code==null)
      	return "2图片编码出现问题"; 
 	 m.put("image2",image2Code);
      m.put("description2",imageBean2.getMarkbean().getDescription());
      m.put("location2",imageBean2.getMarkbean().getLocation()+" 数量："+imageBean2.getMarkbean().getNumber());
     
      
  }
  else{
        
		File blank=new File("image/default.jpg");
		try {
			System.out.println(blank.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			m.put("imageName2",blank.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	  m.put("image2",getImageData(blank));//默认空白图片
       m.put("description2"," ");
       m.put("location2"," ");
  }
  
  if(loadFreemark(m, bodyFile))
  {
 	 bodyFlag++;
 	 return "true";
 	 
  }
  else {
		return "FreeMark出现问题";
	}    
  }


private boolean loadFreemark(Map<String,Object> root,File e) {
	try {
	 /* Create and adjust the configuration singleton */
 Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
 
 cfg.setDirectoryForTemplateLoading(new File("mark"));
 cfg.setDefaultEncoding("UTF-8");
 /* Get the template (uses cache internally) */
 Template temp = cfg.getTemplate("body.xml");
 /* Merge data-model with template */  
 Writer out = new OutputStreamWriter(new FileOutputStream(e));
 temp.process(root, out);    
 out.close();//不关闭无法删除body文件
 
 return true;
	}
	catch(Exception exception) {
		exception.printStackTrace();
		
		return false;
	}
	
}

private String getImageData(File file)  {//对图像文件进行编码，错误返回null
	try {
 FileInputStream fi=new FileInputStream(file);
 byte[] b=new byte[fi.available()];
 fi.read(b);
 fi.close();
 BASE64Encoder encoder=new BASE64Encoder();
 return encoder.encode(b);
	}
	catch (Exception e) {
		e.printStackTrace();
		return null;
	}
 
}


} 
