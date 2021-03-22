package com.elif.wep;

import android.content.DialogInterface;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    private ArrayList<Task> tasks;
    private ArrayList<TaskChild> subItemList = new ArrayList<>();

    private ChildRecyclerViewAdapter adapter;

    public TaskRecyclerViewAdapter(ArrayList<Task> tasks) {

        this.tasks = tasks;
        adapter = new ChildRecyclerViewAdapter(subItemList);
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

        // Create sub item view adapter
        ChildRecyclerViewAdapter subItemAdapter = new ChildRecyclerViewAdapter(task.getChildArrayList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.childRecyclerView.getContext());
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setAdapter(subItemAdapter);


        holder.addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create LinearLayout Dynamically
                LinearLayout layout = new LinearLayout(view.getContext());

                //Setup Layout Attributes
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setLayoutParams(params);
                layout.setOrientation(LinearLayout.VERTICAL);

                // task name

                final EditText taskName = new EditText(holder.itemView.getRootView().getContext());
                taskName.setInputType(InputType.TYPE_CLASS_TEXT);


                // priority select
                Spinner spinner = new Spinner(view.getContext());
                String[] priority_list = new String[]{"Low", "Medium", "High"};
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(holder.itemView.getRootView().getContext(), android.R.layout.simple_selectable_list_item, priority_list);
                spinner.setAdapter(spinnerAdapter);
                spinner.setGravity(Gravity.CENTER);


                layout.addView(taskName);
                layout.addView(spinner);


                AlertDialog.Builder taskNameDialog = new AlertDialog.Builder(holder.itemView.getRootView().getContext());
                taskNameDialog.setTitle("Enter Item Name");


                taskNameDialog.setView(layout);

                taskNameDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String taskNameUser = taskName.getText().toString();
                        String priority = spinner.getSelectedItem().toString();
                        TaskChild taskChild = new TaskChild(taskNameUser, priority);

                        task.addTaskChild(taskNameUser, priority);
                        adapter.addTaskItem(taskChild);

                        Toast.makeText(holder.itemView.getRootView().getContext(), taskChild.getTaskItem(), Toast.LENGTH_LONG).show();

                        notifyDataSetChanged();


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


        // delete
        holder.removeListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder checkTaskDialog = new AlertDialog.Builder(holder.itemView.getRootView().getContext());
                checkTaskDialog.setTitle("Delete Item");
                checkTaskDialog.setCancelable(false);
                checkTaskDialog.setMessage("Do you want to delete it?");
                checkTaskDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeList(position);

                    }
                });

                checkTaskDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                checkTaskDialog.show();
            }


        });


    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder {


        private TextView title;
        private RecyclerView childRecyclerView;
        private FloatingActionButton addTaskButton;
        private FloatingActionButton removeListButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.taskTitle);
            childRecyclerView = itemView.findViewById(R.id.Child_RV);
            addTaskButton = itemView.findViewById(R.id.addItemBtn);
            removeListButton = itemView.findViewById(R.id.deleteList);

        }

    }


    public void removeList(int position) {
        tasks.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, tasks.size());
    }


}
