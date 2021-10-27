package AubergeInn;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Services {
	private static final String QUERYSELECT = "SELECT * FROM Services WHERE idService = ?";
	private static final String QUERYINSERT = "INSERT INTO Services(idService, description, surplusPrix) VALUES(?, ?, ?)";
	
	private TypedQuery<Service> stmtSelect;
	private TypedQuery<Service> stmtInsert;
	private Connexion cx;
	
	public Services(Connexion cx) throws SQLException {
		this.cx = cx;
	}
		
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(int idService) throws SQLException {
		return true;
	}
	
	public Service getService(int idService) throws SQLException {
		return new Service();
	}
	
	public void creerService(int idService, String description, double surplusPrix) throws SQLException {
	}

}
