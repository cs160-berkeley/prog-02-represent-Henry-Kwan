package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    EditText ZCodeInfo;
    TextView testview;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ZCodeInfo = (EditText) findViewById(R.id.ZipCode);
        //final TextView testview = (TextView) findViewById(R.id.testView2);
        final Button ZipButton = (Button) findViewById(R.id.Zip_Go);
        Button LocBut = (Button) findViewById(R.id.My_Loc);
        //Button TestButton = (Button) findViewById(R.id.testButton);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        ZipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), People_Overview.class);
                //Intent sendIntent = new Intent(getApplicationContext(), PhoneToWatchService.class);
                //StringBuilder SB = new StringBuilder();
                JSONObject json = null;
                try {
                    json = new GetCongress().execute(ZCodeInfo.getText().toString()).get();
                    JSONArray json_parse = (json.getJSONArray("results"));


                    for (int i = 0; i < 3; i++) {
                        intent.putExtra("Name" + i, json_parse.getJSONObject(i).getString("first_name") + " " + json_parse.getJSONObject(i).getString("last_name"));
                        intent.putExtra("Party" + i, json_parse.getJSONObject(i).getString("party"));
                        intent.putExtra("Email" + i, json_parse.getJSONObject(i).getString("oc_email"));
                        intent.putExtra("Website" + i, json_parse.getJSONObject(i).getString("website"));
                        intent.putExtra("End" + i, json_parse.getJSONObject(i).getString("term_end"));

                        String bioID = json_parse.getJSONObject(i).getString("bioguide_id");

                        intent.putExtra("bioID" + i, bioID);

                       System.out.println("BIOIDDDDDDDDD+++++++++++++++++++ " + bioID);


                        JSONObject billsJSON = new GetCongressBills().execute(bioID).get();
                        JSONArray bill_parse = (billsJSON.getJSONArray("results"));
                        ArrayList bills = new ArrayList<String>();
                        for (int x = 0; x < 3; x++) {
                            bills.add(bill_parse.getJSONObject(x).getString("official_title"));
                        }

                        JSONObject commJSON = new GetCommit().execute(bioID).get();
                        JSONArray comm_parse = (commJSON.getJSONArray("results"));
                        ArrayList comms = new ArrayList<String>();
                        if (comm_parse != null && comm_parse.length() != 0) {
                            for (int x = 0; x < 3; x++) {
                                comms.add(comm_parse.getJSONObject(x).getString("name"));
                            }
                        } else {
                            comms.add("None");
                        }

                        intent.putStringArrayListExtra("bills" + i, bills);
                        intent.putStringArrayListExtra("commit" + i, comms);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //String built = SB.toString();
                //sendIntent.putExtra("info",built);

                //intent.putExtra("ZIP" , zip_s);
                //intent.putExtra("Name","Bernie Sanders");
                //intent.putExtra("Party","Independent");
                //intent.putExtra("Email","info@betonbernie.com");
                //intent.putExtra("Website", "berniesanders.com");
                //intent.putExtra("Twitter", "This is a really long tweet so that I can check that it works even if it gets pretty long lemao");




                //System.out.println("I got here!");
                //            startService(sendIntent);
                startActivity(intent);
                //startService(sendIntent);
            }
        });


        LocBut.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                            mGoogleApiClient);
                    if (mLastLocation != null) {
                        Double lat = mLastLocation.getLatitude();
                        Double longi = mLastLocation.getLongitude();
                        LocActivity(lat, longi);

                    } else {
                        System.out.println("TIS NULL");
                    }
                } finally {
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            LocActivity(intent.getDoubleExtra("LAT", 0.0), intent.getDoubleExtra("LONG", 0.0));
        }
    }

    public void LocActivity(double lat, double longi){
        Intent intent = new Intent(getApplicationContext(), People_Overview.class);
        try{
            String loca[] = new String[2];
            loca[0] = Double.toString(lat);
            loca[1] = Double.toString(longi);
            JSONObject jjson = new GetCongressByLoc().execute(loca).get();
            JSONArray jjson_parse = jjson.getJSONArray("results");

            for (int i = 0; i < 3; i++) {
                intent.putExtra("Name" + i, jjson_parse.getJSONObject(i).getString("first_name") + " " + jjson_parse.getJSONObject(i).getString("last_name"));
                intent.putExtra("Party" + i, jjson_parse.getJSONObject(i).getString("party"));
                intent.putExtra("Email" + i, jjson_parse.getJSONObject(i).getString("oc_email"));
                intent.putExtra("Website" + i, jjson_parse.getJSONObject(i).getString("website"));
                intent.putExtra("End" + i, jjson_parse.getJSONObject(i).getString("term_end"));

                String bioID = jjson_parse.getJSONObject(i).getString("bioguide_id");

                intent.putExtra("bioID" + i, bioID);

                JSONObject billsJSON = new GetCongressBills().execute(bioID).get();
                JSONArray bill_parse = (billsJSON.getJSONArray("results"));
                ArrayList bills = new ArrayList<String>();
                for (int x = 0; x < 3; x++) {
                    bills.add(bill_parse.getJSONObject(x).getString("official_title"));
                }

                JSONObject commJSON = new GetCommit().execute(bioID).get();
                JSONArray comm_parse = (commJSON.getJSONArray("results"));
                ArrayList comms = new ArrayList<String>();
                if (comm_parse != null && comm_parse.length() != 0) {
                    for (int x = 0; x < 3; x++) {
                        comms.add(comm_parse.getJSONObject(x).getString("name"));
                    }
                } else {
                    comms.add("None");
                }

                intent.putStringArrayListExtra("bills" + i, bills);
                intent.putStringArrayListExtra("commit" + i, comms);

            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        startActivity(intent);
    }


    //Location Tracking Stuff
    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}


    //Get congress information
    class GetCongress extends AsyncTask<String, Void, JSONObject> {
        private Exception exception;

        protected void onPreExecute() {

        }

        protected JSONObject doInBackground(String... zipcode) {
            Log.i("Checkpoint","Got HERE");
            // Do some validation here

            try {
                URL url = new URL("http://congress.api.sunlightfoundation.com/legislators/locate?zip=" + zipcode[0] + "&apikey=" + "f1390df572024aacbdaf644cfb3c4306");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return new JSONObject(stringBuilder.toString());
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            testview.setText(response);
            //try {
            //   JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                //String requestID = object.getString("requestId");
                //int likelihood = object.getInt("likelihood");
                //JSONArray photos = object.getJSONArray("photos");
            //} catch (JSONException e) {
            //    e.printStackTrace();
            //}

        }
    }

    //Congress by Cur Location
    class GetCongressByLoc extends AsyncTask<String, Void, JSONObject> {
        private Exception exception;

        protected void onPreExecute() {

        }

        protected JSONObject doInBackground(String ... locs) {
            Log.i("Checkpoint","Got HERE P2");
            // Do some validation here

            try {
                URL url = new URL("http://congress.api.sunlightfoundation.com/legislators/locate?latitude="+locs[0]+"&longitude="+locs[1]+"&apikey=f1390df572024aacbdaf644cfb3c4306");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return new JSONObject(stringBuilder.toString());
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            testview.setText(response);
            //try {
            //   JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            //String requestID = object.getString("requestId");
            //int likelihood = object.getInt("likelihood");
            //JSONArray photos = object.getJSONArray("photos");
            //} catch (JSONException e) {
            //    e.printStackTrace();
            //}

        }
    }

    //Get bills and comittees
    class GetCongressBills extends AsyncTask<String, Void, JSONObject> {
        private Exception exception;

        protected void onPreExecute() {

        }

        protected JSONObject doInBackground(String ... id) {
            Log.i("Checkpoint","Got HERE P3");
            // Do some validation here

            try {
                URL url = new URL("http://congress.api.sunlightfoundation.com/bills?sponsor_id="+ id[0] +"&apikey=f1390df572024aacbdaf644cfb3c4306");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return new JSONObject(stringBuilder.toString());
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            testview.setText(response);
            //try {
            //   JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            //String requestID = object.getString("requestId");
            //int likelihood = object.getInt("likelihood");
            //JSONArray photos = object.getJSONArray("photos");
            //} catch (JSONException e) {
            //    e.printStackTrace();
            //}

        }
    }

    class GetCommit extends AsyncTask<String, Void, JSONObject> {
        private Exception exception;

        protected void onPreExecute() {

        }

        protected JSONObject doInBackground(String ... id) {
            Log.i("Checkpoint","Got HERE P3");
            // Do some validation here

            try {
                URL url = new URL("http://congress.api.sunlightfoundation.com/committees?member_ids="+id[0]+"&apikey=f1390df572024aacbdaf644cfb3c4306");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return new JSONObject(stringBuilder.toString());
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            testview.setText(response);
            //try {
            //   JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            //String requestID = object.getString("requestId");
            //int likelihood = object.getInt("likelihood");
            //JSONArray photos = object.getJSONArray("photos");
            //} catch (JSONException e) {
            //    e.printStackTrace();
            //}

        }
    }


}
