package t.golab.tasker.unittests.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import t.golab.tasker.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskTest {

    @Test
    void test_isTasksEqual_returnTrue() throws Exception {
        //Arrange
        Task task1 = new Task("Task 1", Task.OPEN);
        task1.setId(2);
        Task task2 = new Task("Task 1", Task.OPEN);
        task2.setId(2);

        //Act

        //Assert
        assertEquals(task1, task2);
        System.out.println("The tasks are equal. Same ids");
    }

    @Test
    void test_isTasksEqual_diffrentIds_returnFalse() throws Exception {
        //Arrange
        Task task1 = new Task("Task 1", Task.OPEN);
        task1.setId(1);
        Task task2 = new Task("Task 1", Task.OPEN);
        task2.setId(2);

        //Act

        //Assert
        assertNotEquals(task1, task2);
        System.out.println("The tasks aren't equal. Different ids");
    }

    @Test
    void test_isTasksEqual_diffrentStatus_returnTrue() throws Exception {
        //Arrange
        Task task1 = new Task("Task 1", Task.OPEN);
        task1.setId(2);
        Task task2 = new Task("Task 1", Task.TRAVELLING);
        task2.setId(2);

        //Act

        //Assert
        assertEquals(task1, task2);
        System.out.println("The tasks are equal. Different status");
    }

    @Test
    void test_isTasksEqual_diffrentName_returnFalse() throws Exception {
        //Arrange
        Task task1 = new Task("Task 2", Task.OPEN);
        task1.setId(2);
        Task task2 = new Task("Task 1", Task.OPEN);
        task2.setId(2);

        //Act

        //Assert
        assertNotEquals(task1, task2);
        System.out.println("The tasks aren't equal. Different name.");
    }
}
