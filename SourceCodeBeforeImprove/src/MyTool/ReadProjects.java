package MyTool;
import java.io.*;
import java.util.ArrayList;

import com.sun.prism.Image;

import MyBean.ImageBean;
import MyBean.ImageBeanWithIndex;
import MyBean.MarkBean;
import MyBean.ProjectBean;

public class ReadProjects {
	public  ArrayList<ProjectBean> readProjects() throws IOException {
		File file=new File("Project");
		if(!file.exists())
			return null;
		File[] files=file.listFiles();
		if(files.length==0) {
		return null;
		}
		ArrayList<ProjectBean> list=new ArrayList<>();
		
		for(File e:files) {//读取项目集信息
			if(e.length()>0) {//确保不是空文件
			
			ProjectBean singe=singleProject(e);
			if(singe!=null)
			  list.add(singe);
		}
			else {
				e.delete();
				
				
			}
		}
			 return list;
			 
		
			
			
	}
	private ProjectBean singleProject(File e) throws IOException{//读取一个文件的项目信息,
		//若里面的图片出错则返回null
		ProjectBean single=new ProjectBean();
		ArrayList<File> images=new ArrayList<>();
		BufferedReader reader=new BufferedReader(new FileReader(e));
		String string="";
	    StringBuilder builder=new StringBuilder();
	    while((string=reader.readLine())!=null)
	    {
	    	
	    	builder.append(string);
	    	
	    	
	    }
		string=builder.toString();
		String s[]=string.split("#");
		for(int i=0;i<s.length;i=i+4)
		{
			File file=new File(s[i+3]);
			if(!file.exists())
				return null;
			
			images.add(file);	
		}
		
		
		/*for(int i=0;i<s.length;i=i+4)
		{
			File file=new File(s[i+2]);
			if(!file.exists())
				return null;
			ImageBean bean=new ImageBean();
			MarkBean markBean=new MarkBean();
			markBean.setDescription(s[i]);
			markBean.setLocation(s[i+1]);
			markBean.setNumber(s[i+1]);
			bean.setImageFile(file);
			bean.setMarkbean(markBean);	
			imageBeans.add(bean);
			
		}*/
		single.setProjectName(e.getName());
		single.setImages(images);
		return single;

	}
	


}
		
		
		
	


