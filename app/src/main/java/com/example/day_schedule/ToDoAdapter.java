package com.example.day_schedule;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyToDo> myToDo;

    public  ToDoAdapter(Context c, ArrayList<MyToDo> p) {
        context = c;
        myToDo = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.title.setText(myToDo.get(position).getTitle());
        myViewHolder.description.setText(myToDo.get(position).getDescription());
        myViewHolder.date.setText(myToDo.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return myToDo.size();
    }

    class  MyViewHolder extends  RecyclerView.ViewHolder {
        TextView title, description, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById((R.id.toDoTitle));
            description = (TextView) itemView.findViewById((R.id.toDoDescription));
            date = (TextView) itemView.findViewById((R.id.toDoDate));
        }
    }
}
