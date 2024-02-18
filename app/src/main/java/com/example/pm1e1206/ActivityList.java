package com.example.pm1e1206;

import static android.widget.AdapterView.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;
import Models.Contactos;

public class ActivityList extends AppCompatActivity {

    SQLiteConexion conexion;

    ListView listContactos;

    ArrayList<Contactos> lista;

    ArrayList<String> Arreglo;

    Button btnAtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnAtras = (Button) findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
        listContactos = (ListView) findViewById(R.id.idListContactos);

        ObtenerDatos();

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, Arreglo);
        listContactos.setAdapter(adp);


        listContactos.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contactos contactoSeleccionado = lista.get(position);


                Intent intent = new Intent(ActivityList.this, MainActivity.class);

                intent.putExtra("id", contactoSeleccionado.getId());
                intent.putExtra("nombre", contactoSeleccionado.getNombre());
                intent.putExtra("telefono", contactoSeleccionado.getTelefono());
                intent.putExtra("nota", contactoSeleccionado.getNota());
                intent.putExtra("imagen", contactoSeleccionado.getImagen());

                startActivity(intent);
            }
        });

    }


    private void ObtenerDatos(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Contactos contac = null;
        lista = new ArrayList<Contactos>();

        Cursor cursor = db.rawQuery(Transacciones.SelectAllContactos, null);

        while (cursor.moveToNext()){
            contac = new Contactos();
            contac.setId(cursor.getInt(0));
            contac.setPais(cursor.getString(1));
            contac.setNombre(cursor.getString(2));
            contac.setTelefono(String.valueOf(cursor.getInt(3)));
            contac.setNota(cursor.getString(4));
            contac.setImagen(cursor.getBlob(5));

            lista.add(contac);

        }
        cursor.close();

        FillData();

    }

    private void FillData() {
        Arreglo = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            Contactos contac = lista.get(i);
            String datosContacto = "Pais: " + contac.getPais() + "\n"
                    + "Nombre: " + contac.getNombre() + "\n"
                    + "Telefono: " + contac.getTelefono() + "\n"
                    + "Nota: " + contac.getNota() + "\n"
                    + "imagen: " + contac.getImagen();
            Arreglo.add(datosContacto);
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    public void onBackButtonClick(View view) {
        onBackPressed();
    }


}