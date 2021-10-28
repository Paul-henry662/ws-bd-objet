package AubergeInn;

import javax.persistence.*;

@Entity
public class Service {
	@Id
	@GeneratedValue
	private long m_id;
	
	private long m_idService;
	private String m_description;
	private double m_surplusPrix;
	
	public Service() {
		
	}
	
	public Service(long idService, String description, double surplusPrix) {
		this.m_idService = idService;
		this.m_description = description;
		this.m_surplusPrix = surplusPrix;
	}
	
	public long getID() {
		return m_id;
	}
	public long getIdService() {
		return m_idService;
	}
	public String getDescription() {
		return m_description;
	}
	public double getSurplusPrix() {
		return m_surplusPrix;
	}
	
}
