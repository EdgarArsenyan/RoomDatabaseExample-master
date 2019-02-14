package com.project.dajver.roomdatabaseexample.ui.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.dajver.roomdatabaseexample.R;
import com.project.dajver.roomdatabaseexample.db.model.DataModel;
import com.project.dajver.roomdatabaseexample.ui.AddDataActivity;
import com.project.dajver.roomdatabaseexample.ui.EditDataActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 11/16/17.
 */

public class SomeDataRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<DataModel> dataModels = new ArrayList<>();
    private OnDeleteListener onDeleteListener;
    private Context context;
    AddDataActivity addDataActivity;


    public SomeDataRecyclerAdapter(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_some_data, parent, false);
        return new NewsViewHolder(view);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final NewsViewHolder viewHolder = (NewsViewHolder) holder;
        viewHolder.title.setText(dataModels.get(position).getTitle());
        viewHolder.description.setText(dataModels.get(position).getDescription());
        viewHolder.date.setText(dataModels.get(position).getDate());
        viewHolder.time.setText(dataModels.get(position).getTime());
        viewHolder.priorStatus.setText(dataModels.get(position).getPriority());
        viewHolder.view.setBackgroundColor(dataModels.get(position).getColor());

        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, EditDataActivity.class);
        intent.putExtra("title", dataModels.get(position).getTitle());
        intent.putExtra("description", dataModels.get(position).getDescription());
        intent.putExtra("date", dataModels.get(position).getDate());
        intent.putExtra("time", dataModels.get(position).getTime());
        context.startActivities(new Intent[]{intent});
    }
});
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.description)
        public TextView description;
        @BindView(R.id.date)
        public TextView date;
        @BindView(R.id.time)
        public TextView time;
        @BindView(R.id.delete)
        public TextView delete;
        @BindView(R.id.edit)
        public TextView edit;
        @BindView(R.id.pri)
        public TextView priorStatus;
        @BindView(R.id.view)
        public LinearLayout view;





        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteListener.onDelete(dataModels.get(getAdapterPosition()));
                    dataModels.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

        }


    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public interface OnDeleteListener {
        void onDelete(DataModel dataModel);
    }
}