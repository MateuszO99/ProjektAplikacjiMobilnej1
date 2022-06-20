package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        int fontSize = sharedPreferences.getInt("FONT_SIZE", 2);

        ViewGroup layout = findViewById(R.id.activity_main_layout);
        for (int i = 0; i < layout.getChildCount(); i++){
            View child = layout.getChildAt(i);

            if (child instanceof Button){
                Button button = (Button) child;
                button.setTextSize(fontSize * 10);
            }

            else if (child instanceof EditText){
                EditText editText = (EditText) child;
                editText.setTextSize(fontSize * 10);
            }

            else if (child instanceof TextView){
                TextView textView = (TextView) child;
                textView.setTextSize(fontSize * 10);
            }
        }
    }

    public void openReminder(View view) {
        Intent intent = new Intent(MainActivity.this, ReminderActivity.class);
        startActivity(intent);
    }

    public void openNotes(View view) {
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        startActivity(intent);
    }

    public void openGoHome(View view) {
        Intent intent = new Intent(MainActivity.this, GoHomeActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openQuiz(View view) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(intent);
    }

    public void numberMemoryGame(View view) {
        Intent intent = new Intent(MainActivity.this, NumberMemoryGameActivity.class);
        startActivity(intent);
    }
}