package com.example.proyecto_delivery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto_delivery.Adaptadores.AdaptadorProducto;
import com.example.proyecto_delivery.Clases.classFactura;
import com.example.proyecto_delivery.Entidades.Producto;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Editar_Detalles extends AppCompatActivity {

    private Double Precio;
    private int contador;
    private AdaptadorProducto adaptador;
    private List<Producto> lista=new ArrayList<Producto>();
    private double TotalVenta;
    private LinearLayoutManager manager;
    private RecyclerView listaProducto;
    private RecyclerView listaInformacion;
    private Integer Cantidad;
    private double precio_unitario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_detalles);
        adaptador=new AdaptadorProducto(lista);

        final String imagen=getIntent().getStringExtra(ListaProducto.ID_IMAGEN);
        this.Precio=getIntent().getDoubleExtra(ListaCarrito.ID_PRECIO,0);
        final String descripcion=getIntent().getStringExtra(ListaCarrito.ID_DESCRIPCION);
        final String cantidad=getIntent().getStringExtra(ListaCarrito.ID_DATO);
        final String titulo=getIntent().getStringExtra(ListaCarrito.ID_TITULO);
        final ImageView imgProducto=findViewById(R.id.imgProducto);
        contador=getIntent().getIntExtra(ListaCarrito.ID_DATO,0);
        precio_unitario=getIntent().getDoubleExtra(ListaCarrito.ID_VALOR,00);
        TextView lbltitulo=findViewById(R.id.lblTitulo);
        TextView lbldescripcion=findViewById(R.id.lblDescripcion);
        final TextView lblPrecio=findViewById(R.id.lblPrecio);
        Button btnAgregar=findViewById(R.id.btnAgregar);
        Button btnMas = findViewById(R.id.btnMas);
        Button btnMenos = findViewById(R.id.btnMenos);
        final TextView txtCantidad = findViewById(R.id.txtCantidad);

        Button btnActualizar = findViewById(R.id.btnActualizar);
        Button btnEliminar = findViewById(R.id.btnEliminar);
        //Toast.makeText(this, "contador: "+precio_unitario, Toast.LENGTH_SHORT).show();
        lbltitulo.setText(titulo);
        lbldescripcion.setText(descripcion);
        txtCantidad.setText(""+contador);

        lblPrecio.setText("$ "+ Double.toString(this.Precio));
        Picasso.get().load(imagen).error(R.mipmap.ic_launcher_round).fit().centerInside().into((ImageView) imgProducto);
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                txtCantidad.setText(""+contador);
                //DecimalFormat df = new DecimalFormat("#.00");
                lblPrecio.setText("$ "+ String.format("%.2f", contador*precio_unitario));
            }
        });
        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //contador=(contador>1)?contador--:contador;
                if(contador>1)
                    contador--;
                txtCantidad.setText(""+contador);
                lblPrecio.setText("$ "+ String.format("%.2f", contador*precio_unitario));
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Editar_Detalles.this);
                builder.setTitle("Confirmacion");
                builder.setMessage("¿Desea modificar el producto?");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int i=getIntent().getIntExtra(ListaCarrito.ID_ITERADOR,0);
                        Producto p=ListaInformacion.ListaCarrito.get(i);
                        p.setPrecio(precio_unitario*contador);
                        p.setDato(contador);
                        ListaCarrito.adaptador.notifyDataSetChanged();
                        ListaCarrito.CalcularTotal();
                        finish();
                    }

                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();



            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Editar_Detalles.this);
                builder.setTitle("Avertencia");
                builder.setMessage("¿Esta seguro que desea eliminar el producto?");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int i=getIntent().getIntExtra(ListaCarrito.ID_ITERADOR,0);
                        ListaInformacion.ListaCarrito.remove(i);
                        ListaCarrito.adaptador.notifyDataSetChanged();
                        ListaCarrito.CalcularTotal();
                        finish();

                    }

                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
    }

    private int ObtenerIdFactura(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String fecha=dateFormat.format(date);
        int respuesta=-1;
        classFactura factura=new classFactura(this);
        factura.setIdCliente(ListaInformacion.IdCliente);
        factura.setTotal(this.TotalVenta);
        factura.setCantidadProductos(lista.size());
        factura.setFecha(fecha);
        if(factura.Insert()){
            respuesta=factura.getIdFactura();
        }
        return respuesta;
    }
}