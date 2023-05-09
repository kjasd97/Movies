package com.ulyanenko.movies.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){

            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, " Battery is low ! ", Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object{
    }

}