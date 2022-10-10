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

import android.view.View;

import com.example.a04creacionelementosporcodigo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding; // es una clase automática;


    //1. Contenedor donde mostrar la info --> Scroll con un linear dentro

    //2. Lógica para pintar los elementos --> una función que llamaremos "pintar elementos"

    //3. Conjuntos de datos

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
        

    }


}