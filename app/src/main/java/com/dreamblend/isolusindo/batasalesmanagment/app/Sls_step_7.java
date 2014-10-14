package com.dreamblend.isolusindo.batasalesmanagment.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Sls_step_7 extends ActionBarActivity {

    private static String id;
    private static Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sls_step_7);

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dash = new Intent(getApplicationContext(), DashboardActivity.class);
                dash.putExtra("username-id",id);
                startActivity(dash);
            }
        });

    }



}
