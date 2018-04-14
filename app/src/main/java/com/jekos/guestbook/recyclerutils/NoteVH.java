package com.jekos.guestbook.recyclerutils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jekos.guestbook.R;
import com.jekos.guestbook.models.Note;

/**
 * Created by жекос on 12.04.2018.
 */

public class NoteVH extends RecyclerView.ViewHolder {

    protected TextView firstName, secondName;
    protected Note note;

    public NoteVH(View itemView) {
        super(itemView);
        firstName = (TextView) itemView.findViewById(R.id.item_first);
        secondName = (TextView) itemView.findViewById(R.id.item_second);
    }
}
