package com.example.day_schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewTaskActivity extends AppCompatActivity {
    TextView titlepage, addtitle, adddescription, adddate;
    EditText titletodo, descriptiontodo, datetodo;
    Button btnSaveTask, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titlepage = findViewById(R.id.titlepage);
        addtitle = findViewById(R.id.addtitle);
        adddescription = findViewById(R.id.adddesc);
        adddate = findViewById(R.id.adddate);
        titletodo = findViewById(R.id.titletodo);
        descriptiontodo = findViewById(R.id.descrtodo);
        datetodo = findViewById(R.id.datetodo);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
