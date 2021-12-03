package com.example.proyecto_delivery;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_delivery.Adaptadores.AdaptadorInformacion;
import com.example.proyecto_delivery.Clases.classFactura;
import com.example.proyecto_delivery.Entidades.Factura;
import com.example.proyecto_delivery.Entidades.Informacion;
import com.example.proyecto_delivery.Entidades.Producto;

import java.util.ArrayList;
import java.util.List;

//Toolbar Librerias
//Otras

public class ListaInformacion extends AppCompatActivity {
    //Variables de sesion....
    public static List<Producto> ListaCarrito=new ArrayList<Producto>();
    public static List<Factura> ListaFactura=new ArrayList<>();
    public static String Nombres;
    public static int IdCliente;
    public static String Telefono;
    public static String Correo;
    public static String Direccion;
    public static String Usuario;
    public static boolean Modificar=false;
    Toolbar toolbar;
    private List<Informacion> lista=new ArrayList<Informacion>();
    public static final String ID_POLLO="Pollo";
    public static final String ID_HAMBURGUESA="Hamburguesas";
    public static final String ID_PIZZA="Pizzas";
    public static final String ID_PROMOCION="Promociones";
    public static final String ID_HISTORIAL="Historial de compras";
    private AdaptadorInformacion adaptador;
    private LinearLayoutManager manager;
    private RecyclerView listaInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_informacion);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.listaInformacion=findViewById(R.id.listInformacion);
        manager=new LinearLayoutManager(this);
        adaptador=new AdaptadorInformacion(lista);
        CargarInformacion();
        this.listaInformacion.setHasFixedSize(true);
        this.listaInformacion.setLayoutManager(manager);
        this.listaInformacion.setAdapter(adaptador);

        //Toolbar
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu Principal ");


        this.adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(),"Mensaje: "
                        +lista.get(listaInformacion.getChildAdapterPosition(view)).getTitulo(),Toast.LENGTH_SHORT).show();*/
                String titulo=lista.get(listaInformacion.getChildAdapterPosition(view)).getTitulo();
                if(titulo.equals(ID_HISTORIAL)){
                    CargarFacturas();
                    if(ListaFactura.size()>0){
                        Intent intn=new Intent(ListaInformacion.this, ListaFactura.class);
                        startActivity(intn);
                    }else{
                        AlertDialog.Builder builder=new AlertDialog.Builder(ListaInformacion.this);
                        builder.setTitle("Mensaje");
                        builder.setMessage("Todavia no ha realizado compras");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.show();
                    }
                }else{
                    Intent intn=new Intent(ListaInformacion.this, ListaProducto.class);
                    intn.putExtra("ID",titulo);
                    startActivity(intn);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        //item.add
        switch(item.getItemId()){
            case R.id.carrito:
                if(ListaInformacion.ListaCarrito.size()>0){
                    Intent intn=new Intent(ListaInformacion.this,ListaCarrito.class);
                    startActivity(intn);
                }else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(ListaInformacion.this);
                    builder.setTitle("Mensaje");
                    builder.setMessage("No hay productos agregados al carrito");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
                break;
            case R.id.persona:
                Intent intn=new Intent(ListaInformacion.this,PerfilActivity.class);
                startActivity(intn);
                break;
        }
        return true;
    }


    private void CargarInformacion(){
        /*for(int i=0;i<15;i++){
            Informacion info=new Informacion();
            info.setTitulo("Titulo"+Integer.toString(i));
            info.setSubTitulo("SubTitulo"+Integer.toString(i));
            info.setDescripcion("Descripcion"+Integer.toString(i));
            info.setImagen("https://developer.android.com/studio/images/write/tools-attribute-context_2x.png?hl=es");
            lista.add(info);
        }*/
        Informacion info=new Informacion();
        info.setTitulo(ID_PROMOCION);
        info.setSubTitulo("Disfruta de las mejores ofertas que tenemos para ti!");
        info.setDescripcion("Promociones por tiempo limitado, limitada a cobertura geografica");
        info.setImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTQ-ijWhM4fhOHN0IDGXRJzO5D-67xAeCget9WKf3mOz1RsTzCLFgkara3VksMhdVo4c8&usqp=CAU");
        lista.add(info);

        Informacion info1=new Informacion();
        info1.setTitulo(ID_HAMBURGUESA);
        info1.setSubTitulo("Disfruta hamburguesas de todos precios y todos los gustos !");
        info1.setDescripcion("Promociones por tiempo limitado, limitada a cobertura geografica");
        info1.setImagen("https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2020/04/recetas-de-hamburguesas-caseras-de-carne-molida.jpg");
        lista.add(info1);

        Informacion info2=new Informacion();
        info2.setTitulo(ID_PIZZA);
        info2.setSubTitulo("Disfruta tus pizzas de todos tamaños !");
        info2.setDescripcion("Aprovecha tus pizzas de todos los tamaños, a precios accesibles");
        info2.setImagen("https://t2.rg.ltmcdn.com/es/images/1/9/3/pizza_casera_31391_orig.jpg");
        lista.add(info2);

        Informacion info3=new Informacion();
        info3.setTitulo(ID_POLLO);
        info3.setSubTitulo("Disfruta de pollo frito, muchos combos para ti !");
        info3.setDescripcion("Aprovecha tus combos de pollo frito, a precios accesibles");
        info3.setImagen("https://pc-gt-cdn.s3.amazonaws.com/menu-images/prod-a/9fd658ba-7290-404f-a967-35eba279ebf0.png");
        lista.add(info3);

        Informacion info4=new Informacion();
        info4.setTitulo(ID_HISTORIAL);
        info4.setSubTitulo("Historial de todas tus compras realizadas!");
        info4.setDescripcion("Puedes verificar tus pedidos, realizados anteriormente");
        info4.setImagen("https://t-position.com/wp-content/uploads/2021/02/crear-una-tienda-online.jpg");
        lista.add(info4);

    }
    private void CargarFacturas(){
        this.ListaFactura.clear();
        try{
            int i=0;
            List <classFactura> listaFactura=new ArrayList<>();
            classFactura factura=new classFactura(this);
            factura.setIdCliente(ListaInformacion.this.IdCliente);
            listaFactura = factura.GetAllFacturas();
            for(classFactura fct : listaFactura ){
                Factura fct2=new Factura();
                fct2.setIdFactura(fct.getIdFactura());
                fct2.setFecha(fct.getFecha());
                fct2.setCantidad(fct.getCantidadProductos());
                fct2.setTotal(fct.getTotal());
                this.ListaFactura.add(fct2);
                //Toast.makeText(this, pro.getProducto()+" "+(++i), Toast.LENGTH_SHORT).show();
            }
        }catch (SQLiteException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}