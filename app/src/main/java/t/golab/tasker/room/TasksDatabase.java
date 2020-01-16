package t.golab.tasker.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import t.golab.tasker.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TasksDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "db_tasks";

    //singleton

    private static TasksDatabase instance;

    static TasksDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TasksDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract Dao getDao();

}
