package AubergeInn;

import javax.persistence.*;

@Entity
public class Chambre {
	@Id
	@GeneratedValue
	private long m_id;
	
	private long m_idChambre;
	private String m_nom;
	private String m_typeLit;
	private double m_prixDeBase;
	
	public Chambre() {
		
	}
	
	public Chambre(long idChambre, String nom, String typeLit, double prixDeBase) {
		this.m_idChambre = idChambre;
		this.m_nom = nom;
		this.m_typeLit = typeLit;
		this.m_prixDeBase = prixDeBase;
	}

	public long getID() {
		return m_id;
	}

	public long getIdChambre() {
		return m_idChambre;
	}

	public String getNom() {
		return m_nom;
	}

	public String getTypeLit() {
		return m_typeLit;
	}

	public double getM_prixDeBase() {
		return m_prixDeBase;
	}

}

