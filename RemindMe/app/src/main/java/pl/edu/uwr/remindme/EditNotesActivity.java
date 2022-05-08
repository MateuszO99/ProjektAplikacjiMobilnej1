package pl.edu.uwr.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class EditNotesActivity extends AppCompatActivity {
    private EditText editTitle, editDescription, editCreateDate;
    private NotesDataBaseHelper notesDataBaseHelper;

    private int id;

    private final Calendar calender = Calendar.getInstance();
    private int year = calender.get(Calendar.YEAR);
    private int month = calender.get(Calendar.MONTH);
    private int day = calender.get(Calendar.DAY_OF_MONTH);

    private NotesModel note;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        notesDataBaseHelper = new NotesDataBaseHelper(this);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        editCreateDate = findViewById(R.id.editCreateDate);

        Button saveBtn = findViewById(R.id.saveBtn);
        Button cancelBtn = findViewById(R.id.cancelBtn);

        Intent intent = getIntent();
        id = intent.getIntExtra(NoteActivity.MESSAGE_POSITION, -1);

        if (id != -1){
            note = notesDataBaseHelper.getAllNotes().get(id);
            editTitle.setText(note.getTitle());
            editDescription.setText(note.getDescription());
            editCreateDate.setText(note.getCreateDate());
        }

        editCreateDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(EditNotesActivity.this,
                    android.R.style.Theme_Holo_Dialog_MinWidth, onDateSetListener, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        onDateSetListener = (view, year, month, dayOfMonth) -> {
            month++;
            String date = dayOfMonth + "/" + month + "/" + year;
            editCreateDate.setText(date);
        };

        cancelBtn.setOnClickListener(v -> {
            this.finish();
        });

        saveBtn.setOnClickListener(v -> {
            String title = editTitle.getText().toString();
            String description = editDescription.getText().toString();
            String data = editCreateDate.getText().toString();

            if (title.equals("") || description.equals("") || data.equals("")){
                Toast.makeText(EditNotesActivity.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                if (id != -1){
                    notesDataBaseHelper.editNote(new NotesModel(note.getId(), title, description, data));
                } else {
                    notesDataBaseHelper.addNote(new NotesModel(title, description, data));
                }
                Intent intent1 = new Intent(EditNotesActivity.this, NotesActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });
    }
}