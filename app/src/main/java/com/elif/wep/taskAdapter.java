package com.elif.wep;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.google.firebase.database.Query;

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
        String date_time = model.getDate().split(",")[0];
        holder.date.setText(date_time);



        fAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);

        String id = model.getId();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(holder.mView.getContext());
                LayoutInflater layoutInflater = LayoutInflater.from(holder.mView.getContext());

                View dialog_view = layoutInflater.inflate(R.layout.multiple_selection_alert_dialog, null);
                alertDialog.setView(dialog_view);

                AlertDialog dialog = alertDialog.create();

                Button deleteBtn = dialog_view.findViewById(R.id.deleteButton);
                Button studyBtn = dialog_view.findViewById(R.id.studyButton);

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.child(id).removeValue();
                        dialog.dismiss();



                    }
                });

                studyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), TaskTimer.class);
                        intent.putExtra("task", model);
                        holder.itemView.getContext().startActivity(intent);
                        dialog.dismiss();


                    }
                });

                dialog.show();
            }


        });


    }

    @NonNull
    @Override
    public taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_design, parent, false);
        return new taskViewHolder(view);
    }

    public static class taskViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final TextView date;
        private final  View mView;



        public taskViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.taskTitle);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            mView = itemView;

        }
    }


}