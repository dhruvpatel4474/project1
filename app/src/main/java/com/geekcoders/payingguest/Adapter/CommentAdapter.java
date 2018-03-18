package com.geekcoders.payingguest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geekcoders.payingguest.Objects.Category;
import com.geekcoders.payingguest.Objects.Comment;
import com.geekcoders.payingguest.R;

import java.util.ArrayList;

/**
 * Created by dhruvpatel on 3/18/2018.
 */

public class CommentAdapter extends BaseAdapter{
    private final ArrayList<Comment> arrayList;
    private final Context mcontext;
    public CommentAdapter(Context mcontext, ArrayList<Comment> arrayList) {
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
        View view1= LayoutInflater.from(mcontext).inflate(R.layout.category_row_view,viewGroup,false);
        TextView name=(TextView)view1.findViewById(R.id.category_row_name);
        name.setText(arrayList.get(i).getCommentMessage());

        return view1;
    }

}
