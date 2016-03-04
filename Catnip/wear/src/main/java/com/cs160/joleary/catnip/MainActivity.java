package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cs160.joleary.catnip.R;

public class MainActivity extends Activity {

    private TextView mTextView;
    private Button mFeedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFeedBtn = (Button) findViewById(R.id.feed_btn);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String catName = extras.getString("Name");
            mFeedBtn.setText("Start!");
        }

        mFeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                System.out.println("I have arrived but I will not work");
                Intent sendIntent = new Intent(getBaseContext(), ShowPoli.class);
                startActivity(sendIntent);
            }
        });
    }
}
