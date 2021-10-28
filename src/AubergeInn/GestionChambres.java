package AubergeInn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GestionChambres {
	
	private Chambres chambres;
	private ChambresServices chambresServices;
	private Reservations reservations;
	private Connexion cx;

	
	public GestionChambres(Chambres chambres, Services services, ChambresServices chambresServices, Reservations reservations) {
		this.cx = chambres.getConnexion();
		this.chambres = chambres;
		this.chambresServices = chambresServices;
		this.reservations = reservations;
	}
	
	public void ajouterChambre(int idChambre, String nom, String typeLit, double prixDeBase) throws AubergeInnException {
		cx.demarreTransaction();
		
		if(!chambres.existe(idChambre)) {
			chambres.ajouterChambre(new Chambre(idChambre, nom, typeLit, prixDeBase));
			cx.commit();
		}
		else {
			cx.rollback();
			throw new AubergeInnException("Cette chambre est deja enregistree");
		}
	}
	
	public void supprimerChambre(int idChambre) throws AubergeInnException {
		cx.demarreTransaction();
		
		if(chambres.existe(idChambre)) {
			if(!reservations.existeChambre(chambres.getChambre(idChambre))) {
				chambres.supprimerChambre(chambres.getChambre(idChambre));
				cx.commit();
			}else {
				cx.rollback();
				throw new AubergeInnException("Cette chambre a une reservation en cours ou future");
			}
				
		}else {
			cx.rollback();
			throw new AubergeInnException("Cette chambre n'existe pas");
		}
	}
	
	public void afficherChambre(int idChambre) throws AubergeInnException {
		cx.demarreTransaction();
		
		if(chambres.existe(idChambre)) {
			Chambre chambre = chambres.getChambre(idChambre);
			List<Service> servicesChambre = chambresServices.getServices(chambre);
			
			System.out.println(chambre.toString());
			System.out.println("-> Service inclus: ");
			
			if(servicesChambre.size() > 0) {
				for(Service service : servicesChambre)
					System.out.println(service.toString());
			}else {
				System.out.println("Aucun service.");
			}
		}else {
			cx.rollback();
			throw new AubergeInnException("Cette chambre n'existe pas");
		}
		cx.commit();
	}
	
	public void afficherChambresLibres() {
		cx.demarreTransaction();
		
		List<Chambre> allChambres = chambres.getAllChambres();
		List<Chambre> chambresLibres = new ArrayList<Chambre>();
		
		System.out.println(Calendar.getInstance().getTime());
		
		for(Chambre chambre : allChambres) {
			if(!reservations.existeChambreActive(chambre, Calendar.getInstance().getTime(), Calendar.getInstance().getTime()));
				chambresLibres.add(chambre);
		}
		
		if(chambresLibres.size() > 0) {
			for(Chambre chambre : chambresLibres)
				System.out.println(chambre.toString());
		}else {
			System.out.println("\nIl n'y a aucune chambre libre");
		}
		
		cx.commit();
		
	}

}