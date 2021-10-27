package AubergeInn;

import java.sql.SQLException;
import java.util.List;

public class GestionChambres {
	
	private Chambres chambres;
	private Services services;
	private ChambresServices chambresServices;
	private Reservations reservations;
	private Connexion cx;

	
	public GestionChambres(Chambres chambres, Services services, ChambresServices chambresServices, Reservations reservations) {
		this.cx = chambres.getConnexion();
		this.chambres = chambres;
		this.services = services;
		this.chambresServices = chambresServices;
		this.reservations = reservations;
	}
	
	public void ajouterChambre(int idChambre, String nom, String typeLit, double prixdeBase, boolean libre) throws SQLException, AubergeInnException {
		if(!chambres.existe(idChambre)) {
			chambres.ajouterChambre(idChambre, nom, typeLit, prixdeBase, libre);
			cx.commit();
		}
		else {
			throw new AubergeInnException("Cette chambre est déjà enregistrée");
		}
	}
	
	public void supprimerChambre(int idChambre) throws AubergeInnException, SQLException {
		if(chambres.existe(idChambre)) {
			if(!reservations.existeChambre(idChambre)) {
				chambres.supprimerChambre(idChambre);
				cx.commit();
			}else
				throw new AubergeInnException("Cette chambre a une réservation en cours ou future");
				
		}else 
			throw new AubergeInnException("Cette chambre n'existe pas");
	}
	
	public void afficherChambre(int idChambre) throws SQLException {
		if(chambres.existe(idChambre)) {
			List<Service> services = chambresServices.getServices(idChambre);
			
			Chambre tupleChambre = chambres.getChambre(idChambre);
			System.out.println(tupleChambre.toString());
			
			if(services.size() > 0) {
				System.out.println("Services inclus: ");
				
				for(Service tupleService : services)
					System.out.println(tupleService.toString());
			}
		}
		
	}
	
	public void afficherChambresLibres() throws SQLException {
		List<Chambre> chambresLibres = chambres.getChambresLibres();
		
		if(chambresLibres.size() > 0) {
			for(Chambre tupleChambre : chambresLibres) {				
				System.out.println(tupleChambre.toString());
			}
		}else 
			System.out.println("Il n'y a aucune chambre libre");
	}

}