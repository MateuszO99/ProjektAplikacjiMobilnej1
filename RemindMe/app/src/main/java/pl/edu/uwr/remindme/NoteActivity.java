package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {
    public static final String MESSAGE_POSITION = "pl.edu.uwr.remindme.MESSAGE_POSITION";

    private int id;

    private NotesDataBaseHelper notesDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextView detailId, detailTitle, detailDescription, detailCreateDate;
        Button editBtn, deleteBtn;

        Intent intent = getIntent();
        id = intent.getIntExtra(NotesRecViewAdapter.MESSAGE, -1);

        notesDataBaseHelper = new NotesDataBaseHelper(this);
        NotesModel note = notesDataBaseHelper.getAllNotes().get(id);

        detailTitle = findViewById(R.id.detailTitle);
        detailId = findViewById(R.id.detailId);
        detailDescription = findViewById(R.id.detailDescription);
        detailCreateDate = findViewById(R.id.detailCreateDate);

        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        detailTitle.setText(note.getTitle());
        detailId.setText(String.valueOf(note.getId()));
        detailDescription.setText(note.getDescription());
        detailCreateDate.setText(note.getCreateDate());

        editBtn.setOnClickListener(v -> {
            Intent intent1 = new Intent(NoteActivity.this, EditNotesActivity.class);
            intent1.putExtra(MESSAGE_POSITION, id);
            startActivity(intent1);
        });

        deleteBtn.setOnClickListener(v -> {
            notesDataBaseHelper.deleteNote(note.getId());
            Intent intent1 = new Intent(NoteActivity.this, NotesActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        });
    }
}