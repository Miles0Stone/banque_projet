package banqueProjet;

import java.sql.Date;

public class Operations {
	
	private int numero;
	private int numCompte;
	private Date date;
	private String libelle;
	private float montant;
	private char typeOf;
	
	
	public Operations(int numero, int numCompte, Date date, String libelle, float montant, char typeOf) {
		
		super();
		this.numero = numero;
		this.numCompte = numCompte;
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
		this.typeOf = typeOf;
		
		
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public int getNumCompte() {
		return numCompte;
	}


	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}


	public char getTypeOf() {
		return typeOf;
	}


	public void setTypeOf(char typeOf) {
		this.typeOf = typeOf;
	}


	@Override
	public String toString() {
		return "Operations numero : " + numero + ", numCompte : " + numCompte + ", date : " + date + ", libelle : " + libelle
				+ ", montant : " + montant + ", typeOf : " + typeOf + "";
	}
	
	
	
	

}
