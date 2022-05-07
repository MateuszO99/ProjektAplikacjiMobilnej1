package pl.edu.uwr.remindme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public void setNotes() {
        notesDataBaseHelper = new NotesDataBaseHelper(context);
        this.notes = notesDataBaseHelper.getAllNotes();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list, parent,
                false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Intent intent = new Intent(context, NoteActivity.class);
            intent.putExtra(MESSAGE, position);
            context.startActivity(intent);
        }
    }
}
