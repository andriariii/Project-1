package com.dreamblend.isolusindo.batasalesmanagment.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dreamblend.isolusindo.batasalesmanagment.app.R;

public class Sls_step_6a extends ActionBarActivity {

    private String id, storePlace, edp;
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sls_step_6a);

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");
        storePlace = receive.getExtras().getString("place");
        edp = receive.getExtras().getString("edp");

        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toStep6b = new Intent(getApplicationContext(), Sls_step_6b.class);
                toStep6b.putExtra("username-id", id);
                toStep6b.putExtra("place", storePlace);
                toStep6b.putExtra("edp", edp);
                startActivity(toStep6b);
            }
        });

    }



}
