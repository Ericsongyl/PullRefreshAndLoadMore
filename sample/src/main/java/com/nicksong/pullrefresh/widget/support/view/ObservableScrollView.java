package com.nicksong.pullrefresh.widget.support.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.nicksong.pullrefresh.widget.support.impl.OnScrollListener;

public class ObservableScrollView extends ScrollView {

    private OnScrollListener onScrollListener = null;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollListener != null) {
            onScrollListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
