package com.roundarch.codetest.part3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;
import java.util.Map;

public class Part3Service extends Service {

    private final String TAG = this.getClass().getSimpleName();

    // TODO - we can use this as the broadcast intent to filter for in our Part3Fragment
    public static final String ACTION_SERVICE_DATA_UPDATED = "com.roundarch.codetest.ACTION_SERVICE_DATA_UPDATED";

    private List<Map<String,String>> data = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO - this interface needs to be implemented to allow consumers
        // TODO - access to the data we plan to download
        return new Part3ServiceBinder();
    }

    private void updateData() {
        // TODO - start the update process for our data
    }

    private void broadcastDataUpdated() {
        // TODO - send the broadcast
    }

    public final class Part3ServiceBinder extends Binder {
        // TODO - we need to expose our public IBinder API to clients
    }

    // TODO - eventually we plan to request JSON from the network, so we need
    // TODO - to implement a way to perform that off the main thread.  Then, once we
    // TODO - have the data we can parse it as JSON (using standard Android APIs is fine)
    // TODO - before finally returning to the main thread to store our data on the service.
    // TODO - Keep in mind that the service will keep a local copy and will need an interface
    // TODO - to allow clients to access it.

    // TODO - if you need a simple JSON endpoint, you can obtain the ZIP codes for the state
    // TODO - of Illinois by using this URL:
    //
    // TODO - http://gomashup.com/json.php?fds=geo/usa/zipcode/state/IL

}
