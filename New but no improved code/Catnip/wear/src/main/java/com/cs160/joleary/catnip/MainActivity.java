package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cs160.joleary.catnip.R;

public class MainActivity extends Activity {

    private TextView mTextView;
    private Button mFeedBtn;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

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
                Intent sendIntent = new Intent(getBaseContext(), ShowPoli.class);
                startActivity(sendIntent);
            }
        });

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                if(count >= 3){
                    Intent intent = new Intent(getBaseContext(),WatchToPhoneService.class);
                    intent.putExtra("PATH" , "/random");
                    startService(intent);
                }
            }

        });


    }

    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
