package com.guanpj.me.japp.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Jie on 2017-3-7.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, getAdapterPosition());
            }
        });
    }

    protected abstract void bindViewHolder(int position);
    protected abstract void onItemClick(View view, int position);
}
