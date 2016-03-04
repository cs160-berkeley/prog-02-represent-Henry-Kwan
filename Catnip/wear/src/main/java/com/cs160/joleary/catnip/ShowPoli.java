package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.cs160.joleary.catnip.R;

public class ShowPoli extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_poli);

        ImageButton senator1Button = (ImageButton)findViewById(R.id.Sen1);
        ImageButton senator2Button = (ImageButton)findViewById(R.id.Sen2);
        ImageButton senator3Button = (ImageButton)findViewById(R.id.Sen3);

        senator1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WearDetail.class);
                startActivity(sendIntent);

                Intent watchIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                startService(watchIntent);
            }
        });
    }


}
