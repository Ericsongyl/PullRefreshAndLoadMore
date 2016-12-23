# PullRefreshAndLoadMore
Android：实现下拉刷新、上拉加载更多的框架，支持所有view，如常用布局、TextView、ScrollView等。

#如何使用
这里以经典下拉刷新为例，其他类别使用与此类似。 
1. 直接在相应的布局文件中使用PullRefreshLayout、NormalHeaderView、NormalFooterView，eg：
```
<?xml version="1.0" encoding="utf-8"?>
<com.nicksong.pullrefresh.widget.layout.PullRefreshLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/root"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/color_gray">

    <com.nicksong.pullrefresh.view.NormalHeaderView
        android:id="@+id/header"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />


    <com.nicksong.pullrefresh.view.NormalFooterView
        android:id="@+id/footer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />


    <ListView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/white"
        android:cacheColorHint="@null"
        android:divider="#ccc"
        android:dividerHeight="1px" />

</com.nicksong.pullrefresh.widget.layout.PullRefreshLayout>
```
2. 在源文件中实现BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener这两个方法，eg：
```
headerView.setOnRefreshListener(this);
footerView.setOnLoadListener(this);

@Override
public void onRefresh(BaseHeaderView baseHeaderView) {
    baseHeaderView.postDelayed(new Runnable() {
        @Override
        public void run() {
            page = 1;
            //这里用户直接处理下拉刷新要做的事情
            ...... ......
            headerView.stopRefresh(); //最后要停止刷新
        }
    }, 3000);
}

@Override
public void onLoad(BaseFooterView baseFooterView) {
    baseFooterView.postDelayed(new Runnable() {
        @Override
        public void run() {
            page++;
            //这里用户直接处理上拉加载要做的事情
            ...... ......
            footerView.stopLoad(); //最后要停止加载
        }
    }, 3000);
}
```

#效果图
##经典下拉刷新上拉加载（整体布局下移的下拉刷新、上移的上拉加载）
![经典下拉刷新上拉加载](https://github.com/Ericsongyl/PullRefreshAndLoadMore/blob/master/gif/GIF1.gif)


##抽屉模式下拉刷新上拉加载（整体布局不移动的下拉刷新、上拉加载）
![抽屉模式下拉刷新上拉加载](https://github.com/Ericsongyl/PullRefreshAndLoadMore/blob/master/gif/GIF2.gif)


##滑块模式下拉刷新上拉加载（整体布局下移的下拉刷新、上移的上拉加载，与经典模式不同的就是显示效果）
![滑块模式下拉刷新上拉加载](https://github.com/Ericsongyl/PullRefreshAndLoadMore/blob/master/gif/GIF3.gif)


##文本控件的下拉刷新上拉加载（整体布局下移的下拉刷新、上移的上拉加载，支持页面布局全部为文本控件）
![文本控件的下拉刷新上拉加载](https://github.com/Ericsongyl/PullRefreshAndLoadMore/blob/master/gif/GIF4.gif)


##ScorllView下拉刷新上拉加载（整体布局下移的下拉刷新、上移的上拉加载）
![ScorllView下拉刷新上拉加载](https://github.com/Ericsongyl/PullRefreshAndLoadMore/blob/master/gif/GIF5.gif)
