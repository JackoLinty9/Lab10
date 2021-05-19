package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.SchedaT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public SchedaT getScheda(River r){
		
		final String sql = "SELECT MIN(f.DAY) AS min, MAX(f.DAY) AS max, COUNT(*) AS tot, AVG(f.flow) as avg "
				+ "FROM flow f "
				+ "WHERE f.river=? "
				+ "GROUP BY f.river";
		
		SchedaT stemp=null;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			
			if(res.next()) {
				
				LocalDate prima=res.getDate("min").toLocalDate();
				LocalDate ultima=res.getDate("max").toLocalDate();
				
				stemp=new SchedaT(prima,ultima,r, res.getDouble("avg"),res.getInt("tot"));
			}
			conn.close();
			return stemp;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	public List<Flow>getFlows(River r){
		
		final String sql = "SELECT day, flow "
				+ "FROM flow "
				+ "WHERE river = ? "
				+ "ORDER BY day";

        List<Flow> flows = new LinkedList<Flow>();

        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, r.getId());

            ResultSet res = st.executeQuery();

            while (res.next()) {
	        flows.add(new Flow(res.getDate("day").toLocalDate(), res.getFloat("flow")*3600*24, r));
            }

            conn.close();

            } catch (SQLException e) {
            //e.printStackTrace();
                throw new RuntimeException("SQL Error");
            }
            return flows;	
	}
}
