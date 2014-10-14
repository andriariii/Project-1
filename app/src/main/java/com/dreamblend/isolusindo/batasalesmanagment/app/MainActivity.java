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
import android.widget.EditText;
import android.widget.Toast ;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    JSONParser jsonParser = new JSONParser();

    EditText username, password;
    Button loginButton;
    ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://wearedreamblend.com/bata/login.php";
    private static final String TAG_S = "success";
    private static final String TAG_M = "message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        loginButton = (Button) findViewById(R.id.loginButton);
        username.setText("user1@bata.co.id");
        password.setText("1234");
        loginButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if(username.getText().toString().length() < 1 || password.getText().toString().length() < 1) {
            Toast.makeText(getApplicationContext(), "Harap mengisi semua form", Toast.LENGTH_LONG).show();
        } else {
            new login().execute();
        }
    }

    class login extends AsyncTask<String, String, String> {

        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            int success;
            String name = username.getText().toString();
            String pass = password.getText().toString();

            List<NameValuePair> dataArray = new ArrayList<NameValuePair>();
            dataArray.add(new BasicNameValuePair("name", name));
            dataArray.add(new BasicNameValuePair("pass", pass));

            Log.d("Login Attempt", "Starting");
            JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", dataArray);
            Log.d("Response Code", json.toString());

            try {
                success = json.getInt(TAG_S);
                if(success == 1) {
                    Log.d("Login Success", json.toString());

                    String id = username.getText().toString();
                    Intent toDashboard = new Intent(getApplicationContext(), DashboardActivity.class);
                    toDashboard.putExtra("username-id", id);
                    startActivity(toDashboard);

                    finish();
                } else if (success == 2) {
                    Log.d("Result", "Failed Login");
                    pDialog.dismiss();
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
