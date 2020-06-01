package com.example.proyectoinventario;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DropDepartment extends AppCompatActivity {

    private EditText id, nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_departments);
        getSupportActionBar().hide();
        id = findViewById(R.id.id);
        nombre = findViewById(R.id.name);
    }

    public void back(View view){
        Intent ir = new Intent(this,MainActivity.class);
        startActivity(ir);
    }

    public void search(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String id_d = id.getText().toString();

        Cursor fila = baseDeDatos.rawQuery("select department_name from departments where department_id ="+id_d, null);
        Toast.makeText(this, "Departamento Encontrado", Toast.LENGTH_SHORT).show();
        if (fila.moveToFirst()){
            nombre.setText(fila.getString(0));
            baseDeDatos.close();
        }else{
            Toast.makeText(this, "No se encontro el Departamento", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
        }
    }

    public void delete(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String id_d = id.getText().toString();

        Cursor fila = baseDeDatos.rawQuery("delete from departments where department_id ="+id_d, null);
        Toast.makeText(this, "Departamento Eliminado Correctamente", Toast.LENGTH_SHORT).show();
        if (fila.moveToFirst()){
            nombre.setText(fila.getString(0));
            baseDeDatos.close();
        }else{
            Toast.makeText(this, "Articulo Eliminado Correctamente", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
        }
    }

}
