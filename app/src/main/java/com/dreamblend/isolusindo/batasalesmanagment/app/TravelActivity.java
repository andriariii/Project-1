package com.dreamblend.isolusindo.batasalesmanagment.app;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class TravelActivity extends ActionBarActivity {

    JSONParser jsonParser = new JSONParser();

    private String URL = "http://wearedreamblend.com/bata/dsm_planning_insert.php";
    private ProgressDialog pDialog;
    private String id;
    private EditText storeName;
    private EditText datePicker;
    private EditText accomodation;
    private Button btnSave;
    private Spinner storeDrop;

    private ArrayList<Store> storeList;
    private String URL_CATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");
        datePicker = (EditText) findViewById(R.id.datePicker);
        accomodation = (EditText) findViewById(R.id.accomodation);
        btnSave = (Button) findViewById(R.id.btnSave);
        storeDrop = (Spinner) findViewById(R.id.storeSelector);

        datePicker.setInputType(InputType.TYPE_NULL);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(TravelActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        //datePicker.setText(year + "-" + (month + 0) + "-" + day);
                        String dateFormat = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                        datePicker.setText(sdf.format(c.getTime()));
                    }
                }, mYear, mMonth, mDay);

                dpd.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(storeDrop.getSelectedItem()) == ""
                        || datePicker.getText().toString().length() < 1
                        || accomodation.getText().toString().length() < 1) {
                    Toast.makeText(TravelActivity.this, "All field can't empty", Toast.LENGTH_LONG).show();
                } else {
                    new insert().execute();
                }
            }
        });

        URL_CATE = "http://wearedreamblend.com/bata/dsm_category.php?bataID="+id;
        storeList = new ArrayList<Store>();

        new listStoreName().execute();

    }

    class insert extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TravelActivity.this);
            pDialog.setMessage("Sending ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            //String store = storeName.getText().toString();
            String store = String.valueOf(storeDrop.getSelectedItem());
            String date = datePicker.getText().toString();
            String acco = accomodation.getText().toString();
            String did = id;

            //int success;
            List<NameValuePair> dataArray = new ArrayList<NameValuePair>();
            dataArray.add(new BasicNameValuePair("id", did));
            dataArray.add(new BasicNameValuePair("store", store));
            dataArray.add(new BasicNameValuePair("date", date));
            dataArray.add(new BasicNameValuePair("acco", acco));

            Log.d("Action", "Starting");
            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", dataArray);
            Log.d("Response Code", json.toString());
            int success;
            String TAG_S = "success";
            try {
                success = json.getInt(TAG_S);
                if(success == 1) {
                    Log.d("Action", json.toString());

                    Intent toDashboard = new Intent(TravelActivity.this, DashboardActivity.class);
                    toDashboard.putExtra("username-id", id);
                    startActivity(toDashboard);
                    finish();
                } else if (success == 0) {
                    Log.d("Action", "Failed");
                    pDialog.dismiss();
                    Toast.makeText(TravelActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            pDialog.dismiss();

        }
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
            pDialog = new ProgressDialog(TravelActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            JSONObject json = jsonParser.getJSONFromUrl(URL_CATE);
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


}
