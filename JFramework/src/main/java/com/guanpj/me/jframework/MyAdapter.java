package com.guanpj.me.jframework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guanpj.me.jframework.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jie on 2017-3-8.
 */

public class MyAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<Person> mData = new ArrayList<>();

    public MyAdapter(Context context, List<Person> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BaseViewHolder holder;
        if(viewType == 0) {
            View view = inflater.inflate(R.layout.list_item_one, parent, false);
            holder = new TypeOneViewHolder(view);
        }
        else {
            View view = inflater.inflate(R.layout.list_item_two, parent, false);
            holder = new TypeTwoViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.convert(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if(mData == null)
        {
            return 0;
        }
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? 0 : 1;
    }

}
