package com.example.actalamacenamiento;

import android.os.Bundle;

import com.example.actalamacenamiento.dialogo.DialogoAbrir;
import com.example.actalamacenamiento.dialogo.DialogoEliminar;
import com.example.actalamacenamiento.modelo.ServicioArchivo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements DialogoAbrir.OnAbrirArchivoListener
        , DialogoEliminar.OnEliminarArchivoListener {

    private EditText editNombreArchivo, editTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editNombreArchivo = findViewById(R.id.edit_archivo);
        editTexto = findViewById(R.id.edit_texto);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.guardar:
                if (!editNombreArchivo.getText().toString().equals("")
                && !editTexto.getText().toString().equals("")){
                    try {
                        ServicioArchivo.guardar(this
                                ,editNombreArchivo.getText().toString()+".txt"
                                ,editTexto.getText().toString());
                        editTexto.setText("");
                        editNombreArchivo.setText("");
                        Toast.makeText(this, "Archivo guardado Correctamente", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error al guardar el archivo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Rellene todas las cajas de texto", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.abrir:
                DialogoAbrir abrir = new DialogoAbrir();
                abrir.show(getSupportFragmentManager(),abrir.TAG);
                break;
            case R.id.borrar:
                DialogoEliminar eliminar = new DialogoEliminar();
                eliminar.show(getSupportFragmentManager(),eliminar.TAG);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAbrirArchivo(String nombreArchivo) {
        try {
            editTexto.setText(ServicioArchivo.cargar(this,nombreArchivo+".txt"));
            editNombreArchivo.setText(nombreArchivo);
            Toast.makeText(this, "El archivo fue abierto correctamente", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, "Error al cargar el archivo", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void OnEliminarArchivo(String nombreArchivo) {

        if (ServicioArchivo.eliminar(this, nombreArchivo+".txt")){
            Toast.makeText(this, "Archivo Eliminado", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "El archivo a eliminar no existe", Toast.LENGTH_SHORT).show();
        }
    }
}
