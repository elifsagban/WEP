package com.elif.wep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GoalRecyclerViewAdapter extends RecyclerView.Adapter<GoalRecyclerViewAdapter.GoalViewHolder>{

    ArrayList<Goal> goals;


    public GoalRecyclerViewAdapter(ArrayList<Goal> goals) {

        this.goals = goals;

    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_view_row, parent, false);
        GoalViewHolder goalViewHolder = new GoalViewHolder(view);

        return goalViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {

        Goal goal = goals.get(position);
        holder.goal.setText(goal.getGoalName());
    }

    @Override
    public int getItemCount() {
        return this.goals.size();
    }

    public class GoalViewHolder  extends RecyclerView.ViewHolder{

        TextView goal;
        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            goal = itemView.findViewById(R.id.goalItem);

        }
    }
}
