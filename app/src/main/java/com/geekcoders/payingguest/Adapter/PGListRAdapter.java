package com.geekcoders.payingguest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekcoders.payingguest.Activities.PGListActivity;
import com.geekcoders.payingguest.Activities.PgDetailActivity;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by raj15 on 20-Mar-18.
 */

public class PGListRAdapter extends RecyclerView.Adapter<PGListRAdapter.MyViewHolder> {


    private final Context context;
    private final ArrayList<PGObject> arrayList;

    public PGListRAdapter(Context context, ArrayList<PGObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.adapter_pglist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(arrayList.get(position).getName());
            Picasso.get()
                    .load(arrayList.get(position).getImage())
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.place_holder)
                    .into(holder.image);

       // holder.image.setImageBitmap(arrayList.get(position).getImage());
        holder.city.setText(arrayList.get(position).getCity());
        String price="₹"+String.valueOf(arrayList.get(position).getPrice());
        holder.price.setText(price);
        holder.date.setText(arrayList.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Constant.pgObject = arrayList.get(position);

                Constant.setValueAndKeyString("PGid", arrayList.get(position).getObjectId());
                Intent intent = new Intent(context, PgDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name, price, date, city;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_pglist);
            name = (TextView) itemView.findViewById(R.id.name_pglist);
            price = (TextView) itemView.findViewById(R.id.price_pglist);
            date = (TextView) itemView.findViewById(R.id.date_pglist);
            city = (TextView) itemView.findViewById(R.id.city_pglist);

        }
    }
}
