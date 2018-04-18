package com.guanpj.me.japp.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guanpj.me.japp.R;
import com.guanpj.me.japp.api.Api;
import com.guanpj.me.japp.core.BaseListActivity;
import com.guanpj.me.japp.core.BaseViewHolder;
import com.guanpj.me.japp.module.BaseModel;
import com.guanpj.me.japp.module.Benefit;
import com.guanpj.me.japp.widget.jrecyclerview.JRecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jie on 2017/3/9.
 */

public class SimpleListActivity extends BaseListActivity<Benefit> {

    private int page = 1;

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.title_recycler_activity);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        super.setUpData(savedInstanceState);
        mJRecyclerView.setRefreshing();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onRefresh(final int action) {
        if(mData == null){
            mData = new ArrayList<>();
        }
        if (action == JRecyclerView.ACTION_PULL_TO_REFRESH) {
            page = 1;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<BaseModel<ArrayList<Benefit>>> call = api.defaultBenefits(10, page++);
        call.enqueue(new Callback<BaseModel<ArrayList<Benefit>>>() {
            @Override
            public void onResponse(Call<BaseModel<ArrayList<Benefit>>> call, Response<BaseModel<ArrayList<Benefit>>> response) {
                if(action == JRecyclerView.ACTION_PULL_TO_REFRESH) {
                    mData.clear();
                }
                if (response.body().results == null || response.body().results.size() == 0) {
                    mJRecyclerView.enableLoadMore(false);
                } else {
                    mJRecyclerView.enableLoadMore(true);
                    mData.addAll(response.body().results);
                    mAdapter.notifyDataSetChanged();
                }
                mJRecyclerView.onRefreshCompleted();
            }

            @Override
            public void onFailure(Call<BaseModel<ArrayList<Benefit>>> call, Throwable t) {
                mJRecyclerView.onRefreshCompleted();
            }
        });
    }

    private class SampleViewHolder extends BaseViewHolder{

        ImageView mSampleListItemImg;
        TextView mSampleListItemLabel;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
            mSampleListItemImg = (ImageView) itemView.findViewById(R.id.mSampleListItemImg);
        }

        @Override
        protected void bindViewHolder(int position) {
            mSampleListItemLabel.setVisibility(View.GONE);
            Glide.with(mSampleListItemImg.getContext())
                    .load(mData.get(position).url)
                    .centerCrop()
                    .placeholder(R.color.app_primary_color)
                    .crossFade()
                    .into(mSampleListItemImg);
        }

        @Override
        protected void onItemClick(View view, int position) {

        }
    }
}
