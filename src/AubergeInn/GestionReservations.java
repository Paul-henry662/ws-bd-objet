package AubergeInn;

import java.sql.Date;
import java.sql.SQLException;

public class GestionReservations {
	
	private Reservations reservations;
	private Chambres chambres;
	private Connexion cx;


	public GestionReservations(Reservations reservations, Chambres chambres) {
		this.cx = reservations.getConnexion();
		
		this.reservations = reservations;
		this.chambres = chambres;
	}
	
	public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin) throws SQLException, AubergeInnException {
		if(!reservations.existe(idClient, idChambre)) {
			reservations.reserver(idClient, idChambre, dateDebut, dateFin);
			chambres.setLibre(idChambre, false);
			cx.commit();
		}else {
			throw new AubergeInnException("Cette chambre est déjà réservée par un autre client");
		}
	}

}
