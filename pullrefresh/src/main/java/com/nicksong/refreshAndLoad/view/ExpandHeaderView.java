package com.nicksong.refreshAndLoad.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicksong.refreshAndLoad.R;
import com.nicksong.refreshAndLoad.utils.AnimUtil;
import com.nicksong.refreshAndLoad.widget.layout.BaseHeaderView;
import com.nicksong.refreshAndLoad.widget.layout.PullRefreshLayout;
import com.nicksong.refreshAndLoad.widget.support.type.LayoutType;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

public class ExpandHeaderView extends BaseHeaderView {
    View progress;
    View stateImg;
    View loadBox;

    int state = NONE;

    private final static int layoutType = LayoutType.LAYOUT_DRAWER;

    public ExpandHeaderView(Context context) {
        this(context, null);
    }

    public ExpandHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_header_expand, this, true);
        progress = findViewById(R.id.progress);
        stateImg = findViewById(R.id.state);
        loadBox = findViewById(R.id.load_box);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    @Override
    public void setPullRefreshLayout(PullRefreshLayout refreshLayout) {
        super.setPullRefreshLayout(refreshLayout);
        refreshLayout.setMaxDistance(300);
    }

    @Override
    protected void onStateChange(int state) {
        this.state = state;
        ObjectAnimator.clearAllAnimations();
        stateImg.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.VISIBLE);
        ViewHelper.setAlpha(progress, 1f);
        switch (state) {
            case NONE:
                break;
            case PULLING:
                break;
            case LOOSENT_O_REFRESH:
                break;
            case REFRESHING:
                AnimUtil.startRotation(progress, ViewHelper.getRotation(progress) + 359.99f, 500, 0, -1);
                break;
            case REFRESH_CLONE:
                AnimUtil.startShow(stateImg, 0.1f, 400, 200);
                AnimUtil.startHide(progress);
                break;

        }

    }

    @Override
    public float getSpanHeight() {
        return loadBox.getHeight();
    }

    @Override
    public int getLayoutType() {
        return layoutType;
    }

    @Override
    public boolean onScroll(float y) {
        boolean intercept = super.onScroll(y);
        if (!isLockState()) {
            ViewHelper.setRotation(progress, y * y * 48 / 31250);
        }
        return intercept;
    }
}
