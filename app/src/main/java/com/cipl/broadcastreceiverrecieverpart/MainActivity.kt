package com.cipl.broadcastreceiverrecieverpart

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


// Receiver App

class MainActivity : AppCompatActivity() {

    // This is how we create anonymous class in kotlin using object
    private val broadcastReceiver = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action){
                // This is the package name of sender app
                "com.cipl.broadcastreceiversenderpart.ACTION_SEND" ->{
                    // Now we need to get data from sender app. So for that we use getStringExtra() method to get data
                    val string = intent.getStringExtra("com.cipl.broadcastreceiversenderpart.EXTRA_DATA")
                    Toast.makeText(context, "Receiver app received this data called $string", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // In onCreate(), we have to register our broadcast receiver
        val intentFilter = IntentFilter("com.cipl.broadcastreceiversenderpart.ACTION_SEND")
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        // In onDestroy(), we have to unregister our broadcast receiver
        unregisterReceiver(broadcastReceiver)
    }
}