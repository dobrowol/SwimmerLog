package com.example.swimmerslog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CharacteristicListViewAdapter extends RecyclerView.Adapter<CharacteristicListViewAdapter.CustomViewHolder> {
    private ArrayList<String> dataList;

    public CharacteristicListViewAdapter(ArrayList<String> data)
    {
        this.dataList = data;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewText;
        TextView textViewComment;
        TextView textViewDate;

        public CustomViewHolder(View itemView)
        {
            super(itemView);
            this.textViewText = (TextView) itemView.findViewById(R.id.characteristicTextView);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.characteristic_layout, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position)
    {
        holder.textViewText.setText(dataList.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}

