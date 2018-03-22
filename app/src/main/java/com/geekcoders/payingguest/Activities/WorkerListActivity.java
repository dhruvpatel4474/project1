package com.geekcoders.payingguest.Activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.geekcoders.payingguest.Objects.Worker;
import com.geekcoders.payingguest.R;
import com.geekcoders.payingguest.Utils.Constant;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class WorkerListActivity extends AppCompatActivity {

    private ListView ItemList;
    private int cat_id;

    private ArrayList<Worker> arrayList;

    private SwipeRefreshLayout swip_refresh;
    private ArrayList newtimelist;
    ArrayAdapter<Worker> list_adpt;
    private Handler handler;
    int a=0;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);
        ItemList=(ListView)findViewById(R.id.booklist_booklist_lstv);
        swip_refresh=(SwipeRefreshLayout)findViewById(R.id.auc_item_list_refresh_worker);
        cat_id=getIntent().getIntExtra("cat_id",0);
        String cat_name=getIntent().getStringExtra("cat_name");


        setTitle(cat_name);

        ItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Constant.workerObj=arrayList.get(position);
                Intent Auc_item_intent=new Intent(WorkerListActivity.this,WorkerDetail.class);
                startActivity(Auc_item_intent);

            }
        });


//        swip_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//
//                swip_refresh.setRefreshing(false);
//            }
//        });


    }


    public void CategoryList() {
        arrayList = new ArrayList<>();
        ParseQuery<ParseObject> bandQuery = ParseQuery.getQuery("Worker");

        final ProgressDialog dialog = new ProgressDialog(WorkerListActivity.this);
        dialog.setMessage("Please wait");
        dialog.show();

//
//        if (band_type == 0) {
//            bandQuery.whereEqualTo("creator", ParseUser.getCurrentUser());
//        } else {
//            bandQuery.whereContainedIn("bandMembers", Collections.singletonList(ParseUser.getCurrentUser()));
//        }

        bandQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects != null && objects.size() > 0) {


                    for (int i = 0; i < objects.size(); i++) {

                        final Worker obj = new Worker();
                        String name = objects.get(i).getString("name");
                        String description = objects.get(i).getString("description");
                        String worktype = objects.get(i).getString("worktype");
                        int price = objects.get(i).getInt("price");
                        String city = objects.get(i).getString("city");
                        String number = objects.get(i).getString("number");
                        String image = objects.get(i).getString("image");
                        String objectId = objects.get(i).getObjectId();

                        obj.setTitle(name);
                        obj.setDescription(description);
                        obj.setWorkType(worktype);
                        obj.setPrice(price);
                        obj.setCity(city);
                        obj.setNumber(number);
                        obj.setImage(image);



                        // FetchListOfVenuesForSelectedBand(objects.get(i));
                        //ImagesBand(objects.get(i));


//                        ParseQuery<ParseObject> imgQuery = ParseQuery.getQuery("RCBandImage");
//                        imgQuery.whereEqualTo("band", objects.get(i));
//                        imgQuery.findInBackground(new FindCallback<ParseObject>() {
//                            @Override
//                            public void done(List<ParseObject> objects, ParseException e) {
//                                if (e == null && objects.size() != 0) {
//                                    final ParseObject object = objects.get(0);
//                                    final ParseFile fileObject = (ParseFile) object.get("imageFile");
//                                    fileObject.getDataInBackground(new GetDataCallback() {
//                                        @Override
//                                        public void done(byte[] data, ParseException e) {
//                                            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
//                                            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
//                                            objrcband.setImage(bitmap);
//                                            adapter.notifyDataSetChanged();
//                                        }
//                                    });
//                                }
//                            }
//                        });
                        arrayList.add(obj);

                    }

                    //adapter = new CategoryAdapter(CategoryActivity.this, arrayList);
                    setAdapter(arrayList);
                    dialog.cancel();

                } else {
                    dialog.cancel();


                }


            }
        });

    }



    public void setAdapter(final ArrayList<Worker> arrayList)
    {
        this.arrayList=arrayList;

//        BookListAdapter bookListAdapter=new BookListAdapter(BookList.this,arrayList);
//        bookList.setAdapter(bookListAdapter);




        list_adpt=new ArrayAdapter<Worker>(
                WorkerListActivity.this,R.layout.itemlist_custom,arrayList) {

             @NonNull
             @Override
             public View getView(int position, View view, ViewGroup parent) {

                 view = getLayoutInflater().inflate(R.layout.itemlist_custom,parent,false);


                 ImageView img = (ImageView) view.findViewById(R.id.auc_itemlist_custom_img);
                 TextView title = (TextView) view.findViewById(R.id.auc_itemlist_custom_title);
                 TextView price = (TextView) view.findViewById(R.id.auc_itemlist_custom_price);
                 TextView woek = (TextView) view.findViewById(R.id.auc_itemlist_custom_bids);
                 TextView city = (TextView) view.findViewById(R.id.auc_itemlist_custom_time);
                    Worker obj=arrayList.get(position);




                 return view;
             }
         };

         ItemList.setAdapter(list_adpt);

    }


}
