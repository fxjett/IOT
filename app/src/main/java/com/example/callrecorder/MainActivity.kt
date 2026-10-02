package com.example.callrecorder

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Switch

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val sw = findViewById<Switch>(R.id.swAuto)
        sw.isChecked = prefs.getBoolean("auto_record", true)
        sw.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean("auto_record", checked).apply()
        }

        findViewById<Button>(R.id.btnPermissions).setOnClickListener { requestNeeded() }
        requestNeeded()
    }

    private fun requestNeeded() {
        val perms = mutableListOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            perms.add(Manifest.permission.POST_NOTIFICATIONS)
        }
        val toAsk = perms.filter {
            checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
        }
        if (toAsk.isNotEmpty()) requestPermissions(toAsk.toTypedArray(), 1)
    }
}
