package com.geekcoders.payingguest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekcoders.payingguest.Objects.Payment;
import com.geekcoders.payingguest.R;

import java.util.ArrayList;

/**
 * Created by raj15 on 22-Mar-18.
 */

public class PaymentRAdapter extends RecyclerView.Adapter<PaymentRAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Payment> arrayList;

    public PaymentRAdapter(Context context, ArrayList<Payment> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_payment,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Payment object = arrayList.get(position);
        if (object.isStatus())
        {//for recived
            holder.name.setText(object.getRecieverName());
            holder.status.setText("from");
        }
        else
        {//for paid
            holder.name.setText(object.getSenderName());
            holder.status.setText("to");
        }

        holder.price.setText(object.getPrice());
        holder.date.setText(object.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name,price,status,date;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tv_name);
            status = (TextView)itemView.findViewById(R.id.tv_status);
            price = (TextView)itemView.findViewById(R.id.tv_price);
            date = (TextView)itemView.findViewById(R.id.tv_date);
        }
    }
}
