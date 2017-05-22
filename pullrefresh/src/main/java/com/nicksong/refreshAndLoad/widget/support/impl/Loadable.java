package com.nicksong.refreshAndLoad.widget.support.impl;

import com.nicksong.refreshAndLoad.widget.layout.PullRefreshLayout;

public interface Loadable {

    void setPullRefreshLayout(PullRefreshLayout refreshLayout);

    boolean onScroll(float y);

    void onScrollChange(int state);

    boolean onStartFling(float offsetTop);

    void startLoad();

    void stopLoad();
}
