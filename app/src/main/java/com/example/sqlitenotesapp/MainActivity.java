package com.example.sqlitenotesapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
FloatingActionButton floatingActionButton;
MyDatabaseHelper myDB;
ArrayList<String> book_id,book_title,book_author,book_pages;
myAdaptor myAdaptor;
ImageView emptyImageView;
TextView NoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
recyclerView=findViewById(R.id.recyclerView);
floatingActionButton=findViewById(R.id.floatingActionButton);
emptyImageView=findViewById(R.id.imageView);
NoData=findViewById(R.id.textView);
floatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,AddActivity.class));
    }
     });
myDB=new MyDatabaseHelper(MainActivity.this);
book_id=new ArrayList<>();
book_title=new ArrayList<>();
book_author=new ArrayList<>();
book_pages=new ArrayList<>();
 displayData();
 myAdaptor=new myAdaptor(MainActivity.this,this,book_id,book_title,book_author,book_pages);
 recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
 recyclerView.setAdapter(myAdaptor);
    }
    void displayData(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            emptyImageView.setVisibility(View.VISIBLE);
            NoData.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "No Data Exists", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));

            }
            emptyImageView.setVisibility(View.GONE);
            NoData.setVisibility(View.GONE);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.delete_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.delete){
         confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete Data?");
        builder.setMessage("Are you sure you want to delete this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
               myDatabaseHelper.deleteAllData();
               startActivity(new Intent(MainActivity.this,MainActivity.class));
                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }
}