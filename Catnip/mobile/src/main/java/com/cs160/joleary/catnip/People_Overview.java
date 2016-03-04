package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class People_Overview extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people__overview);

        ImageView iview = (ImageView)findViewById(R.id.Senator1);
        ImageView iview2 = (ImageView)findViewById(R.id.Senator2);
        ImageView iview3 = (ImageView)findViewById(R.id.Senator3);
        TextView textView = (TextView)findViewById(R.id.name1);
        TextView textP1 = (TextView)findViewById(R.id.theParty);
        TextView textEM1 = (TextView)findViewById(R.id.email1);
        TextView textW1 = (TextView)findViewById(R.id.thewebsite1);
        TextView textTw1 = (TextView)findViewById(R.id.theTweet1);

        Button Details1 = (Button)findViewById(R.id.Details1);

        Intent intent = getIntent();

        final String name1 = intent.getStringExtra("Name");
        final String party1 = intent.getStringExtra("Party");
        final String email1 = intent.getStringExtra("Email");
        final String website1 = intent.getStringExtra("Website");
        final String tweet1 = intent.getStringExtra("Twitter");

        textView.setText(name1);
        iview.setImageDrawable(getDrawable(R.drawable.bernie));
        textP1.setText(party1);
        textEM1.setText(email1);
        textW1.setText(website1);
        textTw1.setText(tweet1);

        iview2.setImageDrawable(getDrawable(R.drawable.bernie));
        iview3.setImageDrawable(getDrawable(R.drawable.bernie));

        Details1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MoreDetails.class);
                intent.putExtra("Name", name1);
                intent.putExtra("Party", party1);
                intent.putExtra("End", "January 3, 2019");
                ArrayList commit = new ArrayList<String>();
                commit.add("Commitee 1");
                commit.add("Commitee 2");
                commit.add("Commitee 3");
                intent.putStringArrayListExtra("Commitee", commit);
                ArrayList Bill = new ArrayList<String>();
                Bill.add("Bill 1");
                Bill.add("Bill 2");
                Bill.add("Bill 3");
                intent.putStringArrayListExtra("Bills", Bill);
                startActivity(intent);
            }
        });


    }


}