发送求助信息的页面：

用户可编辑文本信息，或者语音，视频。。 提供下拉列表输入常用语。


SendHelpMsgActivity.java  主文件，对下拉列表初始化并监听选择，输入编辑框。


sendhelpmsg_activity.xml  布局文件


table_shape.xml  直接用之前drawable里边的那个


为了布局好看点， 在AndroidManifest文件里添加在<activity>里边：

android:windowSoftInputMode="adjustUnspecified|stateVisible"

使得默认启动输入框，屏幕充满。