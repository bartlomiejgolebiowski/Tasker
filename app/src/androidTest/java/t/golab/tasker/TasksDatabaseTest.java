package t.golab.tasker;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;

import t.golab.tasker.room.Dao;
import t.golab.tasker.room.TasksDatabase;

public abstract class TasksDatabaseTest {

    private TasksDatabase mTasksDatabase;

    public Dao getDao(){
        return mTasksDatabase.getDao();
    }
    @Before
    public void init(){
        mTasksDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                TasksDatabase.class
        ).build();
    }

    @After
    public void finish(){
        mTasksDatabase.close();
    }

}
