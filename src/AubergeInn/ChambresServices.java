package AubergeInn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChambresServices {
	private static final String QUERYSELECT = "SELECT * FROM Chambres_Services WHERE idChambre = ? AND idService = ?";
	private static final String QUERYSELECTCHAMBRE = "SELECT * FROM Chambres_Services WHERE idChambre = ?";
	private static final String QUERYINSERT = "INSERT INTO Chambres_Services(idChambre, idService) VALUES(?, ?)";
	private static final String QUERYDELETE = "DELETE FROM Chambres_Services WHERE idChambre = ? AND idService = ?";
	
	private PreparedStatement stmtSelect;
	private PreparedStatement stmtSelectChambre;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private Connexion cx;
	
	public ChambresServices(Connexion cx) throws SQLException {
		this.cx = cx;
	}
	
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(int idChambre, int idService) throws SQLException {
		return true;
	}
	
	public ChambreService getChambreService(int idChambre, int idService) throws SQLException {
		return new ChambreService();
	}
	
	public void InclureService(int idChambre, int idService) throws SQLException {
	}
	
	public void enleverService(int idChambre, int idService) throws SQLException {
	}
	
	public List<Service> getServices(int idChambre) throws SQLException{		
		return new ArrayList<Service>();
	}
}
