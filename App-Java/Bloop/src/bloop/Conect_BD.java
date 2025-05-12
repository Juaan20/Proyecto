/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bloop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victus
 */
public class Conect_BD {

    Connection cn;
    Statement sql;
    ResultSet rs;

    String resultado = "";
    
    //PantallPrincipal objPantallPrincipal = new PantallPrincipal();
//     PantallaAdmin objPantallaAdmin = new PantallaAdmin();
//    PantallaUsuario objPantallaUsuario = new PantallaUsuario();
    public Conect_BD() {
    }

    //ESTO ES EL CODIGO DE EL INICIO DE SESION
    public void Inicio_Sesion(JTextField JT, JPasswordField JP) {

        try {
            if (!JT.getText().equals("") || !JP.getText().equals("")) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

                String SQL = "SELECT nivel_acceso FROM usuario WHERE Nombre = ? AND Contrasena = ?";
                PreparedStatement ps = cn.prepareStatement(SQL);
                ps.setString(1, JT.getText());
                ps.setString(2, new String(JP.getPassword()));  // Convierte el password char[] a String

                rs = ps.executeQuery();

                if (rs.next()) {
                    resultado = String.valueOf(rs.getBoolean("nivel_acceso"));
                    System.out.println(resultado);
                } else {
                    System.out.println("Usuario y contraseña Incorecto");
                }

            } else {
                System.out.println("DEBE COMPLETAR LOS CAMPOS");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // ESTO ES EL CODIGO DE EL PANEL DE CONTROL DE EVENTOS DEL ADMINISTRADOR
    public void Ver_Categoria(JComboBox jc) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "SELECT Categoria FROM categoria_evento";
            PreparedStatement ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();
            jc.removeAllItems(); // Opcional: limpia el combo antes de agregar nuevos elementos

            while (rs.next()) {
                String categoria = rs.getString("Categoria");
                jc.addItem(categoria);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Eliminar_Categoria(String Categoria){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "DELETE FROM categoria_evento WHERE Categoria like '"+Categoria+"';";
            PreparedStatement ps = cn.prepareStatement(SQL);
            
            int filasAfectadas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filasAfectadas);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Añadir_Categoria(JTextField jt) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "INSERT INTO categoria_evento (Categoria) VALUES (?)";
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, jt.getText());

            ps.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Evento> Ver_Evento(JTable jt) {

        ArrayList<Evento> arrayList_Eventos = new ArrayList<>();

        try {

            DefaultTableModel vaciar_tabla = (DefaultTableModel) jt.getModel();
            vaciar_tabla.setNumRows(0);
            jt.setModel(vaciar_tabla);

            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "SELECT "
                    + "e.Id_evento, e.Titulo, e.Fecha, e.Ubicacion, e.Plazas_totales, e.Plazas_disponibles, "
                    + "c.Categoria AS Nombre_Categoria "
                    + "FROM Evento e "
                    + "JOIN Categoria_evento c ON e.Id_Categoria_Evento = c.Id_Categoria_Evento";
            PreparedStatement ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            DefaultTableModel modelo = (DefaultTableModel) jt.getModel();
            while (rs.next()) {
                Evento objEvento = new Evento();

                objEvento.setID(rs.getInt("Id_evento"));
                objEvento.setCategoria(rs.getString("Nombre_Categoria"));
                objEvento.setTitulo(rs.getString("Titulo"));
                objEvento.setFecha(rs.getDate("Fecha"));
                objEvento.setUbicacion(rs.getString("Ubicacion"));
                objEvento.setPlazas_Totales(rs.getInt("Plazas_totales"));
                objEvento.setPlazas_Disponibles(rs.getInt("Plazas_disponibles"));

                arrayList_Eventos.add(objEvento);

                modelo.addRow(new Object[]{
                    rs.getInt("Id_evento"),
                    rs.getString("Titulo"),
                    rs.getDate("Fecha"),
                    rs.getString("Ubicacion"),
                    rs.getInt("Plazas_totales"),
                    rs.getInt("Plazas_disponibles"),
                    rs.getString("Nombre_Categoria")
                });
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayList_Eventos;

    }

    public void Crear_Evento(Evento EV) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "INSERT INTO evento (Id_Categoria_Evento, Titulo, Fecha, Ubicacion ,Plazas_totales, Plazas_disponibles) " +
                 "VALUES ((SELECT Id_Categoria_Evento FROM categoria_evento WHERE Categoria = ?), ?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(SQL);
             ps.setString(1, EV.getCategoria());
            ps.setString(2, EV.getTitulo());
            ps.setDate(3, EV.getFecha());
            ps.setString(4,EV.getUbicacion());
            ps.setInt(5, EV.getPlazas_Totales());
            ps.setInt(6, EV.getPlazas_Disponibles());
            
            int filasAfectadas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filasAfectadas);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Editar_Evento(Evento EV) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "UPDATE evento SET " +
                 "Id_Categoria_Evento = (SELECT Id_Categoria_Evento FROM categoria_evento WHERE Categoria = ?), " +
                 "Titulo = ?, " +
                 "Fecha = ?, " +
                 "Ubicacion = ?,"+
                 "Plazas_totales = ?, " +
                 "Plazas_disponibles = ? " +
                 "WHERE Id_evento = ?";
            
            PreparedStatement ps = cn.prepareStatement(SQL);
            
            
            ps.setString(1, EV.getCategoria());
            ps.setString(2, EV.getTitulo());
            ps.setDate(3, EV.getFecha());
            ps.setString(4,EV.getUbicacion());
            ps.setInt(5, EV.getPlazas_Totales());
            ps.setInt(6, EV.getPlazas_Disponibles());
            ps.setInt(7, EV.getID());

            
            int filasAfectadas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filasAfectadas);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void Eliminar_Evento(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "DELETE FROM evento WHERE Id_evento = "+id+";";
            PreparedStatement ps = cn.prepareStatement(SQL);
            
            int filasAfectadas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filasAfectadas);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //ESTO ES EL CODIGO DE EL PANEL DE RESERVA DEL ADMINISTRADOR
}
