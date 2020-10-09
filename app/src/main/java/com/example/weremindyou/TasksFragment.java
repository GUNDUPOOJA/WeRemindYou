package com.example.weremindyou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

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
    public void onAddClick(view v)
    {

<<<<<<< HEAD
    }
}


=======
//    public void onClick(View view) {
//        openMainActivity2();
//    }

//
//    public void openMainActivity2() {
//        Intent intent = new Intent(this,openMainActivity2().class);
//        startActivity(intent);
//
//    }
}
>>>>>>> a3eed50546c498ebd0a55724ad8b2686954088e6
