package com.nfc.pingx.babycare;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private List<CostBean> costBeanList;
    private DatabaseHelper databaseHelper;
    private CostListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        costBeanList =new ArrayList<>();
        databaseHelper =new DatabaseHelper(this);
        final RecyclerView costList= (RecyclerView) findViewById(R.id.lv_main);
        initCostData();
        adapter = new CostListAdapter(costBeanList);
        costList.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        costList.setLayoutManager(layoutManager);
        // allows for optimizations if all item views are of the same size:
        costList.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        costList.addItemDecoration(itemDecoration);
        // this is the default;this call is actually only necessary with custom ItemAnimators
        costList.setItemAnimator(new DefaultItemAnimator());
        // onClickDetection is done in this Activity’s OnItemTouchListener
        // with the help of a GestureDetector;
        // Tip by Ian Lake on G+ in a comment to this post:
        // https://plus.google.com/+LucasRocha/posts/37U8GWtYxDE
//        costList.addOnItemTouchListener(this);
//        gesturedetector = new GestureDetectorCompat(this, new RecyclerViewDemoOnGestureListener());
        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                costList,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        CostBean costBean = ((CostListAdapter)costList.getAdapter()).getList().get(costList.getChildAdapterPosition(view));
                        databaseHelper.deleteItem(costBean);
                        costBeanList.remove(costBean);
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getBaseContext(), String.format("Delete item %d",costBean.id),Toast.LENGTH_LONG).show();
                    }
                })
                .setIsVertical(false)
                .create();

        costList.setOnTouchListener(listener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflate = LayoutInflater.from(MainActivity.this);
                View viewDialog = inflate.inflate(R.layout.new_cost_data,null);
                final EditText title = (EditText) viewDialog.findViewById(R.id.et_cost_title);
                final EditText money = (EditText) viewDialog.findViewById(R.id.et_cost_money);
                final DatePicker date = (DatePicker) viewDialog.findViewById(R.id.dp_cost_date);
                builder.setView(viewDialog);
                builder.setTitle("New Cost");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CostBean costBean =new CostBean();
                        costBean.costTitle = title.getText().toString();
                        costBean.costMoney = money.getText().toString();
                        costBean.costDate = date.getYear() +"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth();
                        long insertedId= databaseHelper.insertCost(costBean);
                        costBean.id=insertedId;
                        costBeanList.add(costBean);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();

            }
        });
    }

    private void initCostData() {
        Cursor cursor = databaseHelper.getAllCostData();
        if(cursor!=null){
            while(cursor.moveToNext()){
                CostBean costBean=new CostBean();
                costBean.costTitle=cursor.getString(cursor.getColumnIndex("cost_title"));
                costBean.costDate=cursor.getString(cursor.getColumnIndex("cost_date"));
                costBean.costMoney=cursor.getString(cursor.getColumnIndex("cost_money"));
                costBeanList.add(costBean);
            }
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_charts) {
            Intent intent = new Intent(MainActivity.this, ChartActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
