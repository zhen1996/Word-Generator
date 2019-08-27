/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTool;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author 振
 */
public class ImageReduce {   //减少图片的分辨率
    public static File of(File e) throws IOException{
        Calendar c=Calendar.getInstance();
          String s=""+c.get(Calendar.YEAR)+"."+(c.get(Calendar.MONTH)+1)+"."+c.get(Calendar.DATE);
          File file=new File("image/"+s);
          
          if(!file.exists()){
              file.mkdirs();
          }     
          File re=new File("image/"+s+"/"+e.getName());
          int i=1;
          while(re.exists()) {
        	  re=new File("image/"+s+"/"+i+e.getName());
        	  i++;  
          }
        Thumbnails.of(e).outputQuality(1).size(800,600).toFile(re);
        return re;
    }
}
