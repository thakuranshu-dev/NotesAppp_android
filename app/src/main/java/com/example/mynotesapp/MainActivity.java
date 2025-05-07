package com.example.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingAdd;
    RecyclerView recyclerView;
    NotesDB_helper noteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().setTitle("Notes");

        floatingAdd = findViewById(R.id.addMore);
        floatingAdd.setOnClickListener(view-> startActivity(new Intent(MainActivity.this, AddNotes.class)));

        recyclerView = findViewById(R.id.recyclerNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<NotesModel> arrayNotes = new ArrayList<>();

        noteHelper = new NotesDB_helper(getApplicationContext());
        var cursor = noteHelper.fetchAll();

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                arrayNotes.add(new NotesModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
//                cursor.moveToNext();
            }
        }

        RecyclerNotesAdapter notesAdapter = new RecyclerNotesAdapter(this, arrayNotes);
        recyclerView.setAdapter(notesAdapter);
    }
}
