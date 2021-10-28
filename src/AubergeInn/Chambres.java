package AubergeInn;

import javax.persistence.*;
import java.util.List;

public class Chambres {
	private static final String QUERYSELECT = "SELECT c FROM Chambre c WHERE c.m_idChambre = :idChambre";
	private static final String QUERYSELECTALL = "SELECT c FROM Chambre c";
	private static final String QUERYCHAMBRESLIBRES	= "";
	
	private TypedQuery<Chambre> stmtSelect;
	private TypedQuery<Chambre> stmtSelectAll;
	private TypedQuery<Chambre> stmtChambresLibres;
	private Connexion cx;
	
	public Chambres(Connexion cx) {
		this.cx = cx;
		
		stmtSelect = cx.getConnection().createQuery(QUERYSELECT, Chambre.class);
		stmtSelectAll = cx.getConnection().createQuery(QUERYSELECTALL, Chambre.class);
		stmtChambresLibres = cx.getConnection().createQuery(QUERYCHAMBRESLIBRES, Chambre.class);
	}
	
	public Connexion getConnexion() {
		return this.cx;
	}
	
	public boolean existe(long idChambre) {		
		stmtSelect.setParameter("idChambre", idChambre);
		return !stmtSelect.getResultList().isEmpty();
	}
	
	public Chambre getChambre(long idChambre) {
		stmtSelect.setParameter("idChambre", idChambre);
		return stmtSelect.getSingleResult();
	}
	
	public void ajouterChambre(Chambre chambre) {
		cx.getConnection().persist(chambre);
	}
	
	public void supprimerChambre(Chambre chambre) {
		cx.getConnection().remove(chambre);
	}
	
	public List<Chambre> getChambresLibres() {
		return stmtChambresLibres.getResultList();
	}
	
	public List<Chambre> getAllChambres(){
		return stmtSelectAll.getResultList();
	}
}
