package AubergeInn;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Clients {
	private static final String QUERYSELECT = "SELECT c FROM Client c WHERE c.m_idClient = :idClient";
	private static final String QUERYINSERT = "INSERT INTO Clients(idClient, prenom, nom, age) VALUES(?, ?, ?, ?)";
	private static final String QUERYDELETE = "DELETE FROM Clients WHERE idClient = ?";
	
	private TypedQuery<Client> stmtSelect;
	private TypedQuery<Client> stmtInsert;
	private TypedQuery<Client> stmtDelete;
	private Connexion cx;
	
	public Clients(Connexion cx)  {
		this.cx = cx;
	}
	
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(int idClient)  {
		return true;
	}
	
	public Client getClient(int idClient)  {
		return new Client();
	}
	
	public void ajouterClient(int idClient, String prenom, String nom, int age)  {
	}
	
	public void supprimerClient(int idClient)  {
	}
}
