package com.guanpj.me.japp.core;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.guanpj.me.japp.R;
import com.guanpj.me.japp.widget.DividerItemDecoration;
import com.guanpj.me.japp.widget.jrecyclerview.JRecyclerView;
import com.guanpj.me.japp.widget.jrecyclerview.layoutmanager.ILayoutManager;
import com.guanpj.me.japp.widget.jrecyclerview.layoutmanager.MyLinearLayoutManager;

import java.util.List;

/**
 * Created by Jie on 2017-3-7.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements JRecyclerView.OnRecyclerViewRefreshListener {

    protected JRecyclerView mJRecyclerView;
    protected BaseAdapter mAdapter;
    protected List<T> mData;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, -1);
    }

    @Override
    protected void setUpView() {
        mJRecyclerView = (JRecyclerView) findViewById(R.id.jRecyclerView);
    }

    @Override
    protected void setUpEvent() {
        mJRecyclerView.setOnRecyclerViewRefreshListener(this);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        mAdapter = new BaseListAdapter();
        mJRecyclerView.setLayoutManager(getLayoutManager());
        mJRecyclerView.addItemDecoration(getItemDecoration());
        mJRecyclerView.setAdapter(mAdapter);
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getApplicationContext(), R.drawable.list_divider);
    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getApplicationContext());
    }

    private class BaseListAdapter extends BaseAdapter{

        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        protected int getDataCount() {
            return mData != null ? mData.size() : 0;
        }
    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);
}
