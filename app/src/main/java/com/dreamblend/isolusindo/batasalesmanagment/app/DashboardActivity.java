package com.dreamblend.isolusindo.batasalesmanagment.app;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends ActionBarActivity {

    Button btnSales;
    Button btnReport;
    Button btnTravel;
    TextView txtUserGreet;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");

        btnSales = (Button) findViewById(R.id.btnSales);
        btnReport = (Button) findViewById(R.id.btnReport);
        btnTravel = (Button) findViewById(R.id.btnTravel);

        /* Action Bar Custom
        ActionBar mActionBar = getActionBar();
        mActionBar.setCustomView(R.layout.actionbar_dashboard_custom);
        TextView userGreet = (TextView) mActionBar.getCustomView().findViewById(R.id.userEmailShow);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        userGreet.setText("Hello, " + id);
        */

        txtUserGreet = (TextView) findViewById(R.id.txtUserGreet);
        txtUserGreet.setText("Hello, " + id);

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toReport = new Intent(getApplicationContext(), ReportActivity.class);
                toReport.putExtra("username-id", id);
                startActivity(toReport);
            }
        });

        btnSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSales = new Intent(getApplicationContext(), Sls_step_0.class);
                toSales.putExtra("username-id", id);
                startActivity(toSales);
            }
        });

        btnTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toTravel = new Intent(getApplicationContext(), TravelActivity.class);
                toTravel.putExtra("username-id", id);
                startActivity(toTravel);
            }
        });
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
