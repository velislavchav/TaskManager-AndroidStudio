package com.example.day_schedule;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {
    Context context;
    ArrayList<Task> tasks;

    public  ToDoAdapter(Context c, ArrayList<Task> p) {
        context = c;
        tasks = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final String getTaskTitle = tasks.get(position).getTitle();
        final String getTaskDescription = tasks.get(position).getDescription();
        final String getTaskDate = tasks.get(position).getDate();
        final String getTaskKey = tasks.get(position).getKey();

        myViewHolder.title.setText(getTaskTitle);
        myViewHolder.description.setText(getTaskDescription);
        myViewHolder.date.setText(getTaskDate);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {  // for opening editing task
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context, EditTaskBoard.class);
                aa.putExtra("title", getTaskTitle);
                aa.putExtra("description", getTaskDescription);
                aa.putExtra("date", getTaskDate);
                aa.putExtra("key", getTaskKey);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById((R.id.toDoTitle));
            description = (TextView) itemView.findViewById((R.id.toDoDescription));
            date = (TextView) itemView.findViewById((R.id.toDoDate));
        }
    }
}
