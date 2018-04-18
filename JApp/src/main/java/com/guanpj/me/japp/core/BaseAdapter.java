package com.guanpj.me.japp.core;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guanpj.me.japp.R;

/**
 * Created by Jie on 2017-3-7.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static int VIEW_TYPE_LOAD_MORE_FOOTER = 100;
    private boolean isLoadMoreFooterShown;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateNormalViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (isLoadMoreFooterShown && position == getItemCount() - 1) {
            if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                params.setFullSpan(true);
            }
        }
        holder.bindViewHolder(position);
    }

    @Override
    public int getItemCount() {
        return getDataCount() + (isLoadMoreFooterShown ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadMoreFooterShown && position == getItemCount() - 1) {
            return VIEW_TYPE_LOAD_MORE_FOOTER;
        }
        return getDataViewType(position);
    }

    protected abstract BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);

    protected abstract int getDataCount();

    protected int getDataViewType(int position) {
        return 0;
    }

    public void onLoadMoreStateChanged(boolean isShown){
        this.isLoadMoreFooterShown = isShown;
        if (isShown) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount());
        }
    }

    public boolean isLoadMoreFooter(int position) {
        return isLoadMoreFooterShown && position == getItemCount() - 1;
    }

    protected BaseViewHolder onCreateLoadMoreFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_pull_to_refresh_footer, parent, false);
        return new LoadMoreFooterViewHolder(view);
    }

    private class LoadMoreFooterViewHolder extends BaseViewHolder {

        public LoadMoreFooterViewHolder(View view) {
            super(view);
        }

        @Override
        protected void bindViewHolder(int position) {

        }

        @Override
        public void onItemClick(View view, int position) {

        }
    }
}
