package com.guanpj.me.japp.widget.jrecyclerview.layoutmanager;

import android.support.v7.widget.RecyclerView;

import com.guanpj.me.japp.core.BaseAdapter;

/**
 * Created by Jie on 2017-3-10.
 */

public interface ILayoutManager {
    RecyclerView.LayoutManager getLayoutManager();
    int getLastPosition();
    void setAdapter(BaseAdapter mAdapter);
}
