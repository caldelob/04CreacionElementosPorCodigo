package com.example.a04creacionelementosporcodigo;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04creacionelementosporcodigo.Modelos.Alumno;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import com.example.a04creacionelementosporcodigo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding; // es una clase automática;


    //1. Contenedor donde mostrar la info --> Scroll con un linear dentro

    //2. Lógica para pintar los elementos --> una función que llamaremos "pintar elementos"

    //3. Conjuntos de datos

    //Plantilla para mostrar los datos

    private ArrayList<Alumno> alumnosList;

    private ActivityResultLauncher<Intent> launcherCrearAlumnos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alumnosList = new ArrayList<>();
        inicializaLaunchers();


        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              launcherCrearAlumnos.launch(new Intent(MainActivity.this, AddAlumnoActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        launcherCrearAlumnos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==RESULT_OK){
                            if(result.getData()!= null && result.getData().getExtras()!=null){
                                Alumno alumno = (Alumno) result.getData().getExtras().getSerializable("ALUMNO");
                                alumnosList.add(alumno);
                                pintarElementos();
                            }
                        }
                    }
                }
        );
    }

    private void pintarElementos() {
        binding.content.contenedor.removeAllViews(); //elimina todos los elementos que tiene dentro
        for (Alumno alumno:alumnosList) {
            View alumnoView = LayoutInflater.from(MainActivity.this).inflate(R.layout.alumno_model_view, null); //aqui meto el xml que hay en alumno_model_view
            TextView lblNombre = alumnoView.findViewById(R.id.lblNombreView);
            TextView lblApellidos = alumnoView.findViewById(R.id.lblApellidoView);
            TextView lblCiclo = alumnoView.findViewById(R.id.lblCicloView);
            TextView lblgrupo = alumnoView.findViewById(R.id.lblGrupoView);

            lblNombre.setText(alumno.getNombre());
            lblApellidos.setText(alumno.getApellidos());
            lblCiclo.setText(alumno.getCiclo());
            lblgrupo.setText(String.valueOf(alumno.getGrupo()));

            binding.content.contenedor.addView(alumnoView);
        }

    }


}