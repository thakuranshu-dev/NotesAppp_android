package com.example.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNotes extends AppCompatActivity {
    private EditText editTitle, editDesc;
    private Button addBtn;
    NotesDB_helper noteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        getSupportActionBar().setTitle("Add Notes");
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        addBtn = findViewById(R.id.addNote);
        noteHelper = new NotesDB_helper(getApplicationContext());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        addBtn.setOnClickListener(view -> {
            var title = editTitle.getText().toString();
            var description = editDesc.getText().toString();
            if(TextUtils.isEmpty(title)){
                Toast.makeText(AddNotes.this,"Enter Title", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(description)){
                Toast.makeText(AddNotes.this, "Enter Description", Toast.LENGTH_SHORT).show();
                return;
            }
            if(noteHelper.insertNote(title, description));
            {
                Toast.makeText(getApplicationContext(), "Note created! 😉", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddNotes.this, MainActivity.class);
                startActivity(intent);
                return;
            }
        });
    }
}