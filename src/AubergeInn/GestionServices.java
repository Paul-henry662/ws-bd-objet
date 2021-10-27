package AubergeInn;

import java.sql.SQLException;

public class GestionServices {
	
	private Chambres chambres;
	private ChambresServices chambresServices;
	private Services services;
	private Connexion cx;


	public GestionServices(Chambres chambres, Services services, ChambresServices chambresServices) {
		this.cx = services.getConnexion();
		this.chambres = chambres;
		this.services = services;
		this.chambresServices = chambresServices;
	}
	
	public void creerService(int idService, String description, double surplusPrix) throws SQLException, AubergeInnException {

		if(!services.existe(idService)) {
			services.creerService(idService, description, surplusPrix);
			cx.commit();
		}else {
			throw new AubergeInnException("Ce service existe déjà");
		}
	}

	public void inclureService(int idService, int idChambre) throws SQLException, AubergeInnException {
	
		if(!chambresServices.existe(idChambre, idService)) {
			if(services.existe(idService) && chambres.existe(idChambre)) {
				chambresServices.InclureService(idChambre, idService);
				cx.commit();
			}
			else
				throw new AubergeInnException("La chambre ou le service n'existe pas.");
		}else {
			throw new AubergeInnException("Cette chambre inclut déjà ce service");
		}
	}
	
	public void enleverService(int idService, int idChambre) throws AubergeInnException, SQLException {
		if(chambresServices.existe(idChambre, idService)) {
			chambresServices.enleverService(idChambre, idService);
			cx.commit();
		}else {
			throw new AubergeInnException("Ce service n'est pas associé à cette chambre");
		}
	}
	
}
