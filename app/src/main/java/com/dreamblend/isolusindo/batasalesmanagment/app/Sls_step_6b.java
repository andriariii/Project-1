package com.dreamblend.isolusindo.batasalesmanagment.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dreamblend.isolusindo.batasalesmanagment.app.R;

public class Sls_step_6b extends ActionBarActivity {

    private String id, storePlace, edp;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sls_step_6b);

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");
        storePlace = receive.getExtras().getString("place");
        edp = receive.getExtras().getString("edp");

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toStep7 = new Intent(getApplicationContext(), Sls_step_7.class);
                toStep7.putExtra("username-id", id);
                toStep7.putExtra("place", storePlace);
                toStep7.putExtra("edp", edp);
                startActivity(toStep7);
            }
        });

    }


}
