package com.example.actalamacenamiento.modelo;

import android.app.Activity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServicioArchivo {

    public static void guardar(Activity activity, String nombreArchivo, String texto) throws IOException {
        FileOutputStream fos = activity.openFileOutput(nombreArchivo, activity.MODE_PRIVATE);
        fos.write(texto.getBytes());
        fos.close();
    }

    public static String cargar(Activity activity, String nombreArchivo) throws IOException{
        FileInputStream fis = activity.openFileInput(nombreArchivo);
        int c;
        String file = "";
        while ((c = fis.read()) != -1) file += String.valueOf((char)c);
        fis.close();

        return file;
    }

    public static boolean eliminar (Activity activity, String nombreArchivo){
        return activity.deleteFile(nombreArchivo);
    }
}
