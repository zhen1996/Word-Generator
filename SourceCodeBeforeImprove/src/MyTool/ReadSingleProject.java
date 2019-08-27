package MyTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PrimitiveIterator.OfDouble;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import MyBean.ImageBean;
import MyBean.MarkBean;

public class ReadSingleProject {

public ArrayList<ImageBean> readSingleProject(String projectName) throws IOException {
	String path="Project/"+projectName;
	File file=new File(path);
	String string="";
	StringBuilder builder=new StringBuilder();
	BufferedReader reader=new BufferedReader(new FileReader(file));
	 while((string=reader.readLine())!=null)
	    {
	    	
	    	builder.append(string);
	    	
	    	
	    }
		string=builder.toString();
		String s[]=string.split("#");
		ArrayList<ImageBean> imageBeans=new ArrayList<>();
	for(int i=0;i<s.length;i=i+4)
	{
	
		File imageLocation=new File(s[i+3]);
		if(!imageLocation.exists())
			return null;
		ImageBean bean=new ImageBean();
		MarkBean markBean=new MarkBean();
		markBean.setDescription(s[i]);
		markBean.setLocation(s[i+1]);
		markBean.setNumber(s[i+2]);
		bean.setImageFile(imageLocation);
		bean.setMarkbean(markBean);	
		imageBeans.add(bean);
		
	}
	return imageBeans;
	
}
}
