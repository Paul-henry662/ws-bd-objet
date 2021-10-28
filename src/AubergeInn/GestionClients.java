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
	
	public void ajouterClient(Client client) throws SQLException, AubergeInnException {
		if(!clients.existe(client)) {
			clients.ajouterClient(client);
			cx.commit();
		}
		else
			throw new AubergeInnException("Ce client est d�j� enregistr�.");
	}
	
	public void supprimerClient(Client client) throws SQLException, AubergeInnException {
		if(clients.existe(client)) {
			if(!reservations.existeClient(client)) {
				clients.supprimerClient(client);
				cx.commit();
			}else
				throw new AubergeInnException("Ce client a une ou plusieurs r�servation(s) en cours");
		}
		else
			throw new AubergeInnException("Ce client n'existe pas.");
	}
	
	public void afficherClient(Client client) throws SQLException, AubergeInnException {
		if(clients.existe(client)) {
			Client tupleClient = clients.getClient(client);
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