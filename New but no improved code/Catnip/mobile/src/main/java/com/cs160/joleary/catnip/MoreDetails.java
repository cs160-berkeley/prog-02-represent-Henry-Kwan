package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class MoreDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        TextView Name = (TextView)findViewById(R.id.MainName);
        TextView Party = (TextView)findViewById(R.id.Affiliation);
        TextView tEnd = (TextView)findViewById(R.id.termEnds);
        TextView Com_list = (TextView)findViewById(R.id.commiteeTag);
        TextView bill_list = (TextView)findViewById(R.id.BillList);
        ImageView spotlight = (ImageView)findViewById(R.id.CenterStage);


        Intent intent = getIntent();
        String topic = intent.getStringExtra("Name");
        String party_choice = intent.getStringExtra("Party");
        String term_end = intent.getStringExtra("End");
        String ID = intent.getStringExtra("ID");
        ArrayList raw_commit = intent.getStringArrayListExtra("Commitee");
        ArrayList raw_bill = intent.getStringArrayListExtra("Bills");

        String URL= "https://theunitedstates.io/images/congress/225x275/" +
                ID + ".jpg";
        DownloadImageTask image2 = new DownloadImageTask(spotlight);
        image2.execute(URL);

        String commit_refined = "";
        for(int i=0; i<raw_commit.size(); i++){
            commit_refined += Integer.toString(i + 1)+". ";
            commit_refined += raw_commit.get(i);
            commit_refined += "\n";
        }
        String bill_refined = "";
        for(int i=0; i<raw_bill.size(); i++){
            bill_refined += Integer.toString(i+1)+". ";
            bill_refined += raw_bill.get(i);
            bill_refined += "\n";
        }
        System.out.println(bill_refined);


        spotlight.setImageDrawable(getDrawable(R.drawable.bernie));

        Name.setText(topic);
        Party.setText(party_choice);
        tEnd.setText(term_end);
        Com_list.setText(commit_refined);
        bill_list.setText(bill_refined);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView iv){bmImage = iv;}

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if(result != null) {
                System.out.println("I GOT HERE");
                bmImage.setImageBitmap(result);
            }
        }
    }
}