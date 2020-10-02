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
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class list_view_articulos extends AppCompatActivity {


    private FloatingActionMenu menu;
    private FloatingActionButton item1, item2,item3,item4, item5;



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
                    Toast.makeText(list_view_articulos.this, "Menú Abierto", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(list_view_articulos.this, "Menú Cerrado", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(list_view_articulos.this, MainActivity.class);
                startActivity(intent);
            }
        });


        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(list_view_articulos.this, consulta_spinner.class);
                startActivity(intent);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(list_view_articulos.this,list_view_articulos.class);
                startActivity(intent);
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(list_view_articulos.this, consulta_recyclerView.class);
                startActivity(intent);
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(list_view_articulos.this, datos.class);
                startActivity(intent);
            }
        });






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
        int id = item.getItemId();

        if(id==R.id.volver) {
            Intent spinnerActivity = new Intent(list_view_articulos.this, MainActivity.class);
            startActivity(spinnerActivity);
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

            //Acciones a realizar
            //Toast.makeText (this, "Has echo clic en opcion acerca", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, datos.class);
            startActivity(intent);



            return true;


        }else if(id==R.id.recycler){
            //Acciones a realizar
            //Toast.makeText (this, "Has echo clic en opcion recyclerview", Toast.LENGTH_LONG).show();

            Intent listViewActivity = new Intent(list_view_articulos.this, consulta_recyclerView.class);
            startActivity(listViewActivity);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }


}