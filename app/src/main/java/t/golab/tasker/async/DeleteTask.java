package t.golab.tasker.async;

import android.os.AsyncTask;
import android.util.Log;

import t.golab.tasker.model.Task;
import t.golab.tasker.room.Dao;

public class DeleteTask extends AsyncTask<Task, Void, Void>{

    private static final String TAG = "DeleteTask";

    private Dao mDao;

    public DeleteTask(Dao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(Task... tasks) {
        Log.d(TAG, "doInBackground: " + Thread.currentThread().getName());
        mDao.delete(tasks);
        return null;
    }

// I implement this class because I don't want to crash the app. I can't create a new task in MainActivity because there is a downloadTasks() method.
// This method use one of thread, so I must use another by AsyncTask(background thread)

}
