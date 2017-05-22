package com.nicksong.refreshAndLoad.widget.layout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.nicksong.refreshAndLoad.widget.support.impl.Refreshable;
import com.nicksong.refreshAndLoad.widget.support.type.LayoutType;

public abstract class BaseHeaderView extends RelativeLayout implements Refreshable {

    public final static int NONE = 0;
    public final static int PULLING = 1;
    public final static int LOOSENT_O_REFRESH = 2;
    public final static int REFRESHING = 3;
    public final static int REFRESH_CLONE = 4;
    private int stateType = NONE;

    private PullRefreshLayout pullRefreshLayout;

    private boolean isLockState = false;

    OnRefreshListener onRefreshListener;

    private int scrollState = FlingLayout.SCROLL_STATE_IDLE;

    public BaseHeaderView(Context context) {
        this(context, null);
    }

    public BaseHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusable(false);
        setFocusableInTouchMode(false);
    }

    protected boolean isLockState() {
        return isLockState;
    }

    public int getLayoutType() {
        return LayoutType.LAYOUT_NORMAL;
    }


    private void setState(int state) {
        if (isLockState || stateType == state) {
            return;
        }
        Log.i("BaseHeaderView", "" + state);
        this.stateType = state;
        if (state == REFRESHING) {
            isLockState = true;
            if (onRefreshListener != null) {
                onRefreshListener.onRefresh(this);
            }
        }
        onStateChange(state);
    }

    public int getType() {
        return stateType;
    }


    private void close() {
        if (this.pullRefreshLayout != null) {
            float moveY = pullRefreshLayout.getMoveY();
            if (moveY > 0) {
                pullRefreshLayout.startMoveTo(moveY, 0);
                setState(NONE);
            }
        }
    }

    @Override
    public void setPullRefreshLayout(PullRefreshLayout pullRefreshLayout) {
        this.pullRefreshLayout = pullRefreshLayout;
    }

    @Override
    public void startRefresh() {
        if (pullRefreshLayout != null) {
            float moveY = pullRefreshLayout.getMoveY();
            if (moveY == 0) {
                float headerSpanHeight = getSpanHeight();
                pullRefreshLayout.startMoveTo(0, headerSpanHeight);
                setState(REFRESHING);
            }
        }
    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {
        if (pullRefreshLayout != null) {
            float moveY = pullRefreshLayout.getMoveY();
            if (moveY == 0) {
                float headerSpanHeight = 310;
                pullRefreshLayout.startMoveTo(0, headerSpanHeight);
                setState(REFRESHING);
            }
        }
    }

    @Override
    public void stopRefresh() {
        isLockState = false;
        setState(REFRESH_CLONE);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }, 400);
    }

    @Override
    public boolean onScroll(float y) {
        boolean intercept = false;
        int layoutType = getLayoutType();
        if (layoutType == LayoutType.LAYOUT_SCROLLER) {
            ViewCompat.setTranslationY(this, getMeasuredHeight());
        } else if (layoutType == LayoutType.LAYOUT_DRAWER) {
            ViewCompat.setTranslationY(this, y);
            ViewCompat.setTranslationY(pullRefreshLayout.getPullView(), 0);
            intercept = true;
        } else {
            ViewCompat.setTranslationY(this, y);
        }
        float headerSpanHeight = getSpanHeight();
        if (scrollState == FlingLayout.SCROLL_STATE_TOUCH_SCROLL) {
            if (y >= headerSpanHeight) {
                setState(LOOSENT_O_REFRESH);
            } else {
                setState(PULLING);
            }
        }
        return intercept;
    }

    @Override
    public void onScrollChange(int state) {
        scrollState = state;
    }

    @Override
    public boolean onStartFling(float nowY) {
        float headerSpanHeight = getSpanHeight();
        if (nowY >= headerSpanHeight) {
            pullRefreshLayout.startMoveTo(nowY, headerSpanHeight);
            setState(REFRESHING);
            return true;
        }
        pullRefreshLayout.startMoveTo(nowY, 0);
        setState(NONE);
        return false;
    }

    public abstract float getSpanHeight();

    protected abstract void onStateChange(int state);

    public interface OnRefreshListener {
        void onRefresh(BaseHeaderView baseHeaderView);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

}
