package com.example.proyecto_delivery;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_delivery.Entidades.Producto;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PizzaDtActivity extends AppCompatActivity {
    private List<Producto> lista=new ArrayList<Producto>();
    private RecyclerView listaProducto;
    LinearLayoutManager manager;
     private Double Precio;
     private Integer contador=1;
     private Double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_dt);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final String imagen=getIntent().getStringExtra(ListaProducto.ID_IMAGEN);
        this.Precio=getIntent().getDoubleExtra(ListaProducto.ID_PRECIO,0);
        final String descripcion=getIntent().getStringExtra(ListaProducto.ID_DESCRIPCION);
        final String titulo=getIntent().getStringExtra(ListaProducto.ID_TITULO);
        final ImageView imgProducto=findViewById(R.id.imgProducto);

        TextView lbltitulo=findViewById(R.id.lblTitulo);
        TextView lbldescripcion=findViewById(R.id.lblDescripcion);
        final TextView lblPrecio=findViewById(R.id.lblPrecio);
        Button btnAgregar=findViewById(R.id.btnAgregar);
        Button btnMas = findViewById(R.id.btnMas);
        Button btnMenos = findViewById(R.id.btnMenos);
        final TextView txtCantidad = findViewById(R.id.txtCantidad);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador>=1){
                    Producto aux=new Producto();
                    txtCantidad.setText(contador.toString());
                    Total=Precio*contador;
                    lblPrecio.setText("$ "+String.format("%.2f", Total));
                    aux.setProducto(titulo);
                    aux.setDescripcion(descripcion);
                    aux.setPrecio(Total);
                    aux.setValor(Precio);
                    aux.setDato(contador);
                    aux.setImagen(imagen);
                    ListaInformacion.ListaCarrito.add(aux);
                    AlertDialog.Builder builder=new AlertDialog.Builder(PizzaDtActivity.this);
                    builder.setTitle("Seleccion");
                    builder.setMessage("El producto fue agregado al carrito de compras");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })/*.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })*/;
                    AlertDialog dialog=builder.create();
                    dialog.show();
                    //Toast.makeText(PizzaDtActivity.this,pr.getProducto(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText( PizzaDtActivity.this, "Indique la cantida de productos que desea agregar",  Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        lbltitulo.setText(titulo);
        txtCantidad.setText(contador.toString());
        lbldescripcion.setText(descripcion);
        lblPrecio.setText("$ "+Double.toString(this.Precio));
        Picasso.get().load(imagen).error(R.mipmap.ic_launcher_round).fit().centerInside().into((ImageView) imgProducto);

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DecimalFormat df = new DecimalFormat("#.00");
                contador++;
                txtCantidad.setText(contador.toString());
                Total=Precio*contador;
                lblPrecio.setText("$ "+String.format("%.2f", Total));

            }

        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador <= 0){

                    txtCantidad.setText("0");
                    lblPrecio.setText("$0.00");


                }
                else{
                    contador--;
                    txtCantidad.setText(contador.toString());
                    Total=Precio*contador;
                    lblPrecio.setText("$ "+String.format("%.2f", Total));
                }

            }
        });

        };


    }

