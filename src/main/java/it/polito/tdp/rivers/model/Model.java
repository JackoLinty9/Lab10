package it.polito.tdp.rivers.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao;
	List<River>allRivers;
	
	public Model() {
		this.dao=new RiversDAO();
		dao.getAllRivers();
	}
	
	public List<River>listRivers(){
		return dao.getAllRivers();
	}
	
	public SchedaT getScheda(River r) {
		return dao.getScheda(r);
	}
	
	public List<Flow> getFlows(River r) {
		return this.dao.getFlows(r);
	}
}
