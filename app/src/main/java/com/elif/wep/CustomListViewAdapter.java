package com.elif.wep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomListViewAdapter extends ArrayAdapter<Task> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<Task> tasks;


    public CustomListViewAdapter(Context context, ArrayList<Task> tasks) {
        super(context,0, tasks);
        this.context = context;
        this.tasks = tasks;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasks.get(position).hashCode();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.list_view_item, null);

            holder = new ViewHolder();
          //  holder.taskListName =  (TextView) convertView.findViewById(R.id.taskListName);
            holder.addButton = (Button) convertView.findViewById(R.id.addTaskList);

            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();
        }

        Task task = tasks.get(position);
        if(task != null){
            holder.taskListName.setText(task.getTitle());
            holder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,  "working", Toast.LENGTH_LONG).show();

                }
            });


        }
        return convertView;
    }


    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView taskListName;
        Button addButton;


    }
}
