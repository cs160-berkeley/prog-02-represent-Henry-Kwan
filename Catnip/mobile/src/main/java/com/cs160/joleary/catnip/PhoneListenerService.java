package com.cs160.joleary.catnip;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

//   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
private static final String TOAST = "/Name";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase(TOAST) ) {

            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MoreDetails.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("Name", value);
            intent.putExtra("Party", "Independent");
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
            Log.d("T", "about to start watch MainActivity with NAME: Bernie");
            startActivity(intent);

            // so you may notice this crashes the phone because it's
            //''sending message to a Handler on a dead thread''... that's okay. but don't do this.
            // replace sending a toast with, like, starting a new activity or something.
            // who said skeleton code is untouchable? #breakCSconceptions

        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
