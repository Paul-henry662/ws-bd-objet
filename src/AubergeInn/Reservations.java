package AubergeInn;

import java.sql.Date;
import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reservations {
	private static final String QUERYSELECT = "SELECT * FROM Reservations WHERE idClient = ? AND idChambre = ?";
	private static final String QUERYINSERT = "INSERT INTO Reservations(idClient, idChambre, dateDebut, dateFin) VALUES(?, ?, ?, ?)";
	private static final String QUERYSELECTCLIENT = "SELECT * FROM Reservations WHERE idClient = ?";
	private static final String QUERYSELECTCHAMBRE = "SELECT * FROM Reservations WHERE idChambre = ?";

	private TypedQuery<Reservation> stmtSelect;
	private TypedQuery<Reservation> stmtInsert;
	private TypedQuery<Reservation> stmtSelectClient;
	private TypedQuery<Reservation> stmtSelectChambre;

	private Connexion cx;
	
	public Reservations(Connexion cx) throws SQLException {
		this.cx = cx;
	}
	
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(int idClient, int idChambre) throws SQLException {
		return true;
	}
	
	public boolean existeClient(int idClient) throws SQLException {
		return true;
	}
	
	public boolean existeChambre(int idChambre) throws SQLException {
		return true;
	}
	
	public List<Reservation> getReservations(int idClient) throws SQLException {		
		return new ArrayList<Reservation>();
	}
	
	public Reservation getReservation(int idClient, int idChambre) throws SQLException {
		return new Reservation();
	}
	
	public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin) throws SQLException {		
	}
}
