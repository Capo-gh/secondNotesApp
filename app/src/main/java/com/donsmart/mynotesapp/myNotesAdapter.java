package com.donsmart.mynotesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myNotesAdapter extends RecyclerView.Adapter<myNotesAdapter.ViewHolder>
{
    private List<myNotes> notes;
    LayoutInflater inflater;
    NotesDB db;

    public myNotesAdapter(Context context, List<myNotes> list_notes)
    {
        this.inflater = LayoutInflater.from(context);
        this.notes = list_notes;
        //activity = (ItemClicked) context;

    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle,tvNotes;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvNotes = itemView.findViewById(R.id.tvNotes);

            tvNotes.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), DisplayNote.class);
                    intent.putExtra("ID",notes.get(getAdapterPosition()).getID());
                    v.getContext().startActivity(intent);
                }
            });

        }
    }


    @NonNull
    @Override
    public myNotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.from(parent.getContext()).inflate(R.layout.note_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myNotesAdapter.ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String the_notes = notes.get(position).getThe_notes();

        holder.tvTitle.setText(title);
        holder.tvNotes.setText(the_notes);


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
