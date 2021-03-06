package com.pfh.app_mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pfh.app_mvp.R;
import com.pfh.app_mvp.model.Repository;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<Repository> repositories;

    public RepositoryAdapter() {
        this.repositories = Collections.emptyList();
    }

    public RepositoryAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }


    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        holder.text_repo_title.setText(repository.getName());
        holder.text_repo_description.setText(repository.getDescription());
        holder.text_watchers.setText(repository.getWatchers()+"");
        holder.text_forks.setText(repository.getForks()+"");
        holder.text_stars.setText(repository.getStars()+"");
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    static class RepositoryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_repo_title)
        TextView text_repo_title;
        @BindView(R.id.text_repo_description)
        TextView text_repo_description;
        @BindView(R.id.text_watchers)
        TextView text_watchers;
        @BindView(R.id.text_forks)
        TextView text_forks;
        @BindView(R.id.text_stars)
        TextView text_stars;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
