package AubergeInn;

import java.sql.SQLException;
import java.util.List;

public class GestionClients {
	
	private Clients clients;
	private Reservations reservations;
	private Connexion cx;

	public GestionClients(Clients clients, Reservations reservations) {
		this.cx = clients.getConnexion();
		this.clients = clients;
		this.reservations = reservations;
	}
	
	public void ajouterClient(int idClient, String Prenom, String Nom, int Age ) throws SQLException, AubergeInnException {
		if(!clients.existe(idClient)) {
			clients.ajouterClient(idClient, Prenom, Nom, Age);
			cx.commit();
		}
		else
			throw new AubergeInnException("Ce client est d�j� enregistr�.");
	}
	
	public void supprimerClient(int idClient) throws SQLException, AubergeInnException {
		if(clients.existe(idClient)) {
			if(!reservations.existeClient(idClient)) {
				clients.supprimerClient(idClient);
				cx.commit();
			}else
				throw new AubergeInnException("Ce client a une ou plusieurs r�servation(s) en cours");
		}
		else
			throw new AubergeInnException("Ce client n'existe pas.");
	}
	
	public void afficherClient(int idClient) throws SQLException, AubergeInnException {
		if(clients.existe(idClient)) {
			Client tupleClient = clients.getClient(idClient);
			List<Reservation> reservationsClient = reservations.getReservations(idClient);
			
			System.out.println(tupleClient.toString());
			System.out.println("R�servations: ");
			
			if(reservationsClient.size() > 0) {
				for(Reservation tupleReservation : reservationsClient) 
					System.out.println(tupleReservation.toString());
			}
			else
				System.out.println("Aucune r�servation enregistr�e pour ce client");
			
			cx.commit();
		}
		else
			throw new AubergeInnException("Ce client n'existe pas.");
	}
}