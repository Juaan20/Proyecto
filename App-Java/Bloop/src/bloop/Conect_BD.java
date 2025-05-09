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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Victus
 */
public class Conect_BD {

    Connection cn;
    Statement sql;
    ResultSet rs;
    
    boolean resultado;

    //PantallPrincipal objPantallPrincipal = new PantallPrincipal();
//     PantallaAdmin objPantallaAdmin = new PantallaAdmin();
//    PantallaUsuario objPantallaUsuario = new PantallaUsuario();
    public Conect_BD() {
    }

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
                    resultado = rs.getBoolean("nivel_acceso");
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
}
