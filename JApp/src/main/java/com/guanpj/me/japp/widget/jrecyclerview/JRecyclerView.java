package com.guanpj.me.japp.widget.jrecyclerview;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.guanpj.me.japp.R;
import com.guanpj.me.japp.core.BaseAdapter;
import com.guanpj.me.japp.widget.jrecyclerview.layoutmanager.ILayoutManager;

/**
 * Created by Jie on 2017/3/8.
 */

public class JRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    public static final int ACTION_PULL_TO_REFRESH = 1;
    public static final int ACTION_LOAD_MORE_REFRESH = 2;
    public static final int ACTION_IDLE = 0;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ILayoutManager mLayoutManager;
    private BaseAdapter mAdapter;
    private int mCurrentState;
    private boolean isPullToRefreshEnabled;
    private boolean isLoadMoreEnabled;

    public JRecyclerView(@NonNull Context context) {
        super(context);
        setUpView();
    }

    public JRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public JRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    private void setUpView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_refresh, this, true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mCurrentState == ACTION_IDLE && isLoadMoreEnabled && checkNeedLoadMore() && dy > 0){
                    mCurrentState = ACTION_LOAD_MORE_REFRESH;
                    mSwipeRefreshLayout.setEnabled(false);
                    listener.onRefresh(ACTION_LOAD_MORE_REFRESH);
                }
            }
        });
    }

    private boolean checkNeedLoadMore() {
        int lastPosition = mLayoutManager.getLastPosition();
        int totalCount = mLayoutManager.getLayoutManager().getItemCount();
        return totalCount - lastPosition > 5;
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        if(null != itemDecoration){
            mRecyclerView.addItemDecoration(itemDecoration);
        }
    }

    public void setLayoutManager(ILayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        mRecyclerView.setLayoutManager(mLayoutManager.getLayoutManager());
    }

    public void setRefreshing() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public void enableLoadMore(boolean enable) {
        isLoadMoreEnabled = enable;
    }

    public void enablePullToRefresh(boolean enable) {
        isPullToRefreshEnabled = enable;
        mSwipeRefreshLayout.setEnabled(enable);
    }

    public void setAdapter(BaseAdapter adapter) {
        mAdapter = adapter;
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {
        mCurrentState = ACTION_PULL_TO_REFRESH;
        if(null != listener){
            listener.onRefresh(ACTION_PULL_TO_REFRESH);
        }
    }


    public void onRefreshCompleted(){
        switch (mCurrentState) {
            case ACTION_PULL_TO_REFRESH:
                mSwipeRefreshLayout.setRefreshing(false);
                break;
            case ACTION_LOAD_MORE_REFRESH:
                mAdapter.onLoadMoreStateChanged(false);
                if (isPullToRefreshEnabled) {
                    mSwipeRefreshLayout.setEnabled(true);
                }
                break;
        }
        mCurrentState = ACTION_IDLE;
    }

    public interface OnRecyclerViewRefreshListener{
        void onRefresh(int action);
    }

    private OnRecyclerViewRefreshListener listener;

    public void setOnRecyclerViewRefreshListener(OnRecyclerViewRefreshListener listener){
        this.listener = listener;
    }

}
