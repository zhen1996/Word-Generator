/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 振
 */
public class PreMark {//预先设置的值
    private String[] descriptions;
    private String[] locations;
    private String[] numbers;
    
    
    public PreMark() throws IOException{ 
      initDecriptions();
      initNumbers();
      initLocations();
    }
    
    private void initDecriptions() throws IOException{//初始化预先设置的描述
    File file=new File("desriptions.dat"); 
       if(!file.exists()) 
       {
           FileWriter output=new FileWriter(file);    
       output.close();
       }
        FileReader r=new FileReader(file);
        BufferedReader r1=new BufferedReader(r);
        StringBuilder sb=new StringBuilder();
        String lineString="";
        while((lineString=r1.readLine())!=null){
           sb.append(lineString+"\n");
        }
         if(sb.lastIndexOf("\n")!=-1)
         sb.deleteCharAt(sb.lastIndexOf("\n"));
        if(sb.lastIndexOf("#")!=-1)
         sb.deleteCharAt(sb.lastIndexOf("#"));
        String s=sb.toString();
        setDescriptions(s.split("#"));
    }//初始化预先设置的描述
    
   private void initNumbers() throws IOException{
    File file=new File("numbers.dat"); 
       if(!file.exists()) 
       {
           FileWriter output=new FileWriter(file);
     
       output.close();
       }
        FileReader r=new FileReader(file);
        BufferedReader r1=new BufferedReader(r);
        StringBuilder sb=new StringBuilder();
        String lineString="";
        while((lineString=r1.readLine())!=null){
           sb.append(lineString+"\n");
        }
     
         if(sb.lastIndexOf("\n")!=-1)
         sb.deleteCharAt(sb.lastIndexOf("\n"));
        if(sb.lastIndexOf("#")!=-1)
         sb.deleteCharAt(sb.lastIndexOf("#"));
        String s=sb.toString();
        setNumbers(s.split("#"));
    }//初始化预先设置的数量
 
   private void initLocations() throws IOException{
    File file=new File("locations.dat"); 
       if(!file.exists()) 
       {
           FileWriter output=new FileWriter(file);
        
           
           
       output.close();
       }
        FileReader r=new FileReader(file);
        BufferedReader r1=new BufferedReader(r);
        StringBuilder sb=new StringBuilder();
        String lineString="";
        while((lineString=r1.readLine())!=null){
           sb.append(lineString+"\n");      
        }
       if(sb.lastIndexOf("\n")!=-1)
         sb.deleteCharAt(sb.lastIndexOf("\n"));
        if(sb.lastIndexOf("#")!=-1)
         sb.deleteCharAt(sb.lastIndexOf("#"));
        String s=sb.toString();
        setLocations(s.split("#"));
    }//初始化预先设置位置

   

    /**
     * @return the descriptions
     */
    public String[] getDescriptions() {
        return descriptions;
    }

    /**
     * @param descriptions the descriptions to set
     */
    public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * @return the locations
     */
    public String[] getLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    /**
     * @return the numbers
     */
    public String[] getNumbers() {
        return numbers;
    }

    /**
     * @param numbers the numbers to set
     */
    public void setNumbers(String[] numbers) {
        this.numbers = numbers;
    }
    
    
}
