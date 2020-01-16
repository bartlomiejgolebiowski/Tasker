package t.golab.tasker.model;

import androidx.annotation.StringDef;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

@Entity(tableName = "tasks")
public class Task {

    public static final String OPEN = "OPEN";
    public static final String TRAVELLING = "TRAVELLING";
    public static final String WORKING = "WORKING";

    @StringDef({OPEN, TRAVELLING, WORKING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status{}

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "status")
    private @Status String status;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Task(String name, int id, @Status String status) {
        this.name = name;
        this.id = id;
        this.status = status;
    }

    @Ignore
    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(name, task.name) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getIdString(){
        return Integer.toString(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Status String getStatus() {
        return status;
    }

    public void setStatus(@Status String status) {
        this.status = status;
    }

}
