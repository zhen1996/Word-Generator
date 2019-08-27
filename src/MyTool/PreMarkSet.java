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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 *
 * @author 振
 */
public class PreMarkSet {// 设置预先的值
	public static void setPreMark(ListView listView, Set<String> insert) {
		List<String> needChange=new ArrayList<String>();
		for (String s : insert) {//动态换行
			if (s.length() > 18) {
				needChange.add(s);
			}
		}
		for(String s:needChange)
		{
			StringBuilder sBuilder = new StringBuilder();
			for (int i = 0; i < s.length(); i += 18) {
				sBuilder.append(s.substring(i, i + 18 <= s.length() ? i + 18 : s.length()));
				if (i + 18 < s.length())
					sBuilder.append("\n");
			}
			insert.remove(s);
			insert.add(sBuilder.toString());
		}
		listView.setItems(FXCollections.observableArrayList(insert));
	}
}
