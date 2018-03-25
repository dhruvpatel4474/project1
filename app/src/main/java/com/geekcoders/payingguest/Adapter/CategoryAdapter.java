package com.geekcoders.payingguest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dhruvpatel on 3/15/2018.
 */

public class CategoryAdapter extends BaseAdapter {
    private final ArrayList<Category> arrayList;
    private final Context mcontext;

    public CategoryAdapter(Context mcontext, ArrayList<Category> arrayList) {
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
        ImageView img = (ImageView)view1.findViewById(R.id.category_row_img);
        name.setText(arrayList.get(i).getName());
        Picasso.with(mcontext)
                .load(arrayList.get(i).getImg())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(img);
        return view1;
    }
}
