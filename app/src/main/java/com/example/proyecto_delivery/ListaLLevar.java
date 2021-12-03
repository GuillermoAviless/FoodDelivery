package com.example.proyecto_delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.proyecto_delivery.Adaptadores.AdaptadorCarrito;

import java.util.ArrayList;
import java.util.List;

public class ListaLLevar extends AppCompatActivity {
    RecyclerView recyclerView;
    AdaptadorCarrito adaptador;
    List<Carrito> ListaCarrito=new ArrayList<Carrito>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_l_levar);
        recyclerView=findViewById(R.id.reciclerview_llevar);
        Carrito carrito=new Carrito();
        carrito.setImagen("asd");
        carrito.setSubTotal(10.1);
        carrito.setCantidad(1);
        carrito.setPrecio(1.2);
        carrito.setIdProducto(1);
        carrito.setProducto("hola");
        ListaCarrito.add(carrito);
        adaptador=new AdaptadorCarrito(ListaCarrito);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);
    }
}