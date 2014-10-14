package com.dreamblend.isolusindo.batasalesmanagment.app;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import java.sql.SQLInput;


public class Sls_Step_1 extends ActionBarActivity {

    JSONParser jsonParser = new JSONParser();
    ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://wearedreamblend.com/bata/insert.a.php";
    private static final String TAG_S = "success";
    private static final String TAG_M = "message";

    private static final String DBNAME = "dsm_report_db";
    private static final String TBLNAME = "dsm_report_a1";
    private String id;
    private Button btnNext;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;
    private RadioGroup rg4;
    private RadioGroup rg5;
    private RadioGroup rg6;
    private RadioGroup rg7;
    private RadioGroup rg8;
    private RadioGroup rg9;
    private RadioGroup rg10;
    private RadioGroup rg11;
    private RadioGroup rg12;
    private RadioGroup rg13;

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioButton rb6;
    private RadioButton rb7;
    private RadioButton rb8;
    private RadioButton rb9;
    private RadioButton rb10;
    private RadioButton rb11;
    private RadioButton rb12;
    private RadioButton rb13;

    private int s1;
    private int s2;
    private int s3;
    private int s4;
    private int s5;
    private int s6;
    private int s7;
    private int s8;
    private int s9;
    private int s10;
    private int s11;
    private int s12;
    private int s13;

    String r1 = "";
    String r2 = "";
    String r3 = "";
    String r4 = "";
    String r5 = "";
    String r6 = "";
    String r7 = "";
    String r8 = "";
    String r9 = "";
    String r10 = "";
    String r11 = "";
    String r12 = "";
    String r13 = "";

    String edp;
    String store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sls_1);

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");
        store = receive.getExtras().getString("place");
        edp = receive.getExtras().getString("edp");

        Log.d("TAG_INTENT", "Intent received "+store+" and "+edp);
        btnNext = (Button) findViewById(R.id.btnNext);
        rg1 = (RadioGroup) findViewById(R.id.A1_1_Option);
        rg2 = (RadioGroup) findViewById(R.id.A1_2_Option);
        rg3 = (RadioGroup) findViewById(R.id.A2_1_Option);
        rg4 = (RadioGroup) findViewById(R.id.A2_2_Option);
        rg5 = (RadioGroup) findViewById(R.id.A2_3_Option);
        rg6 = (RadioGroup) findViewById(R.id.A2_4_Option);
        rg7 = (RadioGroup) findViewById(R.id.A2_5_Option);
        rg8 = (RadioGroup) findViewById(R.id.A2_6_Option);
        rg9 = (RadioGroup) findViewById(R.id.A2_7_Option);
        rg10 = (RadioGroup) findViewById(R.id.A2_8_Option);
        rg11 = (RadioGroup) findViewById(R.id.A2_9_Option);
        rg12 = (RadioGroup) findViewById(R.id.A2_10_Option);
        rg13 = (RadioGroup) findViewById(R.id.A2_11_Option);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Value
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
                s7 = rg7.getCheckedRadioButtonId();
                rb7 = (RadioButton) findViewById(s7);
                s8 = rg8.getCheckedRadioButtonId();
                rb8 = (RadioButton) findViewById(s8);
                s9 = rg9.getCheckedRadioButtonId();
                rb9 = (RadioButton) findViewById(s9);
                s10 = rg10.getCheckedRadioButtonId();
                rb10 = (RadioButton) findViewById(s10);
                s11 = rg11.getCheckedRadioButtonId();
                rb11 = (RadioButton) findViewById(s11);
                s12 = rg12.getCheckedRadioButtonId();
                rb12 = (RadioButton) findViewById(s12);
                s13 = rg13.getCheckedRadioButtonId();
                rb13 = (RadioButton) findViewById(s13);
                */

                Intent toDashboard = new Intent(getApplicationContext(), Sls_Step_2.class);
                toDashboard.putExtra("username-id", id);
                toDashboard.putExtra("place", store);
                toDashboard.putExtra("edp", edp);
                startActivity(toDashboard);

                /*
                if(s1 == -1 || s2 == -1 || s3 == -1 || s4 == -1 || s5 == -1 || s6 == -1
                        || s7 == -1 || s8 == -1 || s9 == -1 || s10 == -1 || s11 == -1 || s12 == -1
                        || s13 == -1 ) {
                    Toast.makeText(getApplicationContext(), "Please check all radio button", Toast.LENGTH_LONG).show();
                } else {

                    r1 = rb1.getText().toString();
                    r2 = rb2.getText().toString();
                    r3 = rb3.getText().toString();
                    r4 = rb4.getText().toString();
                    r5 = rb5.getText().toString();
                    r6 = rb6.getText().toString();
                    r7 = rb7.getText().toString();
                    r8 = rb8.getText().toString();
                    r9 = rb9.getText().toString();
                    r10 = rb10.getText().toString();
                    r11 = rb11.getText().toString();
                    r12 = rb12.getText().toString();
                    r13 = rb13.getText().toString();

                    //Toast.makeText(getApplicationContext(), "Check", Toast.LENGTH_LONG).show();
                    new login().execute();
                }
                */




            }
        });
    }
    /*
    public boolean insert(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m) {
        SQLiteDatabase maindb = this.openOrCreateDatabase(DBNAME, MODE_PRIVATE, null);
        maindb.execSQL("CREATE TABLE IF NOT EXISTS " + TBLNAME
                + "(Id int primary key autoincrement, rptid VARCHA, a1 varchar, a2 varchar, a3 varchar, a4 varchar," +
                "a5 varchar, a6 varchar, a7 varchar, a8 varchar, a9 varchar, a10 varchar, a11 varchar, a12 varchar, a13 varchar);");
        maindb.execSQL("INSERT INTO "+ TBLNAME +" VALUES (null,'"+ a +"','"+ b +"','"+ c +"','"+ d +"','"+ e +"','"+ f +"','"+ g +"','"+ h +"','"+ i +"','"+ j +"','"+ k +"','"+ l +"','"+ m +"');");
        maindb.close();
        return true;
    }*/

    class login extends AsyncTask<String, String, String> {

        @Override
        protected  void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sls_Step_1.this);
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
            String g = r7;
            String h = r8;
            String i = r9;
            String j = r10;
            String k = r11;
            String l = r12;
            String m = r13;


            int success;
            List<NameValuePair> dataArray = new ArrayList<NameValuePair>();
            dataArray.add(new BasicNameValuePair("id", did));
            dataArray.add(new BasicNameValuePair("1", a));
            dataArray.add(new BasicNameValuePair("2", b));
            dataArray.add(new BasicNameValuePair("3", c));
            dataArray.add(new BasicNameValuePair("4", d));
            dataArray.add(new BasicNameValuePair("5", e1));
            dataArray.add(new BasicNameValuePair("6", f));
            dataArray.add(new BasicNameValuePair("7", g));
            dataArray.add(new BasicNameValuePair("8", h));
            dataArray.add(new BasicNameValuePair("9", i));
            dataArray.add(new BasicNameValuePair("10", j));
            dataArray.add(new BasicNameValuePair("11", k));
            dataArray.add(new BasicNameValuePair("12", l));
            dataArray.add(new BasicNameValuePair("13", m));

            Log.d("Action", "Starting");
            JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", dataArray);
            Log.d("Response Code", json.toString());

            try {
                success = json.getInt(TAG_S);
                if(success == 1) {
                    Log.d("Action", json.toString());

                    Intent toDashboard = new Intent(getApplicationContext(), Sls_Step_2.class);
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
