/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBean;

import java.io.Serializable;

/**
 *
 * @author 振
 */
public class MarkBean implements Serializable{
    private String description;
    private String number;
    private String location;
    
    public String getDescription(){
    
    return this.description;
    };
    public String getLocation(){
    
    return this.location;
    };
    public String getNumber(){
    return this.number;
    }
    public void setDescription(String description){
    
    this.description=description;
    };
    public void setLocation(String location){
    
    this.location=location;
    };
    public void setNumber(String number){
       this.number=number;
    }
}
