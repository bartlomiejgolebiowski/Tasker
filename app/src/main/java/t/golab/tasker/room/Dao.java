package t.golab.tasker.room;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import t.golab.tasker.model.Task;

@androidx.room.Dao
public interface Dao {

    @Insert
    long[]  insertTasks(Task... tasks);

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getTasks();

    @Query("DELETE FROM tasks")
    int deleteAll();

    @Delete
    int delete(Task... tasks);

    @Update
    int update(Task... tasks);

}
