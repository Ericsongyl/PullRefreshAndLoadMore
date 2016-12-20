package com.nicksong.pullrefresh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nicksong.pullrefresh.widget.layout.BaseFooterView;
import com.nicksong.pullrefresh.widget.layout.FlingLayout;
import com.nicksong.pullrefresh.R;
import com.nicksong.pullrefresh.utils.AnimUtil;
import com.nicksong.pullrefresh.widget.support.type.LayoutType;

public class LockFooterView extends BaseFooterView {
    View progress;
    View stateImg;
    View loadBox;
    int width = 0;
    Path path;
    Paint paint;

    int state = NONE;

    int layoutType = LayoutType.LAYOUT_SCROLLER;

    public LockFooterView(Context context) {
        this(context, null);
    }

    public LockFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_footer_lock, this, true);
        progress = findViewById(R.id.progress);
        stateImg = findViewById(R.id.state);
        loadBox = findViewById(R.id.load_box);
        path = new Path();
        paint = new Paint();
        paint.setColor(0x8888ff00);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, 400);
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(400, MeasureSpec.EXACTLY));
        width = getWidth();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onStateChange(int state) {
        this.state = state;
        ObjectAnimator.clearAllAnimations();
        stateImg.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
        ViewHelper.setAlpha(progress, 1f);
        switch (state) {
            case NONE:
                break;
            case PULLING:
                break;
            case LOOSENT_O_LOAD:
                break;
            case LOADING:
                AnimUtil.startShow(progress, 0.1f, 200, 0);
                AnimUtil.startRotation(progress, ViewHelper.getRotation(progress) + 359.99f, 500, 0, -1);
                break;
            case LOAD_CLONE:
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
        ViewHelper.setTranslationY(loadBox, 400 + 0.97f * y);
        if (!isLockState()) {
            ViewHelper.setRotation(progress, y * y * 48 / 31250);
        }
        path.reset();// 重置path
        if (y == 0) {
            invalidate();
            return intercept;
        }
        // 贝赛尔曲线的起始点
        path.moveTo(0, 400);
        // 设置贝赛尔曲线的操作点以及终止点
        path.quadTo(width / 2, 400 + 1.94f * y, width, 400);
        invalidate();
        return intercept;
    }
}
