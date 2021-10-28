package AubergeInn;

import javax.persistence.*;

@Entity
public class Client {
	@Id
	@GeneratedValue
	private long m_id;
	
	private long m_idClient;
	private String m_prenom;
	private String m_nom;
	private int m_age;
	
	public Client() {
	
	}
	
	public Client(long idClient, String prenom, String nom, int age) {
		this.m_idClient = idClient;
		this.m_prenom = prenom;
		this.m_nom = nom;
		this.m_age = age;
	}

	public long getID() {
		return m_id;
	}

	public long getIdClient() {
		return m_idClient;
	}

	public String getPrenom() {
		return m_prenom;
	}

	public String getNom() {
		return m_nom;
	}

	public int getAge() {
		return m_age;
	}
}
