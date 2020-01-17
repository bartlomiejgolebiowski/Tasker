package t.golab.tasker.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import t.golab.tasker.EspressoIdlingResource;
import t.golab.tasker.MainActivity;
import t.golab.tasker.R;
import t.golab.tasker.model.Task;
import t.golab.tasker.room.Repository;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static t.golab.tasker.MainActivity.*;

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.ViewHolder>{

    private ArrayList<Task> mTasks;
    private OnMaterialButtonClick onMaterialButtonClick;
    private int clickedPosition;


    public TasksRecyclerAdapter(ArrayList<Task> tasks, OnMaterialButtonClick onMaterialButtonClick) {
        this.onMaterialButtonClick = onMaterialButtonClick;
        this.mTasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tasks_list, parent, false);
        return new ViewHolder(view, onMaterialButtonClick);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.id.setText(mTasks.get(position).getIdString());
        holder.name.setText(mTasks.get(position).getName());
        holder.status.setText(mTasks.get(position).getStatus());

        try {
            clickedPosition = mSharedPreferences.getInt("pos",0);
            if(mTasks.get(clickedPosition).getId() != mTasks.get(position).getId()){
                if(mTasks.get(clickedPosition).getStatus().equals(Task.TRAVELLING) || mTasks.get(clickedPosition).getStatus().equals(Task.WORKING)){
                    holder.materialButton.setEnabled(false);
                }else holder.materialButton.setEnabled(true);
            }else holder.materialButton.setEnabled(true);
        } catch (IndexOutOfBoundsException e){
            Log.d(TAG, "onBindViewHolder: " + e.getMessage());
            MainActivity.mSharedPreferences.edit().remove("pos").apply();
        }



        switch (mTasks.get(position).getStatus()) {
            case Task.OPEN:
                holder.materialButton.setText(R.string.startTravelling);
                EspressoIdlingResource.INSTANCE.increment();
                holder.itemView.setBackgroundResource(R.drawable.shape_open);
                EspressoIdlingResource.INSTANCE.decrement();
                break;
            case Task.TRAVELLING:
                holder.materialButton.setText(R.string.startWorking);
                EspressoIdlingResource.INSTANCE.increment();
                holder.itemView.setBackgroundResource(R.drawable.shape_travelling);
                EspressoIdlingResource.INSTANCE.decrement();
                break;
            case Task.WORKING:
                holder.materialButton.setText(R.string.stop);
                EspressoIdlingResource.INSTANCE.increment();
                holder.itemView.setBackgroundResource(R.drawable.shape_working);
                EspressoIdlingResource.INSTANCE.decrement();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView id, name, status;
        public MaterialButton materialButton;
        OnMaterialButtonClick onMaterialButtonClick;


        public ViewHolder(@NonNull View itemView, OnMaterialButtonClick onMaterialButtonClick) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            materialButton = itemView.findViewById(R.id.button);
            this.onMaterialButtonClick = onMaterialButtonClick;
            materialButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMaterialButtonClick.onButtonClick(view, getAdapterPosition());
            int posit = getAdapterPosition();
            mSharedPreferences.edit().putInt("pos", posit).apply();
            Log.d(TAG, "onClickPosition: " + clickedPosition + " " + posit);
        }
    }

    public interface OnMaterialButtonClick{

        void onButtonClick(View view, int position);
    }
}
