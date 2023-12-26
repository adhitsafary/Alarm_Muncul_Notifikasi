package com.safarideveloper.alarmmunculnotifikasi;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AlarmService extends IntentService {

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Logika untuk menentukan kapan notifikasi harus muncul
        showNotification();
    }

    private void showNotification() {
        // Logika untuk menampilkan notifikasi di sini
        AlarmReceiver alarmReceiver = new AlarmReceiver();
        alarmReceiver.showNotification(this, "Waktunya untuk sesuatu.");
    }
}
