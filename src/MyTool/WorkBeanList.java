package MyTool;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class WorkBeanList {

	class WorkBean implements WorkBeanIn {
		private File e;
		String description = "";
		String location = "";
		String number = "";
		WorkBean next;
		WorkBean pre;
		BindImageView iv;// 图形和数据双向绑定

		public WorkBean(File e) {
			this.e = e;
		}

		@Override
		public String getDesription() {
			return description;
		}

		@Override
		public String getLocation() {
			return location;
		}

		@Override
		public String getNumber() {
			return number;
		}

		@Override
		public File getFile() {
			return e;
		}
	}

	class BindImageView extends ImageView {
		WorkBean workBean;
	}

	private int size;
	private WorkBean head = null;
	private WorkBean tail = null;
	private WorkBean now = null;// 当前工作的图像
	// 图形控件
	private HBox imageList;// 小图片展示区
	private ImageView imageShow;// 大图片展示区
	private ListView chooserOfDescription;
	private ListView chooserOfLocation;
	private ListView chooserOfNumber;
	private TextArea setOwnDescriptionArea;
	private TextArea setOwnLocationArea;
	private TextField setOwnNumberField;
	private Label descriptionShow;
	private Label numberShow;
	private Label locationShow;
	private Label informationShow;

	public WorkBeanList(HBox imageList, ImageView imageShow, ListView chooserOfDescription,
			ListView chooserOfLocation, ListView chooserOfNumber, TextArea setOwnDescriptionArea,
			TextArea setOwnLocationArea, TextField setOwnNumberField, Label descriptionShow, Label numberShow,
			Label locationShow, Label informationShow) {
		super();
		this.imageList = imageList;
		this.imageShow = imageShow;
		this.chooserOfDescription = chooserOfDescription;
		this.chooserOfLocation = chooserOfLocation;
		this.chooserOfNumber = chooserOfNumber;
		this.setOwnDescriptionArea = setOwnDescriptionArea;
		this.setOwnLocationArea = setOwnLocationArea;
		this.setOwnNumberField = setOwnNumberField;
		this.descriptionShow = descriptionShow;
		this.numberShow = numberShow;
		this.locationShow = locationShow;
		this.informationShow = informationShow;
	}

	public void insertImage(File e) {// 导入图像
		size++;
		Image i = null;
		try {
			i = new Image(new BufferedInputStream((new FileInputStream(e))));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BindImageView iv = new BindImageView();
		iv.setFitHeight(90);
		iv.setFitWidth(150);
		iv.setImage(i);
		WorkBean workBean = new WorkBean(e);
		iv.workBean = workBean;// 双向绑定.
		workBean.iv = iv;
		setIcon(iv);// 添加小图片到小图片展示区
		setHandle(e, iv);// 为每个小图片添加事件
		if (tail == null) {// 工作队列为空
			head = tail = now = workBean;
			setImageShow();// 设置大图片
			setText();// 设置标记
			clearPreItemSelected();// 重置选择项
			// 初始化界面
		} else {
			tail.next = workBean;// 双向链接
			workBean.pre = tail;
			tail = workBean;
		}
	}

	public void deleteImage() {
		if (now != null) {
			imageList.getChildren().remove(now.iv);// 移除小图片展示区对应的小图片
			// 为头节点
			if (size == 1)// 删除后为空
				head = tail = now = null;
			else {
				// 对头节点和尾节点的处理
				if (head == now)
					head = head.next;
				if (tail == now)
					tail = tail.pre;
				// 断开
				WorkBean pre = now.pre;
				WorkBean next = now.next;
				if (pre != null)
					pre.next = next;
				if (next != null)
					next.pre = pre;
				// 对now重新赋值;
				now = next == null ? pre : next;
			}
			size--;// 大小减1
			// 改变图形区的状态
			change();
		}
	}

	public void nextImage() {
		if (now != null && now.next != null) {
			now = now.next;
			change();
		}
	}

	public void preImage() {
		if (now != null && now.pre != null) {
			now = now.pre;
			change();
		}
	}

	public void setDescription(String description) {
		if (now == null)
			return;
		descriptionShow.setText(description);
		now.description = description;
	}

	public void setLocation(String location) {
		if (now == null)
			return;
		locationShow.setText(location);
		now.location = location;
	}

	public void setNumber(String number) {
		if (now == null)
			return;
		numberShow.setText(number);
		now.number = number;
	}

	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	public boolean isFull() {
		WorkBean cur = head;
		while (cur != null) {
			if (cur.description == "" || cur.location == "" || cur.number == "")
				return false;
			cur = cur.next;
		}
		return true;
	}

	public List<WorkBeanIn> getInformation() {
		List<WorkBeanIn> re = new ArrayList<>();
		WorkBean cur = head;
		while (cur != null) {
			re.add(cur);
			cur = cur.next;
		}
		/*
		 * head = now = tail = null;// 将其中的数据复原 size=0;
		 * imageList.getChildren().clear();// 小图片展示区清空 setImageShow();// 设置大图片 change();
		 */
		return re;
	}

	private void change() {
		// 改变图形区的状态
		setImageShow();// 设置大图片
		setText();// 设置标记
		clearPreItemSelected();// 重置选择项
	}

	private void setText() {
		String one = now == null ? "" : now.description;
		String two = now == null ? "" : now.location;
		String three = now == null ? "" : now.number;
		descriptionShow.setText(one);
		locationShow.setText(two);
		numberShow.setText(three);
		if (now == null)
			informationShow.setText("请添加图片进行编辑");

	}// 修改工作区状态

	private void clearOwnArea() {
		setOwnDescriptionArea.setText("");
		setOwnLocationArea.setText("");
		setOwnNumberField.setText("");

	}// 清理自定义区

	private void clearPreItemSelected() {
		chooserOfDescription.getSelectionModel().clearSelection();
		chooserOfLocation.getSelectionModel().clearSelection();
		chooserOfNumber.getSelectionModel().clearSelection();
	}// 重置选择项

	private void setIcon(ImageView iv) {// 添加小图片到小图片展示区
		imageList.getChildren().add(iv);
	}// 添加小图片到小图片展示区

	private void setHandle(File e, BindImageView iv) {// 为每个小图片添加事件
		/*
		 * iv.setOnMousePressed(h->{ imageView.setBackground(new Background( new
		 * BackgroundImage(i,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
		 * BackgroundPosition.CENTER, new
		 * BackgroundSize(500,300,false,false,false,false)))); });
		 */
		iv.setOnMouseClicked(h -> {
			now = iv.workBean;// 改变now
			setImageShow();
			// 设置大图片
			setText();// 设置标记
			clearPreItemSelected();// 重置选择项

		});
	}// 为每个小图片添加事件||随机图片

	private void setImageShow() {
		InputStream inputStream = null;
		try {
			inputStream = now == null ? new BufferedInputStream(new FileInputStream("image/icon.jpg"))
					: new BufferedInputStream(new FileInputStream(now.e));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageShow.setImage(new Image(inputStream));
	}
}
