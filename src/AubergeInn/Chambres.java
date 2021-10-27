package AubergeInn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Chambres {
	private static final String QUERYSELECT = "SELECT * FROM Chambres WHERE idChambre = ?";
	private static final String QUERYUPDATE = "UPDATE Chambres SET libre = ? WHERE idChambre = ?";
	private static final String QUERYINSERT = "INSERT INTO Chambres(idChambre, nom, typeLit, prixDeBase, libre) VALUES(?, ?, ?, ?, ?)";
	private static final String QUERYDELETE = "DELETE FROM Chambres WHERE idChambre = ?";
	private static final String QUERYCHAMBRESLIBRES	= "SELECT * FROM Chambres WHERE libre = true";
	
	private javax.persistence.TypedQuery<Chambre> stmtSelect;
	private TypedQuery<Chambre> stmtUpdate;
	private TypedQuery<Chambre> stmtInsert;
	private TypedQuery<Chambre> stmtDelete;
	private TypedQuery<Chambre> stmtChambresLibres;
	private Connexion cx;
	
	public Chambres(Connexion cx) {
		this.cx = cx;
	}
	
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(int idChambre) {		
		return true;
	}
	
	public Chambre getChambre(int idChambre) {
		return new Chambre();
	}
	
	public void ajouterChambre(int idChambre, String nom, String typeLit, double prixDeBase, boolean libre) {
	}
	
	public void supprimerChambre(int idChambre) {
	}
	
	public List<Chambre> getChambresLibres() {
		return new ArrayList<Chambre>();
	}
	
	public void setLibre(int idChambre, boolean b) {
	}
}
