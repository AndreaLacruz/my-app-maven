package ar.com.ada.maven.DAO;

import ar.com.ada.maven.DTO.FamilyDTO;
import ar.com.ada.maven.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;

public class FamilyDAO implements DAO<FamilyDTO> {

    private Boolean willCloseConnection = true;

    public FamilyDAO(){}

    public FamilyDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public FamilyDTO findById(Integer id) {
        String sql = "SELECT * FROM Family WHERE id = ?";
        FamilyDTO family = null;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next());
               family = new FamilyDTO(rs.getInt("id"), rs.getString("name"));
               if (willCloseConnection);
                conn.close();
        } catch ( SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return family;
    }

    public ArrayList<FamilyDTO> findAll(){
        String sql = "SELECT * FROM Family";
        ArrayList<FamilyDTO> families = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                FamilyDTO family = new FamilyDTO(rs.getInt("id"), rs.getString("name"));
                families.add(family);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return families;
    }

    @Override
    public Boolean update(FamilyDTO familyDTO, Integer id) {
        String sql = "UPDATE Family SET name = ? WHERE id = ?";
        int hasUpdate = 0;
        FamilyDTO famDB = findById(id);
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, familyDTO.getId());
            ps.setString(2, familyDTO.getName());
            if (!familyDTO.getName().equals(famDB.getName()));
            hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate == 1;
    }

    @Override
    public Boolean save(FamilyDTO familyDTO) {
        String sql = "INSERT INTO Family (id,name) VALUES (?,?)";
        int hasInsert = 0;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,familyDTO.getId());
            ps.setString(2, familyDTO.getName());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Family WHERE id = ?";
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
