package com.guanpj.me.japp.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanpj.me.japp.R;
import com.guanpj.me.japp.common.ConstantValues;
import com.guanpj.me.japp.core.BaseListActivity;
import com.guanpj.me.japp.core.BaseViewHolder;
import com.guanpj.me.japp.module.Module;

import java.util.ArrayList;

/**
 * Created by Jie on 2017/3/9.
 */

public class HomeActivity extends BaseListActivity<Module> {

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.title_framework_main);
    }

    @Override
    protected void setUpMenu(int menuId) {
        super.setUpMenu(R.menu.menu_home);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        super.setUpData(savedInstanceState);
        mJRecyclerView.enablePullToRefresh(false);
        mJRecyclerView.setRefreshing();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onRefresh(int action) {
        mData = new ArrayList<>();
        mData.add(new Module("RecyclerView基于BaseListActivity\n支持下拉刷新,加载更多", SimpleListActivity.class));
        mJRecyclerView.onRefreshCompleted();
    }

    private class ViewHolder extends BaseViewHolder{

        private final TextView mHomeItemModuleLabel;

        public ViewHolder(View view) {
            super(view);
            mHomeItemModuleLabel = (TextView) view.findViewById(R.id.mHomeItemModuleLabel);
        }

        @Override
        protected void bindViewHolder(int position) {
            mHomeItemModuleLabel.setText(mData.get(position).moduleName);
        }

        @Override
        protected void onItemClick(View view, int position) {
            startActivity(new Intent(HomeActivity.this, mData.get(position).moduleTargetClass));
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.stay4it.com/course/7"));
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int action = intent.getIntExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_BACK_TO_HOME);
        switch (action) {
            case ConstantValues.ACTION_KICK_OUT:
                break;
            case ConstantValues.ACTION_LOGOUT:
                break;
            case ConstantValues.ACTION_RESTART_APP:
                protectApp();
                break;
            case ConstantValues.ACTION_BACK_TO_HOME:
                break;
        }
    }

    @Override
    protected void protectApp() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}
