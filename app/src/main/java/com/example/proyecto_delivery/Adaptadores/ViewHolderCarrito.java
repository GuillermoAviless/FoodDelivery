package com.example.proyecto_delivery.Adaptadores;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_delivery.R;

public class ViewHolderCarrito extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView lblProducto;
    private TextView lblPrecioUnitario;
    private TextView lblCantidad;
    private TextView lblSubTotal;
    private ImageView Imagen;
    private Button btnMas;
    private Button btnMenos;
    private Button btnEliminar;
    public ViewHolderCarrito(@NonNull View itemView) {
        super(itemView);
        lblProducto=itemView.findViewById(R.id.clblProducto);
        lblPrecioUnitario=itemView.findViewById(R.id.clblPrecioUnitario);
        lblCantidad=itemView.findViewById(R.id.clblCantidad);
        lblSubTotal=itemView.findViewById(R.id.clblSubTotal);
        Imagen=itemView.findViewById(R.id.cImagenProducto);
        /*btnMas=itemView.findViewById(R.id.cbtnMas);
        btnMenos=itemView.findViewById(R.id.cbtnMenos);
        btnEliminar=itemView.findViewById(R.id.cbtnEliminar);*/
    }

    @Override
    public void onClick(View v) {

    }

    public TextView getLblProducto() {
        return lblProducto;
    }

    public void setLblProducto(TextView lblProducto) {
        this.lblProducto = lblProducto;
    }

    public TextView getLblPrecioUnitario() {
        return lblPrecioUnitario;
    }

    public void setLblPrecioUnitario(TextView lblPrecioUnitario) {
        this.lblPrecioUnitario = lblPrecioUnitario;
    }

    public TextView getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(TextView lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    public TextView getLblSubTotal() {
        return lblSubTotal;
    }

    public void setLblSubTotal(TextView lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }

    public ImageView getImagen() {
        return Imagen;
    }

    public void setImagen(ImageView imagen) {
        Imagen = imagen;
    }

    public Button getBtnMas() {
        return btnMas;
    }

    public void setBtnMas(Button btnMas) {
        this.btnMas = btnMas;
    }

    public Button getBtnMenos() {
        return btnMenos;
    }

    public void setBtnMenos(Button btnMenos) {
        this.btnMenos = btnMenos;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Button btnEliminar) {
        this.btnEliminar = btnEliminar;
    }
}
