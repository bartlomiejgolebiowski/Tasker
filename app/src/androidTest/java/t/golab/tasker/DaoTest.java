package t.golab.tasker;

import android.database.sqlite.SQLiteConstraintException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import t.golab.tasker.model.Task;
import t.golab.tasker.room.LiveDataTestUtil;
import t.golab.tasker.room.TestUtil;

import static org.junit.Assert.*;


public class DaoTest extends TasksDatabaseTest {


    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void insertReadDelete() throws Exception{
        Task task = new Task(TestUtil.TEST_TASK_1);

        //insert
        getDao().insertTasks(task);

        //read
        LiveDataTestUtil<List<Task>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Task> insertedTasks = liveDataTestUtil.getValue(getDao().getTasks());

        assertNotNull(insertedTasks);

        assertEquals(task.getStatus(), insertedTasks.get(0).getStatus());
        assertEquals(task.getName(), insertedTasks.get(0).getName());

        task.setId(insertedTasks.get(0).getId());
        assertEquals(task, insertedTasks.get(0));

        //delete
        getDao().delete(task);
        insertedTasks = liveDataTestUtil.getValue(getDao().getTasks());
        assertEquals(0, insertedTasks.size());
    }

    @Test
    public void insertReadUpdateReadDelete() throws Exception{
        Task task = new Task(TestUtil.TEST_TASK_1);

        //insert
        getDao().insertTasks(task);

        //read
        LiveDataTestUtil<List<Task>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Task> insertedTasks = liveDataTestUtil.getValue(getDao().getTasks());

        assertNotNull(insertedTasks);

        assertEquals(task.getStatus(), insertedTasks.get(0).getStatus());
        assertEquals(task.getName(), insertedTasks.get(0).getName());

        task.setId(insertedTasks.get(0).getId());
        assertEquals(task, insertedTasks.get(0));

        //update
        task.setStatus(Task.WORKING);
        getDao().update(task);

        //read
        insertedTasks = liveDataTestUtil.getValue(getDao().getTasks());
        assertEquals(Task.WORKING, insertedTasks.get(0).getStatus());
        task.setId(insertedTasks.get(0).getId());
        assertEquals(task, insertedTasks.get(0));

        //delete
        getDao().delete(task);
        insertedTasks = liveDataTestUtil.getValue(getDao().getTasks());
        assertEquals(0, insertedTasks.size());
    }
}
