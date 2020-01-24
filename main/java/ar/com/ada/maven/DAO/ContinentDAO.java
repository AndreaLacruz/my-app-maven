package ar.com.ada.maven.DAO;

import ar.com.ada.maven.DTO.ContinentDTO;
import ar.com.ada.maven.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class ContinentDAO implements DAO<ContinentDTO> {


    private Boolean willCloseConnection = true;

    public ContinentDAO() {
    }

    public ContinentDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    public List<ContinentDTO> findAll() {
        String sql = "SELECT * FROM Continent";
        List<ContinentDTO> continents = new ArrayList<ContinentDTO>();
        try {
            Connection conn = ConnectionDB.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ContinentDTO continent = new ContinentDTO(rs.getInt("id"), rs.getString("name"));
                continents.add(continent);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return continents;
    }


    @Override
    public ContinentDTO findById(Integer id) {
        String sql = "SELECT * FROM Continent WHERE id = ?";
        // se coloca nulo para que cuando entre en el try pueda hacer la consulta  no lance error, si es un numero = 0
        // si es un booleano = false y cuando es un objeto = null
        ContinentDTO continent = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                continent = new ContinentDTO(rs.getInt("id"), rs.getString("name"));
            if (willCloseConnection)
                conn.close();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return continent;

    }

    @Override
    public Boolean save(ContinentDTO continentDTO) {
        String sql = "INSERT INTO Continent (name) VALUES (?)";
        //se coloca 0 = falso para validar si se hizo la modificacion en la base de dato y se inicializa con falso.
        int hasInsert = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, continentDTO.getName());
            hasInsert = ps.executeUpdate();
            conn.close();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasInsert == 1;
    }

    @Override
    public Boolean update(ContinentDTO continentDTO, Integer id) {
        String sql = "UPDATE Continent SET name = ? WHERE id = ?";
        int hasUpdate = 0;
        ContinentDTO conDB = findById(id);
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, continentDTO.getName());
            ps.setInt(2, id);
            //preguntar porq el signo de exclamacion?
            if (!continentDTO.getName().equals(conDB.getName()))
                hasUpdate = ps.executeUpdate();

            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Continent WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            hasDelete = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasDelete == 1;

    }

    public ContinentDTO findByName(String continent){
        String sql = "SELECT * FROM Continent WHERE name = ?";
        ContinentDTO continentName = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, continent);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                continentName = new ContinentDTO(rs.getInt("id"), rs.getString("name"));
            if (willCloseConnection)
                conn.close();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return continentName;
    }

    public List<ContinentDTO> findAll(int limit, int offset){
        String sql = "SELECT * FROM Continent LIMIT ? OFFSET ?";
        List<ContinentDTO> continents = new ArrayList<>();

        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,limit);
            ps.setInt(2,offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ContinentDTO contDTO = new ContinentDTO(rs.getInt("id"), rs.getString("name"));
                continents.add(contDTO);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return continents;
    }

    public int getTotalContinents(){
        String sql = "SELECT COUNT(*) AS total FROM Continent";
        int total = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) total = rs.getInt("total");
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return total;
    }
}