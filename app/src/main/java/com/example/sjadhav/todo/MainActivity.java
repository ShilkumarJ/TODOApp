package com.example.sjadhav.todo;

import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        RecyclerView listView=(RecyclerView)findViewById(R.id.recycler_view);
        listView.setLayoutManager(layoutManager);

        ArrayList<Task>taskArrayList=new ArrayList<Task>();
        Task t1=new Task("Dinner","With bajirao");
        Task t2=new Task("Movie","Natsamrat");
        taskArrayList.add(t1);
        taskArrayList.add(t2);

        CustomAdapter adapter=new CustomAdapter(taskArrayList);
        listView.setAdapter(adapter);
     //   RecyclerView.ItemDecoration itemDecoration=new RecyclerView.ItemDecoration() {}
       // listView.addItemDecoration();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.TaskHolder>
{
    ArrayList<Task>taskList=new ArrayList<Task>();

    public static class TaskHolder extends RecyclerView.ViewHolder
    {
        TextView taskName;
        TextView description;
        public TaskHolder(View itemView) {
            super(itemView);
            taskName=(TextView) itemView.findViewById(R.id.task_item_name);
            description=(TextView)itemView.findViewById(R.id.task_descript_id);
        }
        public TextView getTaskNameTextView(){
            return taskName;
        }
        public TextView getTasDescTextView(){
            return description;
        }
    }

    public CustomAdapter(ArrayList<Task> taskArrayList) {
        super();
        for(int i=0;i<taskArrayList.size();i++) {
            taskList.add(taskArrayList.get(i));
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDD"+taskArrayList.get(i));
        }

    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new TaskHolder(v) ;
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.getTaskNameTextView().setText(taskList.get(position).taskName);
        holder.getTasDescTextView().setText(taskList.get(position).taskDescription);
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
class Task
{
      String taskName;
     String taskDescription;
    Task(String taskName,String taskDescription)
    {
        this.taskName=taskName;
        this.taskDescription=taskDescription;
    }
}
