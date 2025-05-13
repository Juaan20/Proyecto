/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bloop;

import java.sql.Date;

/**
 *
 * @author Victus
 */
public class Evento {
    
    private int ID;
    private String Categoria;
    private String Titulo;
    private Date fecha;
    private String Ubicacion;
    private int Plazas_Totales;
    private int Plazas_Disponibles;

    public Evento() {
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the Categoria
     */
    public String getCategoria() {
        return Categoria;
    }

    /**
     * @param Categoria the Categoria to set
     */
    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    /**
     * @return the Titulo
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * @param Titulo the Titulo to set
     */
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the Ubicacion
     */
    public String getUbicacion() {
        return Ubicacion;
    }

    /**
     * @param Ubicacion the Ubicacion to set
     */
    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    /**
     * @return the Plazas_Totales
     */
    public int getPlazas_Totales() {
        return Plazas_Totales;
    }

    /**
     * @param Plazas_Totales the Plazas_Totales to set
     */
    public void setPlazas_Totales(int Plazas_Totales) {
        this.Plazas_Totales = Plazas_Totales;
    }

    /**
     * @return the Plazas_Disponibles
     */
    public int getPlazas_Disponibles() {
        return Plazas_Disponibles;
    }

    /**
     * @param Plazas_Disponibles the Plazas_Disponibles to set
     */
    public void setPlazas_Disponibles(int Plazas_Disponibles) {
        this.Plazas_Disponibles = Plazas_Disponibles;
    }
    
    
    
}
