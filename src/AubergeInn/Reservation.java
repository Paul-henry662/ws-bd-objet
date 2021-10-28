package AubergeInn;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Reservation {
	@Id
	@GeneratedValue
	private long m_id;
	
	private Client m_client;
	private Chambre m_chambre;
	private Date m_dateDebut;
	private Date m_dateFin;
	
	public Reservation() {
		
	}
	
	public Reservation(Client client, Chambre chambre, Date dateDebut, Date dateFin) {
		this.m_client = client;
		this.m_chambre = chambre;
		this.m_dateDebut = dateDebut;
		this.m_dateFin = dateFin;
	}
	
	public long getID() {
		return m_id;
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
	
	public String toString() {
		String str = "\n*** Reservation Chambre #"+this.getChambre().getIdChambre()+" ***\n"
					+"-> Date de debut: "+this.getDateDebut()+"\n"
					+"-> Date de fin: "+this.getDateFin()+"\n";
		
		return str;
	}

}
