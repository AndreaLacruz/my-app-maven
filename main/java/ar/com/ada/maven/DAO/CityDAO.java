package ar.com.ada.maven.DAO;

import ar.com.ada.maven.DTO.CityDTO;
import ar.com.ada.maven.DTO.CountryDTO;
import ar.com.ada.maven.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;

public class CityDAO implements DAO<CityDTO> {

    private Boolean willCloseConnection = true;
    private CountryDAO counDAO = new CountryDAO(false);

    public CityDAO(){}

    public CityDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;}

    @Override
    public CityDTO findById(Integer id) {
        String sql = "SELECT * FROM City WHERE id = ?";
        CityDTO city = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                CountryDTO country = counDAO.findById(rs.getInt("id_Country"));
                city = new CityDTO(rs.getInt("id"), rs.getString("name"), country);
            }
            if (willCloseConnection);
            conn.close();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return city;
    }

    public ArrayList<CityDTO> findAll(){
        String sql = "SELECT * FROM City";
        ArrayList<CityDTO> cities = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CountryDTO country = counDAO.findById(rs.getInt("Country_id"));
                CityDTO city = new CityDTO(rs.getInt("id"),rs.getString("name"), country);
                cities.add(city);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return cities;
    }

    @Override
    public Boolean update(CityDTO cityDTO, Integer id) {
        String sql = "UPDATE City SET name = ?, id_Country = ? WHERE id = ?";
        int hasUpdate = 0;
        CityDTO cityDB = findById(id);
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,cityDTO.getName());
            ps.setInt(2, cityDTO.getCountry().getId());
            if (!cityDTO.getName().equals(cityDB.getName())  &&
            cityDTO.getCountry().equals(cityDB.getCountry()));
            hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate == 1;
    }

    @Override
    public Boolean save(CityDTO cityDTO) {
        String sql = "INSERT INTO City (id, name) VALUES (?, ?)";
        int hasInsert = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cityDTO.getId());
            ps.setString(2, cityDTO.getName());
            ps.setInt(3, cityDTO.getCountry().getId());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasInsert == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM City WHERE id = ?";
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
}
