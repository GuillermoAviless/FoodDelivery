package com.example.proyecto_delivery.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_delivery.Carrito;
import com.example.proyecto_delivery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorCarrito extends RecyclerView.Adapter<ViewHolderCarrito> implements View.OnClickListener{
    private List<Carrito> lista;
    private View.OnClickListener listener;

    public AdaptadorCarrito(List<Carrito> lst){
        this.lista=lst;
    }


    @NonNull
    @Override
    public ViewHolderCarrito onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_carrito_xml, parent, false);
        Toast.makeText(parent.getContext(), "asdf", Toast.LENGTH_SHORT).show();
        vista.setOnClickListener(this);
        ViewHolderCarrito vhInformacion=new ViewHolderCarrito(vista);
        return vhInformacion;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCarrito holder, int position) {
        Carrito item=lista.get(position);
        holder.getLblProducto().setText(item.getProducto());
        holder.getLblPrecioUnitario().setText(Double.toString(item.getPrecio()));
        holder.getLblCantidad().setText(Integer.toString(item.getCantidad()));
        holder.getLblSubTotal().setText(Double.toString(item.getSubTotal()));
        Picasso.get().load(item.getImagen()).error(R.mipmap.ic_launcher_round).fit().centerInside().into((ImageView) holder.getImagen());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if(this.listener!=null){
            this.listener.onClick(view);
        }
    }
    /*
    @NonNull
    @Override
    public ViewHolderCarrito onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_carrito, parent, false);
        vista.setOnClickListener(this);
        ViewHolderCarrito vhInformacion=new ViewHolderCarrito(vista);
        return vhInformacion;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCarrito holder, int position) {
        Carrito item=lista.get(position);
        holder.getLblProducto().setText(item.getProducto());
        holder.getLblPrecioUnitario().setText(Double.toString(item.getPrecio()));
        holder.getLblCantidad().setText(Integer.toString(item.getCantidad()));
        holder.getLblSubTotal().setText(Double.toString(item.getSubTotal()));
        Picasso.get().load(item.getImagen()).error(R.mipmap.ic_launcher_round).fit().centerInside().into((ImageView) holder.getImagen());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

*/
}
