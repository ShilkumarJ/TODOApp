package com.example.sjadhav.todo;

import android.content.Context;
import android.content.Intent;
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
import android.app.Activity;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    static ArrayList<Task>taskArrayList;
   static public RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        listView=(RecyclerView)findViewById(R.id.recycler_view);
        listView.setLayoutManager(layoutManager);

        taskArrayList=new ArrayList<Task>();
        Task t1=new Task("Dinner","With bajirao");
        Task t2=new Task("Movie","Natsamrat");
        taskArrayList.add(t1);
        taskArrayList.add(t2);

        CustomAdapter adapter=new CustomAdapter(taskArrayList);
        listView.setAdapter(adapter);
    }
    public void add(View v)
    {
        Intent intent=new Intent(this,TaskActivity.class);
        startActivityForResult(intent,1);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                String description=data.getStringExtra("desc");
                Task t=new Task(result,description);
                taskArrayList.add(t);
                listView.setAdapter(new CustomAdapter(taskArrayList));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.TaskHolder>
{
    ArrayList<Task>taskList=new ArrayList<Task>();

    public static class TaskHolder extends RecyclerView.ViewHolder
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
    }

    public CustomAdapter(ArrayList<Task> taskArrayList) {
        super();
        for(int i=0;i<taskArrayList.size();i++) {
            taskList.add(taskArrayList.get(i));
          //  System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDD"+taskArrayList.get(i));
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
        holder.img.setTag("" + position);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=Integer.parseInt(((ImageView)v).getTag()+"");
                MainActivity.taskArrayList.remove(position);
                CustomAdapter customAdapter=new CustomAdapter(MainActivity.taskArrayList);
                MainActivity.listView.setAdapter(customAdapter);
            }
        });
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
