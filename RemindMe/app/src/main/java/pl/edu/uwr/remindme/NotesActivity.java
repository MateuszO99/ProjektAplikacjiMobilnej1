package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        NotesDataBaseHelper notesDataBaseHelper = new NotesDataBaseHelper(this);
        ArrayList<NotesModel> notes = notesDataBaseHelper.getAllNotes();

        RecyclerView recView = findViewById(R.id.recView);

        NotesRecViewAdapter adapter = new NotesRecViewAdapter(this);
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setNotes();

        if (notes.isEmpty()){
            Intent intent = new Intent(NotesActivity.this, EditNotesActivity.class);
            startActivity(intent);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        int fontSize = sharedPreferences.getInt("FONT_SIZE", 2);

        ViewGroup layout = findViewById(R.id.activity_notes_layout);
        Common.changeFontSize(NotesActivity.this, layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    public void add_todo(MenuItem item) {
        Intent intent = new Intent(NotesActivity.this, EditNotesActivity.class);
        startActivity(intent);
    }
}