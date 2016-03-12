package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class People_Overview extends Activity {
     String name1;
     String party1;
     String email1;
     String website1;
     String bioGuideId0;
     String end1;
     ArrayList bills1;
     ArrayList commit1;

     String name2;
     String party2;
     String email2;
     String website2;
     String bioGuideId1;
     String end2;
     ArrayList bills2;
     ArrayList commit2;

     String name3;
     String party3;
     String email3;
     String website3;
     String bioGuideId2;
     String end3;
     ArrayList bills3;
     ArrayList commit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people__overview);

        ImageView iview = (ImageView) findViewById(R.id.Senator1);
        ImageView iview2 = (ImageView) findViewById(R.id.Senator2);
        ImageView iview3 = (ImageView) findViewById(R.id.Senator3);
        TextView textView = (TextView) findViewById(R.id.name1);
        TextView textView2 = (TextView) findViewById(R.id.name2);
        TextView textView3 = (TextView) findViewById(R.id.name3);
        TextView textP1 = (TextView) findViewById(R.id.theParty);
        TextView textP2 = (TextView) findViewById(R.id.theParty2);
        TextView textP3 = (TextView) findViewById(R.id.theParty3);
        TextView textEM1 = (TextView) findViewById(R.id.email1);
        TextView textEM2 = (TextView) findViewById(R.id.theEmail2);
        TextView textEM3 = (TextView) findViewById(R.id.theEmail3);
        TextView textW1 = (TextView) findViewById(R.id.thewebsite1);
        TextView textW2 = (TextView) findViewById(R.id.thewebsite2);
        TextView textW3 = (TextView) findViewById(R.id.thewebsite3);
        TextView textTw1 = (TextView) findViewById(R.id.theTweet1);

        Button Details1 = (Button) findViewById(R.id.Details1);
        Button Details2 = (Button) findViewById(R.id.Details2);
        Button Details3 = (Button) findViewById(R.id.Details3);

        Intent intent = getIntent();

        name1 = intent.getStringExtra("Name0");
        party1 = intent.getStringExtra("Party0");
        email1 = intent.getStringExtra("Email0");
        website1 = intent.getStringExtra("Website0");
         bioGuideId0 = intent.getStringExtra("bioID0");
        end1 = intent.getStringExtra("End0");
        //final String tweet1 = intent.getStringExtra("Twitter");
        bills1 = intent.getStringArrayListExtra("bills0");
        commit1 = intent.getStringArrayListExtra("commit0");

        name2 = intent.getStringExtra("Name1");
        party2 = intent.getStringExtra("Party1");
        email2 = intent.getStringExtra("Email1");
        website2 = intent.getStringExtra("Website1");
         bioGuideId1 = intent.getStringExtra("bioID1");
        end2 = intent.getStringExtra("End1");
        //final String tweet2 = intent.getStringExtra("Twitter");
         bills2 = intent.getStringArrayListExtra("bills1");
        commit2 = intent.getStringArrayListExtra("commit1");

        name3 = intent.getStringExtra("Name2");
        party3 = intent.getStringExtra("Party2");
        email3 = intent.getStringExtra("Email2");
        website3 = intent.getStringExtra("Website2");
        bioGuideId2 = intent.getStringExtra("bioID2");
        end3 = intent.getStringExtra("End2");
        //final String tweet3 = intent.getStringExtra("Twitter");
        bills3 = intent.getStringArrayListExtra("bills2");
        commit3 = intent.getStringArrayListExtra("commit2");



        //iview.setImageDrawable(getDrawable(R.drawable.bernie));
        String URL = "https://theunitedstates.io/images/congress/225x275/" +
                bioGuideId0 + ".jpg";
        DownloadImageTask image = new DownloadImageTask(iview);
        image.execute(URL);
        textView.setText(name1);
        textP1.setText(party1);
        textEM1.setText(email1);
        textW1.setText(website1);
        //textTw1.setText(tweet1);

        iview2.setImageDrawable(getDrawable(R.drawable.bernie));
        textView2.setText(name2);
        textP2.setText(party2);
        textEM2.setText(email2);
        textW2.setText(website2);
        String URL2 = "https://theunitedstates.io/images/congress/225x275/" +
                bioGuideId1 + ".jpg";
        DownloadImageTask image2 = new DownloadImageTask(iview2);
        image2.execute(URL2);

        iview3.setImageDrawable(getDrawable(R.drawable.bernie));
        textView3.setText(name3);
        textP3.setText(party3);
        textEM3.setText(email3);
        textW3.setText(website3);
        String URL3 = "https://theunitedstates.io/images/congress/225x275/" +
                bioGuideId2 + ".jpg";
        DownloadImageTask image3 = new DownloadImageTask(iview3);
        image3.execute(URL3);

        Details1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                but1click();
            }
        });



        Details2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                but2click();
            }
        });

        Details3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                but3click();
            }
        });

        textW1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website1));
                startActivity(intent);
            }
        });
        textW2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website2));
                startActivity(intent);
            }
        });

        textW3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website3));
                startActivity(intent);
            }
        });

        textEM1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: " + email1));
                startActivity(intent);
            }
        });
        textEM2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: " + email2));
                startActivity(intent);
            }
        });
        textEM3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: " + email3));
                startActivity(intent);
            }
        });

    }

    public void but1click(){
        Intent intent = new Intent(getApplicationContext(), MoreDetails.class);
        intent.putExtra("Name", name1);
        intent.putExtra("Party", party1);
        intent.putExtra("ID", bioGuideId0);
        intent.putExtra("End", end1);
        intent.putStringArrayListExtra("Commitee", commit1);
        intent.putStringArrayListExtra("Bills", bills1);
        startActivity(intent);
    }

    public void but2click(){
        Intent intent = new Intent(getApplicationContext(), MoreDetails.class);
        intent.putExtra("Name", name2);
        intent.putExtra("Party", party2);
        intent.putExtra("ID", bioGuideId1);
        intent.putExtra("End", end2);
        intent.putStringArrayListExtra("Commitee", commit2);
        intent.putStringArrayListExtra("Bills", bills2);
        startActivity(intent);
    }

    public void but3click(){
        Intent intent = new Intent(getApplicationContext(), MoreDetails.class);
        intent.putExtra("Name", name3);
        intent.putExtra("Party", party3);
        intent.putExtra("ID", bioGuideId2);
        intent.putExtra("End", end3);
        intent.putStringArrayListExtra("Commitee", commit3);
        intent.putStringArrayListExtra("Bills", bills3);
        startActivity(intent);
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
                bmImage.setImageBitmap(result);
            }
        }
    }


}