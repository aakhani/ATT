package com.att;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.att.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avdhesh AKhani on 9/30/2016.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    private ArrayList<Post> postList;
    private int rowLayout;
    private Context context;

    public PostAdapter(ArrayList<Post> postList, int rowLayout, Context context) {
        this.postList = postList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.tvUserName.setText(postList.get(position).getUserName());
        holder.tvDate.setText(postList.get(position).getDate());
        holder.tvMsg.setText(postList.get(position).getMessage());

        Picasso.with(context).load(postList.get(position).getUserPic())
                .into(holder.imgProfile);
        Picasso.with(context).load(postList.get(position).getPhoto())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return this.postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{

        TextView tvUserName,tvMsg,tvDate;
        private ImageView img,imgProfile;
        public PostViewHolder(View v) {
            super(v);
            tvUserName = (TextView) v.findViewById(R.id.tvUserName);
            imgProfile = (ImageView) v.findViewById(R.id.profile_image);
            img = (ImageView) v.findViewById(R.id.img);
            tvMsg = (TextView) v.findViewById(R.id.tvMsg);
            tvDate = (TextView) v.findViewById(R.id.tvDate);
        }
    }
}

