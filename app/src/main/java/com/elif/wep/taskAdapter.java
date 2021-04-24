package com.elif.wep;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class taskAdapter extends FirebaseRecyclerAdapter<TaskItem, taskAdapter.taskViewHolder> {

    FirebaseAuth fAuth;
    DatabaseReference db;
    String userID;

    public taskAdapter(@NonNull FirebaseRecyclerOptions<TaskItem> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull taskViewHolder holder, int position, @NonNull TaskItem model) {

        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());

        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);

        String id = model.getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.itemView.getContext(), TaskTimer.class);
                intent.putExtra("task", model);
                holder.itemView.getContext().startActivity(intent);

            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder checkTaskDialog = new AlertDialog.Builder(holder.itemView.getRootView().getContext());
                checkTaskDialog.setTitle("Delete");
                checkTaskDialog.setCancelable(false);
                checkTaskDialog.setMessage("Do you want to delete it?");
                checkTaskDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.child(id).removeValue();


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

    @NonNull
    @Override
    public taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view_row, parent, false);
        return new taskViewHolder(view);
    }

    public static class taskViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final ImageButton deleteButton;


        public taskViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.taskTitle);
            description = itemView.findViewById(R.id.description);
            deleteButton = itemView.findViewById(R.id.deleteTask);

        }
    }
}
