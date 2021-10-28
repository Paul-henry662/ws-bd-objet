package AubergeInn;

import java.sql.SQLException;
import java.util.List;

public class GestionClients {
	
	private Clients clients;
	private Reservations reservations;
	private ChambresServices chambresServices;
	private Connexion cx;

	public GestionClients(Clients clients, Reservations reservations, ChambresServices chambresServices) {
		this.cx = clients.getConnexion();
		this.clients = clients;
		this.reservations = reservations;
		this.chambresServices = chambresServices;
	}
	
	public void ajouterClient(Client client) throws SQLException, AubergeInnException {
		cx.demarreTransaction();
		
		if(!clients.existe(client.getIdClient())) {
			clients.ajouterClient(client);
			cx.commit();
		}
		else {
			cx.rollback();
			throw new AubergeInnException("Ce client est déjà enregistré.");
		}
	}
	
	public void supprimerClient(long idClient) throws SQLException, AubergeInnException {
		cx.demarreTransaction();
		
		if(clients.existe(idClient)) {
			Client client = clients.getClient(idClient);
			if(!reservations.existeClient(client)) {
				clients.supprimerClient(client);
				cx.commit();
			}
			else {
				cx.rollback();
				throw new AubergeInnException("Ce client a une ou plusieurs reservation(s) en cours ou future(s)");
			}
		
		}
		else {
			cx.rollback();
			throw new AubergeInnException("Ce client n'existe pas.");
		}
	}
	
	public void afficherClient(long idClient) throws AubergeInnException {
		cx.demarreTransaction();
		
		if(clients.existe(idClient)) {
			Client client = clients.getClient(idClient);
			List<Reservation> reservationsClient = reservations.getReservations(client);
			
			System.out.println(client.toString());
			System.out.println("-> Reservations:");
			
			if(reservationsClient.size() > 0) {
				for(Reservation reservation : reservationsClient) {
					Chambre chambre = reservation.getChambre();
					double prixTotal = chambre.getPrixDeBase();
					List<Service> servicesChambre = chambresServices.getServices(chambre);
					
					for(Service service : servicesChambre)
						prixTotal += service.getSurplusPrix();
					
					System.out.println(reservation.toString());
					System.out.println("-> Prix total de la reservation: "+prixTotal+" FCFA");
				}
			}else 
				System.out.println("\t\tAucune reservation enregistree pour ce client.");
			
			cx.commit();
		}else {
			cx.rollback();
			throw new AubergeInnException("Ce client n'existe pas.");
		}
	}
}