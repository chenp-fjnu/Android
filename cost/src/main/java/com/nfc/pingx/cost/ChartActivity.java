package com.nfc.pingx.cost;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


/**
 * Created by chenp_fjnu on 2017/3/5.
 */

public class ChartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_view);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
