package com.recordreport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MantenimientoActivity extends AppCompatActivity {

    String modelo_puerta = "";
    String anio_puerta = "";
    String estado_carril = "";
    String estado_carros = "";
    String motor_averia = "";
    String electronica = "";
    String correa = "";
    String cerrojo = "";
    String selector = "";
    String hojas = "";
    String mecanismo_completo = "";
    String cajon = "";
    String deteccion = "";
    String proteccion = "";
    String tiempo_trabajo = "";
    String bateria = "";
    String guias = "";
    String gomas = "";
    String cableado = "";
    String desbloqueo = "";
    String modelo_radares = "";
    String modelo_baterias = "";
    String modelo_electronicas = "";
    String modelo_cerrojos = "";
    String modelo_motores = "";
    String modelo_guias = "";
    String modelo_selectores = "";
    String modelo_carros = "";
    String modelo_correas = "";
    String modelo_poleas = "";
    String modelo_puertas = "";

    private static final int PERMISSION_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_averia);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        formularioAveria();

        //Acciones del botón
        Button btn_crear_pdf    =   (Button) findViewById(R.id.btn_averia_send);
        btn_crear_pdf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checkFormValid()){
                    Toast.makeText(MantenimientoActivity.this, "Archivo creado correctamente", Toast.LENGTH_SHORT).show();
                    createFile();
                }
                else{
                    Toast.makeText(MantenimientoActivity.this, "Rellena todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private boolean saveExcelFile(Context context, String fileName) {

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.LIME.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("Avería");

        // Generate column headings
        Row row = sheet1.createRow(0);

        String[] valores = {
                "Cliente",
                "Trabajo a realizar",
                "Modelo de puerta",
                "Año de puerta",
                "ESTADO CARRIL",
                "ESTADO CARROS",
                "MOTOR",
                "ELECTRONICA",
                "CORREA",
                "CERROJO",
                "SELECTOR",
                "HOJAS",
                "Mecanismo completo",
                "CAJON",
                "DETECCION",
                "PROTECCION",
                "TIEMPO DE TRABAJO",
                "bateria",
                "guias",
                "gomas",
                "cableado",
                "desbloqueo",
                "tmodelo de radares",
                "modelo de baterias",
                "Modelo de electronicas",
                "Modelos de cerrojos",
                "Modelo de motores",
                "modelo de guias",
                "modelos de selectores",
                "Modelo de carros",
                "modelo de correas",
                "modelo de poleas",
                "modelo de puertas"
        };

        for(int i=0; i<valores.length; i++){
            c = row.createCell(i);
            c.setCellValue(valores[i]);
            c.setCellStyle(cs);
        }

        //Segunda columna con el contenido de la tabla
        row = sheet1.createRow(1);
        c = row.createCell(0);
        c.setCellValue("Columna 1");

        for(int i=0; i<valores.length; i++){
            c = row.createCell(i);
            c.setCellValue(valores[i]);
            c.setCellStyle(cs);
        }

        String[] valores_tabla = {
                "Cliente de prueba",
                "Avería",
                modelo_puerta,
                anio_puerta,
                estado_carril,
                estado_carros,
                motor_averia,
                electronica,
                correa,
                cerrojo,
                selector,
                hojas,
                mecanismo_completo,
                cajon,
                deteccion,
                proteccion,
                tiempo_trabajo,
                bateria,
                guias,
                gomas,
                cableado,
                desbloqueo,
                modelo_radares,
                modelo_baterias,
                modelo_electronicas,
                modelo_cerrojos,
                modelo_motores,
                modelo_guias,
                modelo_selectores,
                modelo_carros,
                modelo_correas,
                modelo_poleas,
                modelo_puertas,
        };

        for(int i=0; i<valores_tabla.length; i++){
            c = row.createCell(i);
            c.setCellValue(valores_tabla[i]);

            sheet1.setColumnWidth(i, (15 * 500));
        }



        // Create a path where we will place our List of objects on external storage
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Editando el fichero" + file);
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error al editar el fichero " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Error al guardar el fichero", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
        return success;
    }

    private boolean checkFormValid(){

        String[] valores_tabla = {
                "Cliente de prueba",
                "Avería",
                modelo_puerta,
                anio_puerta,
                estado_carril,
                estado_carros,
                motor_averia,
                electronica,
                correa,
                cerrojo,
                selector,
                hojas,
                mecanismo_completo,
                cajon,
                deteccion,
                proteccion,
                tiempo_trabajo,
                bateria,
                guias,
                gomas,
                cableado,
                desbloqueo,
                modelo_radares,
                modelo_baterias,
                modelo_electronicas,
                modelo_cerrojos,
                modelo_motores,
                modelo_guias,
                modelo_selectores,
                modelo_carros,
                modelo_correas,
                modelo_poleas,
                modelo_puertas,
        };

        for (String s : valores_tabla) {
            if (s.equals("Selecciona un valor")) {
                return false;
            }
        }

        return true;
    }


    private void createFile(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkPermission()) {
                    saveExcelFile(MantenimientoActivity.this, "archivo.xls");
                } else {
                    requestPermission(); // Code for permission
                }
            } else {
                saveExcelFile(MantenimientoActivity.this, "archivo.xsl");
            }
        }
    }




    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MantenimientoActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MantenimientoActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(MantenimientoActivity.this, "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MantenimientoActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }



    /**
     * Dar los valores al formulario
     */
    private void formularioAveria(){


        final Spinner modelo_puerta_spinner = (Spinner) findViewById(R.id.modelo_puerta_averia);
        final Spinner anio_puerta_spinner = (Spinner) findViewById(R.id.anio_puerta_averia);
        final Spinner estado_carril_spinner = (Spinner) findViewById(R.id.estado_carril_averia);
        final Spinner estado_carros_spinner = (Spinner) findViewById(R.id.estado_carros_averia);
        final Spinner motor_averia_spinner = (Spinner) findViewById(R.id.motor_averia);
        final Spinner electronica_spinner = (Spinner) findViewById(R.id.electronica_averia);
        final Spinner correa_spinner = (Spinner) findViewById(R.id.correa_averia);
        final Spinner cerrojo_spinner = (Spinner) findViewById(R.id.cerrojo_averia);
        final Spinner selector_spinner = (Spinner) findViewById(R.id.selector_averia);
        final Spinner hojas_spinner = (Spinner) findViewById(R.id.hojas_averia);
        final Spinner mecanismo_completo_spinner = (Spinner) findViewById(R.id.mecanismo_averia);
        final Spinner cajon_spinner = (Spinner) findViewById(R.id.cajon_averia);
        final Spinner deteccion_spinner = (Spinner) findViewById(R.id.deteccion_averia);
        final Spinner proteccion_spinner = (Spinner) findViewById(R.id.proteccion_averia);
        final Spinner tiempo_trabajo_spinner = (Spinner) findViewById(R.id.tiempo_trabajo_averia);
        final Spinner bateria_spinner = (Spinner) findViewById(R.id.bateria_averia);
        final Spinner guias_spinner = (Spinner) findViewById(R.id.guias_averia);
        final Spinner gomas_spinner = (Spinner) findViewById(R.id.gomas_averia);
        final Spinner cableado_spinner = (Spinner) findViewById(R.id.cableado_averia);
        final Spinner desbloqueo_spinner = (Spinner) findViewById(R.id.desbloqueo_averia);
        final Spinner modelo_radares_spinner = (Spinner) findViewById(R.id.modelo_radares_averia);
        final Spinner modelo_baterias_spinner = (Spinner) findViewById(R.id.modelo_baterias_averia);
        final Spinner modelo_electronicas_spinner = (Spinner) findViewById(R.id.modelo_electronicas_averia);
        final Spinner modelo_cerrojos_spinner = (Spinner) findViewById(R.id.modelo_cerrojos_averia);
        final Spinner modelo_motores_spinner = (Spinner) findViewById(R.id.modelo_motores_averia);
        final Spinner modelo_guias_spinner = (Spinner) findViewById(R.id.modelo_guias_averia);
        final Spinner modelo_selectores_spinner = (Spinner) findViewById(R.id.modelo_selectores_averia);
        final Spinner modelo_carros_spinner = (Spinner) findViewById(R.id.modelo_carros_averia);
        final Spinner modelo_correas_spinner = (Spinner) findViewById(R.id.modelo_correas_averia);
        final Spinner modelo_poleas_spinner = (Spinner) findViewById(R.id.modelo_poleas_averia);
        final Spinner modelo_puertas_spinner = (Spinner) findViewById(R.id.modelo_puertas_averia);


        //Modelo puerta
        ArrayAdapter<CharSequence> modelo_puerta_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_puerta, android.R.layout.simple_spinner_item);
        modelo_puerta_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_puerta_spinner.setAdapter(modelo_puerta_adapter);

        modelo_puerta_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_puerta = modelo_puerta_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_puerta = "No seleccionado";
            }
        });


        //Año puerta
        ArrayAdapter<CharSequence> anio_puerta_adapter = ArrayAdapter.createFromResource(this,
                R.array.anio_puerta, android.R.layout.simple_spinner_item);
        anio_puerta_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anio_puerta_spinner.setAdapter(anio_puerta_adapter);

        anio_puerta_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                anio_puerta = anio_puerta_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_puerta = "No seleccionado";
            }
        });


        //Estado del carril
        ArrayAdapter<CharSequence> estado_carril_adapter = ArrayAdapter.createFromResource(this,
                R.array.estado_carril, android.R.layout.simple_spinner_item);
        estado_carril_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_carril_spinner.setAdapter(estado_carril_adapter);

        estado_carril_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                estado_carril = estado_carril_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                estado_carril = "No seleccionado";
            }
        });

        //Estado carros
        ArrayAdapter<CharSequence> estado_carros_adapter = ArrayAdapter.createFromResource(this,
                R.array.estado_carros, android.R.layout.simple_spinner_item);
        estado_carros_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_carros_spinner.setAdapter(estado_carros_adapter);

        estado_carros_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                estado_carros = estado_carros_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                estado_carros = "No seleccionado";
            }
        });

        //Motor
        ArrayAdapter<CharSequence> motor_adapter = ArrayAdapter.createFromResource(this,
                R.array.motor_averia, android.R.layout.simple_spinner_item);
        motor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        motor_averia_spinner.setAdapter(motor_adapter);
        motor_averia_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                motor_averia = motor_averia_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                motor_averia = "No seleccionado";
            }
        });

        //Electrónica
        ArrayAdapter<CharSequence> electronica_adapter = ArrayAdapter.createFromResource(this,
                R.array.electronica, android.R.layout.simple_spinner_item);
        electronica_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        electronica_spinner.setAdapter(electronica_adapter);

        electronica_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                electronica = electronica_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                electronica = "No seleccionado";
            }
        });

        //Correa
        ArrayAdapter<CharSequence> correa_adapter = ArrayAdapter.createFromResource(this,
                R.array.correa, android.R.layout.simple_spinner_item);
        correa_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        correa_spinner.setAdapter(correa_adapter);
        correa_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                correa = correa_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                correa = "No seleccionado";
            }
        });

        //Cerrojo
        ArrayAdapter<CharSequence> cerrojo_adapter = ArrayAdapter.createFromResource(this,
                R.array.cerrojo, android.R.layout.simple_spinner_item);
        cerrojo_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cerrojo_spinner.setAdapter(cerrojo_adapter);

        cerrojo_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cerrojo = cerrojo_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                cerrojo = "No seleccionado";
            }
        });

        //Selector
        ArrayAdapter<CharSequence> selector_adapter = ArrayAdapter.createFromResource(this,
                R.array.selector, android.R.layout.simple_spinner_item);
        selector_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selector_spinner.setAdapter(selector_adapter);
        selector_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selector = selector_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selector = "No seleccionado";
            }
        });

        //Hojas
        ArrayAdapter<CharSequence> hojas_adapter = ArrayAdapter.createFromResource(this,
                R.array.hojas, android.R.layout.simple_spinner_item);
        hojas_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hojas_spinner.setAdapter(hojas_adapter);

        hojas_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                hojas = hojas_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                hojas = "No seleccionado";
            }
        });

        //Mecanismo completo
        ArrayAdapter<CharSequence> mecanismo_completo_adapter = ArrayAdapter.createFromResource(this,
                R.array.mecanismo_completo, android.R.layout.simple_spinner_item);
        mecanismo_completo_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mecanismo_completo_spinner.setAdapter(mecanismo_completo_adapter);

        mecanismo_completo_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                mecanismo_completo = mecanismo_completo_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                mecanismo_completo = "No seleccionado";
            }
        });


        ArrayAdapter<CharSequence> cajon_adapter = ArrayAdapter.createFromResource(this,
                R.array.cajon, android.R.layout.simple_spinner_item);
        cajon_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cajon_spinner.setAdapter(cajon_adapter);

        cajon_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cajon = cajon_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                cajon = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> deteccion_adapter = ArrayAdapter.createFromResource(this,
                R.array.deteccion, android.R.layout.simple_spinner_item);
        deteccion_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deteccion_spinner.setAdapter(deteccion_adapter);

        deteccion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                deteccion = deteccion_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                deteccion = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> proteccion_adapter = ArrayAdapter.createFromResource(this,
                R.array.proteccion, android.R.layout.simple_spinner_item);
        proteccion_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteccion_spinner.setAdapter(proteccion_adapter);

        proteccion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                proteccion = proteccion_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                proteccion = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> tiempo_trabajo_adapter = ArrayAdapter.createFromResource(this,
                R.array.tiempo_trabajo, android.R.layout.simple_spinner_item);
        tiempo_trabajo_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tiempo_trabajo_spinner.setAdapter(tiempo_trabajo_adapter);

        tiempo_trabajo_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                tiempo_trabajo = tiempo_trabajo_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                tiempo_trabajo = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> bateria_adapter = ArrayAdapter.createFromResource(this,
                R.array.bateria, android.R.layout.simple_spinner_item);
        bateria_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bateria_spinner.setAdapter(bateria_adapter);

        bateria_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                bateria = bateria_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                bateria = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> guias_adapter = ArrayAdapter.createFromResource(this,
                R.array.guias, android.R.layout.simple_spinner_item);
        guias_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        guias_spinner.setAdapter(guias_adapter);

        guias_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                guias = guias_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                guias = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> gomas_adapter = ArrayAdapter.createFromResource(this,
                R.array.gomas, android.R.layout.simple_spinner_item);
        gomas_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gomas_spinner.setAdapter(gomas_adapter);

        gomas_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                gomas = gomas_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                gomas = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> cableado_adapter = ArrayAdapter.createFromResource(this,
                R.array.cableado, android.R.layout.simple_spinner_item);
        cableado_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cableado_spinner.setAdapter(cableado_adapter);

        cableado_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cableado = cableado_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                cableado = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> desbloqueo_adapter = ArrayAdapter.createFromResource(this,
                R.array.desbloqueo, android.R.layout.simple_spinner_item);
        desbloqueo_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desbloqueo_spinner.setAdapter(desbloqueo_adapter);

        desbloqueo_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                desbloqueo = desbloqueo_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                desbloqueo = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_radares_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_radares, android.R.layout.simple_spinner_item);
        modelo_radares_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_radares_spinner.setAdapter(modelo_radares_adapter);

        modelo_radares_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_radares = modelo_radares_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_radares = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_baterias_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_baterias, android.R.layout.simple_spinner_item);
        modelo_baterias_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_baterias_spinner.setAdapter(modelo_baterias_adapter);

        modelo_baterias_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_baterias = modelo_baterias_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_baterias = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_electronicas_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_electronicas, android.R.layout.simple_spinner_item);
        modelo_electronicas_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_electronicas_spinner.setAdapter(modelo_electronicas_adapter);

        modelo_electronicas_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_electronicas = modelo_electronicas_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_electronicas = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_cerrojos_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_cerrojos, android.R.layout.simple_spinner_item);
        modelo_cerrojos_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_cerrojos_spinner.setAdapter(modelo_cerrojos_adapter);

        modelo_cerrojos_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_cerrojos = modelo_cerrojos_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_cerrojos = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_motores_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_motores, android.R.layout.simple_spinner_item);
        modelo_motores_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_motores_spinner.setAdapter(modelo_motores_adapter);

        modelo_motores_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_motores = modelo_motores_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_motores = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_guias_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_guias, android.R.layout.simple_spinner_item);
        modelo_guias_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_guias_spinner.setAdapter(modelo_guias_adapter);

        modelo_guias_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_guias = modelo_guias_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_guias = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_selectores_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelos_selectores, android.R.layout.simple_spinner_item);
        modelo_selectores_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_selectores_spinner.setAdapter(modelo_selectores_adapter);

        modelo_selectores_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_selectores = modelo_selectores_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_selectores = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_carros_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_carros, android.R.layout.simple_spinner_item);
        modelo_carros_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_carros_spinner.setAdapter(modelo_carros_adapter);

        modelo_carros_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_carros = modelo_carros_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_carros = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_correas_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_correas, android.R.layout.simple_spinner_item);
        modelo_correas_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_correas_spinner.setAdapter(modelo_correas_adapter);

        modelo_correas_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_correas = modelo_correas_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_correas = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_poleas_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_poleas, android.R.layout.simple_spinner_item);
        modelo_poleas_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_poleas_spinner.setAdapter(modelo_poleas_adapter);

        modelo_poleas_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_poleas = modelo_poleas_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_poleas = "No seleccionado";
            }
        });

        ArrayAdapter<CharSequence> modelo_puertas_adapter = ArrayAdapter.createFromResource(this,
                R.array.modelo_puertas, android.R.layout.simple_spinner_item);
        modelo_puertas_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelo_puertas_spinner.setAdapter(modelo_puertas_adapter);

        modelo_puertas_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                modelo_puertas = modelo_puertas_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                modelo_puertas = "No seleccionado";
            }
        });

    }
//
//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(AveriaActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (result == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private void requestPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(AveriaActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Toast.makeText(AveriaActivity.this, "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        } else {
//            ActivityCompat.requestPermissions(AveriaActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.e("value", "Permission Granted, Now you can use local drive .");
//                } else {
//                    Log.e("value", "Permission Denied, You cannot use local drive .");
//                }
//                break;
//        }
//    }
//
//    private void addChildP(Paragraph childParagraph){
//        childParagraph.setAlignment(Element.ALIGN_CENTER);
//        paragraph.add(childParagraph);
//    }


}
