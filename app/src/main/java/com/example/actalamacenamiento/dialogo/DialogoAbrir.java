package com.example.actalamacenamiento.dialogo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.actalamacenamiento.R;

public class DialogoAbrir extends DialogFragment {

    public static final String TAG = "dialogo_abrir";

    public interface OnAbrirArchivoListener {
        void onAbrirArchivo(String nombreArchivo);
    }

    private OnAbrirArchivoListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.abrir_archivo, null);
        final EditText editNombreArchivo = view.findViewById(R.id.edit_abrir_archivo);
        Button btnAbrir = view.findViewById(R.id.btn_abrir);
        Button btnCancelar = view.findViewById(R.id.btn_cancelar_abrir);

        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editNombreArchivo.getText().toString().equals("")) {
                    listener.onAbrirArchivo(editNombreArchivo.getText().toString());
                    dismiss();
                } else {
                    Toast.makeText(getActivity(), "Escriba el nombre del archivo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OnAbrirArchivoListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException("La actividad no implemento la interfaz OnAbrirArchivoListener "+e.getMessage());
        }
    }
}
