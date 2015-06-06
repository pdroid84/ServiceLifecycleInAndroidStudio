package com.paul.debashis.servicelifecycle;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    Intent mIntent = null;
    MyService mMyService;
    boolean mBound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public  void startService (View view){
        mIntent = new Intent(this,MyService.class);
        startService(mIntent);
    }
    public  void stopService (View view){
        stopService(mIntent);
    }

    public  void bindService (View view){
        mIntent = new Intent(this,MyService.class);
        bindService(mIntent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    public  void unbindService (View view){
        if(mBound){
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.
            Log.d("DEB","MainActivity - onServiceConnected is called");
            MyService.MyBinder mMyBinder = (MyService.MyBinder) service;
            mMyService = mMyBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            Log.d("DEB","MainActivity - onServiceDisconnected is called");
            mBound = false;
        }
    };
}
