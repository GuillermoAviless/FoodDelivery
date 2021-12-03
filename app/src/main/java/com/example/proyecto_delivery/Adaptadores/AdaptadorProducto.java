package com.example.proyecto_delivery.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_delivery.Entidades.Producto;
import com.example.proyecto_delivery.ListaInformacion;
import com.example.proyecto_delivery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorProducto extends RecyclerView.Adapter<ViewHolderProducto> implements View.OnClickListener{
    private List<Producto> listaInformacion;
    private View.OnClickListener listener;
        public AdaptadorProducto(List<Producto> listaInformacion){
        this.listaInformacion=listaInformacion;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vista donde se invoca el xml donde tenemos la informacion
        //Creamos la vista que representa el item y se enlaza con la informacion del ViewHolderInformacion
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla2_xml,parent,false);
        vista.setOnClickListener(this);
        ViewHolderProducto vhInformacion=new ViewHolderProducto(vista);
        return vhInformacion;
    }
    private String ValidarLongitud(int parametro,String titulo){
        switch(parametro){
            case 1:
                if(titulo.length()>35)
                    return titulo.substring(0,30);
            case 2:
                if(titulo.length()>60)
                    return titulo.substring(0,60);
            default:
                return titulo;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
            Producto producto= listaInformacion.get(position);
        holder.getLblProducto().setText(ValidarLongitud(1,listaInformacion.get(position).getProducto()));
        holder.getLblPrecio().setText("$ "+Double.toString(listaInformacion.get(position).getPrecio()));
        if(producto.getValor()!=0)
            holder.getLblValor().setText(""+(listaInformacion.get(position).getValor()));
        if(producto.getDato()!=0)
            holder.getLblDato().setText(""+(listaInformacion.get(position).getDato()));
        holder.getLblDescripcion().setText(ValidarLongitud(2,listaInformacion.get(position).getDescripcion()));
        //holder.getImagen().setImageURI(Uri.parse(listaInformacion.get(position).getImagen()));
        Picasso.get().load(listaInformacion.get(position).getImagen()).error(R.mipmap.ic_launcher_round).fit().centerInside().into((ImageView) holder.getImagen());

    }

    @Override
    public int getItemCount() {
        //retorna el numero de items que tiene el reciclerview
        return listaInformacion.size();
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
}
