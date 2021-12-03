package com.example.proyecto_delivery;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        String imagen="https://previews.123rf.com/images/yupiramos/yupiramos1712/yupiramos171220597/92183510-hombre-avatar-perfil-icono-imagen-vector-ilustraci%C3%B3n-dise%C3%B1o.jpg";
        ImageView imgPerfilImagen=findViewById(R.id.imgPerfilImagen);
        TextView lblNombre=findViewById(R.id.lblPerfilNombre);
        TextView lblUsuario=findViewById(R.id.lblPerfilUsuario);
        TextView lblTelefono=findViewById(R.id.lblPerfilTelefono);
        TextView lblCorreo=findViewById(R.id.lblPerfilCorreo);
        TextView lblDireccion=findViewById(R.id.lblPerfilDireccion);

        Picasso.get().load(imagen).error(R.mipmap.ic_launcher_round).fit().centerInside().into((ImageView) imgPerfilImagen);
        lblNombre.setText(ListaInformacion.Nombres);
        lblUsuario.setText(ListaInformacion.Usuario);
        lblTelefono.setText(ListaInformacion.Telefono);
        lblCorreo.setText(ListaInformacion.Correo);
        lblDireccion.setText(ListaInformacion.Direccion);
    }
}