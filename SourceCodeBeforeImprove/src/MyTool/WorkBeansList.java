/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTool;

import MyBean.ImageBean;
import MyBean.ImageBeanWithIndex;
import MyBean.MarkBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author 振
 */
public class WorkBeansList {

    private ArrayList<ImageBeanWithIndex> list;

    public WorkBeansList() {
        list = new ArrayList<>();
    }

    public ImageBeanWithIndex next(ImageBeanWithIndex currentBean) {
        if (currentBean != null) {
            int i = getList().indexOf(currentBean);
            i++;
            if (i >= getList().size()) {
                return currentBean;
            }
            return getList().get(i);
        } else {
            return null;
        }

    }//根据当前工作图片返回下一张工作图片,当下一张图片不存在时返回当前图片

    public ImageBeanWithIndex previous(ImageBeanWithIndex currentBean) {
        if (currentBean != null) {
            int i = getList().indexOf(currentBean);
            i--;
            if (i < 0) {
               
                return currentBean;

            }
        
            return getList().get(i);
        } else {
            return null;
        }

    }//根据当前工作图片返回上一张工作图片，当上一张图片不存在时返回当前图片

    public ImageBeanWithIndex delete(ImageBeanWithIndex currentBean) {
        if (getList().size() == 1) {
            getList().remove(0);
            return null;
        }
        int i = getList().indexOf(currentBean);
        ImageBeanWithIndex re;
        if (i < (getList().size() - 1)) {
            re = next(currentBean);
        } else {
            re = previous(currentBean);
        }
        getList().remove(i);
        return re;
    }//删除当前工作图片返回一张工作图片

    public ImageBeanWithIndex random(ImageView iv) {
        for (ImageBeanWithIndex e : getList()) {
            if (e.getIndexImageView().equals(iv)) {
                return e;
            }
        }
        return null;

    }//根据选择的小图片返回工作图片

    /*
    添加图片时，会新增一个ImageBeanWithIndex加入该类的list变量中。
    
     */
    public void addImageBean(File e, ImageViewWithIndex iv) throws FileNotFoundException {//添加一个图片到项目组中
        ImageBeanWithIndex ibi = initImageBeanWithIndex(e, iv);//初始化workbean
        getList().add(ibi);
        
    }  //添加一个图片到项目组中
    public void addProjectBean(ImageBeanWithIndex ibi) {
    	getList().add(ibi);
    }

    private ImageBeanWithIndex initImageBeanWithIndex(File e, ImageViewWithIndex iv) {//一个workbean的初始化
        ImageBeanWithIndex ibi = new ImageBeanWithIndex();
        ibi.setIndexImageView(iv);
        ImageBean ib = new ImageBean();
        ib.setImageFile(e);
        MarkBean mb = new MarkBean();
        mb.setDescription("");
        mb.setLocation("");
        mb.setNumber("");
        ib.setMarkbean(mb);
        ibi.setImageBean(ib);
        return ibi;
    }//一个workbean的初始化

    public ImageBeanWithIndex getFirstBean() {
         return getList().get(0);
    }

    /**
     * @return the list
     */
    public ArrayList<ImageBeanWithIndex> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList<ImageBeanWithIndex> list) {
        this.list = list;
    }

    public boolean isFull() {
       for(ImageBeanWithIndex e:list){
       if(e.getImageBean().getMarkbean().getDescription()==""||e.getImageBean().getMarkbean().getLocation()=="")
           return false;
       
       }
       return true;
    }

}
