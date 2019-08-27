package MyTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PreMarkGet {

	public static Set<String> getDescriptions() {
		
		return getMark("mark/descriptions.dat");
	}

	public static Set<String> getNumbers() {
		
		return getMark("mark/numbers.dat");
	}

	public static Set<String> getLocations() {
		
		return getMark("mark/locations.dat");
	}
	private static Set<String> getMark(String path) {
		Set<String> re=new HashSet<>();
		File file=new File(path); 
		FileReader r = null;
		try {
			r = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	    BufferedReader r1=new BufferedReader(r);
	    StringBuilder sb=new StringBuilder();
	    String lineString;
        try {
			while((lineString=r1.readLine())!=null){
			   sb.append(lineString.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        if(sb.length()==0)
        	return re;
        String[] ssStrings=sb.toString().split("#");
        for(String s:ssStrings)
        	re.add(s);
        return re;
	}
}
