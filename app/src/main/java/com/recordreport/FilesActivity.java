package com.recordreport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.recordreport.entity.Archivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesActivity extends AppCompatActivity {

    private List<Archivo> listaArchivos = new ArrayList<>();

    private File carpetaAveria;
    private File carpetaMantenimiento;
    private File carpetaInstalacion;
    private File carpetaMantenimientoFlip;
    private File carpetaInstalacionFlip;

    //Variables para el RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Declaración del RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_files);

        //Implementación del Recycler
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        String dir = getApplicationContext().getExternalFilesDir(null)+ "/Averias/";
        carpetaAveria = new File(dir);
        checkDirectory(dir);

        dir = getApplicationContext().getExternalFilesDir(null)+ "/Mantenimiento/";
        carpetaMantenimiento = new File(dir);
        checkDirectory(dir);

        dir = getApplicationContext().getExternalFilesDir(null)+ "/Instalacion/";
        carpetaInstalacion = new File(dir);
        checkDirectory(dir);

        dir = getApplicationContext().getExternalFilesDir(null)+ "/MantenimientoFlipFlow/";
        carpetaMantenimientoFlip = new File(dir);
        checkDirectory(dir);

        dir = getApplicationContext().getExternalFilesDir(null)+ "/InstalacionFlipFlow/";
        carpetaInstalacionFlip = new File(dir);
        checkDirectory(dir);


        //Refrescamos el RecyclerView
        mAdapter = new ArchivosAdapter();
        recyclerView.setAdapter(mAdapter);

        refrescar(carpetaAveria, "Avería");
        refrescar(carpetaMantenimiento, "Mantenimiento");
        refrescar(carpetaInstalacion, "Instalación");
        refrescar(carpetaMantenimientoFlip, "Mantenimiento Flip Flow");
        refrescar(carpetaInstalacionFlip, "Instalación Flip Flow");

    }


    /**
     * Cargar una lista de los archivos en la carpeta especificada
     */
    private void refrescar(File directory, String tipo){


        File[] files = directory.listFiles();

        Log.d("Files", "Averias: "+ files.length);

        for (int i = 0; i < files.length; i++)
        {
            listaArchivos.add(new Archivo(files[i].getName(), files[i].getPath().toString(), tipo, files[i]));
            mAdapter.notifyDataSetChanged();
            Log.i("ARCHIVO", "Nuevo archivo creado: "+files[i].getName());
        }


        Log.e("CONTADOR",mAdapter.getItemCount()+" elementos");
    }

    /**
     * Comprobar si un directorio existe y crearlo en caso contrario
     * @param dir
     */
    private void checkDirectory(String dir){
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }
    }




    public class ArchivosAdapter extends RecyclerView.Adapter{

        public void add(Archivo archivo) {
            listaArchivos.add(archivo);
            notifyItemInserted(listaArchivos.indexOf(archivo));
        }
        public void remove(Archivo archivo) {
            int position = listaArchivos.indexOf(archivo);

            if(position != -1) {
                listaArchivos.remove(position);
                notifyItemRemoved(position);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.archivo_adapter, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

            final Archivo archivo = listaArchivos.get(position);

            TextView texto = (TextView) holder.itemView.findViewById(R.id.nombre_archivo);
            TextView text_tipo = (TextView) holder.itemView.findViewById(R.id.tipo_archivo);

            texto.setText(archivo.getName());
            text_tipo.setText(archivo.getTipo());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FilesActivity.this, "Archivo: "+archivo.getName(), Toast.LENGTH_SHORT).show();



                    //Obtiene la Uri del recurso.

                    Uri uri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", archivo.getFile());

//                    Uri uri = Uri.fromFile(new
//                            File(getApplicationContext().getExternalFilesDir(null)+ "/Averias/", archivo.getName()));
                    //Crea intent para enviar el email.
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("application/excel");
                    //Agrega email o emails de destinatario.
                    i.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@uxcreative.es" });
                    i.putExtra(Intent.EXTRA_SUBJECT, "Formulario de "+archivo.getTipo());
                    i.putExtra(Intent.EXTRA_TEXT, "Enviando el archivo con nombre "+archivo.getName());
                    i.putExtra(Intent.EXTRA_STREAM,  uri);
                    startActivity(Intent.createChooser(i, "Enviar e-mail mediante:"));


                }
            });

        }


        @Override
        public int getItemCount() {
            return listaArchivos.size();
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView text_nombre;
        protected TextView text_tipo;

        public ViewHolder(View v) {
            super(v);
            text_nombre = (TextView) v.findViewById(R.id.nombre_archivo);
            text_tipo = (TextView) v.findViewById(R.id.tipo_archivo);
        }

    }










}
