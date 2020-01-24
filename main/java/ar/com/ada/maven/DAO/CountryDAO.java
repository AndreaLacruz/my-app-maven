package ar.com.ada.maven.DAO;

import ar.com.ada.maven.DTO.ContinentDTO;
import ar.com.ada.maven.DTO.CountryDTO;
import ar.com.ada.maven.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CountryDAO implements DAO<CountryDTO>{

    private Boolean willCloseConnection = true;
    private ContinentDAO contDAO = new ContinentDAO(false);

    public CountryDAO(){}

    public CountryDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;}

    @Override
    public ArrayList<CountryDTO> findAll(){
        String sql = " SELECT * FROM Country";
        ArrayList<CountryDTO> countries = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                // con el campo Continent_id busco el continente con el dao de Continent
                ContinentDTO continent = contDAO.findById(rs.getInt("Continent_id"));
                CountryDTO country = new CountryDTO(rs.getInt("id"),rs.getString("name"), continent);
                countries.add(country);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return countries;
    }

    @Override
    public CountryDTO findById(Integer id) {
        String sql = "SELECT * FROM Country WHERE id = ?";
        CountryDTO country = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // contDAO es la variable que se va a alojar el DAO de continent
                ContinentDTO continent = contDAO.findById(rs.getInt("id_Continent"));
                country = new CountryDTO(rs.getInt("id"), rs.getString("name"), continent);
            }
            if (willCloseConnection)
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return country;
    }

    @Override
    public Boolean save(CountryDTO countryDTO) {
        String sql = "INSERT INTO Country (id, name) VALUES (?, ?)";
        int hasInsert = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, countryDTO.getId());
            ps.setString(2,countryDTO.getName());
            ps.setInt(3, countryDTO.getContinent().getId());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasInsert == 1;
    }

    @Override
    public Boolean update(CountryDTO countryDTO, Integer id) {
        String sql = "UPDATE country SET name = ?, id_Continent = ? WHERE id = ?";
        int hasUpdate = 0;
        CountryDTO countryDB = findById(id);
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,countryDTO.getName());
            ps.setInt(2, countryDTO.getContinent().getId());
            ps.setInt(3, countryDTO.getId());
            if (!countryDTO.getName().equals(countryDB.getName())&& countryDTO.getId().equals(countryDB.getId())&&
            countryDTO.getContinent().equals(countryDB.getContinent()));
            hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Country WHERE id = ? ";
        int hasErase = 0;
        try {
            Connection conn  = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            hasErase = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasErase == 1;
    }

}
