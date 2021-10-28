package AubergeInn;

import java.sql.Date;
import java.sql.SQLException;

public class GestionReservations {
	
	private Reservations reservations;
	private Clients clients;
	private Chambres chambres;
	private Connexion cx;


	public GestionReservations(Reservations reservations, Clients clients,Chambres chambres) {
		this.cx = reservations.getConnexion();
		
		this.reservations = reservations;
		this.chambres = chambres;
		this.clients = clients;
	}
	
	public void reserver(long idClient, long idChambre, Date dateDebut, Date dateFin) throws SQLException, AubergeInnException {
		cx.demarreTransaction();
		
		if(clients.existe(idClient) && chambres.existe(idChambre)) {
			Client client = clients.getClient(idClient);
			Chambre chambre = chambres.getChambre(idChambre);
			
			if(!reservations.existeChambreActive(chambre, dateDebut, dateFin)) {
				reservations.reserver(new Reservation(client, chambre, dateDebut, dateFin));
				cx.commit();
			}else {
				cx.rollback();
				throw new AubergeInnException("Il y a deja une reservation pour cette chambre dans cet intervalle de temps");
			}
			
		}else {
			cx.rollback();
			throw new AubergeInnException("La chambre ou le client n'existe pas");
		}
	}

}
