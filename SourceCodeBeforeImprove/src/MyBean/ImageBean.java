/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBean;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author 振
 */
public class ImageBean implements Serializable{
    private File imageFile;
    private MarkBean markbean;
    

    /**
     * @return the imageFile
     */
    public File getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFile the imageFile to set
     */
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * @return the markbean
     */
    public MarkBean getMarkbean() {
        return markbean;
    }

    /**
     * @param markbean the markbean to set
     */
    public void setMarkbean(MarkBean markbean) {
        this.markbean = markbean;
    }
}
