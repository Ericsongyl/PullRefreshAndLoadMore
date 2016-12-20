package com.nicksong.pullrefresh.widget.support.impl;

import com.nicksong.pullrefresh.widget.layout.PullRefreshLayout;

public interface Refreshable {

    void setPullRefreshLayout(PullRefreshLayout refreshLayout);

    boolean onScroll(float y);

    void onScrollChange(int state);

    boolean onStartFling(float offsetTop);

    void startRefresh();

    void stopRefresh();
}
