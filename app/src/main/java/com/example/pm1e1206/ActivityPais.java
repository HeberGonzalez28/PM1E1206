package com.example.pm1e1206;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Configuracion.SQLiteConexion;
import Configuracion.Transapais;

public class ActivityPais extends AppCompatActivity {

    String Pais;
    private Transapais db;

    EditText codigo, nombre;
    Button btnaddpais, btnupdatep, btneliminarp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);

        //CAJA DE TEXTO
        codigo = (EditText) findViewById(R.id.txtcodarea);
        nombre = (EditText) findViewById(R.id.txtnombre);

        //BOTONES
        btnaddpais = (Button) findViewById(R.id.btnsavepais);
        btnupdatep = (Button) findViewById(R.id.btnupdatepais);
        btneliminarp = (Button) findViewById(R.id.btnelimpais);


        btnaddpais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPais();
            }
        });
    }

    private void AddPais() {

        String codigotext = codigo.getText().toString().trim();
        String nombretext = nombre.getText().toString().trim();

        if (codigotext.isEmpty()) {
            showAlert("Debe escribir un codigo de area");
        } else if (nombretext.isEmpty()) {
            showAlert("Debe escribir el nombre del pais");
        } else {

            SQLiteConexion conexion = new SQLiteConexion(this, Transapais.BDname, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Transapais.codigo, codigo.getText().toString());
            valores.put(Transapais.nombre, nombre.getText().toString());

            Long resultado = db.insert(Transapais.TablePais, Transapais.id, valores);

            if(resultado>0) {
                Toast.makeText(getApplicationContext(), "Registro Ingresado con exito ",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Error al Ingresar los datos ",Toast.LENGTH_LONG).show();
            }

            db.close();

            //Limpia los campos
            codigo.setText("");
            nombre.setText("");
        }
    }
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Campo Vacío")
                .setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}