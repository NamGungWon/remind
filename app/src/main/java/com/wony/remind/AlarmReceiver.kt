package com.wony.remind

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver: BroadcastReceiver() {
    companion object {

        const val KEY_ID = "KEY_ID"
    }

    override fun onReceive(context: Context, intent: Intent) {
        var id = intent?.getLongExtra(KEY_ID, 0)

        if(id != 0L){
            val activityIntent = MainActivity.newIntent(context,id)
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(activityIntent)
        }

    }
}