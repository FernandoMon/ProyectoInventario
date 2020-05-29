package com.example.proyectoinventario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectoinventario.Fragments.NewUser;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase InventarioDB) {
        InventarioDB.execSQL("create table users(id_user int primary key, user_name text, user_password text)");
        InventarioDB.execSQL("create table departments(department_id int primary key, department_name text)");
        InventarioDB.execSQL("create table items(item_id int primary key, item_name text, item_description text, department_id int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
