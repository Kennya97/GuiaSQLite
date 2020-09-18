package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class consulta_spinner extends AppCompatActivity {

  //  private EditText ed_cod, ed_des, ed_pre;
    //private Button guardar, concod, condes, borrar, editar;
    private Spinner sp_options;
    private TextView tv_cod, tv_descripcion, tv_precio;


    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);


        /*ed_cod = (EditText) findViewById(R.id.ed_cod);
        ed_des = (EditText) findViewById(R.id.ed_des);
        ed_pre = (EditText) findViewById(R.id.ed_pre);
        guardar = (Button) findViewById(R.id.guardar);
        concod = (Button) findViewById(R.id.concod);
        condes = (Button) findViewById(R.id.condes);
        borrar = (Button) findViewById(R.id.borrar);
        editar = (Button) findViewById(R.id.editar);*/








        sp_options = (Spinner) findViewById(R.id.sp_options);
        tv_cod = (TextView) findViewById(R.id.tv_cod);
        tv_descripcion = (TextView) findViewById(R.id.tv_descripcion);
        tv_precio = (TextView) findViewById(R.id.tv_precio);

        conexion.consultaListaArticulos();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, conexion.obtenerListaArticulos());

        sp_options.setAdapter(adaptador);

        sp_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if (position != 0) {
                    tv_cod.setText("Código: " + conexion.consultaListaArticulos().get(position - 1).getCodigo());
                    tv_descripcion.setText("Descripción: " + conexion.consultaListaArticulos().get(position - 1).getDescripcion());
                    tv_precio.setText("Precio: " + String.valueOf(conexion.consultaListaArticulos().get(position - 1).getPrecio()));
                } else {
                    tv_cod.setText("Código: ");
                    tv_descripcion.setText("Descripción: ");
                    tv_precio.setText("Precio: ");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
            Intent spinnerActivity = new Intent(consulta_spinner.this, consulta_spinner.class);
            startActivity(spinnerActivity);
            return true;


        }else if(id==R.id.action_listaArticulos1){

            Intent listViewActivity = new Intent(consulta_spinner.this, list_view_articulos.class);
            startActivity(listViewActivity);
            return true;



        }else if(id==R.id.acerca){

            //Acciones a realizar
            //Toast.makeText (this, "Has echo clic en opcion acerca", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, datos.class);
            startActivity(intent);



            return true;



    }


        return super.onOptionsItemSelected(item);
    }



}