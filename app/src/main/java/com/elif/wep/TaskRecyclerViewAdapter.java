package com.elif.wep;

import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    ArrayList<Task> tasks;


    public TaskRecyclerViewAdapter(ArrayList<Task> tasks) {

        this.tasks = tasks;


    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view_row, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);

        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task task = tasks.get(position);
        holder.title.setText(task.getTitle());
        holder.list.setText(task.getTaskList());


    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder {


        TextView title;
        TextView list;
        private String item;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.taskTitle);
            list = itemView.findViewById(R.id.taskItems);

            itemView.findViewById(R.id.addItemBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder taskNameDialog = new AlertDialog.Builder(itemView.getRootView().getContext());
                    taskNameDialog.setTitle("Enter Item Name");

                    final EditText itemName = new EditText(itemView.getRootView().getContext());

                    itemName.setInputType(InputType.TYPE_CLASS_TEXT);
                    taskNameDialog.setView(itemName);

                    taskNameDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            item = itemName.getText().toString();
                            Toast.makeText(itemView.getRootView().getContext(), item + " is added", Toast.LENGTH_LONG).show();


                        }
                    });

                    taskNameDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    taskNameDialog.show();
                }


            });

        }

    }
}
