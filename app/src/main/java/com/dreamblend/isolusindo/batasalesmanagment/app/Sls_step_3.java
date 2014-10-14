package com.dreamblend.isolusindo.batasalesmanagment.app;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Sls_step_3 extends ActionBarActivity {

    JSONParser jsonParser = new JSONParser();
    ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://wearedreamblend.com/bata/insert.cd.php";
    private static final String TAG_S = "success";
    private static final String TAG_M = "message";

    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;
    private RadioGroup rg4;
    private RadioGroup rg5;
    private RadioGroup rg6;

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioButton rb6;

    private int s1;
    private int s2;
    private int s3;
    private int s4;
    private int s5;
    private int s6;

    String r1 = "";
    String r2 = "";
    String r3 = "";
    String r4 = "";
    String r5 = "";
    String r6 = "";

    private String id;
    private Button btnNext;

    String store,edp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sls_step_3);

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");
        store = receive.getExtras().getString("place");
        edp = receive.getExtras().getString("edp");

        btnNext = (Button) findViewById(R.id.btnNext);
        rg1 = (RadioGroup) findViewById(R.id.A4_1_Option);
        rg2 = (RadioGroup) findViewById(R.id.A4_2_Option);
        rg3 = (RadioGroup) findViewById(R.id.A4_3_Option);
        rg4 = (RadioGroup) findViewById(R.id.A4_4_Option);
        rg5 = (RadioGroup) findViewById(R.id.A5_1_Option);
        rg6 = (RadioGroup) findViewById(R.id.A5_1_Option);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                s1 = rg1.getCheckedRadioButtonId();
                rb1 = (RadioButton) findViewById(s1);
                s2 = rg2.getCheckedRadioButtonId();
                rb2 = (RadioButton) findViewById(s2);
                s3 = rg3.getCheckedRadioButtonId();
                rb3 = (RadioButton) findViewById(s3);
                s4 = rg4.getCheckedRadioButtonId();
                rb4 = (RadioButton) findViewById(s4);
                s5 = rg5.getCheckedRadioButtonId();
                rb5 = (RadioButton) findViewById(s5);
                s6 = rg6.getCheckedRadioButtonId();
                rb6 = (RadioButton) findViewById(s6);
                */
                /*
                if(s1 == -1 || s2 == -1 || s3 == -1 || s4 == -1 || s5 == -1 || s6 == -1) {
                    Toast.makeText(getApplicationContext(), "Please check all radio button", Toast.LENGTH_LONG).show();
                } else {

                    r1 = rb1.getText().toString();
                    r2 = rb2.getText().toString();
                    r3 = rb3.getText().toString();
                    r4 = rb4.getText().toString();
                    r5 = rb5.getText().toString();
                    r6 = rb6.getText().toString();

                    //Toast.makeText(getApplicationContext(), "Check", Toast.LENGTH_LONG).show();
                    new login().execute();
                }
                */
                Intent toDashboard = new Intent(getApplicationContext(), Sls_step_4.class);
                toDashboard.putExtra("username-id", id);
                toDashboard.putExtra("place", store);
                toDashboard.putExtra("edp", edp);
                startActivity(toDashboard);

            }
        });
    }

    class login extends AsyncTask<String, String, String> {

        @Override
        protected  void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sls_step_3.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {

            String did = id;
            String a = r1;
            String b = r2;
            String c = r3;
            String d = r4;
            String e1 = r5;
            String f = r6;

            int success;
            List<NameValuePair> dataArray = new ArrayList<NameValuePair>();
            dataArray.add(new BasicNameValuePair("id", did));
            dataArray.add(new BasicNameValuePair("1", a));
            dataArray.add(new BasicNameValuePair("2", b));
            dataArray.add(new BasicNameValuePair("3", c));
            dataArray.add(new BasicNameValuePair("4", d));
            dataArray.add(new BasicNameValuePair("5", e1));
            dataArray.add(new BasicNameValuePair("6", f));

            Log.d("Action", "Starting");
            JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", dataArray);
            Log.d("Response Code", json.toString());

            try {
                success = json.getInt(TAG_S);
                if(success == 1) {
                    Log.d("Action", json.toString());

                    Intent toDashboard = new Intent(getApplicationContext(), Sls_step_4.class);
                    toDashboard.putExtra("username-id", id);
                    toDashboard.putExtra("place", store);
                    toDashboard.putExtra("edp", edp);
                    startActivity(toDashboard);

                    finish();
                } else if (success == 0) {
                    Log.d("Action", "Failed");
                    pDialog.dismiss();
                    Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }
    }
}
