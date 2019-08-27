**本次重构主要对项目中重复的代码和低效的数据结构以及差劲的类设计进行更好的优化.**

**主要的优化的功能为图片编辑和标记管理.**

# 1.重构projectSetController类中臃肿的代码

 ## 1.针对fxml文件的加载

将fxml文件加载功能包装为类LoaderFxml类的load静态方法,供以调用,在菜单事件的处理器中直接调用该方法,省去了大量的重复代码.

##2.针对标记预处理

由于标记预处理的过程完全可以让PreMark来完成,所以将标记预处理的过程移动到MyTool包中的PreMark类中完成,且封闭了之前开放的getDescriptions,getLocations,getNumbers方法,使之成为一个完全的工具类,此时可将controller中无意义的setPreMark方法移除.

##3.将不合理的WorkBeansList重写:

改写WorkBeanList中的底层数据结构,将之前不合理的List结构改为自己可控的双向链表,使得对图片的操作都能在O(1)的时间内完成.(之前有些操作为O(n)).

-  双向链表的节点WokerBean与对应的ImageView双向绑定,便于当随意图片切换时可以在O(1)的时间中找到对应的WokerBean,也便于删除操作.
-   将图片的切换和删除等等功能封装到WorkBeansList中,而不在控制器中实现这些业务逻辑,进一步减少那些写在控制器中的业务逻辑代码.
-   重写后的WorkBeanList类主要功能为映射页面操作对应的数据操作.并保存由页面操作产生的数据.并提供对外的接口getInformation以便后序对操作完成的数据进行word导出.
- 所有对图形界面的操作都会导致内存数据模型的变化,而这个变化都包装在重写的WorkBeanList类中.
- WorkBeansList采用内部类Workbean来存储数据,并于继承自ImageView的BindImageView双向绑定来实时响应界面操作导致的数据变化.

## 4.针对导入图片功能中重复的代码

将控制器中使用文件夹导入图片和直接导入图片的两个控制方法的业务逻辑重新梳理,在导入的代码中直接调用重写的WorkBeansList的insertImage方法.

## 5.针对无效变量和方法

将controller中无意义的setPreMark方法移除.

将无意义的stage,init成员变量移除.

## 6.使用重写的WorkBeansList来重构ProjectSetController类

在控制器的初始化方法中实例化WorkBeansList类,并作为成员变量保存.

后续将那些回调方法中需要实现的功能大部分都可以委托WorkBeansList来完成.

## 7.新增WorkBeanIn接口

该接口主要为WokerBeansList内部类的接口,主要功能为提供给外部获取信息的方法,而不允许修改.由内部类实现.

在WokerBeansList中的getInformation方法中,将保存内部类的信息的实例作为该接口的实例返回,从而使得原本对外部是不可见的内部类信息可以通过接口的方法获得.

# 2.对ImportWord中的代码进行更高效的重构

##1.针对ImportWordThread类

 删除之前无意义的ImportWordThread类,将导出功能的代码放在ImportWord中进行更好的组织.

##2.针对代码中的不合理设计

将频繁使用的目标文件target和Freemaker中的Template设为成员变量,并在构造方法中进行初始化.

## 3.使用缓冲增加I/0速度

所有对磁盘的输出输入都使用Buffered类来包装,提高读写的速度.

#3.针对PreMark类实现功能进行优化

- 新增PreMarkGet类,PreMarkGet类主要功能为得到mark文件夹里信息文件预设的标记.
- 分离原本PreMark里的功能,将名字改为PreMarkSet,只需要在界面设置预设的标记.
- 将标记获取的功能移至新增的PreMarkGet类中实现,PreMarkSet类调用PreMarkGet类来获取预设标记信息.

# 4.新增MarkManage工具类作为标记的管理

- 使用Set来避免重复的标记

- 更好的复用了PreMarkSet和PreMarkGet类.

- 代码更有组织性


# 5.项目中的一些更改

- 将之前任意命名的包名重新命名.
- 将所有的fxml文件放入fxml包中.
- 增加了icon图标.
- 为了防止大质量的图片对系统造成的影响,使用thumbnailator去缩小图片质量,并将压缩后的图片放入相对位置下的Image文件夹中.经测试当处理对象为大量高质量的图片时,系统速度得到极大提高.
- 删除不再需要的MyBean包和里面所有的类,因为数据的储存已交由WokBeansList的内部类来实现.
- 将之前分文件输出再合并的方式改为使用I/O工具类中的追加写入和Freemaker结合输出,极大提高了导出速度,
- 且将图片的编码后的大对象设置为局部对象,使得当内存不足时,GC会开始工作,不会再发生OutOfMemeryException.
- 修改了鼠标移动到大约右下侧会清除数据展示区的bug:使用MouseClicked事件来代替MouseExisted事件.
- 新增对标记展示的动态换行,使之不超过水平最大范围.
- 解决了表格会跨页的问题.

重构后的代码组织图为:

![](C:\Users\振\Desktop\重构后.PNG)

重构前的代码组织图为:

![](C:\Users\振\Desktop\重构前.PNG)

