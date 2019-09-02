package com.example.engaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TaskClass> arrayList = new ArrayList<>();
    GridAdapter gridAdapter;
    tasksController tasksController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksController = new tasksController(MainActivity.this, null, null, 1);
        arrayList = tasksController.GET();
        gridAdapter = new GridAdapter(this, arrayList);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(gridAdapter);

        checkNumOfTasks();


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure you want to delete this task?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                tasksController.DELETEtask(arrayList.get(i).gettId());
                                arrayList.remove(i);
                                gridAdapter.notifyDataSetChanged();
                                checkNumOfTasks();
                                dialog.dismiss();
                            }
                        })

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(R.drawable.danger)
                        .show();
                return true;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.task_dialog, viewGroup, false);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final CheckBox tCheck = alertDialog.findViewById(R.id.tCheck);
                if (arrayList.get(i).gettStatue() == 1)
                    tCheck.setChecked(true);
                else
                    tCheck.setChecked(false);

                final TextView tTitle = alertDialog.findViewById(R.id.taskTitleDialog);
                tTitle.setText(arrayList.get(i).gettTitle());

                final TextView tDetails = alertDialog.findViewById(R.id.taskDetailsDialog);
                tDetails.setText(arrayList.get(i).gettDetails());

                LinearLayout tLayout = alertDialog.findViewById(R.id.taskLayoutDialog);
                switch (arrayList.get(i).gettColor()) {
                    case R.id.color1Button:
                        tLayout.setBackgroundResource(R.color.color1);
                        break;
                    case R.id.color2Button:
                        tLayout.setBackgroundResource(R.color.color2);
                        break;
                    case R.id.color3Button:
                        tLayout.setBackgroundResource(R.color.color3);
                        break;
                    case R.id.color4Button:
                        tLayout.setBackgroundResource(R.color.color4);
                        break;
                    case R.id.color5Button:
                        tLayout.setBackgroundResource(R.color.color5);
                        break;
                    default:
                        break;
                }

                ImageView closeImage = alertDialog.findViewById(R.id.close);


                closeImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                tCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (tCheck.isChecked()) {
                            arrayList.get(i).settStatue(1);
                            tasksController.UPDATEtask(arrayList.get(i).gettId(),1);
                        } else {
                            arrayList.get(i).settStatue(0);
                            tasksController.UPDATEtask(arrayList.get(i).gettId(),0);
                        }
                        gridAdapter.notifyDataSetChanged();
                    }
                });

                alertDialog.show();
            }
        });
    }

    private void checkNumOfTasks() {
        LinearLayout layout = findViewById(R.id.layoutNoTasks);

        if(arrayList.size()==0)
            layout.setVisibility(View.VISIBLE);
        else
            layout.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.addTask:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_task, viewGroup, false);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText tTitle = alertDialog.findViewById(R.id.titleEditText);
                final EditText tDetails = alertDialog.findViewById(R.id.detailsEditText);


                final LinearLayout tLayout = alertDialog.findViewById(R.id.addLayout);

                final RadioGroup radioGroup = alertDialog.findViewById(R.id.colorsGroup);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (radioGroup.getCheckedRadioButtonId()) {
                            case R.id.color1Button:
                                tLayout.setBackgroundResource(R.color.color1);
                                break;
                            case R.id.color2Button:
                                tLayout.setBackgroundResource(R.color.color2);
                                break;
                            case R.id.color3Button:
                                tLayout.setBackgroundResource(R.color.color3);
                                break;
                            case R.id.color4Button:
                                tLayout.setBackgroundResource(R.color.color4);
                                break;
                            case R.id.color5Button:
                                tLayout.setBackgroundResource(R.color.color5);
                                break;
                        }
                    }
                });

                final ImageView close = alertDialog.findViewById(R.id.closeAdd);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                Button addButton = alertDialog.findViewById(R.id.addButton);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!tTitle.getText().toString().trim().equals("") && !tDetails.getText().toString().trim().equals("")) {
                            TaskClass task = new TaskClass(tTitle.getText().toString(), tDetails.getText().toString(), radioGroup.getCheckedRadioButtonId(), 0);
                            arrayList.add(task);
                            gridAdapter.notifyDataSetChanged();
                            checkNumOfTasks();
                            tasksController.POST(task);
                            alertDialog.dismiss();
                        }
                    }
                });
                alertDialog.show();
        }
        return true;
    }
}
