package com.project.dajver.roomdatabaseexample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.project.dajver.roomdatabaseexample.App;
import com.project.dajver.roomdatabaseexample.R;
import com.project.dajver.roomdatabaseexample.db.DatabaseHelper;
import com.project.dajver.roomdatabaseexample.db.model.DataModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditDataActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText date;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        ButterKnife.bind(this);
        setTitle();
        setDescription();
        setDate();
        setTime();
    }
    public void setTitle(){
        title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title"));
    }
    public void setDescription(){
        description = findViewById(R.id.description);
        description.setText(getIntent().getStringExtra("description"));
    }
    public void setDate(){
        date = findViewById(R.id.date);
        date.setText(getIntent().getStringExtra("date"));
    }
    public void setTime(){
        time = findViewById(R.id.time);
        time.setText(getIntent().getStringExtra("time"));
    }

    @OnClick(R.id.save1)
    public void onSaveClick() {

        DatabaseHelper databaseHelper = App.getInstance().getDatabaseInstance();

        DataModel model = new DataModel();
        model.setTitle(title.getText().toString());
        model.setDescription(description.getText().toString());
        model.setDate(date.getText().toString());
        model.setTime(time.getText().toString());
        databaseHelper.getDataDao().update(model);

        finish();
    }
}
