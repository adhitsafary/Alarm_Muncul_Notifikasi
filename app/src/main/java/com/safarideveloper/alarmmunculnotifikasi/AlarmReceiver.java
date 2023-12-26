package com.safarideveloper.alarmmunculnotifikasi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Logika untuk menampilkan notifikasi di sini
        showNotification(context, "Waktunya untuk sesuatu.");
    }

    void showNotification(Context context, String message) {
        String channelId = "alarm_channel";
        String channelName = "Alarm Channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.createNotificationChannel(channel);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Alarm Notification")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        notificationManager.notify(1, builder.build());
    }
}
