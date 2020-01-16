package t.golab.tasker.async;

import android.os.AsyncTask;
import android.util.Log;

import t.golab.tasker.model.Task;
import t.golab.tasker.room.Dao;

public class DeleteAllTasks extends AsyncTask<Void, Void, Void>{

    private static final String TAG = "InsertTask";

    private Dao mDao;

    public DeleteAllTasks(Dao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mDao.deleteAll();
        return null;
    }

// I implement this class because I don't want to crash the app. I can't create a new task in MainActivity because there is a downloadTasks() method.
// This method use one of thread, so I must use another by AsyncTask(background thread)

}
