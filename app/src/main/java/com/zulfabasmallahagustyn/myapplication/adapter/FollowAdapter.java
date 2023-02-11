package com.zulfabasmallahagustyn.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.zulfabasmallahagustyn.myapplication.R;
import com.zulfabasmallahagustyn.myapplication.model.follow.ModelFollow;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.FolowersViewHolder> {

    private final ArrayList<ModelFollow> modelFollowArrayList = new ArrayList<>();

    public FollowAdapter(Context ignoredContext) {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFollowList(ArrayList<ModelFollow> items) {
        modelFollowArrayList.clear();
        modelFollowArrayList.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FollowAdapter.FolowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new FolowersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowAdapter.FolowersViewHolder holder, int position) {
        ModelFollow item = modelFollowArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.imageUser);

        holder.tvUsername.setText(item.getLogin());
        holder.tvUrl.setText(item.getHtmlUrl());
        /*holder.cvListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.DETAIL_USER, modelFollowersArrayList.get(position));
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return modelFollowArrayList.size();
    }

    public static class FolowersViewHolder extends RecyclerView.ViewHolder {

        CardView cvListUser;
        TextView tvUsername, tvUrl;
        ImageView imageUser, imageArrow;

        public FolowersViewHolder(View itemView) {
            super(itemView);
            cvListUser = itemView.findViewById(R.id.cvListUser);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvUrl = itemView.findViewById(R.id.tvUrl);
            imageUser = itemView.findViewById(R.id.imageUser);
            imageArrow = itemView.findViewById(R.id.imageArrow);

            imageArrow.setVisibility(View.GONE);
        }
    }

}
