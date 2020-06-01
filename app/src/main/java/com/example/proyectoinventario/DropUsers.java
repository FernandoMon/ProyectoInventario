package com.example.proyectoinventario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DropUsers extends AppCompatActivity {

    private EditText nombre, pass;
    private Button btnSaveUser;
    private TextView mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_users);
        nombre = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        btnSaveUser = findViewById(R.id.saveUser);
        mostrar = findViewById(R.id.contenido);
        getSupportActionBar().hide();
    }

    public void back(View view){
        Intent ir = new Intent(this,MainActivity.class);
        startActivity(ir);
    }

    //consultas
    public void search(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        Cursor fila = baseDeDatos.rawQuery("select id_user, user_name, user_password from users where id_user = 1", null);

        if (fila.moveToFirst()){
            do{
                mostrar.setText(fila.getString(0));
            }while(fila.moveToNext());
            baseDeDatos.close();
        }else{
            Toast.makeText(this, "No se encontro el registro", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
        }
    }

}
