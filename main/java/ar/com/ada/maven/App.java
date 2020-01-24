package ar.com.ada.maven;

import ar.com.ada.maven.controller.MainController;
import ar.com.ada.maven.database.ConnectionDB;
import ar.com.ada.maven.statement.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {

        /*// consulta
            String sql = "SELECT *  FROM Continent";
        //hace la coneccion con la base
            Connection  conn = ConnectionDB.getConnection();
        // creacion de statement para realizar la consulta
           Statement st = conn.createStatement();
        //grilla de datos cuando lo ves en mysql, ves los resultados para que ejecute las consultas
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getInt("id"));
        }
        // cierra la coneccion despues de realizar las consultas
            conn.close();
         }

        //City.listCities();
        //City.insertCity();
*/
        MainController.run();
    }
}

