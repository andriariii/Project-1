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

import java.text.SimpleDateFormat;
import java.util.Date;

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

        String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        txtUserGreet = (TextView) findViewById(R.id.txtUserGreet);
        txtUserGreet.setText("Hello, " + id + " - " + dateNow);

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



}
