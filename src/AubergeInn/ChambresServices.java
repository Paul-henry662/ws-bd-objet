package AubergeInn;

import java.util.List;

import javax.persistence.TypedQuery;

public class ChambresServices {
	private static final String QUERYSELECT = "SELECT cs FROM ChambreService cs WHERE cs.m_chambre = :chambre AND cs.m_service = :service";
	private static final String QUERYSELECTCHAMBRE = "SELECT cs.m_service FROM ChambreService cs WHERE cs.m_chambre = :chambre";
	
	private TypedQuery<ChambreService> stmtSelect;
	private TypedQuery<Service> stmtSelectChambre;
	private Connexion cx;
	
	public ChambresServices(Connexion cx) {
		this.cx = cx;
		
		stmtSelect = cx.getConnection().createQuery(QUERYSELECT, ChambreService.class);
		stmtSelectChambre = cx.getConnection().createQuery(QUERYSELECTCHAMBRE, Service.class);
	}
	
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(Chambre chambre, Service service) {
		stmtSelect.setParameter("chambre", chambre);
		stmtSelect.setParameter("service", service);
		
		return !stmtSelect.getResultList().isEmpty();
	}
	
	public ChambreService getChambreService(Chambre chambre, Service service) {
		stmtSelect.setParameter("chambre", chambre);
		stmtSelect.setParameter("service", service);
		
		return stmtSelect.getSingleResult();
	}
	
	public void inclureService(ChambreService chambreService) {
		cx.getConnection().persist(chambreService);
	}
	
	public void enleverService(ChambreService chambreService) {
		cx.getConnection().remove(chambreService);
	}
	
	public List<Service> getServices(Chambre chambre) {		
		stmtSelectChambre.setParameter("chambre", chambre);
		
		return stmtSelectChambre.getResultList();
	}
}
