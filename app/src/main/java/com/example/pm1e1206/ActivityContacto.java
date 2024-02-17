package com.example.pm1e1206;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;

public class ActivityContacto extends AppCompatActivity {

    static final int peticion_camara = 100;
    static final int  peticion_foto = 102;
    EditText pais, nombre, telefono, nota;
    Spinner combopais;
    ImageView imageView;
    Button btntakefoto, btnaddpais, btnaddcont, btnlistcont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        //IMAGEN
        imageView = (ImageView) findViewById(R.id.imageView);

        //BOTONES
        btnlistcont = (Button) findViewById(R.id.btnlistcont);
        btnaddcont = (Button) findViewById(R.id.btnsavecont);
        btntakefoto = (Button) findViewById(R.id.btnimagen);
        btnaddpais = (Button) findViewById(R.id.btnaddpais);

        //COMBOBOX
        combopais = (Spinner) findViewById(R.id.spinner);

        //CAJA DE TEXTO
        nombre = (EditText) findViewById(R.id.Nombre);
        telefono = (EditText) findViewById(R.id.Telefono);
        nota = (EditText) findViewById(R.id.Nota);

        btntakefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Permisos();
            }
        });

        btnaddpais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityPais.class);
                startActivity(intent);
            }
        });
        btnaddcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContacto();
            }
        });
        btnlistcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityList.class);
                startActivity(intent);
            }
        });
    }

    private void Permisos() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA},
                    peticion_camara);
        }
        else
        {
            takefoto();
        }
    }

    private void takefoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, peticion_foto);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
    int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == peticion_camara)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                takefoto();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Permiso denegado", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Guardar la imagen en la carpeta File de la aplicacion
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == peticion_foto && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imagen = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imagen);

            // Guardar la imagen en el almacenamiento interno
            FileOutputStream archivo = null;
            try {
                archivo = openFileOutput(crearNombreArchivojpg(), Context.MODE_PRIVATE);
                imagen.compress(Bitmap.CompressFormat.JPEG, 100, archivo);
                archivo.close();
                Toast.makeText(this, "Imagen guardada correctamente", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Problemas al guardar la imagen", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    //Nombrar el archivo creado
    private String crearNombreArchivojpg() {
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre = fecha + ".jpg";
        return nombre;
}

    //Agregar un nuevo contacto
    private void AddContacto() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.pais, pais.getText().toString());
        valores.put(Transacciones.nombre, nombre.getText().toString());
        valores.put(Transacciones.telefono, telefono.getText().toString());
        valores.put(Transacciones.nota, nota.getText().toString());

        Long resultado = db.insert(Transacciones.TableContactos, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro Ingresado con exito " + resultado.toString(),
                Toast.LENGTH_LONG).show();

        db.close();
    }
}