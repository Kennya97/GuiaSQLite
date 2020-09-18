package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.DialogPreference;
import android.view.KeyEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



private EditText ed_cod, ed_des, ed_pre;
private Button guardar, concod,condes, borrar,editar;



boolean inputEt=false;
boolean inputEd=false;
boolean input1= false;

int resultadoInsert=0;

Modal ventanas = new Modal();

ConexionSQLite conexion = new ConexionSQLite(this);
Dto datos = new Dto();

AlertDialog.Builder dialogo;


@Override
public  boolean onKeyDown(int keyCode, KeyEvent event ) {
if (keyCode == KeyEvent.KEYCODE_BACK) {
 new android.app.AlertDialog.Builder(this)
.setIcon(R.drawable.ic_close)
.setTitle("Warning")
.setMessage("¿Realmente desea salir?")
.setNegativeButton(android.R.string.cancel, null)
.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
{

@Override
public void onClick(DialogInterface dialog, int which) {
finishAffinity();
}
})
.show();
return true;
}
return super.onKeyDown(keyCode, event);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_apagado));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor));
        toolbar.setTitleMargin(0,0,0,0);
       // toolbar.setSubtitle("CRUD SQLite");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));

        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

toolbar.setNavigationOnClickListener(new View.OnClickListener(){
@Override

    public void onClick(View view){
    confirmacion();
}

            });


     FloatingActionButton fab = findViewById(R.id.fab);
   fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
    // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            // .setAction("Action", null).show();

            ventanas.Search(MainActivity.this);
            }
        });

        ed_cod = (EditText) findViewById(R.id.ed_cod);
        ed_des = (EditText) findViewById(R.id.ed_des);
        ed_pre = (EditText) findViewById(R.id.ed_pre);
        guardar = (Button) findViewById(R.id.guardar);
        concod = (Button) findViewById(R.id.concod);
        condes = (Button) findViewById(R.id.condes);
        borrar = (Button) findViewById(R.id.borrar);
        editar = (Button) findViewById(R.id.editar);

        String senal ="";
        String codigo ="";
        String descripcion="";
        String precio="";


        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle!= null){
            codigo = bundle.getString("codigo");
            senal = bundle.getString("senal");
            descripcion = bundle.getString("descripcion");
            precio = bundle.getString("precio");

          if (senal.equals("1")){
          ed_cod.setText(codigo);
          ed_des.setText(descripcion);
          ed_pre.setText(precio);
}
}
}catch (Exception e){
}
}

private  void confirmacion (){
String mensaje="¿Realmente desea salir?";
dialogo = new AlertDialog.Builder(MainActivity.this);
dialogo.setIcon(R.drawable.ic_close);
dialogo.setTitle("Warning");
dialogo.setMessage(mensaje);
dialogo.setCancelable(false);
dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){

public  void onClick(DialogInterface dialogo, int id){
MainActivity.this.finish();
}
});

dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

    public void onClick(DialogInterface dialogo, int id) {

    }
});

