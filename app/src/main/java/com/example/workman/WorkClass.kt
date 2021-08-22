package com.example.workman

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.*

private const val TAG = "WorkClass"

class WorkClass(context: Context, params: WorkerParameters): Worker(context, params) {


    override fun doWork(): Result {

        val context = applicationContext
        val pendingIntent = pendingIntent(context)
        val notification = createNotification(context, pendingIntent)
        val notificationManager: NotificationManager =
            getSystemService(context, NotificationManager::class.java) as NotificationManager

        Log.d(TAG, "*************: before delay")
        runBlocking {
            delay(6000)
            Log.d(TAG, "******************: inside coroutine ")
        }


        Log.d(TAG, "******************: after delay ")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        with(NotificationManagerCompat.from(context)) {
            notify(2, notification.build())
        }

        return Result.success()
    }

}