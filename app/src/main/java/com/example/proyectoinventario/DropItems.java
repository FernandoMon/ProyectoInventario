package com.example.proyectoinventario;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DropItems extends AppCompatActivity {

    private EditText id, nombre, descripcion, departamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_items);
        getSupportActionBar().hide();

        id = findViewById(R.id.id);
        nombre = findViewById(R.id.name);
        descripcion = findViewById(R.id.description);
        departamento = findViewById(R.id.department);
    }

    public void back(View view){
        Intent ir = new Intent(this,MainActivity.class);
        startActivity(ir);
    }

    public void search(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String id_d = id.getText().toString();

        Cursor fila = baseDeDatos.rawQuery("select item_name, item_description, department_id from items where item_id ="+id_d, null);
        Toast.makeText(this, "Articulo Encontrado", Toast.LENGTH_SHORT).show();
        if (fila.moveToFirst()){
            nombre.setText(fila.getString(0));
            descripcion.setText(fila.getString(1));
            departamento.setText(fila.getString(2));
            baseDeDatos.close();
        }else{
            Toast.makeText(this, "No se encontro el Articulo", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
        }
    }

    public void delete(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String id_d = id.getText().toString();

        Cursor fila = baseDeDatos.rawQuery("delete from items where item_id ="+id_d, null);
        Toast.makeText(this, "Articulo Eliminado Correctamente", Toast.LENGTH_SHORT).show();
        if (fila.moveToFirst()){
            nombre.setText(fila.getString(0));
            descripcion.setText(fila.getString(1));
            departamento.setText(fila.getString(2));
            baseDeDatos.close();
        }else{
            Toast.makeText(this, "Articulo Eliminado Correctamente", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
        }
    }
}
