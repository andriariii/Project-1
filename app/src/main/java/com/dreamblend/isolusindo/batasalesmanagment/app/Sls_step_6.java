package com.dreamblend.isolusindo.batasalesmanagment.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class Sls_step_6 extends ActionBarActivity {

    JSONParser jsonParser = new JSONParser();
    ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://wearedreamblend.com/bata/insert.ef.php";
    private static final String TAG_S = "success";
    private static final String TAG_M = "message";

    private String id;
    private Button btnNext;

    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioButton rb1;
    private RadioButton rb2;
    private int s1;
    private int s2;
    private String r1 = "";
    private String r2 = "";

    private Spinner storeDrop;
    private EditText district,dVisit,wVisit;
    private EditText tArrive,reportby;
    private EditText edpCode,spcCode,tDepart,manager,dLastVisit;
    private EditText tw1,td1,sest1,test1,saly1,taly1,stock1;
    private EditText tw2,td2,sest2,test2,saly2,taly2,stock2;
    private EditText avgSprice,avgStprice,othernfw;

    private ArrayList<Store> storeList;
    private String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sls_step_6);

        rg1 = (RadioGroup) findViewById(R.id.t1_Option);
        rg2 = (RadioGroup) findViewById(R.id.t11_Option);
        district = (EditText) findViewById(R.id.ed_1);
        dVisit = (EditText) findViewById(R.id.ed_2);
        wVisit = (EditText) findViewById(R.id.ed_3);
        storeDrop = (Spinner) findViewById(R.id.ed_4);

        tArrive = (EditText) findViewById(R.id.ed_5);
        reportby = (EditText) findViewById(R.id.ed_6);

        edpCode = (EditText) findViewById(R.id.ed_7);
        spcCode = (EditText) findViewById(R.id.ed_8);
        tDepart = (EditText) findViewById(R.id.ed_9);
        manager = (EditText) findViewById(R.id.ed_10);

        dLastVisit = (EditText) findViewById(R.id.ed_11);

        /*
        tw1 = (EditText) findViewById(R.id.eds_1);
        td1 = (EditText) findViewById(R.id.eds_2);
        sest1 = (EditText) findViewById(R.id.eds_3);
        test1 = (EditText) findViewById(R.id.eds_4);
        saly1 = (EditText) findViewById(R.id.eds_5);
        taly1 = (EditText) findViewById(R.id.eds_6);
        stock1 = (EditText) findViewById(R.id.eds_7);

        tw2 = (EditText) findViewById(R.id.eds_8);
        td2 = (EditText) findViewById(R.id.eds_9);
        sest2 = (EditText) findViewById(R.id.eds_10);
        test2 = (EditText) findViewById(R.id.eds_11);
        saly2 = (EditText) findViewById(R.id.eds_12);
        taly2 = (EditText) findViewById(R.id.eds_13);
        stock2 = (EditText) findViewById(R.id.eds_14);

        avgSprice = (EditText) findViewById(R.id.eds_20);
        avgStprice = (EditText) findViewById(R.id.eds_21);
        othernfw = (EditText) findViewById(R.id.eds_22);
        */

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");
        final String storePlace = receive.getExtras().getString("place");
        final String edp = receive.getExtras().getString("edp");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        String dnow = df1.format(c.getTime());



        district.setText("Distrik 1");
        district.setInputType(InputType.TYPE_NULL);
        edpCode.setText(edp);
        edpCode.setInputType(InputType.TYPE_NULL);
        dVisit.setText(dnow);
        dVisit.setInputType(InputType.TYPE_NULL);
        reportby.setText(id);
        reportby.setInputType(InputType.TYPE_NULL);
        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toStep6a = new Intent(getApplicationContext(), Sls_step_6a.class);
                toStep6a.putExtra("username-id", id);
                toStep6a.putExtra("place", storePlace);
                toStep6a.putExtra("edp", edp);
                startActivity(toStep6a);

                /*
                s1 = rg1.getCheckedRadioButtonId();
                rb1 = (RadioButton) findViewById(s1);
                s2 = rg2.getCheckedRadioButtonId();
                rb2 = (RadioButton) findViewById(s2);

                if(s1 == -1 || s2 == -1 ) {
                    Toast.makeText(getApplicationContext(), "Please input all field", Toast.LENGTH_LONG).show();
                }
                else {

                    Intent toStep7 = new Intent(getApplicationContext(), Sls_step_7.class);
                    toStep7.putExtra("username-id", id);
                    startActivity(toStep7);

                }
                */
            }
        });

        URL = "http://wearedreamblend.com/bata/dsm_category.php?bataID="+id;
        storeList = new ArrayList<Store>();

        new listStoreName().execute();
    }

    private void populateSpinner() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < storeList.size(); i++) {
            lables.add(storeList.get(i).getName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        storeDrop.setAdapter(spinnerAdapter);
    }

    private class listStoreName extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sls_step_6.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            JSONObject json = jsonParser.getJSONFromUrl(URL);
            try {
                if(json != null) {
                    JSONArray store = json.getJSONArray("store");

                    for (int i = 0; i < store.length(); i++) {
                        JSONObject catchObj = (JSONObject) store.get(i);
                        Store sto = new Store(catchObj.getInt("id"),
                                catchObj.getString("name"));
                        storeList.add(sto);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(pDialog.isShowing()) {
                pDialog.dismiss();
            }
            populateSpinner();
        }
    }

    public void addItemSpinnerStoreName() {
        storeDrop = (Spinner) findViewById(R.id.ed_4);

        List<String> list = new ArrayList<String>();
            list.add("ITC Serpong");
            list.add("Bintaro Plaza");
            list.add("ITC Ciledug");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            storeDrop.setAdapter(dataAdapter);
    }



}
