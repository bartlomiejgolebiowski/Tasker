package t.golab.tasker.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import t.golab.tasker.async.DeleteAllTasks;
import t.golab.tasker.async.DeleteTask;
import t.golab.tasker.async.InsertTask;
import t.golab.tasker.async.UpdateTask;
import t.golab.tasker.model.Task;

public class Repository {

    private TasksDatabase mTasksDatabase;

    public Repository(Context context){
        mTasksDatabase = TasksDatabase.getInstance(context);
    }

    public TasksDatabase getmTasksDatabase() {
        return mTasksDatabase;
    }

    public void insertTask(Task... task){
        new InsertTask(mTasksDatabase.getDao()).execute(task);
    }

    public void updateTask(Task task){
        new UpdateTask(mTasksDatabase.getDao()).execute(task);
    }

    public void deleteAllTasks(){
        new DeleteAllTasks(mTasksDatabase.getDao()).execute();
    }

    public void deleteTask(Task task){
        new DeleteTask(mTasksDatabase.getDao()).execute(task);
    }

    public LiveData<List<Task>> downloadTasks(){
        return mTasksDatabase.getDao().getTasks();
    }

}
