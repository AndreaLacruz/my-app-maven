package ar.com.ada.maven.DAO;

import ar.com.ada.maven.DTO.AnimalDTO;
import ar.com.ada.maven.DTO.ContinentDTO;
import ar.com.ada.maven.DTO.CountryDTO;
import ar.com.ada.maven.DTO.SpeciesDTO;
import ar.com.ada.maven.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class AnimalDAO implements DAO<AnimalDTO> {

    private Boolean willCloseConnection = true;
    private SpeciesDAO speDAO = new SpeciesDAO(false);
    private CountryDAO counDAO = new CountryDAO(false);


    public AnimalDAO(){}

    public AnimalDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public AnimalDTO findById(Integer id) {
        String sql = "SELECT * FROM Animal WHERE id = ?";
        AnimalDTO animal = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            java.sql.Date date = new java.sql.Date(animal.getBirthday().getTime());
            ps.setDate(3, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                CountryDTO country = counDAO.findById(rs.getInt("Country_id"));
                SpeciesDTO species = speDAO.findById(rs.getInt("Species_id"));
                animal = new AnimalDTO(rs.getInt("id"), rs.getString("sex"),
                        rs.getDate("birthday"), country, species);
            }
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return animal;
    }

    public ArrayList<AnimalDTO> findAll(){
        String sql = "SELECT * FROM Animal";
        ArrayList<AnimalDTO> animals = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CountryDTO country = counDAO.findById(rs.getInt("Country_id"));
                SpeciesDTO species = speDAO.findById(rs.getInt("Species_id"));
                AnimalDTO animal = new AnimalDTO(rs.getInt("id"), rs.getString("sex"),
                        rs.getDate("birthday"), country,species);
                animals.add(animal);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return animals;
    }

    @Override
    public Boolean save(AnimalDTO animalDTO) {
        String sql = "INSERT INTO Animal (id, sex, birthday) VALUES (?,?,?)";
        int hasInsert = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, animalDTO.getId());
            ps.setString(2, animalDTO.getSex());
            java.sql.Date date = new java.sql.Date(animalDTO.getBirthday().getTime());
            ps.setDate(3, date);
            ps.setInt(4, animalDTO.getCountry().getId());
            ps.setInt(5, animalDTO.getSpecies().getId());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasInsert == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    @Override
    public Boolean update(AnimalDTO animalDTO, Integer id) {
        return null;
    }
}
