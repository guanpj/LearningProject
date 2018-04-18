package com.guanpj.me.jframework;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanpj.me.jframework.bean.Person;

import butterknife.ButterKnife;

/**
 * Created by Jie on 2017-3-8.
 */

public class TypeOneViewHolder extends BaseViewHolder<Person> {

    ImageView image;
    TextView name;
    TextView age;

    public TypeOneViewHolder(View itemView) {
        super(itemView);
        image = ButterKnife.findById(itemView, R.id.img);
        name = ButterKnife.findById(itemView, R.id.name);
        age = ButterKnife.findById(itemView, R.id.age);
    }

    @Override
    protected void onItemClick(View v, int position) {

    }

    @Override
    protected void convert(Person data) {
        image.setBackgroundResource(data.getHeader());
        name.setText(data.getName());
        age.setText(data.getAge() + "");
    }
}
