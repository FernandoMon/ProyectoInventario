package com.example.proyectoinventario.Fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyectoinventario.AdminSQLiteOpenHelper;
import com.example.proyectoinventario.R;

import org.w3c.dom.Text;

public class NewUser extends Fragment {

    private EditText nombre, pass;
    private Button btnSaveUser;
    private TextView mostrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_user_fragment,container,false);

        nombre = view.findViewById(R.id.name);
        pass = view.findViewById(R.id.pass);
        btnSaveUser = view.findViewById(R.id.saveUser);
        mostrar = view.findViewById(R.id.contenido);

        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });

        return view;
    }

    //Method to create a new user

     public void create(){
         AdminSQLiteOpenHelper admin  = new AdminSQLiteOpenHelper(getView().getContext(), "InventarioDB", null, 1);
         SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

         String name = nombre.getText().toString();
         String password = pass.getText().toString();

         if (!name.isEmpty() || !password.isEmpty()){
             ContentValues registro = new ContentValues();
             registro.put("id_user", 1);
             registro.put("user_name", name);
             registro.put("user_pass", password);

             if (baseDeDatos.insert("users", null, registro)>0){
                 Toast.makeText(getView().getContext(), "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(getView().getContext(), "No se guardaron los datos", Toast.LENGTH_SHORT).show();
             }

             baseDeDatos.close();
             nombre.setText("");
             pass.setText("");


         }else{
             Toast.makeText(getView().getContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
         }
     }

     //consultas
    public void search(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), "InventarioDB", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        Cursor fila = baseDeDatos.rawQuery("select user_name, user_password from users where id_user = '1'", null);

        if (fila.moveToFirst()){
            do{
                mostrar.setText(fila.getString(0));
            }while(fila.moveToNext());
            baseDeDatos.close();
        }else{
            Toast.makeText(getView().getContext(), "No se encontro el registro", Toast.LENGTH_SHORT).show();
            baseDeDatos.close();
        }
    }

}
