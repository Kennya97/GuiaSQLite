package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class detalle_articulos extends AppCompatActivity {

    private TextView tv_codigo, tv_descripcion, tv_precio;
    private TextView tv_codigo1, tv_descripcion1, tv_precio1, tv_fecha;


    private FloatingActionMenu menu;
    private FloatingActionButton item1, item2,item3,item4, item5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulos);




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
                    Toast.makeText(detalle_articulos.this, "Menú Abierto", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(detalle_articulos.this, "Menú Cerrado", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(detalle_articulos.this, MainActivity.class);
                startActivity(intent);
            }
        });


        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(detalle_articulos.this, consulta_spinner.class);
                startActivity(intent);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(detalle_articulos.this,list_view_articulos.class);
                startActivity(intent);
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(detalle_articulos.this, consulta_recyclerView.class);
                startActivity(intent);
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(detalle_articulos.this, datos.class);
                startActivity(intent);
            }
        });



        tv_codigo = (TextView) findViewById(R.id.tv_codigo);
        tv_descripcion = (TextView) findViewById(R.id.tv_descripcion);
        tv_precio = (TextView) findViewById(R.id.tv_precio);

        tv_codigo1 = (TextView) findViewById(R.id.tv_codigo1);
        tv_descripcion1 = (TextView) findViewById(R.id.tv_descripcion1);
        tv_precio1 = (TextView) findViewById(R.id.tv_precio1);
        tv_fecha = (TextView) findViewById(R.id.tv_fecha);

        Bundle objeto = getIntent().getExtras();
        Dto dto = null;
        if (objeto != null) {
            dto = (Dto) objeto.getSerializable("articulo");
            tv_codigo.setText("" + dto.getCodigo());
            tv_descripcion.setText(dto.getDescripcion());
            tv_precio.setText(String.valueOf(dto.getPrecio()));

            tv_codigo1.setText("" + dto.getCodigo());
            tv_descripcion1.setText(dto.getDescripcion());
            tv_precio1.setText(String.valueOf(dto.getPrecio()));
            tv_fecha.setText("Fecha de creación: " + getDateTime());

        }


    }

    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
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
            Intent spinnerActivity = new Intent(detalle_articulos.this, MainActivity.class);
            startActivity(spinnerActivity);
            return true;


        }else if(id==R.id.action_listaArticulos) {
            Intent spinnerActivity = new Intent(detalle_articulos.this, consulta_spinner.class);
            startActivity(spinnerActivity);
            return true;


        }else if(id==R.id.action_listaArticulos1){

            Intent listViewActivity = new Intent(detalle_articulos.this, list_view_articulos.class);
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

            Intent listViewActivity = new Intent(detalle_articulos.this, consulta_recyclerView.class);
            startActivity(listViewActivity);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}