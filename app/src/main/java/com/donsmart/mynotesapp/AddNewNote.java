package com.donsmart.mynotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNote extends AppCompatActivity {

    EditText etTitle, etNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle(" New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etTitle = findViewById(R.id.etTitle);
        etNotes = findViewById(R.id.etNotes);

        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save:
                if (etTitle.getText().toString().length() != 0) {
                    Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
                    myNotes notes = new myNotes(etTitle.getText().toString(), etNotes.getText().toString());
                    NotesDB db = new NotesDB(getApplicationContext());
                    db.addNote(notes);
                    goBack();
                    break;
                } else if (etTitle.getText().toString().length() == 0 && etNotes.getText().toString().length() == 0) {
                    goBack();
                    Toast.makeText(this, "No new note added", Toast.LENGTH_SHORT).show();
                } else {
                    etTitle.setError("Title cannot be blank");
                }


            case android.R.id.home:
                Toast.makeText(this, "No note added", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void goBack() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "No note added", Toast.LENGTH_SHORT).show();
    }
}