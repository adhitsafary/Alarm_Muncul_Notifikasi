package com.safarideveloper.alarmmunculnotifikasi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btnDatePicker;
    private Button btnTimePicker;
    private Button btnSetAlarm;
    private TextView txthasil;

    private Calendar selectedCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnTimePicker = findViewById(R.id.btnTimePicker);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        txthasil = findViewById(R.id.hasil_pilih);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });

        selectedCalendar = Calendar.getInstance();
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        selectedCalendar.set(year, month, day);
                    }
                },
                selectedCalendar.get(Calendar.YEAR),
                selectedCalendar.get(Calendar.MONTH),
                selectedCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        selectedCalendar.set(Calendar.HOUR_OF_DAY, hour);
                        selectedCalendar.set(Calendar.MINUTE, minute);
                    }
                },
                selectedCalendar.get(Calendar.HOUR_OF_DAY),
                selectedCalendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void setAlarm() {
        // Setel alarm menggunakan AlarmManager
        setAlarmUsingAlarmManager(selectedCalendar.getTimeInMillis());

        // Menampilkan pesan toast dengan informasi alarm yang diatur
        showAlarmToast(selectedCalendar);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setAlarmUsingAlarmManager(long timeInMillis) {
        // Implementasikan setAlarmUsingAlarmManager sesuai kebutuhan Anda
    }

    private void showAlarmToast(Calendar selectedCalendar) {
        String toastMessage = "Alarm diatur untuk " +
                selectedCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
                (selectedCalendar.get(Calendar.MONTH) + 1) + "/" +
                selectedCalendar.get(Calendar.YEAR) + " " +
                selectedCalendar.get(Calendar.HOUR_OF_DAY) + ":" +
                selectedCalendar.get(Calendar.MINUTE);

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        txthasil.setText(toastMessage);
    }
}
