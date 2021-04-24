package com.elif.wep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class statisticsAdapter extends FirebaseRecyclerAdapter<TaskItem, statisticsAdapter.taskViewHolder> {

    public statisticsAdapter(@NonNull FirebaseRecyclerOptions<TaskItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull statisticsAdapter.taskViewHolder holder, int position, @NonNull TaskItem model) {
        holder.statistic1.setText(model.getTitle());
        holder.statistic2.setText(Integer.toString(model.getSeconds()));

    }

    @NonNull
    @Override
    public statisticsAdapter.taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_task, parent, false);
        return new statisticsAdapter.taskViewHolder(view);
    }

    public class taskViewHolder extends RecyclerView.ViewHolder {

        TextView statistic1, statistic2, statistic3, statistic4;

        public taskViewHolder(@NonNull View itemView) {
            super(itemView);

            statistic1 = (TextView) itemView.findViewById(R.id.statistic1);
            statistic2 = (TextView) itemView.findViewById(R.id.statistic2);
            statistic3 = (TextView) itemView.findViewById(R.id.statistic3);
            statistic4 = (TextView) itemView.findViewById(R.id.statistic4);
        }
    }
}