dialogo.show();
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

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_limpiar){
            ed_cod.setText(null);
            ed_des.setText(null);
            ed_pre.setText(null);


            return true;


        }else */if(id==R.id.action_listaArticulos) {
            Intent spinnerActivity = new Intent(MainActivity.this, consulta_spinner.class);
            startActivity(spinnerActivity);
            return true;


        }else if(id==R.id.action_listaArticulos1){

            Intent listViewActivity = new Intent(MainActivity.this, list_view_articulos.class);
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


    public void alta(View view){
    if (ed_cod.getText().toString().length()==0){
    ed_cod.setError("Campo obligatorio");
    inputEt= false;

    }else{
        inputEt=true;
    }
        if (ed_des.getText().toString().length()==0) {
            ed_des.setError("Campo obligatorio");
            inputEd = false;

        }else{
            inputEd=true;
        }
        if (ed_pre.getText().toString().length()==0) {
            ed_pre.setError("Campo obligatorio");
            input1 = false;

        }else{
        input1=true;
    }
    if(inputEt&& inputEd && input1) {
        try {
            datos.setCodigo(Integer.parseInt(ed_cod.getText().toString()));
            datos.setDescripcion(ed_des.getText().toString());
            datos.setPrecio(Double.parseDouble(ed_pre.getText().toString()));

            if (conexion.InsertRegister(datos)) {


                Toast.makeText(this, "Registro agregado sastifactoriamente", Toast.LENGTH_SHORT).show();
                limpiarDatos();


            } else {
                Toast.makeText(getApplicationContext(), "Error ya existe un registro\n " + "Código: " + ed_cod.getText().toString(), Toast.LENGTH_LONG).show();
                limpiarDatos();

            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR ya Existe.", Toast.LENGTH_SHORT).show();
        }

    }
        }

        public void mensaje(String mensaje){
            Toast.makeText(this, ""+mensaje, Toast.LENGTH_SHORT).show();
        }

        public  void limpiarDatos(){
    ed_cod.setText(null);
    ed_des.setText(null);
    ed_pre.setText(null);
    ed_cod.requestFocus();

        }



        public void consultaporcodigo(View view){
            if (ed_cod.getText().toString().length()==0){
                ed_cod.setError("Campo obligatorio");
                inputEt= false;

            }else{
                inputEt=true;
            }

            if (inputEt){
                String codigo= ed_cod.getText().toString();
                datos.setCodigo(Integer.parseInt(codigo));


                if (conexion.consultaArticulos(datos)){
                    ed_des.setText(datos.getDescripcion());
                    ed_pre.setText(""+datos.getPrecio());
                }else{
                    Toast.makeText(this, "No existe un articulo con este código", Toast.LENGTH_SHORT).show();
            limpiarDatos();
                }


    }else{
                Toast.makeText(this, "Ingrese el código del articulo a buscar. ", Toast.LENGTH_SHORT).show();
            }

        }

        public void consultapordescripcion(View v){
            if (ed_des.getText().toString().length()==0) {
                ed_des.setError("Campo obligatorio");
                inputEd = false;

            }else{
                inputEd=true;
        }
            if (inputEd) {
                String descripcion = ed_des.getText().toString();
                datos.setDescripcion(descripcion);

                if (conexion.consultarDescripcion(datos)) {
                    ed_cod.setText("" + datos.getCodigo());
                    ed_des.setText(datos.getDescripcion());
                    ed_pre.setText("" + datos.getPrecio());
                } else {
                    Toast.makeText(this, "No existe ningun articulo con dicha descripción", Toast.LENGTH_SHORT).show();

                    limpiarDatos();
                }
            }else {
                Toast.makeText(this, "Ingrese la descripción del articulo a buscar", Toast.LENGTH_SHORT).show();
            }
            }

            public void  bajaporcodigo(View v){
                if (ed_cod.getText().toString().length()==0){
                    ed_cod.setError("Campo obligatorio");
                    inputEt= false;

                }else{
                    inputEt=true;
                }


                if (inputEt){
                    String cod = ed_cod.getText().toString();
                    datos.setCodigo(Integer.parseInt(cod));
                    if (conexion.bajaCodigo(MainActivity.this,datos)){
                        limpiarDatos();
                    }else {
                        Toast.makeText(this, "  No existe  articulo con dicho código.", Toast.LENGTH_SHORT).show();
                   limpiarDatos();
                    }

                }

            }

            public  void modificacion(View v){
                if (ed_cod.getText().toString().length()==0){
                    ed_cod.setError("Campo obligatorio");
                    inputEt= false;

                }else{
                    inputEt=true;
                }

                if (inputEt){
                    String cod= ed_cod.getText().toString();
                    String descripcion = ed_des.getText().toString();
                    double precio = Double.parseDouble(ed_pre.getText().toString());

                    datos.setCodigo(Integer.parseInt(cod));
                    datos.setDescripcion(descripcion);
                    datos.setPrecio(precio);


                    if (conexion.modificar(datos)){
                        Toast.makeText(this, "Registro modificado correctamente", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "No se han encontrado resultados para la busqueda especificada ", Toast.LENGTH_SHORT).show();
                    }

                }
            }

    }











