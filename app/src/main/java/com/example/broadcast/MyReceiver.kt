package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnedOn = intent.getBooleanExtra("state", false)
                Toast.makeText(
                    context,
                    "Airplane mode changed. turned on $turnedOn",
                    Toast.LENGTH_LONG
                ).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "Battery low", Toast.LENGTH_LONG).show()
            }
            ACTION_CLICKED -> {
                val clickNumber = intent.getIntExtra(EXTRA_CLICK_NUMBER, 0)
                Toast.makeText(context, "button clicked $clickNumber", Toast.LENGTH_SHORT).show()
            }
            ACTION_LOADED -> {
                val percent = intent.getIntExtra(EXTRA_PERCENT, 0)
                Toast.makeText(context, "$percent%", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val ACTION_CLICKED = "clicked"
        const val ACTION_LOADED = "loaded"

        const val EXTRA_CLICK_NUMBER = "extra click number"
        const val EXTRA_PERCENT = "percent"
    }
}