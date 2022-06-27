package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private var clickNumber = 1

//    private val receiver = MyReceiver()

    private val receiver = object: BroadcastReceiver() {

        override fun onReceive(p0: Context?, intent: Intent?) {
            if (intent?.action == MyReceiver.ACTION_LOADED) {
                val percent = intent.getIntExtra(MyReceiver.EXTRA_PERCENT, 0)
                progressBar.progress = percent
            } else if (intent?.action == MyReceiver.ACTION_CLICKED) {
                val clickNumber = intent.getIntExtra(MyReceiver.EXTRA_CLICK_NUMBER, 0)
                Toast.makeText(p0, "button clicked $clickNumber", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(MyReceiver.ACTION_CLICKED)
            addAction(MyReceiver.ACTION_LOADED)
        }

        registerReceiver(receiver, intentFilter)
        findViewById<Button>(R.id.button).setOnClickListener{
            Intent(MyReceiver.ACTION_CLICKED).apply {
                putExtra(MyReceiver.EXTRA_CLICK_NUMBER, clickNumber++)
                sendBroadcast(this)
            }
        }

        Intent(this, MyService::class.java).apply {
            startService(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}