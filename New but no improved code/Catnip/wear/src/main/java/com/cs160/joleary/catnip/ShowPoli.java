package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.cs160.joleary.catnip.R;

public class ShowPoli extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_poli);

        Button senator1Button = (Button)findViewById(R.id.Sen1);
        Button senator2Button = (Button)findViewById(R.id.Sen2);
        Button senator3Button = (Button)findViewById(R.id.Sen3);

        Intent given = getIntent();
        senator1Button.setText(given.getStringExtra("name0"));
        senator2Button.setText(given.getStringExtra("name1"));
        senator3Button.setText(given.getStringExtra("name2"));

        final String n0 = given.getStringExtra("name0");
        final String n1 = given.getStringExtra("name1");
        final String n2 = given.getStringExtra("name2");

        final String p0 = given.getStringExtra("party0");
        final String p1 = given.getStringExtra("party1");
        final String p2 = given.getStringExtra("party2");

        senator1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WearDetail.class);
                sendIntent.putExtra("name",n0);
                sendIntent.putExtra("name",p0);
                startActivity(sendIntent);

                Intent watchIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                watchIntent.putExtra("/Button", "1");
                startService(watchIntent);
            }
        });

        senator2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WearDetail.class);
                sendIntent.putExtra("name",n1);
                sendIntent.putExtra("name",p1);
                startActivity(sendIntent);

                Intent watchIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                watchIntent.putExtra("/Button" , "2");
                startService(watchIntent);
            }
        });

        senator3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WearDetail.class);
                sendIntent.putExtra("name",n2);
                sendIntent.putExtra("name",p2);
                startActivity(sendIntent);

                Intent watchIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                watchIntent.putExtra("/Button" , "3");
                startService(watchIntent);
            }
        });
    }


}
