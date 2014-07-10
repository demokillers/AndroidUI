完成详细求助信息页面的布局：
最上方写着“求助信息”的TextView，
接着一个ListView（第一个子项是求助信息，之后的子项是援助信息），
最底端是三个按钮的一个Fragment碎片。

JAVA文件夹：

DetailMessageActivity.java: 页面的主文件，负责给ListView传送数据

AssistListViewAdapter.java: 重写ListView的适配器，使其能添加 求助信息 和 援助信息 两种子项

BottomButtonFragment.java: 页面底端的三个按钮的碎片文件，使其能悬浮于界面


layout文件夹：

detailmessage_activity.xml: 对该页面总的布局

bottom_button_fragment.xml: 对底端三个按钮的布局

first_item.xml: 对第一个子项，即求助信息的布局，包括头像，昵称，日期，内容，关注和援助两个按钮

assist_item.xml: 对后边子项，即援助信息的布局，包括头像，昵称，日期，内容


drawable文件夹：

里边是用到的图片。