package com.example.proyectoinventario;

import androidx.appcompat.app.AppCompatActivity;

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

public class NewUsers extends AppCompatActivity {

    private EditText nombre, pass;
    private Button btnSaveUser;
    private TextView mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_users);
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

    public void create(View view){
        AdminSQLiteOpenHelper admin  = new AdminSQLiteOpenHelper(this, "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();
        String password = pass.getText().toString();

        if (!name.isEmpty() || !password.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("id_user", 1);
            registro.put("user_name", name);
            registro.put("user_pass", password);
            baseDeDatos.insert("users", null, registro);
//            if (>0){
//                Toast.makeText(this, "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, "No se guardaron los datos", Toast.LENGTH_SHORT).show();
//            }
            Toast.makeText(this, "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
            nombre.setText("");
            pass.setText("");


        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
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
