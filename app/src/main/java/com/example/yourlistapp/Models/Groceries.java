package com.example.yourlistapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Groceries {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "itemName")
    public String itemName;

    @ColumnInfo(name = "status")
    public boolean status;
}
