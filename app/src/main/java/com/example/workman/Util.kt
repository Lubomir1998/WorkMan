package com.example.workman

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

const val NOTIFICATION_CHANNEL_ID = "channel"

fun createNotification(context: Context, pendingIntent: PendingIntent) =
    NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        .setContentTitle("Title")
        .setContentText("Work is done")
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentIntent(pendingIntent)

fun pendingIntent(context: Context) = PendingIntent.getActivity(
    context,
    111,
    Intent(context, MainActivity::class.java),
    PendingIntent.FLAG_UPDATE_CURRENT
)

fun createNotificationChannel(notificationManager: NotificationManager) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "channel_name", importance)
        // Register the channel with the system
        notificationManager.createNotificationChannel(channel)
    }
}