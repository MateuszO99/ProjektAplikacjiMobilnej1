package pl.edu.uwr.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NotesDataBaseHelper extends SQLiteOpenHelper {
    public static final String NOTES_TABLE = "NOTES_TABLE";
    public static final String COLUMN_NOTES_ID = "COLUMN_NOTES_ID";
    public static final String COLUMN_NOTES_TITLE = "COLUMN_NOTES_TITLE";
    public static final String COLUMN_NOTES_DESCRIPTION = "COLUMN_NOTES_DESCRIPTION";
    public static final String COLUMN_NOTES_CREATE_DATE = "COLUMN_NOTES_CREATE_DATE";

    public NotesDataBaseHelper(@Nullable Context context) {
        super(context, "notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + NOTES_TABLE + " (" + COLUMN_NOTES_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTES_TITLE + " TEXT, " +
                COLUMN_NOTES_DESCRIPTION + " TEXT, " + COLUMN_NOTES_CREATE_DATE + " TEXT " + ");";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNote(NotesModel notesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOTES_TITLE, notesModel.getTitle());
        cv.put(COLUMN_NOTES_DESCRIPTION, notesModel.getDescription());
        cv.put(COLUMN_NOTES_CREATE_DATE, notesModel.getCreateDate());

        db.insert(NOTES_TABLE, null, cv);
    }

    public void editNote(NotesModel notesModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOTES_TITLE, notesModel.getTitle());
        cv.put(COLUMN_NOTES_DESCRIPTION, notesModel.getDescription());
        cv.put(COLUMN_NOTES_CREATE_DATE, notesModel.getCreateDate());

        db.update(NOTES_TABLE, cv, COLUMN_NOTES_ID + "=" + notesModel.getId(), null);
    }

    public void deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTES_TABLE, COLUMN_NOTES_ID + " = " + id, null);
    }

    public ArrayList<NotesModel> getAllNotes(){
        ArrayList<NotesModel> notes = new ArrayList<>();

        String query = "SELECT * FROM " + NOTES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                int noteID = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteDescription = cursor.getString(2);
                String noteDate = cursor.getString(3);

                NotesModel newNote = new NotesModel(noteID, noteTitle, noteDescription, noteDate);
                notes.add(newNote);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return notes;
    }
}
