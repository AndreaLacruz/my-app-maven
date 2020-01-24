package ar.com.ada.maven.statement;

import ar.com.ada.maven.database.ConnectionDB;

import java.sql.*;
import java.util.Scanner;

public class City {

    public static void listCities() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT *  FROM City";
        //hace la coneccion con la base
        Connection conn = ConnectionDB.getConnection();
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

    public static void insertCity()
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //preparar la consulta
        Scanner keyboard = new Scanner(System.in);
        //String name = "Quito";
        //Integer countryId = 2;

        //ingresar los datos por teclado
        System.out.println("Inserte la Ciudad: ");
        String name = keyboard.next();
        System.out.println("Inserte el Pais: ");
        Integer countryId = keyboard.nextInt();

        String sql = "INSERT INTO City (name, Country_id) VALUES (?, ?)";
        // me conecto con la tabla
        Connection conn = ConnectionDB.getConnection();
        //preparo los datos a insertar, borrar o actualizar y en cada posicion
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, name);
        pst.setInt(2, countryId);
        //ejecucion de la conculta (puede insertar, actualizar y borrar)
        int insert = pst.executeUpdate();

        System.out.println(insert);

        // cierra la concection
        conn.close();
    }

}
