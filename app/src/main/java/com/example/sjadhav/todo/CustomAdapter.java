package com.example.sjadhav.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.TaskHolder>
{
    private Activity mainActivity;
    ArrayList<Task> taskList=new ArrayList<Task>();

    public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView taskName;
        TextView description;
        ImageView img;
        public TaskHolder(View itemView) {
            super(itemView);
            taskName=(TextView) itemView.findViewById(R.id.task_item_name);
            description=(TextView)itemView.findViewById(R.id.task_descript_id);
            img=(ImageView)itemView.findViewById(R.id.imageButton);
        }
        public TextView getTaskNameTextView(){
            return taskName;
        }
        public TextView getTasDescTextView(){
            return description;
        }

        @Override
        public void onClick(View v) {
            CustomAdapter.this.clickedItemAt(getAdapterPosition());
        }
    }

    private void clickedItemAt(int position) {
        Intent intent=new Intent(mainActivity,TaskDetails.class);
        intent.putExtra("Task Name",taskList.get(position).taskName);
        intent.putExtra("Description",taskList.get(position).taskDescription);
        mainActivity.startActivity(intent);
    }

    public CustomAdapter(Activity mainActivity, ArrayList<Task> taskArrayList) {
        super();
        this.mainActivity = mainActivity;
        taskList=taskArrayList;

    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new TaskHolder(v) ;
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, final int position) {
        holder.getTaskNameTextView().setText(taskList.get(position).taskName);
        holder.getTasDescTextView().setText(taskList.get(position).taskDescription);
        holder.img.setTag("" + position);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.remove(position);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    }
