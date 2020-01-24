package ar.com.ada.maven.DAO;

import ar.com.ada.maven.DTO.CityDTO;
import ar.com.ada.maven.DTO.FamilyDTO;
import ar.com.ada.maven.DTO.SpeciesDTO;
import ar.com.ada.maven.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;

public class SpeciesDAO implements DAO<SpeciesDTO> {

    private Boolean willCloseConnection = true;
    private FamilyDAO famDAO = new FamilyDAO(false);

    public SpeciesDAO(){}

    public SpeciesDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    public ArrayList<SpeciesDTO> findAll(){
        String sql = "SELECT * FROM Species";
        ArrayList<SpeciesDTO> species = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                FamilyDTO family = famDAO.findById(rs.getInt("id_Family"));
                SpeciesDTO specie = new SpeciesDTO(rs.getInt("id"), rs.getString("CommenName"),
                        rs.getString("scientificName"), rs.getBoolean("endangered"), family);
                species.add(specie);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return species;
    }

    @Override
    public SpeciesDTO findById(Integer id) {
        String sql = "SELECT * FROM Species WHERE id = ?";
        SpeciesDTO species = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                FamilyDTO family =  famDAO.findById(rs.getInt("Family_id"));
                species = new SpeciesDTO(rs.getInt("id"), rs.getString("commenName"),
                        rs.getString("scientificName"), rs.getBoolean("endangered"), family);
            }
            if (willCloseConnection);
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return species;
    }

    @Override
    public Boolean update(SpeciesDTO speciesDTO, Integer id) {
        String sql = "UPDATE Species SET commonName = ?, scientificName = ?, endangered = ?, id_Family = ? WHERE id = ?";
        int hasUpdate = 0;
        SpeciesDTO speciesDB = findById(id);
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, speciesDTO.getCommonName());
            ps.setString(2,speciesDTO.getScientificName());
            ps.setBoolean(3, speciesDTO.getEndangered());
            ps.setInt(4, speciesDTO.getFamily().getId());
            if (!speciesDTO.getCommonName().equals(speciesDB.getCommonName())
                    && speciesDTO.getScientificName().equals(speciesDB.getScientificName())
            && speciesDTO.getEndangered().equals(speciesDB.getEndangered())
                    && speciesDTO.getFamily().equals(speciesDB.getFamily()));
            hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Species WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            hasDelete = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasDelete == 1;
    }

    @Override
    public Boolean save(SpeciesDTO speciesDTO) {
        String sql = "INSERT INTO Species (id, commonName, scientificName, engangered) VALUES (?,?,?,?)";
        int hasInsert = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, speciesDTO.getId());
            ps.setString(2, speciesDTO.getCommonName());
            ps.setString(3,speciesDTO.getScientificName());
            ps.setBoolean(4, speciesDTO.getEndangered());
            ps.setInt(5, speciesDTO.getFamily().getId());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasInsert == 1;

    }
}
