package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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
        ArrayList raw_commit = intent.getStringArrayListExtra("Commitee");
        ArrayList raw_bill = intent.getStringArrayListExtra("Bills");

        String commit_refined = "";
        for(int i=0; i<raw_commit.size(); i++){
            commit_refined += raw_commit.get(i);
            commit_refined += "\n";
        }
        String bill_refined = "";
        for(int i=0; i<raw_bill.size(); i++){
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


}