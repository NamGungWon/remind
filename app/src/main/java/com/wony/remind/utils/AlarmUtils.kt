package com.wony.remind.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.wony.remind.AlarmReceiver
import com.wony.remind.base.BaseApplication

object AlarmUtils {

    fun register(
        id: Long,
        time: Long,
    ) {

        var context = BaseApplication.getContext()

        var intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.KEY_ID, id)

        var pendingIntent = PendingIntent.getBroadcast(
            context,
            id.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                am.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP, time, pendingIntent
                )
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                am.setExact(
                    AlarmManager.RTC_WAKEUP, time, pendingIntent
                )
            }

            else -> am.set(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
            )
        }
    }


    fun remove(alarmId: Long) {

        var context = BaseApplication.getContext()

        var intent = Intent(context, AlarmReceiver::class.java)
        var pendingIntent =
            PendingIntent.getBroadcast(
                context,
                alarmId.toInt(),
                intent,
                PendingIntent.FLAG_NO_CREATE
            )

        if (pendingIntent != null) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }
    }
}


