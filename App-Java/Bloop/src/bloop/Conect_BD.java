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
import javax.swing.JLabel;
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

    int ID;

    //PantallPrincipal objPantallPrincipal = new PantallPrincipal();
//     PantallaAdmin objPantallaAdmin = new PantallaAdmin();
//    PantallaUsuario objPantallaUsuario = new PantallaUsuario();
    public Conect_BD() {
    }

    //ESTO ES EL CODIGO DE EL INICIO DE SESION
    public void Inicio_Sesion(JTextField JT, JPasswordField JP) {

        //LO QUE HACEMOS AQUI ES QUE LE PEDIMOS UN FIELD Y UN PASSWORD ASI HACEMOS LA CONSULTA
        try {
            if (!JT.getText().equals("") || !JP.getText().equals("")) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

                String SQL = "SELECT Id_usuario, nivel_acceso FROM usuario WHERE Nombre = ? AND Contrasena = ?";
                PreparedStatement ps = cn.prepareStatement(SQL);
                ps.setString(1, JT.getText());
                ps.setString(2, new String(JP.getPassword()));  // Convierte el password char[] a String

                rs = ps.executeQuery();

                //SI LA CONSULTA A DADO RESULTADO NO HACE EL IF
                if (rs.next()) {
                    //QUE NOS METE EL BOOLEAN DE EL NIVEL EN UNA VARAIBLE Y LA SACAMOS FUERRA
                    resultado = String.valueOf(rs.getBoolean("nivel_acceso"));
                    ID = rs.getInt("Id_usuario");
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
    //ESTE METODO ES PARA QUE EL COMBOBOX SE VEA LOS NOMBRE DE LAS CATEGORIAS A TIEMPO REAL YA COMO LE PASAMOS
    //EL COMBO LO EDITAMOS EN EL PROPIO METODO
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

    //ESTE METODO ES PARA PODER ELIMINAR LA CATEGORIA LO QUE HACEMOS ES DESDE EL COMBOBOX LE PASAMOS EL NOMBRE Y 
    //ENBASE A EL NOMBRE EN LA CONSULTA LO ELIMINAMOS
    public void Eliminar_Categoria(String Categoria) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "DELETE FROM categoria_evento WHERE Categoria like '" + Categoria + "';";
            PreparedStatement ps = cn.prepareStatement(SQL);

            int filasAfectadas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filasAfectadas);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //LO MIMSO QUE EL DE ARRIBA PARA AÑADIR LA CATEGORIA NUEVA LE PASAMOS EL NOMBRE Y LA CREA EN LA CONSULTA
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

    //ESTE METODO DE VER EVENTO ES UN METODO QUE DEVOLVEMOS UN ARRAY DE LA INFOAMCION Y LE PEDIMOS UN JTABLE PARA 
    //AÑADIR LAS ROW Y VERLAS 
    //ESTE PODRIA SER UN METODO VOID PERO COMO LUEGO NECESITAMOS UN ARRAY CON TODO LA INFORMACION 
    //SE LO PASAMOS YA ASI YA LO TENEMOS
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
                    rs.getString("Nombre_Categoria"),
                    rs.getString("Titulo"),
                    rs.getDate("Fecha"),
                    rs.getString("Ubicacion"),
                    rs.getInt("Plazas_totales"),
                    rs.getInt("Plazas_disponibles")

                });
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayList_Eventos;

    }

    //ESTE METODO NOS CREAR UN EVENTO COMO LE PASMOS UN OBJETO QUE ES EL QUE CREAMOS Y HACEMOS LA CONSUTA   
    public void Crear_Evento(Evento EV) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "INSERT INTO evento (Id_Categoria_Evento, Titulo, Fecha, Ubicacion ,Plazas_totales, Plazas_disponibles) "
                    + "VALUES ((SELECT Id_Categoria_Evento FROM categoria_evento WHERE Categoria = ?), ?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, EV.getCategoria());
            ps.setString(2, EV.getTitulo());
            ps.setDate(3, EV.getFecha());
            ps.setString(4, EV.getUbicacion());
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

    //ESTE METODO ES LO MISMO QUE EL DE CREAR LO UNICO QUE EDITA LA CONSULA DEPENDIENDO DEL ID GRACIAS AL OBJETO QUE LE PASAMOS
    public void Editar_Evento(Evento EV) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "UPDATE evento SET "
                    + "Id_Categoria_Evento = (SELECT Id_Categoria_Evento FROM categoria_evento WHERE Categoria = ?), "
                    + "Titulo = ?, "
                    + "Fecha = ?, "
                    + "Ubicacion = ?,"
                    + "Plazas_totales = ?, "
                    + "Plazas_disponibles = ? "
                    + "WHERE Id_evento = ?";

            PreparedStatement ps = cn.prepareStatement(SQL);

            ps.setString(1, EV.getCategoria());
            ps.setString(2, EV.getTitulo());
            ps.setDate(3, EV.getFecha());
            ps.setString(4, EV.getUbicacion());
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

    //ESTE METODO ELIMINA EL ELEMENTO DEPENDIDO DEL ID QUE SE LO PEDIMOS ANTERIORMENTE Y GRACIAS A ESO HACEMOS LA CONSULTA DE DELETE
    public void Eliminar_Evento(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "DELETE FROM evento WHERE Id_evento = " + id + ";";
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
    public void Ver_Reservas(JTable jt) {

        try {
            DefaultTableModel vaciar_tabla = (DefaultTableModel) jt.getModel();
            vaciar_tabla.setNumRows(0);
            jt.setModel(vaciar_tabla);

            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");

            String SQL = "SELECT r.Id_reserva, u.Nombre AS Nombre_Usuario, e.Titulo AS Nombre_Evento, "
                    + "t.Tipo AS Tipo_Reserva, r.Fecha_Reserva, r.num_entradas "
                    + "FROM Reservas r "
                    + "JOIN Usuario u ON r.Id_usuario = u.Id_usuario "
                    + "JOIN Evento e ON r.Id_evento = e.Id_evento "
                    + "JOIN Tipo_reserva t ON r.Id_Tipo_Reserva = t.Id_Tipo_reserva;";
            PreparedStatement ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            DefaultTableModel modelo = (DefaultTableModel) jt.getModel();
            while (rs.next()) {

                modelo.addRow(new Object[]{
                    rs.getInt("Id_reserva"),
                    rs.getString("Nombre_Usuario"),
                    rs.getString("Nombre_Evento"),
                    rs.getString("Tipo_Reserva"),
                    rs.getDate("Fecha_Reserva"),
                    rs.getInt("num_entradas")
                });
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //AQUI SE ENCUENTRA EL CODIGO DE LA PANTALLA DE LOS USUARIO
    String Nombre;
    
    public void Nombre_User(int id, JLabel jt) {

          
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloop", "root", "");
            
            String SQL = "SELECT Nombre FROM usuario WHERE Id_usuario = ?";
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setInt(1, id); // <- más seguro que concatenar
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                jt.setText(jt.getText() + " " + rs.getString("Nombre"));
            } else {
                jt.setText(jt.getText() + " [Usuario no encontrado]");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conect_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
