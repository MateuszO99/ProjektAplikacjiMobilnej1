package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {
    public static final String MESSAGE_POSITION = "pl.edu.uwr.remindme.MESSAGE_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }
}