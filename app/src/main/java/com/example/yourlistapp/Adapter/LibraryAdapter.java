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

import com.example.yourlistapp.Models.Library;
import com.example.yourlistapp.R;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    private Context context;
    private List<Library> libraryList;
    private LibraryAdapter.HandleItemClick handleItemClick;

    public LibraryAdapter(Context context, LibraryAdapter.HandleItemClick handleItemClick){
        this.context = context;
        this.handleItemClick = handleItemClick;
    }

    public void setLibraryList(List<Library> libraryList){
        this.libraryList = libraryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LibraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view, parent, false);
        return new LibraryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(this.libraryList.get(position).itemName);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.editItem(libraryList.get(holder.getAdapterPosition()));
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.removeItem(libraryList.get(holder.getAdapterPosition()));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick.itemClick(libraryList.get(holder.getAdapterPosition()));
            }
        });
        if (this.libraryList.get(position).status) {
            holder.itemName.setPaintFlags(holder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.itemName.setPaintFlags(0);
        }
    }

    @Override
    public int getItemCount () {
        if(libraryList == null || libraryList.size() == 0)
            return 0;
        else
            return libraryList.size();
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
        void itemClick(Library library);

        void removeItem(Library library);

        void editItem(Library library);
    }
}

