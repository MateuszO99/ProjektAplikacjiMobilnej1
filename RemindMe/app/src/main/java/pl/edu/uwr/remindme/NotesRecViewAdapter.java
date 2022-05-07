package pl.edu.uwr.remindme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesRecViewAdapter extends RecyclerView.Adapter<NotesRecViewAdapter.NoteViewHolder> {
    public static final String MESSAGE = "pl.edu.uwr.remindme.MESSAGE";

    private ArrayList<NotesModel> notes;
    private Context context;
    NotesDataBaseHelper notesDataBaseHelper;

    public NotesRecViewAdapter(Context context) {
        this.context = context;
    }

    public void setToDos() {
        notesDataBaseHelper = new NotesDataBaseHelper(context);
        this.notes = notesDataBaseHelper.getAllNotes();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
