package com.example.mynotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerNotesAdapter extends RecyclerView.Adapter<RecyclerNotesAdapter.ViewHolder>{
    Context context;
    ArrayList<NotesModel> arrayNotes;
    public RecyclerNotesAdapter(Context context, ArrayList<NotesModel> arrayNotes){
        this.context = context;
        this.arrayNotes = arrayNotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_row,parent,false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.noteCount.setText(String.valueOf(arrayNotes.get(position).count));
        holder.noteTitle.setText(arrayNotes.get(position).title);
        holder.noteDescription.setText(arrayNotes.get(position).description);
    }

    @Override
    public int getItemCount() {
        return arrayNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteCount, noteTitle, noteDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteCount = itemView.findViewById(R.id.noteCount);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteDescription = itemView.findViewById(R.id.noteDescription);
        }
    }
}
