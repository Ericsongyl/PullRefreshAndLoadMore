package com.nicksong.refreshAndLoad.widget.layout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.nicksong.refreshAndLoad.widget.support.impl.Loadable;
import com.nicksong.refreshAndLoad.widget.support.type.LayoutType;


public abstract class BaseFooterView extends RelativeLayout implements Loadable {

    public final static int NONE = 0;
    public final static int PULLING = 1;
    public final static int LOOSENT_O_LOAD = 2;
    public final static int LOADING = 3;
    public final static int LOAD_CLONE = 4;
    private int stateType = NONE;

    private PullRefreshLayout pullRefreshLayout;

    private boolean isLockState = false;

    private OnLoadListener onLoadListener;

    private int scrollState = FlingLayout.SCROLL_STATE_IDLE;

    public BaseFooterView(Context context) {
        this(context, null);
    }

    public BaseFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        Log.i("BaseFooterView", "" + state);
        this.stateType = state;
        if (state == LOADING) {
            isLockState = true;
            if (onLoadListener != null) {
                onLoadListener.onLoad(this);
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
            if (moveY < 0) {
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
    public void startLoad() {
        if (this.pullRefreshLayout != null) {
            float moveY = pullRefreshLayout.getMoveY();
            if (moveY == 0) {
                float footerSpanHeight = getSpanHeight();
                pullRefreshLayout.startMoveTo(0, -footerSpanHeight);
                setState(LOADING);
            }
        }
    }

    @Override
    public void stopLoad() {
        isLockState = false;
        setState(LOAD_CLONE);
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
        int footerLayoutType = getLayoutType();
        if (footerLayoutType == LayoutType.LAYOUT_SCROLLER) {
            ViewCompat.setTranslationY(this, -getMeasuredHeight());
        } else if (footerLayoutType == LayoutType.LAYOUT_DRAWER) {
            ViewCompat.setTranslationY(this, y);
            ViewCompat.setTranslationY(pullRefreshLayout.getPullView(), 0);
            intercept = true;
        } else {
            ViewCompat.setTranslationY(this, y);
        }
        float footerSpanHeight = getSpanHeight();
        if (scrollState == FlingLayout.SCROLL_STATE_TOUCH_SCROLL) {
            if (y <= -footerSpanHeight) {
                setState(LOOSENT_O_LOAD);
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
        float footerSpanHeight = getSpanHeight();
        if (nowY <= -footerSpanHeight) {
            pullRefreshLayout.startMoveTo(nowY, -footerSpanHeight);
            setState(LOADING);
            return true;
        }
        pullRefreshLayout.startMoveTo(nowY, 0);
        setState(NONE);
        return false;
    }

    public abstract float getSpanHeight();

    protected abstract void onStateChange(int state);

    public interface OnLoadListener {
        void onLoad(BaseFooterView baseFooterView);
    }

    public void setOnLoadListener(OnLoadListener onRefreshListener) {
        this.onLoadListener = onRefreshListener;
    }

}

