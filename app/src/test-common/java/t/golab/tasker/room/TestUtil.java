package t.golab.tasker.room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import t.golab.tasker.model.Task;

public class TestUtil {

    public static final Task TEST_TASK_1 = new Task("Task 1", Task.OPEN);

    public static final Task TEST_TASK_2 = new Task("Task 2", Task.TRAVELLING);

    public static final List<Task> TEST_TASKS_LIST = Collections.unmodifiableList(
            new ArrayList<Task>(){{
                add(new Task("Task 1",1, Task.OPEN));
                add(new Task("Task 2",2, Task.TRAVELLING));
            }}
    );
}
