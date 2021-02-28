package com.elif.wep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.item.setText(taskChild.getTaskItem());

    }

    @Override
    public int getItemCount() {
        return this.taskItems.size();
    }


    public static class childViewHolder extends RecyclerView.ViewHolder{

        TextView item;

        public childViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.taskItem);
        }
    }

    public void addExercise( TaskChild taskItem) {
        controller.getListOfItems().add(taskItem);
        notifyItemInserted(getItemCount() - 1 );



    }
}
