package t.golab.tasker.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

@Entity(tableName = "tasks")
public class Task implements Parcelable {

    public static final String OPEN = "OPEN";
    public static final String TRAVELLING = "TRAVELLING";
    public static final String WORKING = "WORKING";

    @StringDef({OPEN, TRAVELLING, WORKING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status{}

    protected Task(Parcel in) {
        name = in.readString();
        status = in.readString();
        id = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(status);
        parcel.writeInt(id);
    }

    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "status")
    private @Status String status;
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
    public Task(String name, int id, @NotNull @Status String status) {
        this.name = name;
        this.id = id;
        this.status = status;
    }

    public Task(String name, @NotNull @Status String status) {
        this.name = name;
        this.status = status;
    }

    @Ignore
    public Task() {
    }

    @Ignore
    public Task(Task task) {
        id = task.id;
        name = task.name;
        status = task.status;
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
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass())
            return false;

        Task task = (Task) obj;
        return task.getId() == getId() && task.getIdString().equals(getIdString()) && task.getName().equals(getName());
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
