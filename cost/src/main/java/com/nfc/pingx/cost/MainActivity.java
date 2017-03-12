package com.nfc.pingx.cost;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.nfc.pingx.common.ExceptionHandler;
import com.nfc.pingx.common.SwipeDismissRecyclerViewTouchListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<CostModel> costModelList;
    private CostDAO costDAO;
    private CostListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        costModelList =new ArrayList<>();
        costDAO=new CostDAO(this);
        initCostData();

        //region RecyclerView setup
        final RecyclerView costListView= (RecyclerView) findViewById(R.id.lv_main);
        adapter = new CostListAdapter(costModelList);
        costListView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        costListView.setLayoutManager(layoutManager);
        // allows for optimizations if all item views are of the same size:
        costListView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        costListView.addItemDecoration(itemDecoration);
        // this is the default;this call is actually only necessary with custom ItemAnimators
        costListView.setItemAnimator(new DefaultItemAnimator());
        // onClickDetection is done in this Activity’s OnItemTouchListener
        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                costListView,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        CostModel costModel = ((CostListAdapter)costListView.getAdapter()).getList().get(costListView.getChildAdapterPosition(view));
                        costDAO.delete(costModel);
                        costModelList.remove(costModel);
                        costListView.getAdapter().notifyDataSetChanged();
                        Toast.makeText(getBaseContext(), String.format("Delete item %d", costModel.id),Toast.LENGTH_LONG).show();
                    }
                })
                .create();

        costListView.setOnTouchListener(listener);
        //endregion

        //region FloatingActionButton
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
                        CostModel costModel =new CostModel();
                        costModel.costTitle = title.getText().toString();
                        costModel.costMoney = money.getText().toString();
                        costModel.costDate = date.getYear() +"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth();
                        costDAO.create(costModel);
                        costModelList.add(costModel);
                        costListView.getAdapter().notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();

            }
        });
        //endregion

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }

    private void initCostData() {
        costModelList = costDAO.getAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_charts:
                Intent intent = new Intent(this, ChartActivity.class);
                startActivity(intent);
                break;
            case R.id.action_exit:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
