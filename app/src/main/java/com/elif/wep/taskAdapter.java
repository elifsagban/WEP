package com.elif.wep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class taskAdapter extends FirebaseRecyclerAdapter<TaskItem, taskAdapter.taskViewHolder> {

    public taskAdapter(@NonNull FirebaseRecyclerOptions<TaskItem> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull taskViewHolder holder, int position, @NonNull TaskItem model) {

        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());


    }

    @NonNull
    @Override
    public taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view_row, parent, false);
        return new taskAdapter.taskViewHolder(view);
    }

    public class taskViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;



        public taskViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.taskTitle);
            description = itemView.findViewById(R.id.description);

        }
    }
}
