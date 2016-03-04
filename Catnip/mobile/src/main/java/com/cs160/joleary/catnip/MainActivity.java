package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ZCodeInfo = (EditText) findViewById(R.id.ZipCode);
        Button ZipButton = (Button) findViewById(R.id.Zip_Go);
        Button LocBut = (Button) findViewById(R.id.My_Loc);

        ZipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double zip_s = Double.valueOf(ZCodeInfo.getText().toString());

                Intent intent = new Intent(getApplicationContext(), People_Overview.class);
                intent.putExtra("ZIP" , zip_s);
                intent.putExtra("Name","Bernie Sanders");
                intent.putExtra("Party","Independent");
                intent.putExtra("Email","info@betonbernie.com");
                intent.putExtra("Website", "berniesanders.com");
                intent.putExtra("Twitter", "This is a really long tweet so that I can check that it works even if it gets pretty long lemao");

                Intent sendIntent = new Intent(getApplicationContext(), PhoneToWatchService.class);
                sendIntent.putExtra("Name","Bernie Sanders");

                System.out.println("I got here!");
                startService(sendIntent);
                startActivity(intent);
            }
        });

        LocBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double zip_s = Double.valueOf(ZCodeInfo.getText().toString());

                Intent intent = new Intent(getApplicationContext(), People_Overview.class);
                intent.putExtra("ZIP" , zip_s);
                intent.putExtra("Name","Bernie Sanders");
                intent.putExtra("Party","Independent");
                intent.putExtra("Email","info@betonbernie.com");
                intent.putExtra("Website", "berniesanders.com");
                intent.putExtra("Twitter", "Notice a change in this long TWEEEEEEEET");

                Intent sendIntent = new Intent(getApplicationContext(), PhoneToWatchService.class);
                sendIntent.putExtra("Name","Location");

                System.out.println("I got here!!!!");
                startService(sendIntent);
                startActivity(intent);
            }
        });

    }


}
