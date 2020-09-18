package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class list_view_articulos extends AppCompatActivity {

    ListView listViewPersonas;
    ArrayAdapter adaptador;
    SearchView searchView;
    ArrayList<String> list;
    ArrayAdapter adapter;

    String[] version =
            {"Aestro", "Blender", "CupCake", "Donut", "Pizza", "Sandwich", "Oreo", "IceCream",
                    "Hamburger", "lemon", "lemonade", "chips"};

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();


   // private EditText ed_cod, ed_des, ed_pre;
   // private Button guardar, concod, condes, borrar, editar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_articulos);

      /*  ed_cod = (EditText) findViewById(R.id.ed_cod);
        ed_des = (EditText) findViewById(R.id.ed_des);
        ed_pre = (EditText) findViewById(R.id.ed_pre);
        guardar = (Button) findViewById(R.id.guardar);
        concod = (Button) findViewById(R.id.concod);
        condes = (Button) findViewById(R.id.condes);
        borrar = (Button) findViewById(R.id.borrar);
        editar = (Button) findViewById(R.id.editar);*/


        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas);
        searchView = (SearchView) findViewById(R.id.searchView);

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, conexion.consultaListaArticulos1());
        listViewPersonas.setAdapter(adaptador);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;

            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = s;
                adaptador.getFilter().filter(text);
                return false;
            }
        });

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion = "Código:" + conexion.consultaListaArticulos().get(pos).getCodigo() + "\n";
                informacion += "Descripción:" + conexion.consultaListaArticulos().get(pos).getDescripcion() + "\n";
                informacion += "Precio:" + conexion.consultaListaArticulos().get(pos).getPrecio();

                Dto articulos = conexion.consultaListaArticulos().get(pos);
                Intent intent = new Intent(list_view_articulos.this, detalle_articulos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("articulo", articulos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.volver){

            //Acciones a realizar
            //Toast.makeText (this, "Has echo clic en opcion acerca", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


            return true;

        }else if(id==R.id.action_listaArticulos) {
            Intent spinnerActivity = new Intent(list_view_articulos.this, consulta_spinner.class);
            startActivity(spinnerActivity);
            return true;


        }else if(id==R.id.action_listaArticulos1){

            Intent listViewActivity = new Intent(list_view_articulos.this, list_view_articulos.class);
            startActivity(listViewActivity);
            return true;



        }else if(id==R.id.acerca){


            Intent intent = new Intent(this, datos.class);
            startActivity(intent);



            return true;


        }



        return super.onOptionsItemSelected(item);
    }


}