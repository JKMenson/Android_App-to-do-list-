package com.example.mobilecomputing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewTaskActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<TaskModelClass> taskModelClassList = databaseHelper.getTaskList();

        if (taskModelClassList.size() > 0) {
            TaskAdapterClass taskAdapterClass = new TaskAdapterClass(taskModelClassList, ViewTaskActivity.this);
            recyclerView.setAdapter(taskAdapterClass); // Set the adapter for the RecyclerView
        } else {
            Toast.makeText(this, "There are no tasks in the database,please add task!", Toast.LENGTH_SHORT).show();
        }
    }
}
