package com.example.callrecorder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.TelephonyManager

class CallReceiver : BroadcastReceiver() {

    companion object {
        private var lastState = TelephonyManager.CALL_STATE_IDLE
    }

    override fun onReceive(context: Context, intent: Intent) {
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE) ?: return

        when (state) {
            TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                if (lastState != TelephonyManager.CALL_STATE_OFFHOOK) {
                    lastState = TelephonyManager.CALL_STATE_OFFHOOK
                    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                    if (!prefs.getBoolean("auto_record", true)) return
                    val svc = Intent(context, RecordingService::class.java)
                        .setAction(RecordingService.ACTION_START)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startForegroundService(svc)
                    } else {
                        context.startService(svc)
                    }
                }
            }
            TelephonyManager.EXTRA_STATE_IDLE -> {
                if (lastState != TelephonyManager.CALL_STATE_IDLE) {
                    lastState = TelephonyManager.CALL_STATE_IDLE
                    val svc = Intent(context, RecordingService::class.java)
                        .setAction(RecordingService.ACTION_STOP)
                    context.startService(svc)
                }
            }
            TelephonyManager.EXTRA_STATE_RINGING -> {
                lastState = TelephonyManager.CALL_STATE_RINGING
            }
        }
    }
}
