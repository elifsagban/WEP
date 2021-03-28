package com.elif.wep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class goalAdapter extends FirebaseRecyclerAdapter<Goal, goalAdapter.goalsViewholder> {

    public goalAdapter(@NonNull FirebaseRecyclerOptions<Goal> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull goalsViewholder holder, int position, @NonNull Goal model) {
        holder.goal.setText(model.getGoalName());

    }

    @NonNull
    @Override
    public goalsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_view_row, parent, false);
        return new goalAdapter.goalsViewholder(view);

    }


    public static class goalsViewholder extends RecyclerView.ViewHolder {

        TextView goal;

        public goalsViewholder(@NonNull View itemView) {
            super(itemView);
            goal = itemView.findViewById(R.id.goalItem);
        }
    }
}