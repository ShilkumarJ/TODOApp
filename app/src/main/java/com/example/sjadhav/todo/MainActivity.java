package com.example.sjadhav.todo;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Task>taskArrayList;
    public RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        listView=(RecyclerView)findViewById(R.id.recycler_view);
        listView.setLayoutManager(layoutManager);
        Task t=new Task("Movie","Natsamrat with friends");

        taskArrayList=new ArrayList<Task>();
        taskArrayList.add(t);
        CustomAdapter adapter=new CustomAdapter(this, taskArrayList);
        listView.setAdapter(adapter);
    }
    public void add(View v)
    {
        Intent intent=new Intent(this,TaskActivity.class);
        startActivityForResult(intent,1);
    }
    public void displayDetails(View v)
    {
        LinearLayout linearLayout=(LinearLayout)v.findViewById(R.id.list_i_layout);
        ImageView image=(ImageView)linearLayout.getChildAt(2);
        System.out.println(image.getTag());
        int position=Integer.parseInt("" + image.getTag());
        Intent intent=new Intent(this,TaskDetails.class);
        intent.putExtra("Task Name",taskArrayList.get(position).taskName);
        intent.putExtra("Description",taskArrayList.get(position).taskDescription);
        startActivity(intent);
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
                listView.setAdapter(new CustomAdapter(this, taskArrayList));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
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
