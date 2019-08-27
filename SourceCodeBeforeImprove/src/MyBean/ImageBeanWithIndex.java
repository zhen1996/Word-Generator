/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBean;

import java.io.Serializable;

import MyTool.ImageViewWithIndex;

/**
 *
 * @author 振
 */
public class ImageBeanWithIndex implements Serializable{  //WorkBean
    private ImageBean imageBean;
    private ImageViewWithIndex indexImageView;

    /**
     * @return the imageBean
     */
    public ImageBean getImageBean() {
        return imageBean;
    }

    /**
     * @param imageBean the imageBean to set
     */
    public void setImageBean(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    /**
     * @return the indexImageView
     */
    public ImageViewWithIndex getIndexImageView() {
        return indexImageView;
    }

    /**
     * @param indexImageView the indexImageView to set
     */
    public void setIndexImageView(ImageViewWithIndex indexImageView) {
        this.indexImageView = indexImageView;
    }

    /**
     * @return the index
     */
  
}
