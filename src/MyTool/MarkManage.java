package MyTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import jdk.nashorn.internal.ir.Flags;

public class MarkManage {
	// 图形界面控件
	private ListView<String> desList;
	private ListView<String> locList;
	private ListView<String> numList;
	private TextArea desEditor;
	private TextArea locEditor;
	private TextArea numEditor;

	public MarkManage(ListView<String> desList, ListView<String> locList, ListView<String> numList
			, TextArea desEditor, TextArea locEditor,
			TextArea numEditor) {
		this.desList = desList;
		this.locList = locList;
		this.numList = numList;
		this.desEditor = desEditor;
		this.locEditor = locEditor;
		this.numEditor = numEditor;
	}

	public void save() {// 保存操作后的数据
         Set<String> des=getInfo(1);
         Set<String> loc=getInfo(2);
         Set<String> num=getInfo(3);
         output(des, 1);
         output(loc, 2);
         output(num, 3);
	}
	public void delete(int flag) {// 删除某一个标记,1--des,2--loc,3--num
		ListView<String> listView = getListViw(flag);
		String s = (String) listView.getSelectionModel().getSelectedItem();
		if(s!=null&&s.length()>0)
		{
		listView.getItems().remove(s);
		}
	}

	public void addNew(int flag) {// 新增一个标记,1--des,2--loc,3--num
		// 改变数据
		TextArea textArea = geTextArea(flag);
		ListView<String> listView = getListViw(flag);
		String s=textArea.getText();
		if(s.length()>0) {
		listView.getItems().add(s);
		textArea.setText("");
		}
	}
	
	
	
	private Set<String> getInfo(int flag) {//得到展示区里的数据
		ListView<String> listView=getListViw(flag);
		ObservableList<String> list=listView.getItems();
		Set<String> re=new HashSet<>();
		for(String s:list)
			re.add(s);
		return re;
	}
	private ListView<String> getListViw(int flag) {//得到flag得到对应的图形界面
		return  flag == 1 ? desList : flag == 2 ? locList : numList;
	}
	private TextArea geTextArea(int flag) {//得到flag得到对应的图形界面
		return flag == 1 ? desEditor : flag == 2 ? locEditor : numEditor;
	}
	
	private void output(Set<String> out,int flag) {//对set里的数据进行输出
		
		StringBuilder stringBuilder=new StringBuilder();
		for(String s:out)
			{
			stringBuilder.append(s);
			stringBuilder.append("#");
			}
		String string;
		if(stringBuilder.length()<1)//为空时的处理
			string="";
		else
		string=stringBuilder.substring(0, stringBuilder.length()-1);
		File target=flag==1?new File("mark/descriptions.dat"):flag==2?new File("mark/locations.dat"):new File("mark/numbers.dat");
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(target));
			writer.write(string);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 


}
