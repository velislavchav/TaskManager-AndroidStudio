package com.example.day_schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class EditTaskBoard extends AppCompatActivity {
    EditText toDoTitle, toDoDescription;
    Button btnUpdateTask, btnDeleteTask, btnCancel;
    DatabaseReference reference;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_board);

        toDoTitle = findViewById(R.id.titletodo);
        toDoDescription = findViewById(R.id.descrtodo);
        mDisplayDate = findViewById(R.id.selectdate);
        btnUpdateTask = findViewById(R.id.btnUpdateTask);
        btnDeleteTask = findViewById(R.id.btnDeleteTask);
        btnCancel = findViewById(R.id.btnCancelUpdate);

        // get the value from previous state
        toDoTitle.setText(getIntent().getStringExtra("title"));
        toDoDescription.setText(getIntent().getStringExtra("description"));
        mDisplayDate.setText(getIntent().getStringExtra("date"));

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog dialog = new DatePickerDialog(
                            EditTaskBoard.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateSetListener,
                            day, month, year);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        // set event listeners for buttons
        final String toDoKey = getIntent().getStringExtra("key");
        reference = FirebaseDatabase.getInstance().getReference().child("TasksBox").child("Task" + toDoKey);

        btnUpdateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference.child("title").setValue(toDoTitle.getText().toString());
                        reference.child("description").setValue(toDoDescription.getText().toString());
                        reference.child("date").setValue(mDisplayDate.getText().toString());

                        Intent a = new Intent(EditTaskBoard.this, MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                }
            });

        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful()) {
                              Toast.makeText(getApplicationContext(), "Successfully deleted task", Toast.LENGTH_SHORT).show();
                              Intent a = new Intent(EditTaskBoard.this, MainActivity.class);
                              startActivity(a);
                          } else {
                              Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                          }
                     }
                 });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditTaskBoard.this, MainActivity.class);
                startActivity(a);
            }
        });
    }
}
