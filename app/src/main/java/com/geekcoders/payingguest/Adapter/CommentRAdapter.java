package com.geekcoders.payingguest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekcoders.payingguest.Activities.PgDetailActivity;
import com.geekcoders.payingguest.Objects.Comment;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by raj15 on 21-Mar-18.
 */

public class CommentRAdapter extends RecyclerView.Adapter<CommentRAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Comment> arrayList;

    public CommentRAdapter(Context context, ArrayList<Comment> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.adapter_comment,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comment object = arrayList.get(position);
        holder.name.setText(object.getUserName());
        holder.comment.setText(object.getCommentMessage());
        holder.date.setText(object.getDate().toString());


       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Constant.pgObject = arrayList.get(position);

                Constant.setValueAndKeyString("PGid", arrayList.get(position).getObjectId());
                Intent intent = new Intent(context, PgDetailActivity.class);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name,comment,date;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name_comment);
            comment = (TextView)itemView.findViewById(R.id.comment_comment);
            date = (TextView)itemView.findViewById(R.id.date_comment);

        }
    }
}
