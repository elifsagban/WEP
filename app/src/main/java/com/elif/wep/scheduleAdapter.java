package com.elif.wep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class scheduleAdapter extends FirebaseRecyclerAdapter<TaskItem, scheduleAdapter.taskViewHolder> {


    public scheduleAdapter(@NonNull FirebaseRecyclerOptions<TaskItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull scheduleAdapter.taskViewHolder holder, int position, @NonNull TaskItem model) {
        holder.title.setText(model.getTitle());

    }

    @NonNull
    @Override
    public scheduleAdapter.taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.done_task_view_row, parent, false);
        return new scheduleAdapter.taskViewHolder(view);
    }

    public class taskViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;


        public taskViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.taskTitle);
        }
    }
}
