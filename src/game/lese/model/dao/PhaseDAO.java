/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.model.dao;

import game.lese.connection.DBConnection;
import game.lese.model.Phase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class PhaseDAO {
    
    public static int createPhase(Phase phase){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO phase(name) VALUES(?)");
            ps.setString(1, phase.getName());
            return ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return 0;
    }
    
    public static List<Phase> selectAllPhases(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM phase ORDER BY id_phase ASC");
            ResultSet rs = ps.executeQuery();
            List<Phase> listPhase = new ArrayList<>();
            while(rs.next()){
                Phase phase = new Phase();
                phase.setIdPhase(rs.getInt("id_phase"));
                phase.setName(rs.getString("name"));
                listPhase.add(phase);
            }
            return listPhase;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return null;
    }
    
    public Phase selectPhasePerName(String name){
        Connection connection = null;
        PreparedStatement ps = null;
        Phase phase = null;
        try {
            connection = DBConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM phase WHERE name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                phase = new Phase();
                phase.setIdPhase(rs.getInt("id_phase"));
                phase.setName(rs.getString("name"));
            }            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeConnection(connection, ps);
        }
        return phase;
    }    
}
