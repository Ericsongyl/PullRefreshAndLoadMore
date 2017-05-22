package com.nicksong.refreshAndLoad.widget.support.utils;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

import com.nicksong.refreshAndLoad.widget.support.impl.Pullable;

public class CanPullUtil {

    public static Pullable getPullAble(View view) {
        if (view == null) {
            return null;
        }
        if (view instanceof Pullable) {
            return (Pullable) view;
        } else if (view instanceof AbsListView) {
            return new AbsListViewCanPull((AbsListView) view);
        } else if (view instanceof ScrollView || view instanceof NestedScrollView) {
            return new ScrollViewCanPull((ViewGroup) view);
        } else if (view instanceof WebView) {
            return new WebViewCanPull((WebView) view);
        } else if (view instanceof RecyclerView) {
            return new RecyclerViewCanPull((RecyclerView) view);
        }
        return null;
    }

    private static class AbsListViewCanPull implements Pullable {
        public AbsListViewCanPull(AbsListView absListView) {
            this.absListView = absListView;
        }

        AbsListView absListView;

        @Override
        public boolean isGetTop() {
            if (absListView.getCount() == 0) {
                return true;
            } else if (absListView.getFirstVisiblePosition() == 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop()) {
                return true;
            }
            return false;
        }

        @Override
        public boolean isGetBottom() {
            int firstVisiblePosition = absListView.getFirstVisiblePosition();
            int lastVisiblePosition = absListView.getLastVisiblePosition();
            int count = absListView.getCount();
            if (count == 0) {
                return true;
            } else if (lastVisiblePosition == (count - 1)) {
                View view = absListView.getChildAt(lastVisiblePosition - firstVisiblePosition);
                if (view != null && view.getBottom() <= absListView.getMeasuredHeight() - absListView.getPaddingBottom())
                    return true;
            }
            return false;
        }
    }

    private static class ScrollViewCanPull implements Pullable {
        public ScrollViewCanPull(ViewGroup scrollView) {
            this.scrollView = scrollView;
        }

        ViewGroup scrollView;

        @Override
        public boolean isGetTop() {
            if (scrollView.getScrollY() <= 0)
                return true;
            else
                return false;
        }

        @Override
        public boolean isGetBottom() {
            if (scrollView.getChildCount() == 0) {
                return true;
            }
            if (scrollView.getScrollY() >= (scrollView.getChildAt(0).getHeight() - scrollView.getMeasuredHeight()))
                return true;
            else
                return false;
        }
    }


    private static class RecyclerViewCanPull implements Pullable {
        public RecyclerViewCanPull(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        RecyclerView recyclerView;
        LinearLayoutManager layoutManager;

        private void initLayoutManager() {
            if (layoutManager == null) {
                RecyclerView.LayoutManager layout = recyclerView.getLayoutManager();
                if (layout != null && layout instanceof LinearLayoutManager) {
                    layoutManager = (LinearLayoutManager) layout;
                }
            }
        }

        @Override
        public boolean isGetTop() {
            initLayoutManager();
            if (layoutManager != null) {
                if (layoutManager.getItemCount() == 0) {
                    return true;
                } else if (layoutManager.findFirstVisibleItemPosition() == 0 && recyclerView.getChildAt(0).getTop() >= recyclerView.getPaddingTop()) {
                    return true;
                }
            }
            return false;
        }


        @Override
        public boolean isGetBottom() {
            initLayoutManager();
            if (layoutManager != null) {
                int count = layoutManager.getItemCount();
                if (count == 0) {
                    return true;
                } else if (layoutManager.findLastCompletelyVisibleItemPosition() == count - 1) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class WebViewCanPull implements Pullable {
        public WebViewCanPull(WebView webView) {
            this.webView = webView;
        }

        WebView webView;

        @Override
        public boolean isGetBottom() {
            if (webView.getScrollY() >= webView.getContentHeight() * webView.getScale() - webView.getMeasuredHeight())
                return true;
            else
                return false;
        }

        @Override
        public boolean isGetTop() {
            if (webView.getScrollY() <= 0)
                return true;
            else
                return false;
        }
    }

}
