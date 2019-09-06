package com.example.p360;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SeekBarService extends Service {
    public SeekBarService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
