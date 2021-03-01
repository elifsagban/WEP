package com.elif.wep;

import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.childViewHolder>{


    ArrayList<TaskChild> taskItems;
    private SubItemController controller;

    public ChildRecyclerViewAdapter(ArrayList<TaskChild> taskItems) {
        this.taskItems = taskItems;
        this.controller = SubItemController.getController();

    }

    @NonNull
    @Override
    public childViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
        childViewHolder childViewHolder = new childViewHolder(view);

        return childViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull childViewHolder holder, int position) {
        TaskChild taskChild = taskItems.get(position);
        holder.item.setText(position+1 + ". - " +taskChild.getTaskItem());

       // edit data
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder editTaskItem = new AlertDialog.Builder(holder.itemView.getRootView().getContext());
                editTaskItem.setTitle("Change Task");

               final EditText taskInput = new EditText(holder.itemView.getRootView().getContext());

                taskInput.setInputType(InputType.TYPE_CLASS_TEXT);
                editTaskItem.setView(taskInput);

                editTaskItem.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String taskNameUser = taskInput.getText().toString();
                        Toast.makeText(holder.itemView.getRootView().getContext(), taskNameUser + " is changed", Toast.LENGTH_LONG).show();
                        taskChild.setTaskItem(taskNameUser);
                        holder.item.setText(position+1 + ". - " +taskChild.getTaskItem());
                        notifyDataSetChanged();

                    }
                });

                editTaskItem.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                editTaskItem.show();
            }


        });


    // delete
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder checkTaskDialog = new AlertDialog.Builder(holder.itemView.getRootView().getContext());
                checkTaskDialog.setTitle("Delete Task");
                checkTaskDialog.setCancelable(false);
                checkTaskDialog.setMessage("Do you want to delete it?");
                checkTaskDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeExercise(position);

                    }
                });

                checkTaskDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        holder.checkBox.setChecked(false);
                    }
                });

                checkTaskDialog.show();
            }


        });


    }

    @Override
    public int getItemCount() {
        return this.taskItems.size();
    }


    public static class childViewHolder extends RecyclerView.ViewHolder{

        TextView item;
        Button  editButton;
        CheckBox checkBox;

        public childViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.taskItem);
            editButton = itemView.findViewById(R.id.editButton);
            checkBox = itemView.findViewById(R.id.checkTask);
        }
    }

    public void addExercise( TaskChild taskItem) {
        controller.getListOfItems().add(taskItem);
        notifyItemInserted(getItemCount() - 1 );

    }

    public void removeExercise(int position) {
        taskItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, taskItems.size());
    }
}
