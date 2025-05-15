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
public class Reserva {
    private int ID;
    private String Evento;
    private String Tipo_Reserva;
    private int Numero_Entradas;
    private String Nombres_Acompañantes;

    public Reserva() {
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
     * @return the Evento
     */
    public String getEvento() {
        return Evento;
    }

    /**
     * @param Evento the Evento to set
     */
    public void setEvento(String Evento) {
        this.Evento = Evento;
    }

    /**
     * @return the Tipo_Reserva
     */
    public String getTipo_Reserva() {
        return Tipo_Reserva;
    }

    /**
     * @param Tipo_Reserva the Tipo_Reserva to set
     */
    public void setTipo_Reserva(String Tipo_Reserva) {
        this.Tipo_Reserva = Tipo_Reserva;
    }

    
    /**
     * @return the Numero_Entradas
     */
    public int getNumero_Entradas() {
        return Numero_Entradas;
    }

    /**
     * @param Numero_Entradas the Numero_Entradas to set
     */
    public void setNumero_Entradas(int Numero_Entradas) {
        this.Numero_Entradas = Numero_Entradas;
    }

    /**
     * @return the Nombres_Acompañantes
     */
    public String getNombres_Acompañantes() {
        return Nombres_Acompañantes;
    }

    /**
     * @param Nombres_Acompañantes the Nombres_Acompañantes to set
     */
    public void setNombres_Acompañantes(String Nombres_Acompañantes) {
        this.Nombres_Acompañantes = Nombres_Acompañantes;
    }
    
    
}
