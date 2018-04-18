package com.guanpj.me.jframework;

import android.view.View;
import android.widget.TextView;

import com.guanpj.me.jframework.bean.Person;

import butterknife.ButterKnife;

/**
 * Created by Jie on 2017-3-8.
 */

public class TypeTwoViewHolder extends BaseViewHolder<Person> {

    TextView name;

    public TypeTwoViewHolder(View itemView) {
        super(itemView);
        name = ButterKnife.findById(itemView, R.id.name);
    }

    @Override
    protected void onItemClick(View v, int position) {

    }

    @Override
    protected void convert(Person data) {
        name.setText("Type2  " + data.getName());
    }
}
