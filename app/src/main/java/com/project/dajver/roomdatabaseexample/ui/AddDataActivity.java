package com.project.dajver.roomdatabaseexample.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.project.dajver.roomdatabaseexample.App;
import com.project.dajver.roomdatabaseexample.R;
import com.project.dajver.roomdatabaseexample.db.DatabaseHelper;
import com.project.dajver.roomdatabaseexample.db.model.DataModel;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.String.valueOf;


public class AddDataActivity extends AppCompatActivity {

    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.date)
    EditText date;
    @BindView(R.id.time)
    EditText time;

    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int minute;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private String amPm;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    public TextView priorityText;
    int color;

    SeekBar seekBar;

    public TextView getPriorityText() {
        return priorityText;
    }

    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        priorityText = findViewById(R.id.priority_text);
        seekBar = findViewById(R.id.seek);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "." + (month + 1) + "." + year);
                    }
                }, year, month, dayOfMonth);

                datePickerDialog.show();

            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddDataActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", hourOfDay, minute) + amPm);
                    }
                }, hour, minute, false);

                timePickerDialog.show();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                position = progress;
                switch (progress){

                    case 0: priorityText.setText("No Priority");
                   priorityText.setTextColor(getResources().getColor(R.color.no_priority));
                   color = (getResources().getColor(R.color.no_priority));
                    break;

                    case 1: priorityText.setText("Low");
                        priorityText.setTextColor(getResources().getColor(R.color.low));
                        color = getResources().getColor(R.color.low);

                        break;
                    case 2: priorityText.setText("Normal");
                        priorityText.setTextColor(getResources().getColor(R.color.normal));
                        color = getResources().getColor(R.color.normal);

                        break;
                    case 3 : priorityText.setText("High");
                        priorityText.setTextColor(getResources().getColor(R.color.high));
                        color = getResources().getColor(R.color.high);
                        break;
                    default:
                        return;
                }

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @OnClick(R.id.save)
    public void onSaveClick() {

        DatabaseHelper databaseHelper = App.getInstance().getDatabaseInstance();

        DataModel model = new DataModel();
        model.setTitle(title.getText().toString());
        model.setDescription(description.getText().toString());
        model.setDate(date.getText().toString());
        model.setTime(time.getText().toString());
        model.setPriority(priorityText.getText().toString());
        model.setColor(color);
        databaseHelper.getDataDao().insert(model);

        finish();
    }
}
