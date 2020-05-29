package com.example.proyectoinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewDepartment extends AppCompatActivity {

    private EditText id, nombre;
    private TextView mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_department);
        getSupportActionBar().hide();
        id = findViewById(R.id.id);
        nombre = findViewById(R.id.name);
        mostrar = findViewById(R.id.contenido);
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


        if (!name.isEmpty() || !ident.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("department_id", ident);
            registro.put("department_name", name);
//            baseDeDatos.insert("departments", null, registro);
            if (baseDeDatos.insert("departments", null, registro)>0){
                Toast.makeText(this, "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se guardaron los datos", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
            nombre.setText("");
            id.setText("");


        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void search(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        Cursor fila = baseDeDatos.rawQuery("select department_id, department_name from departments where department_id = 1", null);
        Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show();
        if (fila.moveToFirst()){
           // do{
                mostrar.setText(fila.getString(0));
                mostrar.setText(fila.getString(1));
            //}while(fila.moveToNext());
            baseDeDatos.close();
        }else{
            Toast.makeText(this, "No se encontro el registro", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
        }
    }

}
