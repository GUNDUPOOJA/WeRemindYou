package com.example.weremindyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class TasksFragment<button> extends Fragment {
    private ListView taskList;
    Button newtask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        taskList = (ListView) getView().findViewById(R.id.task_list);
        // TaskListAdapter taskListAdapter = listener.getTaskListAdapter();
        //  taskList.setAdapter(taskListAdapter);
        //taskList.setOnItemClickListener(listOnItemClickListener); // making it editable
        registerForContextMenu(taskList); // choose edit or delete

        return getView();
    }
}