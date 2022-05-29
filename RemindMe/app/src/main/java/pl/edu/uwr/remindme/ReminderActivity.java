package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editCreateDate;
    private final Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int day = calendar.get(Calendar.DAY_OF_MONTH);

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        editCreateDate = findViewById(R.id.editCreateDate);

        // Set onClick Listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);

        editCreateDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(ReminderActivity.this,
                    android.R.style.Theme_Holo_Dialog_MinWidth, onDateSetListener, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        onDateSetListener = (view, year, month, dayOfMonth) -> {
            month++;
            String date = dayOfMonth + "/" + month + "/" + year;
            editCreateDate.setText(date);
        };
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        EditText editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);

        // Intent
        Intent intent = new Intent(ReminderActivity.this, AlarmReceiver.class);
        int notificationId = 1;
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("message", editText.getText().toString());

        // PendingIntent
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(
                ReminderActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:
                String[] data = editCreateDate
                        .getText()
                        .toString()
                        .split("/");
                Calendar startTime = Calendar.getInstance();
                int year, month, day;
                try {
                    year = Integer.parseInt(data[2]);
                    month = Integer.parseInt(data[1]) - 1;
                    day = Integer.parseInt(data[0]);
                }catch (Exception e){
                    year = startTime.get(Calendar.YEAR);
                    month = startTime.get(Calendar.MONTH);
                    day = startTime.get(Calendar.DAY_OF_MONTH);
                }



                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                startTime.set(Calendar.YEAR, year);
                startTime.set(Calendar.MONTH, month);
                startTime.set(Calendar.DAY_OF_MONTH, day);
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}