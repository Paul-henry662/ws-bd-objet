package AubergeInn;

import java.sql.SQLException;

public class GestionAubergeInn {
	
	private GestionClients gestionClients;
	private GestionChambres gestionChambres;
	private GestionReservations gestionReservations;
	private GestionServices gestionServices;
	private Chambres chambres;
	private Clients clients;
	private ChambresServices chambresServices;
	private Services services;
	private Reservations reservations;
	private Connexion cx;



	public GestionAubergeInn(String serveur, String bd, String user, String password) throws AubergeInnException, SQLException {
		this.setConnexion(new Connexion(serveur, bd, user, password));
		this.setChambres(new Chambres(cx));
		this.setClients(new Clients(cx));
		this.setChambresServices(new ChambresServices(cx));
		this.setServices(new Services(cx));
		this.setReservations(new Reservations(cx));
		this.setGestionClients(new GestionClients(this.clients, this.reservations, this.chambresServices));
		this.setGestionChambres(new GestionChambres(this.chambres, this.services, this.chambresServices, this.reservations));
		this.setGestionServices(new GestionServices(this.chambres, this.services, this.chambresServices));
		this.setGestionReservations(new GestionReservations(this.reservations, this.clients, this.chambres));
	}



	public GestionClients getGestionClients() {
		return gestionClients;
	}



	public void setGestionClients(GestionClients gestionClients) {
		this.gestionClients = gestionClients;
	}



	public GestionChambres getGestionChambres() {
		return gestionChambres;
	}



	public void setGestionChambres(GestionChambres gestionChambres) {
		this.gestionChambres = gestionChambres;
	}



	public GestionReservations getGestionReservations() {
		return gestionReservations;
	}



	public void setGestionReservations(GestionReservations gestionReservations) {
		this.gestionReservations = gestionReservations;
	}



	public GestionServices getGestionServices() {
		return gestionServices;
	}



	public void setGestionServices(GestionServices gestionServices) {
		this.gestionServices = gestionServices;
	}



	public Chambres getChambres() {
		return chambres;
	}



	public void setChambres(Chambres chambres) {
		this.chambres = chambres;
	}



	public Clients getClients() {
		return clients;
	}



	public void setClients(Clients clients) {
		this.clients = clients;
	}



	public ChambresServices getChambresServices() {
		return chambresServices;
	}



	public void setChambresServices(ChambresServices chambresServices) {
		this.chambresServices = chambresServices;
	}



	public Services getServices() {
		return services;
	}



	public void setServices(Services services) {
		this.services = services;
	}



	public Reservations getReservations() {
		return reservations;
	}



	public void setReservations(Reservations reservations) {
		this.reservations = reservations;
	}



	public Connexion getConnexion() {
		return cx;
	}



	public void setConnexion(Connexion cx) {
		this.cx = cx;
	}
		
}
	
	




