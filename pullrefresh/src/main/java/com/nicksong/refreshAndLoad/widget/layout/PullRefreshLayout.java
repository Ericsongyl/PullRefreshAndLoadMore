package com.nicksong.refreshAndLoad.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.nicksong.refreshAndLoad.widget.support.impl.Loadable;
import com.nicksong.refreshAndLoad.widget.support.impl.Refreshable;

public class PullRefreshLayout extends FlingLayout {

    protected Loadable mFooter;
    protected Refreshable mHeader;
    protected boolean hasHeader = true;
    protected boolean hasFooter = true;

    public PullRefreshLayout(Context context) {
        this(context, null);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onScroll(float y) {
        if (mHeader != null && hasHeader && y >= 0) {
            boolean intercept = mHeader.onScroll(y);
            if (y != 0) {
                return intercept;
            }
        }
        if (mFooter != null && hasFooter && y <= 0) {
            boolean intercept = mFooter.onScroll(y);
            if (y != 0) {
                return intercept;
            }
        }
        return false;
    }

    @Override
    protected void onScrollChange(int stateType) {
        if (mHeader != null && hasHeader) {
            mHeader.onScrollChange(stateType);
        }
        if (mFooter != null && hasFooter) {
            mFooter.onScrollChange(stateType);
        }

    }

    @Override
    protected boolean onStartFling(float nowY) {
        if (mHeader != null && nowY > 0 && hasHeader) {
            return mHeader.onStartFling(nowY);
        } else if (mFooter != null && nowY < 0 && hasFooter) {
            return mFooter.onStartFling(nowY);
        }
        return false;
    }

    public void startRefresh() {
        if (mHeader != null && hasHeader) {
            mHeader.startRefresh();
        }
    }

    public void startLoad() {
        if (mFooter != null && hasFooter) {
            mFooter.startLoad();
        }
    }

    public void stopRefresh() {
        if (mHeader != null && hasHeader) {
            mHeader.stopRefresh();
        }
    }

    public void stopLoad() {
        if (mFooter != null && hasFooter) {
            mFooter.stopLoad();
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof Refreshable && mHeader == null) {
            mHeader = (Refreshable) child;
            mHeader.setPullRefreshLayout(this);
        } else if (child instanceof Loadable && mFooter == null) {
            mFooter = (Loadable) child;
            mFooter.setPullRefreshLayout(this);
        }
        super.addView(child, index, params);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int height = getHeight();
        if (mHeader != null) {
            View mHeaderView = (View) mHeader;
            mHeaderView.layout(mHeaderView.getLeft(), -mHeaderView.getMeasuredHeight(), mHeaderView.getRight(), 0);
        }
        if (mFooter != null) {
            View mFooterView = (View) mFooter;
            mFooterView.layout(mFooterView.getLeft(), height, mFooterView.getRight(), height + mFooterView.getMeasuredHeight());
        }
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }
}