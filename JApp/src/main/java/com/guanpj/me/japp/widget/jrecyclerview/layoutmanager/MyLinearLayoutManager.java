package com.guanpj.me.japp.widget.jrecyclerview.layoutmanager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.guanpj.me.japp.core.BaseAdapter;

/**
 * Created by Jie on 2017/3/13.
 */

public class MyLinearLayoutManager extends LinearLayoutManager implements ILayoutManager {

    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int getLastPosition() {
        return findLastVisibleItemPosition();
    }

    @Override
    public void setAdapter(BaseAdapter mAdapter) {

    }
}
