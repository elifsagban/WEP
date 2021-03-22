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

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.childViewHolder> {


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
        holder.item.setText(taskChild.getTaskItem());

        // edit data on button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAlertDialog(holder, position);
            }
        });
        // edit on click
        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editAlertDialog(holder, position);
                return true;
            }
        });


        // delete
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlertDialog(holder, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.taskItems.size();
    }


    public static class childViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        Button editButton;
        CheckBox checkBox;

        public childViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.taskItem);
            editButton = itemView.findViewById(R.id.editButton);
            checkBox = itemView.findViewById(R.id.checkTask);
        }
    }

    public void addTaskItem(TaskChild taskItem) {
        controller.getListOfItems().add(taskItem);
        notifyItemInserted(getItemCount() - 1);

    }

    public void removeTaskItem(int position) {
        taskItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, taskItems.size());
    }

    public void editTaskItem(int position, String taskName) {
        taskItems.get(position).setTaskItem(taskName);
        notifyDataSetChanged();
    }

    public void editAlertDialog(childViewHolder holder, int position) {
        AlertDialog.Builder editTaskItem = new AlertDialog.Builder(holder.itemView.getRootView().getContext());
        editTaskItem.setTitle("Change Task");

        final EditText taskInput = new EditText(holder.itemView.getRootView().getContext());

        taskInput.setInputType(InputType.TYPE_CLASS_TEXT);
        editTaskItem.setView(taskInput);

        editTaskItem.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String taskNameUser = taskInput.getText().toString();
                if (taskNameUser.length() > 0 && !taskNameUser.isEmpty()) {
                    editTaskItem(position, taskNameUser);

                } else {
                    Toast.makeText(holder.itemView.getRootView().getContext(), " you can't add empty task.", Toast.LENGTH_LONG).show();
                    editAlertDialog(holder, position);
                }

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


    public void deleteAlertDialog(childViewHolder holder, int position) {
        AlertDialog.Builder checkTaskDialog = new AlertDialog.Builder(holder.itemView.getRootView().getContext());
        checkTaskDialog.setTitle("Delete Task");
        checkTaskDialog.setCancelable(false);
        checkTaskDialog.setMessage("Do you want to delete it?");
        checkTaskDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                removeTaskItem(position);

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
}
