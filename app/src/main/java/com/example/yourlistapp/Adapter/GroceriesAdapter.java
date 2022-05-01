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

import com.example.yourlistapp.Models.Groceries;
import com.example.yourlistapp.Models.Home;
import com.example.yourlistapp.R;

import java.util.List;

public class GroceriesAdapter extends RecyclerView.Adapter<GroceriesAdapter.ViewHolder> {
    private Context context;
    private List<Groceries> groceriesList;
    private HandleItemClick handleItemClick;

    public GroceriesAdapter(Context context, HandleItemClick handleItemClick){
        this.context = context;
        this.handleItemClick = handleItemClick;
    }

    public void setGroceriesList(List<Groceries> groceriesList){
        this.groceriesList = groceriesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view, parent, false);
        return new GroceriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(this.groceriesList.get(position).itemName);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.editItem(groceriesList.get(holder.getAdapterPosition()));
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.removeItem(groceriesList.get(holder.getAdapterPosition()));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.itemClick(groceriesList.get(holder.getAdapterPosition()));
            }
        });
        if (this.groceriesList.get(position).status) {
            holder.itemName.setPaintFlags(holder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.itemName.setPaintFlags(0);
        }
    }

    @Override
    public int getItemCount () {
        if(groceriesList == null || groceriesList.size() == 0)
            return 0;
        else
            return groceriesList.size();
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
        void itemClick(Groceries groceries);

        void removeItem(Groceries home);

        void editItem(Groceries home);
    }
}
