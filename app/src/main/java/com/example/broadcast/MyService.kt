package com.example.broadcast

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.concurrent.thread

class MyService: Service() {

    private val localBroadcastReceiver by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            for (i in 1..10) {
                Thread.sleep(1000)
                Intent(MyReceiver.ACTION_LOADED).apply {
                    putExtra(MyReceiver.EXTRA_PERCENT, i * 10)
                    localBroadcastReceiver.sendBroadcast(this)
                }

            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}