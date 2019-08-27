package MyTool;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

import com.sun.prism.Image;

import MyBean.ImageBean;
import MyBean.ImageBeanWithIndex;
import sun.misc.BASE64Encoder;

public class ImportProject {
public void importProject(ArrayList<ImageBeanWithIndex> l,String name) throws IOException {
	Calendar calendar=Calendar.getInstance();
	String data="_"+calendar.get(Calendar.YEAR)+"."+calendar.get(Calendar.MONTH)+"."+
			calendar.get(Calendar.DATE);
	name=name+data;//项目名称+日期。。以_分隔
	File e1=new File("Project");
	if(!e1.exists()) {
		e1.mkdirs();	
	}
	File file=new File("Project/"+name);
	if(!file.exists())
	file.createNewFile();
	
	BufferedWriter writer=new BufferedWriter(new FileWriter(file));
	int size=l.size();
	for(int i=0;i<size-1;i++) {
		ImageBean e=l.get(i).getImageBean();
		writer.write(e.getMarkbean().getDescription());
		writer.write("#");
		writer.write(e.getMarkbean().getLocation());
		writer.write("#");
		writer.write(e.getMarkbean().getNumber());
		writer.write("#");	
		writer.write(e.getImageFile().getAbsolutePath());
		writer.write("#");	
	}
	ImageBean e=l.get(size-1).getImageBean();
	writer.write(e.getMarkbean().getDescription());
	writer.write("#");
	writer.write(e.getMarkbean().getLocation());
	writer.write("#");
	writer.write(e.getMarkbean().getNumber());
	writer.write("#");	
	writer.write(e.getImageFile().getAbsolutePath());
	writer.flush();
	writer.close();	
}

}
