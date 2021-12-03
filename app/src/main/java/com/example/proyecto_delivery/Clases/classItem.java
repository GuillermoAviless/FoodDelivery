package com.example.proyecto_delivery.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.example.proyecto_delivery.BaseDatos.Persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class classItem extends Persistencia {
    private int IdProducto;
    private String Imagen;
    private String Producto;
    private String Descripcion;
    private Double Precio;
    private double Valor;

    private int IdCategoria;

    public static final String TABLA_PRODUCTOS = "Items";
    public static final String ID_PRODUCTO = "IdProducto";
    public static final String IMAGEN = "Imagen";
    public static final String PRODUCTO = "Producto";
    public static final String DESCRIPCION = "Descripcion";
    public static final String PRECIO = "Precio";
    public static final String ID_CATEGORIA = "IdCategoria";




    public static final String QUERY_CREATE_TABLE =
            "CREATE TABLE "+TABLA_PRODUCTOS+" (" +ID_PRODUCTO+
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    IMAGEN+" TEXT NOT NULL, " +
                    PRODUCTO+" TEXT NOT NULL, " +
                    DESCRIPCION+" TEXT NOT NULL, " +
                    PRECIO+" DECIMAL NOT NULL,"+
                    ID_CATEGORIA+" INTEGER NOT NULL)";

    public static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS "+TABLA_PRODUCTOS;
    private static final String[] FIELDS = new String[]{ID_PRODUCTO,IMAGEN,PRODUCTO,DESCRIPCION,PRECIO, ID_CATEGORIA};

    public classItem(Context contexto) {
        super(contexto);
    }

    public classItem(){
        super(null);
    }

    @Override
    public boolean Insert() throws SQLiteException {
        boolean valReturn = false;
        //Creamos el Content Values para Libros
        ContentValues valoresInsert = new ContentValues();
        valoresInsert.put(FIELDS[1], this.Imagen);
        valoresInsert.put(FIELDS[2], this.Producto);
        valoresInsert.put(FIELDS[3], this.Descripcion);
        valoresInsert.put(FIELDS[4], this.Precio);
        valoresInsert.put(FIELDS[5], this.IdCategoria);
        //Ejecutamos la consulta de la insercion
        super.Abrir();
        if(super.getDataBase().insert(TABLA_PRODUCTOS, null, valoresInsert) > 0){
            valReturn = true;
        }
        super.Cerrar();
        return valReturn;
    }

    @Override
    public boolean Update() throws SQLiteException {
        boolean valReturn = false;
        ContentValues valoresUpdate = new ContentValues();
        valoresUpdate.put(FIELDS[1], this.Imagen);
        valoresUpdate.put(FIELDS[2], this.Producto);
        valoresUpdate.put(FIELDS[3], this.Descripcion);
        valoresUpdate.put(FIELDS[4], this.Precio);


        super.Abrir();
        if(super.getDataBase().update(TABLA_PRODUCTOS, valoresUpdate, FIELDS[0] +" = " + Integer.toString(this.IdProducto),null) > 0 ){
            valReturn = true;
        }
        super.Cerrar();
        return valReturn;
    }

    @Override
    public boolean Delete() {
        boolean valReturn= false;
        super.Abrir();
        //super.getDataBase().delete(TABLA_PRODUCTOS,ID_FACTURA+" = '"+ Integer.toString(this.IdFacturaReferencia)+"'",null);
        super.Cerrar();
        return valReturn;
    }

    @Override
    protected Cursor GetAll() {
        Cursor listReturn = null;
        listReturn = super.getDataBase().query(TABLA_PRODUCTOS,FIELDS,ID_CATEGORIA+" = '"+Integer.toString(getIdCategoria())+"'",null,null,null,null);
        return listReturn;
    }

    public List<classItem> GetAllItems(){
        List<classItem> listProducto = new ArrayList<>();
        super.Abrir();
        Cursor listReturn = this.GetAll();
        //Validamos que exista información en el cursor
        if(listReturn != null && listReturn.getCount() > 0){
            //Moverse al primer elemento
            listReturn.moveToFirst();
            do{
                //Creamos el libro que se incluira en la lista
                classItem nuevoProducto = new classItem();
                nuevoProducto.setIdProducto(listReturn.getInt(0));
                nuevoProducto.setImagen(listReturn.getString(1));
                nuevoProducto.setProducto(listReturn.getString(2));
                nuevoProducto.setDescripcion(listReturn.getString(3));
                nuevoProducto.setPrecio(listReturn.getDouble(4));
                //Agregamos a la lista
                listProducto.add(nuevoProducto);
            }while (listReturn.moveToNext());
        }
        super.Cerrar();
        return listProducto;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        this.IdProducto = idProducto;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        this.Imagen = imagen;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        this.Producto = producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        this.Precio = precio;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        this.Valor = valor;
    }



    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.IdCategoria=idCategoria;
    }

}
