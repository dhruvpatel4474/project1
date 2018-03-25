package com.geekcoders.payingguest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.daimajia.swipe.util.Attributes;
import com.geekcoders.payingguest.Activities.PgDetailActivity;
import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by raj15 on 25-Mar-18.
 */

public class MyPGListRAdapter extends RecyclerSwipeAdapter<MyPGListRAdapter.MyViewHolder> {


    private final Context context;
    private final ArrayList<PGObject> arrayList;
    SwipeLayout mItemManger;
    SwipeItemRecyclerMangerImpl recyclerManger;

    public MyPGListRAdapter(Context context, ArrayList<PGObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        mItemManger = new SwipeLayout(context);
        recyclerManger = new SwipeItemRecyclerMangerImpl(this);
        setMode(Attributes.Mode.Single);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.adapter_mypglist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name.setText(arrayList.get(position).getName());
        Picasso.with(context)
                .load(arrayList.get(position).getImage())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(holder.image);

        // holder.image.setImageBitmap(arrayList.get(position).getImage());
        holder.city.setText(arrayList.get(position).getCity());
        String price = "₹" + String.valueOf(arrayList.get(position).getPrice());
        holder.price.setText(price);
        holder.date.setText(arrayList.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Constant.pgObject = arrayList.get(position);

                Constant.setValueAndKeyString("PGid", arrayList.get(position).getObjectId());
                //Intent intent = new Intent(context, PgDetailActivity.class);
                //context.startActivity(intent);
            }
        });

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, null);
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.itemView.findViewById(R.id.bottom_wrapper));
        //holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.itemView.findViewById(R.id.second//_wrapper));
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(final SwipeLayout layout) {
                //when the BottomView totally show.
                holder.lineLayDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //objectProductArrayList.remove(position);

                        Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show();

                        layout.close();
                       /* Snackbar snackbar = Snackbar
                                .make(context, Html.fromHtml("<font color=\"#ffffff\">Item is deleted</font>"), Snackbar.LENGTH_LONG)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Snackbar snackbar1 = Snackbar.make(context.getActivity().findViewById(android.R.id.content), Html.fromHtml("<font color=\"#ffffff\">Item is restored!</font>"), Snackbar.LENGTH_SHORT);
                                        objectProductArrayList.add(position, object);
                                        snackbar1.setActionTextColor(context.getResources().getColor(R.color.colorPrimary));
                                        notifyDataSetChanged();
                                        snackbar1.show();
                                    }
                                }).setActionTextColor(context.getResources().getColor(R.color.colorPrimary));

                        context.deleteItemFromCart(object.getItemId(),object.getSku());*/


                        //notifyDataSetChanged();

                    }
                });

            }

            @Override
            public void onStartClose(SwipeLayout layout) {
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
                if (holder.swipeLayout != null) {
                    holder.swipeLayout.close();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name, price, date, city;
        private final SwipeLayout swipeLayout;
        private final LinearLayout lineLayDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_pglist);
            name = (TextView) itemView.findViewById(R.id.name_pglist);
            price = (TextView) itemView.findViewById(R.id.price_pglist);
            date = (TextView) itemView.findViewById(R.id.date_pglist);
            city = (TextView) itemView.findViewById(R.id.city_pglist);
            swipeLayout = (SwipeLayout)itemView.findViewById(R.id.swipeLayout);
            lineLayDelete = (LinearLayout)itemView.findViewById(R.id.lineLay_delete);


        }
    }
}

