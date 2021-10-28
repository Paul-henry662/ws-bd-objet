package AubergeInn;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Reservation {
	@Id
	@GeneratedValue
	private long m_id;
	
	private long m_idReservation;
	private Client m_client;
	private Chambre m_chambre;
	private Date m_dateDebut;
	private Date m_dateFin;
	
	public Reservation() {
		
	}
	
	public Reservation(long idReservation, Client client, Chambre chambre) {
		this.m_idReservation = idReservation;
		this.m_client = client;
		this.m_chambre = chambre;
	}
	
	public long getID() {
		return m_id;
	}
	public long getIdReservation() {
		return m_idReservation;
	}
	public Client getClient() {
		return m_client;
	}
	public Chambre getChambre() {
		return m_chambre;
	}
	public Date getDateDebut() {
		return m_dateDebut;
	}
	public Date getDateFin() {
		return m_dateFin;
	}

}
