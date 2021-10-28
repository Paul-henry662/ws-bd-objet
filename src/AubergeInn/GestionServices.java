package AubergeInn;

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
	
	public void creerService(int idService, String description, double surplusPrix) throws AubergeInnException {
		cx.demarreTransaction();
		
		if(!services.existe(idService)) {
			services.creerService(new Service(idService, description, surplusPrix));
			cx.commit();
		}else {
			cx.rollback();
			throw new AubergeInnException("Ce service existe deja");
		}
	}

	public void inclureService(int idService, int idChambre) throws AubergeInnException {
		cx.demarreTransaction();
		
		if(chambres.existe(idChambre) && services.existe(idService)) {
			Chambre chambre = chambres.getChambre(idChambre);
			Service service = services.getService(idService);
			
			if(!chambresServices.existe(chambre, service)) {
				chambresServices.inclureService(new ChambreService(chambre, service));
				cx.commit();
			}else {
				cx.rollback();
				throw new AubergeInnException("Cette chambre inclut deja ce service");
			}
		}else {
			cx.rollback();
			throw new AubergeInnException("La chambre ou le service n'existe pas");
		}
	}
	
	public void enleverService(int idService, int idChambre) throws AubergeInnException {
		cx.demarreTransaction();
		
		if(chambres.existe(idChambre) && services.existe(idService)) {
			Chambre chambre = chambres.getChambre(idChambre);
			Service service = services.getService(idService);
			
			if(chambresServices.existe(chambre, service)) {
				chambresServices.enleverService(chambresServices.getChambreService(chambre, service));
				cx.commit();
			}else {
				cx.rollback();
				throw new AubergeInnException("Cette chambre n'inclut pas ce service");
			}
		}else {
			cx.rollback();
			throw new AubergeInnException("La chambre ou le service n'existe pas");
		}
	}
	
}
