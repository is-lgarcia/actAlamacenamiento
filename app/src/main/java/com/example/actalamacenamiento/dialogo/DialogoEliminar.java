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

public class DialogoEliminar extends DialogFragment {

    public static final String TAG = "dialogo_eliminar";

    public interface OnEliminarArchivoListener{
        void OnEliminarArchivo(String nombreArchivo);
    }

    private OnEliminarArchivoListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.eliminar_archivo,null);
        final EditText editNombreArchivo = view.findViewById(R.id.edit_eliminar_archivo);
        Button btnEliminar = view.findViewById(R.id.btn_eliminar);
        Button btnCancelar = view.findViewById(R.id.btn_cancelar_eliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editNombreArchivo.getText().toString().equals("")){
                    listener.OnEliminarArchivo(editNombreArchivo.getText().toString());
                    dismiss();
                }else {
                    Toast.makeText(getActivity(), "Escribe el nombre del archivo", Toast.LENGTH_SHORT).show();
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
            listener = (OnEliminarArchivoListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException("La Activity no implemento la interfaz OnEliminarArchivoListener "+e.getMessage());
        }
    }
}
