#GoogleIoView

看了[Google I/O 2014](https://play.google.com/store/apps/details?id=com.google.samples.apps.iosched&hl=zh-CN)之后觉得detail界面的效果不错，就模范着做了一个。

#使用方法

###在gradle.build中加入

    compile 'me.biubiubiu.googleioview:library:1.0'

###在xml中配置

    <me.biubiubiu.iolistview.library.GoogleIOView
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:parallax_color="#66ccff"
        />
        
###设置背景图片

        GoogleIOView ioView = (GoogleIOView) findViewById(R.id.io);
        ioView.getImageBackground().setImageResource(R.drawable.go_lang_item);
        
###设置背景颜色

        app:parallax_color="#66ccff"

###设置标题内容

        ioView.setTitle("hongbosb");


#Screenshot

![./slide.gif](http://ww1.sinaimg.cn/large/3f6e95acgw1ehqimoxjgsg20fm0i5b29.gif)

#下载

[百度网盘](http://pan.baidu.com/s/14GRls)
