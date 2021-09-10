package com.example.madlabproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, date, time;
    Button add, update, delete, view;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        add = findViewById(R.id.btnadd);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btndelete);
        view = findViewById(R.id.btnview);
        DB = new DBHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dateTXT = date.getText().toString();
                String timeTXT = time.getText().toString();
                Boolean checkadddata = DB.addMedicine(nameTXT, dateTXT, timeTXT);
                if (checkadddata == true)
                    Toast.makeText(MainActivity.this, "New Entry Added", Toast.LENGTH_SHORT);
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Added", Toast.LENGTH_SHORT);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dateTXT = date.getText().toString();
                String timeTXT = time.getText().toString();
                Boolean checkupdatedata = DB.updateMedicine(nameTXT, dateTXT, timeTXT);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, " Entry updated", Toast.LENGTH_SHORT);
                else
                    Toast.makeText(MainActivity.this, "Entry Not updated", Toast.LENGTH_SHORT);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = DB.deleteMedicine(nameTXT);
                if (checkdeletedata == true)
                    Toast.makeText(MainActivity.this, " Entry deleted", Toast.LENGTH_SHORT);
                else
                    Toast.makeText(MainActivity.this, "Entry Not deleted", Toast.LENGTH_SHORT);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getMedicine();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Date :" + res.getString(1) + "\n");
                    buffer.append("Time :" + res.getString(2) + "\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Medicine Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
    }
}