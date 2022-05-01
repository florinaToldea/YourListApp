package com.example.yourlistapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Home {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "itemName")
    public String itemName;

    @ColumnInfo(name = "status")
    public boolean status;

}
