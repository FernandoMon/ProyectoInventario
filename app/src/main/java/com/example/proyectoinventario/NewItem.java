package com.example.proyectoinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewItem extends AppCompatActivity {
    private EditText id, nombre, descripcion, departamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        id = findViewById(R.id.id);
        nombre = findViewById(R.id.name);
        descripcion = findViewById(R.id.description);
        departamento = findViewById(R.id.department);
        getSupportActionBar().hide();
    }

    public void back(View view){
        Intent ir = new Intent(this,MainActivity.class);
        startActivity(ir);
    }

    public void create(View view){
        AdminSQLiteOpenHelper admin  = new AdminSQLiteOpenHelper(this, "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String ident = id.getText().toString();
        String name = nombre.getText().toString();
        String description = descripcion.getText().toString();
        String department = departamento.getText().toString();


        if (!name.isEmpty() || !ident.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("item_id", ident);
            registro.put("item_name", name);
            registro.put("item_description", description);
            registro.put("department_id", department);
//            baseDeDatos.insert("departments", null, registro);
            if (baseDeDatos.insert("items", null, registro)>0){
                Toast.makeText(this, "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se guardaron los datos", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
            nombre.setText("");
            id.setText("");
            descripcion.setText("");
            departamento.setText("");


        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
