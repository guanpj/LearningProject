package com.guanpj.me.jframework;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Jie on 2017-3-8.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, getLayoutPosition());
            }
        });
    }

    protected abstract void onItemClick(View v, int position);

    protected abstract void convert(T data);
}
