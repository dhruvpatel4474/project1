package com.geekcoders.payingguest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.Objects.Payment;
import com.geekcoders.payingguest.R;

import java.util.ArrayList;

/**
 * Created by dhruvpatel on 3/15/2018.
 */

public class PaymentAdapter extends BaseAdapter {
    private final ArrayList<Payment> arrayList;
    private final Context mcontext;

    public PaymentAdapter(Context mcontext, ArrayList<Payment> arrayList) {
        this.arrayList=arrayList;
        this.mcontext=mcontext;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=LayoutInflater.from(mcontext).inflate(R.layout.category_row_view,viewGroup,false);
        TextView name=(TextView)view1.findViewById(R.id.category_row_name);
        name.setText(arrayList.get(i).getSenderName());

        return view1;
    }
}
