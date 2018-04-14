package com.jekos.guestbook;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        String fName = firstName.getText().toString();
        String sName = lastName.getText().toString();

        if (fName.isEmpty() | sName.isEmpty()) {
            Toast.makeText(this,getString(R.string.notif_button),Toast.LENGTH_LONG).show();
            return;
        }

        hideSoftKeyboard(this);
        Note note = dbHelper.insertRow(firstName.getText().toString(),lastName.getText().toString());
        notesAdapter.addNote(note);
        firstName.setText(null);
        firstName.requestFocus();
        lastName.setText(null);

    }
}
