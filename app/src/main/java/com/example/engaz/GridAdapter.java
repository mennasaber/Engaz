package com.example.engaz;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    View view;
    ArrayList<TaskClass> tasks;
    private LayoutInflater layoutInflater;


    public GridAdapter(Context context, ArrayList<TaskClass> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public TaskClass getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.task_layout, null);
        }
        TaskClass currentTask = getItem(i);

        TextView tTitle = view.findViewById(R.id.taskTitle);
        TextView tDetails = view.findViewById(R.id.taskDetails);
        LinearLayout tLayout = view.findViewById(R.id.taskLayout);

        tTitle.setText(currentTask.gettTitle());
        tDetails.setText(currentTask.gettDetails());

        switch (currentTask.gettColor()) {
            case R.id.color1Button:
                tLayout.setBackgroundResource(R.drawable.rounded1);
                break;
            case R.id.color2Button:
                tLayout.setBackgroundResource(R.drawable.rounded2);
                break;
            case R.id.color3Button:
                tLayout.setBackgroundResource(R.drawable.rounded3);
                break;
            case R.id.color4Button:
                tLayout.setBackgroundResource(R.drawable.rounded4);
                break;
            case R.id.color5Button:
                tLayout.setBackgroundResource(R.drawable.rounded5);
                break;
            default:
                break;
        }
        if (currentTask.gettStatue() == 1)
            tTitle.setPaintFlags(tTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        else
            tTitle.setPaintFlags(0);
        return view;
    }
}
