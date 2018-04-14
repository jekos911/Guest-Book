package com.jekos.guestbook.recyclerutils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jekos.guestbook.R;
import com.jekos.guestbook.models.Note;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by жекос on 12.04.2018.
 */

public class NotesAdapter extends RecyclerView.Adapter<NoteVH> {

    private LinkedList<Note> notes;

    public NotesAdapter(LinkedList<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void onBindViewHolder(NoteVH holder, int position) {
        Note note = notes.get(position);
        holder.firstName.setText(note.getFirstName());
        holder.secondName.setText(note.getLastName());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public NoteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_book_item,parent,false);
        NoteVH vh = new NoteVH(v);
        return vh;
    }

    public void addNote(Note note) {
        notes.add(0,note);
        notifyDataSetChanged();
    }
}
