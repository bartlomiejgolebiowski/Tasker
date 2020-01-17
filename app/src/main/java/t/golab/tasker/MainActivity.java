package t.golab.tasker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import t.golab.tasker.adapter.TasksRecyclerAdapter;
import t.golab.tasker.model.Task;
import t.golab.tasker.room.Repository;

public class MainActivity extends AppCompatActivity implements TasksRecyclerAdapter.OnMaterialButtonClick {

    //UI
    private RecyclerView mRecyclerView;
    //vars
    private static final String TAG = "MainActivity";
    private ArrayList<Task> mTasks = new ArrayList<>();
    private TasksRecyclerAdapter mTasksRecyclerAdapter;
    private Repository mRepository;
    public static SharedPreferences mSharedPreferences;
    private ProgressBar progressBar;
    protected static boolean isTesting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        mSharedPreferences = getSharedPreferences("click", MODE_PRIVATE);
        Log.d(TAG, "onCreate: " + Thread.currentThread().getName());
        mRepository = new Repository(this);
        initializeRecyclerView();
        downloadTasks();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void saveNewTask(Task task) {
        mRepository.insertTask(task);
    }

    private void deleteTask(Task task) {
        mRepository.deleteTask(task);
    }

    public void updateTask(Task task) {
        mRepository.updateTask(task);
    }

    private void downloadTasks() {
        mRepository.downloadTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if(tasks.size() == 0){
                    fake();
                }
                if (mTasks.size() > 0) {
                    mTasks.clear();
                }
                mTasks.addAll(tasks);
                if(isTesting){
                    changeStatusToTest(tasks);
                    isTesting = false;
                }
                mTasksRecyclerAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public int random(int min, int max) {
        Random generator = new Random();
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return generator.nextInt((max - min) + 1) + min;
    }

    private void fake() {
        for (int i = 0; i < 30; i++) {
            Task task = new Task();
            task.setName("No_Name" + i*random(0, i+1));
            task.setStatus(Task.OPEN);
            saveNewTask(task);
        }
    }

    private void initializeRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mTasksRecyclerAdapter = new TasksRecyclerAdapter(mTasks, this);
        mRecyclerView.setAdapter(mTasksRecyclerAdapter);
    }



    public void changeStatusToTest(List<Task> task){
        if(!task.isEmpty()){
            int position = mSharedPreferences.getInt("pos", 0);
            Log.d(TAG, "changeStatusToTest pos: " + position);
            task.get(position).setStatus(Task.OPEN);
            updateTask(task.get(position));
        }
    }


    @Override
    public void onButtonClick(View view, int position) {
        TasksRecyclerAdapter.ViewHolder temp = mTasksRecyclerAdapter.new ViewHolder(view, this);
        if(temp.materialButton.getText().equals("START TRAVELLING")){
            mTasks.get(position).setStatus(Task.TRAVELLING);
        } else if (temp.materialButton.getText().equals("START WORKING")){
            mTasks.get(position).setStatus(Task.WORKING);
        } else if (temp.materialButton.getText().equals("STOP")){
            mTasks.get(position).setStatus(Task.OPEN);
        }
        updateTask(mTasks.get(position));
    }
}
