package com.jekos.guestbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jekos.guestbook.db.DBHelper;
import com.jekos.guestbook.models.Note;
import com.jekos.guestbook.recyclerutils.NotesAdapter;

public class GuestBookMainACtivity extends AppCompatActivity implements View.OnClickListener{

    Button addButton;
    EditText firstName, lastName;
    RecyclerView guestList;
    DBHelper dbHelper;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_book_main_activity);

        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        addButton = (Button) findViewById(R.id.button_add);
        addButton.setOnClickListener(this);
        guestList = (RecyclerView) findViewById(R.id.guest_list);
        dbHelper = new DBHelper(this);
        guestList.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(dbHelper.getNotesList());
        guestList.setAdapter(notesAdapter);

    }

    @Override
    public void onClick(View v) {
        dbHelper.insertRow(firstName.getText().toString(),lastName.getText().toString());
        notesAdapter.updateUI(dbHelper.getNotesList());
    }
}
