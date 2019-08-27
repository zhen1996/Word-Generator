/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sun.misc.BASE64Encoder;

/**
 *
 * @author 振
 */
public class ImportWord {
	private File target = null;
	private Template template = null;

	public ImportWord() {
		// 在构造方法中初始化成员变量
		FileChooser fileChooser1 = new FileChooser();
		fileChooser1.setTitle("储存文件");
		// filec.setInitialDirectory(value);
		fileChooser1.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("doc", "*.doc"));
		target = fileChooser1.showSaveDialog(new Stage());
		// 初始化freemaker
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
			cfg.setDirectoryForTemplateLoading(new File("template"));
			cfg.setDefaultEncoding("UTF-8");
			/* Get the template (uses cache internally) */
			template = cfg.getTemplate("body.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void importWord(List<WorkBeanIn> list) {
		if (target == null || template == null)
			return;
		// 放入头部
		head();
		// 将中部的内容使用Template解析后放入
		Map<String, Object> m = new HashMap<>();
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target, true), "utf-8"));// 追加写入
			for (int i = 0; i < list.size(); i += 2) {
				m.put("imageName1", list.get(i).getFile().getCanonicalPath());
				m.put("image1", getImageData(list.get(i).getFile()));
				m.put("description1", list.get(i).getDesription());
				m.put("location1", list.get(i).getLocation() + " 数量：" + list.get(i).getNumber());
				if (i + 1 < list.size()) {
					m.put("imageName2", list.get(i + 1).getFile().getCanonicalPath());
					m.put("image2", getImageData(list.get(i + 1).getFile()));
					m.put("description2", list.get(i + 1).getDesription());
					m.put("location2", list.get(i + 1).getLocation() + " 数量：" + list.get(i + 1).getNumber());
				} else {// 空白图片代替
					File blank = new File("image/default.jpg");
					m.put("imageName2", blank.getCanonicalPath());
					m.put("image2", getImageData(blank));// 默认空白图片
					m.put("description2", " ");
					m.put("location2", " ");
				}
				template.process(m, out);

			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tail();// 放入尾部
	}

	private void head() {
		appendInfo(new File("template/head.xml"), false);

	}

	private void tail() {
		appendInfo(new File("template/foot.xml"), true);
	}

	private void appendInfo(File source, boolean append) {// append表示是否追加信息
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), "utf-8"));
			BufferedWriter writer = null;
			if (append) {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target, true), "utf-8"));
			} else
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), "utf-8"));
			String info = "";
			while ((info = reader.readLine()) != null) {
				writer.write(info);
				writer.newLine();
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getImageData(File file) {// 对图像文件进行编码，错误返回空字符串
		try {
			FileInputStream fi = new FileInputStream(file);
			byte[] b = new byte[fi.available()];
			fi.read(b);
			fi.close();
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(b);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
