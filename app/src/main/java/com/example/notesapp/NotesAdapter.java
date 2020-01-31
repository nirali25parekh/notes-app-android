package com.example.notesapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<NotesModel> notesModelList;
    private Context context;
    private OnRecyclerItemClick onRecyclerItemClick;

    public NotesAdapter(List<NotesModel> notesModelList, Context context) {
        this.notesModelList = notesModelList;
        this.context = context;
    }

    interface OnRecyclerItemClick {
        void onClick(int pos);
    }

    public void setOnRecyclerItemClick(OnRecyclerItemClick onRecyclerItemClick) {
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.notes_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.headText.setText(notesModelList.get(position).getHead());
        holder.descText.setText(notesModelList.get(position).getDesc());
        holder.timeText.setText(notesModelList.get(position).getTime());

        switch (position) {
            case 1:
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.color2));
                break;
            case 2:
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.color3));
                break;
            case 3:
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.color4));
                break;

            case 4:
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.color5));
                break;

            case 0:

            default:
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.color1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return notesModelList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView headText, descText, timeText;
        private View view;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            headText = itemView.findViewById(R.id.head1);
            descText = itemView.findViewById(R.id.desc1);
            timeText = itemView.findViewById(R.id.time1);
            view = itemView.findViewById(R.id.view1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerItemClick != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            onRecyclerItemClick.onClick(pos);
                        }
                    }
                }
            });
        }
    }
}