package AubergeInn;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

public class Reservations {
	private static final String QUERYSELECT = "SELECT r FROM Reservation r WHERE r.m_client = :client AND r.m_chambre = :chambre AND r.m_dateDebut = :dateDebut AND r.m_dateFin = :dateFin";
	private static final String QUERYSELECTCLIENT = "SELECT r FROM Reservation r WHERE r.m_client = :client";
	private static final String QUERYSELECTCHAMBRE = "SELECT r FROM Reservation r WHERE r.m_chambre = :chambre";
	private static final String QUERYSELECTCHAMBREACTIVE = "SELECT r FROM Reservation r WHERE (r.m_chambre = :chambre) AND ((:dateDebut BETWEEN r.m_dateDebut AND r.m_dateFin) OR (:dateFin BETWEEN r.m_dateDebut AND r.m_dateFin))";

	private TypedQuery<Reservation> stmtSelect;
	private TypedQuery<Reservation> stmtSelectClient;
	private TypedQuery<Reservation> stmtSelectChambre;
	private TypedQuery<Reservation> stmtSelectChambreActive;

	private Connexion cx;
	
	public Reservations(Connexion cx) {
		this.cx = cx;
		
		stmtSelect = cx.getConnection().createQuery(QUERYSELECT, Reservation.class);
		stmtSelectClient = cx.getConnection().createQuery(QUERYSELECTCLIENT, Reservation.class);
		stmtSelectChambre = cx.getConnection().createQuery(QUERYSELECTCHAMBRE, Reservation.class);
		stmtSelectChambreActive = cx.getConnection().createQuery(QUERYSELECTCHAMBREACTIVE, Reservation.class);
	}
	
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(Client client, Chambre chambre, Date dateDebut, Date dateFin) {
		stmtSelect.setParameter("client", client);
		stmtSelect.setParameter("chambre", chambre);
		stmtSelect.setParameter("dateDebut", dateDebut);
		stmtSelect.setParameter("dateFin", dateFin);
		
		return !stmtSelect.getResultList().isEmpty();
	}
	
	public boolean existeClient(Client client) {
		stmtSelectClient.setParameter("client", client);
		
		return !stmtSelectClient.getResultList().isEmpty();
	}
	
	public boolean existeChambre(Chambre chambre) {
		stmtSelectChambre.setParameter("chambre", chambre);
		
		return !stmtSelect.getResultList().isEmpty();
	}
	
	public boolean existeChambreActive(Chambre chambre, Date dateDebut, Date dateFin) {
		stmtSelectChambreActive.setParameter("chambre", chambre);
		stmtSelectChambreActive.setParameter("dateDebut", dateDebut);
		stmtSelectChambreActive.setParameter("dateFin", dateFin);
		
		return !stmtSelectChambreActive.getResultList().isEmpty();
	}
	
	public List<Reservation> getReservations(Client client) {		
		stmtSelectClient.setParameter("client", client);
		return stmtSelectClient.getResultList();
	}
	
	public List<Reservation> getReservation(Client client, Chambre chambre, Date dateDebut, Date dateFin) {
		stmtSelect.setParameter("client", client);
		stmtSelect.setParameter("chambre", chambre);
		stmtSelect.setParameter("dateDebut", dateDebut);
		stmtSelect.setParameter("dateFin", dateFin);
		
		return stmtSelect.getResultList();
	}
	
	public void reserver(Reservation reservation) {
		cx.getConnection().persist(reservation);
	}
}
