package com.geekcoders.payingguest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekcoders.payingguest.Activities.CategoryActivity;
import com.geekcoders.payingguest.Activities.PGListActivity;
import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by raj15 on 20-Mar-18.
 */

public class CategoryRAdapter extends RecyclerView.Adapter<CategoryRAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Category> arrayList;
    private final boolean showOption;

    public CategoryRAdapter(Context context, ArrayList<Category> arrayList,boolean showOption) {
        this.context = context;
        this.arrayList = arrayList;
        this.showOption = showOption;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.category_row_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(arrayList.get(position).getName());
        Picasso.with(context)
                .load(arrayList.get(position).getImg())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(holder.image);

        if (showOption)
        {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;

                }
            });
        }else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constant.setValueAndKeyString("categoryId", arrayList.get(position).getObjectId());
                    Intent intent = new Intent(context, PGListActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.category_row_img);
            name = (TextView)itemView.findViewById(R.id.category_row_name);
        }
    }
}
