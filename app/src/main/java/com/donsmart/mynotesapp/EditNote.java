package com.donsmart.mynotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {

    EditText etTitle,etNotes;
    NotesDB db;
    myNotes notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        etTitle = findViewById(R.id.etTitle);
        etNotes = findViewById(R.id.etNotes);

        Intent intent = getIntent();
        long id = intent.getLongExtra("ID",1);

        db = new NotesDB(getApplicationContext());
        notes = db.getNote(id);

        getSupportActionBar().setTitle(notes.getTitle());

        etTitle.setText(notes.getTitle());
        etNotes.setText(notes.getThe_notes());

        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()!=0)
                {
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

        getMenuInflater().inflate(R.menu.main4,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.save:

                if (etTitle.getText().toString().length() != 0)
                {
                    Toast.makeText(this, "save clicked", Toast.LENGTH_SHORT).show();
                    notes.setTitle(etTitle.getText().toString());
                    notes.setThe_notes(etNotes.getText().toString());
                    int id = db.editNote(notes);
                    if (id == notes.getID())
                    {
                        Toast.makeText(this, "Notes successfully updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "Could not update.Try again", Toast.LENGTH_SHORT).show();
                    }
                    goBack();
                    break;
                }
                else {
                    etTitle.setError("Title cannot be blank");
                    Toast.makeText(this, "Title cannot be blank", Toast.LENGTH_SHORT).show();
                }

            case R.id.clear:
                goBack();
                Toast.makeText(this, "No changes made", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    private void goBack() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }


}