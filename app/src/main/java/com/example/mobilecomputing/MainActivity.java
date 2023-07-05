package com.example.mobilecomputing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText title, description;
    Button btninsert, btnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.txtTitle);
        description = findViewById(R.id.txtDescription);
        btninsert = findViewById(R.id.btnInsert);
        btnview = findViewById(R.id.btnView);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringTitle = title.getText().toString();
                String stringDescription = description.getText().toString();

                if (stringTitle.length() <= 0 || stringDescription.length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please fill up all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                    TaskModelClass taskModelClass = new TaskModelClass(null, stringTitle, stringDescription);
                    databaseHelper.addTask(taskModelClass);
                    Toast.makeText(MainActivity.this, "Task has been added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewTaskActivity.class);
                startActivity(intent);
            }
        });

    }
}
