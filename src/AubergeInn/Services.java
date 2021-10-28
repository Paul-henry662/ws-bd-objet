package AubergeInn;

import javax.persistence.*;
import java.sql.SQLException;

public class Services {
	private static final String QUERYSELECT = "SELECT s FROM Service s WHERE s.m_idService = :idService";
	
	private TypedQuery<Service> stmtSelect;
	private Connexion cx;
	
	public Services(Connexion cx) throws SQLException {
		this.cx = cx;
		
		stmtSelect = cx.getConnection().createQuery(QUERYSELECT, Service.class);
	}
		
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(long idService) {
		stmtSelect.setParameter("idService", idService);
		
		return !stmtSelect.getResultList().isEmpty();
	}
	
	public Service getService(long idService) {
		stmtSelect.setParameter("idService", idService);
		
		return stmtSelect.getSingleResult();
	}
	
	public void creerService(Service service) {
		cx.getConnection().persist(service);
	}

}
