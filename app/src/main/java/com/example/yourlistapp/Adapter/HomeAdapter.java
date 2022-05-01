package com.example.yourlistapp.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlistapp.Models.Home;
import com.example.yourlistapp.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<Home> homeList;
    private HandleItemClick handleItemClick;

    public HomeAdapter(Context context, HandleItemClick handleItemClick){
        this.context = context;
        this.handleItemClick = handleItemClick;
    }

    public void setHomeList(List<Home> homeList){
        this.homeList = homeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view, parent, false);
        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(this.homeList.get(position).itemName);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.editItem(homeList.get(holder.getAdapterPosition()));
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.removeItem(homeList.get(holder.getAdapterPosition()));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.itemClick(homeList.get(holder.getAdapterPosition()));
            }
        });
        if (this.homeList.get(position).status) {
            holder.itemName.setPaintFlags(holder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.itemName.setPaintFlags(0);
        }
    }

        @Override
        public int getItemCount () {
            if(homeList == null || homeList.size() == 0)
                return 0;
            else
                return homeList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView itemName;
            ImageView remove;
            ImageView edit;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                itemName = itemView.findViewById(R.id.name_field);
                remove = itemView.findViewById(R.id.remove_field);
                edit = itemView.findViewById(R.id.edit_field);
            }
        }

        public interface HandleItemClick {
            void itemClick(Home home);

            void removeItem(Home home);

            void editItem(Home home);
        }
}
