package AubergeInn;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Clients {
	private static final String QUERYSELECT = "SELECT c FROM Client c WHERE c.m_idClient = :idClient";
	
	private TypedQuery<Client> stmtSelect;
	private Connexion cx;
	
	public Clients(Connexion cx)  {
		this.cx = cx;
		
		stmtSelect = cx.getConnection().createQuery(QUERYSELECT, Client.class);
	}
	
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(int idClient)  {
		stmtSelect.setParameter("idClient", idClient);
		return !stmtSelect.getResultList().isEmpty(); 
		
	}
	
	public Client getClient(int idClient)  {
		stmtSelect.setParameter("idClient", idClient);
		return stmtSelect.getSingleResult();
		
	}
	
	public void ajouterClient(Client client)  {
		cx.getConnection().persist(client);
	}
	
	public void supprimerClient(Client client)  {
		cx.getConnection().remove(client);
	}
}
