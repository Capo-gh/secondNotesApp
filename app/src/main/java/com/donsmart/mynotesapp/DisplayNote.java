package com.donsmart.mynotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayNote extends AppCompatActivity {

    TextView tvDetails;
    NotesDB db;
    myNotes note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvDetails = findViewById(R.id.tvDetails);

        Intent intent = getIntent();
        long id = intent.getLongExtra("ID",0);

        db = new NotesDB(this);
        note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        tvDetails.setText(note.getThe_notes());

        tvDetails.setMovementMethod(new ScrollingMovementMethod());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main3,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.edit)
        {
           //Another activity to edit
            Intent intent = new Intent(getApplicationContext(), EditNote.class);
            intent.putExtra("ID",note.getID());
            startActivity(intent);
        }
        if (item.getItemId()==R.id.del)
        {
            db.deleteNote(note.getID());
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }




}