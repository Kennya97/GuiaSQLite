package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class consulta_recyclerView extends AppCompatActivity {

    private RecyclerView recycler_view;

    private Clase_AdaptadorArticulos adaptadorArticulos;
    ConexionSQLite datos = new ConexionSQLite(consulta_recyclerView.this);


    private FloatingActionMenu menu;
    private FloatingActionButton item1, item2,item3,item4, item5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_recycler_view);



        menu =  findViewById(R.id.fab_menu);
        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        item4=findViewById(R.id.item4);
        item5=findViewById(R.id.item5);


        menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened){
                    Toast.makeText(consulta_recyclerView.this, "Menú Abierto", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(consulta_recyclerView.this, "Menú Cerrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

/*
menu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
if (menu.isOpened()){
    menu.close(true);
}
    }
});
*/



        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(consulta_recyclerView.this, MainActivity.class);
                startActivity(intent);
            }
        });


        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(consulta_recyclerView.this, consulta_spinner.class);
                startActivity(intent);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(consulta_recyclerView.this,list_view_articulos.class);
                startActivity(intent);
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(consulta_recyclerView.this, consulta_recyclerView.class);
                startActivity(intent);
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(consulta_recyclerView.this, datos.class);
                startActivity(intent);
            }
        });











        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        recycler_view.setHasFixedSize(true);

    // Nuestro RecyclerView usará un linear layout manager
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

    //adaptadorArticulos = new AdaptadorArticulos(consulta_recyclerView.this, obtenerArticulos());
        adaptadorArticulos = new Clase_AdaptadorArticulos(consulta_recyclerView.this, datos.mostrarArticulos());
        recycler_view.setAdapter(adaptadorArticulos);

    }



    public List<Dto> obtenerArticulos() {
        List<Dto> articulos = new ArrayList<>();
        articulos.add(new Dto(1, "Laptop", 200.99));
        articulos.add(new Dto(2, "Impresora HP", 100.78));
        articulos.add(new Dto(3, "Disco Duro 1TB", 100.19));
        return articulos;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_limpiar){
            ed_cod.setText(null);
            ed_des.setText(null);
            ed_pre.setText(null);
            return true;
        }else */

        if(id==R.id.volver) {
            Intent spinnerActivity = new Intent(consulta_recyclerView.this, MainActivity.class);
            startActivity(spinnerActivity);
            return true;


        }else if(id==R.id.action_listaArticulos) {
            Intent spinnerActivity = new Intent(consulta_recyclerView.this, consulta_spinner.class);
            startActivity(spinnerActivity);
            return true;


        }else if(id==R.id.action_listaArticulos1){

            Intent listViewActivity = new Intent(consulta_recyclerView.this, list_view_articulos.class);
            startActivity(listViewActivity);
            return true;



        }else if(id==R.id.acerca){

            //Acciones a realizar
            //Toast.makeText (this, "Has echo clic en opcion acerca", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, datos.class);
            startActivity(intent);



            return true;


        }else if(id==R.id.recycler){
            //Acciones a realizar
            //Toast.makeText (this, "Has echo clic en opcion recyclerview", Toast.LENGTH_LONG).show();

            Intent listViewActivity = new Intent(consulta_recyclerView.this, consulta_recyclerView.class);
            startActivity(listViewActivity);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

}
