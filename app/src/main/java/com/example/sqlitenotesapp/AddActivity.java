package com.example.sqlitenotesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
  EditText title,author,pages;
  Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title=findViewById(R.id.edit_title);
        author=findViewById(R.id.edit_author);
        pages=findViewById(R.id.edit_number_of_page);
        add=findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(AddActivity.this);
                myDatabaseHelper.addBook(title.getText().toString().toString(),author.getText().toString()
                        ,Integer.valueOf(pages.getText().toString().trim()));

            }
        });

    }
}