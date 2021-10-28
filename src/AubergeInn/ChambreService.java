package AubergeInn;

import javax.persistence.*;

@Entity
public class ChambreService {
	@Id
	@GeneratedValue
	private long m_id;
	
	private Chambre m_chambre;
	private Service m_service;
	
	public ChambreService() {
		
	}
	
	public ChambreService(Chambre chambre, Service service) {
		this.m_chambre = chambre;
		this.m_service = service;
	}

	public long getID() {
		return m_id;
	}

	public Chambre getChambre() {
		return m_chambre;
	}

	public Service getService() {
		return m_service;
	}
}
