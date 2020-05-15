package com.example.day_schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAddNew;
    DatabaseReference reference;
    RecyclerView ourtodos;
    ArrayList<MyToDo> list;
    ToDoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivity(a);
            }
        });

        // working with data
        ourtodos = findViewById(R.id.ourtodos);
        ourtodos.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyToDo>();

        // get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Scheduler");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // retrieve data and replace layout
                for (DataSnapshot dataSnap: dataSnapshot.getChildren()) {
                    MyToDo todoData = dataSnap.getValue(MyToDo.class);
                    list.add(todoData);
                }
                todoAdapter = new ToDoAdapter(MainActivity.this, list);
                ourtodos.setAdapter(todoAdapter);
                todoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // show an error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
