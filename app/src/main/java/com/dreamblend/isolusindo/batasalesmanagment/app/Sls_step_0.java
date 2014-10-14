package com.dreamblend.isolusindo.batasalesmanagment.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamblend.isolusindo.batasalesmanagment.app.R;
import com.dreamblend.isolusindo.batasalesmanagment.app.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Sls_step_0 extends ActionBarActivity {

    private String did;
    private ListView list;
    private TextView place;
    private TextView date;

    private Button gData;
    private ProgressDialog pDialog;

    ArrayList<HashMap<String, String>> planningList = new ArrayList<HashMap<String, String>>();
    private static String URL = "";

    private static final String TAG_PLAN = "bataPlanning";
    private static final String TAG_PLACE = "store";
    private static final String TAG_EDP = "edpcode";
    private static final String TAG_DATE = "when";

    JSONArray planning = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sls_step_0);

        Intent receive = getIntent();
        did = receive.getExtras().getString("username-id");
        URL = "http://wearedreamblend.com/bata/dsm_planning.php?bataID="+did;

        planningList = new ArrayList<HashMap<String, String>>();

        new JSONParse().execute();
    }

    class JSONParse extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            place = (TextView) findViewById(R.id.placeText);
            date = (TextView) findViewById(R.id.dateText);
            pDialog = new ProgressDialog(Sls_step_0.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(URL);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                //GET
                planning = json.getJSONArray(TAG_PLAN);

                //Logical
                for (int i=0; i<planning.length(); i++) {
                    JSONObject b = planning.getJSONObject(i);

                    //Store
                    String place = b.getString(TAG_PLACE);
                    String date = b.getString(TAG_DATE);
                    String edp = b.getString(TAG_EDP);

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(TAG_PLACE, place);
                    map.put(TAG_DATE, date);
                    map.put(TAG_EDP, edp);
                    planningList.add(map);

                    list = (ListView) findViewById(R.id.listSchedule);
                    ListAdapter adapter = new SimpleAdapter(Sls_step_0.this, planningList, R.layout.list_view,
                            new String[]{TAG_PLACE, TAG_DATE, TAG_EDP}, new int[]{
                            R.id.placeText, R.id.dateText, R.id.edpText});
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(Sls_step_0.this, "You Choose at"+planningList.get(+position).get("store"), Toast.LENGTH_LONG).show();
                            Intent toStep1 = new Intent(Sls_step_0.this, SalesActivity.class);
                            toStep1.putExtra("username-id", did);
                            toStep1.putExtra("place", planningList.get(+position).get("store"));
                            toStep1.putExtra("edp", planningList.get(+position).get("edpcode"));
                            startActivity(toStep1);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
