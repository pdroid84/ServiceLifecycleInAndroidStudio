package com.paul.debashis.servicelifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    int mStartMode;       // indicates how to behave if the service is killed
    IBinder mBinder;      // interface for clients that bind
    boolean mAllowRebind; // indicates whether onRebind should be used

    @Override
    public void onCreate() {
        // The service is being created
        Log.d("DEB","MyService - onCreate is called");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        Log.d("DEB","MyService - onStartCommand is called. Start mode = "+mStartMode);
        return mStartMode;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        Log.d("DEB","MyService - onBind is called");
        mBinder = new MyBinder();
        return mBinder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        Log.d("DEB","MyService - onUnbind is called");
        mAllowRebind = true;
        return mAllowRebind;
    }
    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
        Log.d("DEB","MyService - onRebind is called");
    }
    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        Log.d("DEB","MyService - onDestroy is called");
    }
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class MyBinder extends Binder {
        MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            Log.d("DEB","MyService -> MyBinder - getService is called");
            return MyService.this;
        }
    }
}
