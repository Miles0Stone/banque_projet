package banqueProjet;

public class Compte {

    private int numero;
    private int codeTypeCompte;
    private int codeTitulaire;
    private float solde;
    
    
    public Compte(int numero, int codeTypeCompte, int codeTitulaire, float solde) {
        this.numero = numero;
        this.codeTypeCompte = codeTypeCompte;
        this.codeTitulaire = codeTitulaire;
        this.solde = solde;
    }


    public int getNumero() {
        return numero;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public int getCodeTypeCompte() {
        return codeTypeCompte;
    }


    public void setCodeTypeCompte(int codeTypeCompte) {
        this.codeTypeCompte = codeTypeCompte;
    }


    public int getCodeTitulaire() {
        return codeTitulaire;
    }


    public void setCodeTitulaire(int codeTitulaire) {
        this.codeTitulaire = codeTitulaire;
    }


    public float getSolde() {
        return solde;
    }


    public void setSolde(float solde) {
        this.solde = solde;
    }


    @Override
    public String toString() {
        return "Compte codeTitulaire : " + codeTitulaire + ", codeTypeCompte : " + codeTypeCompte + ", numero : " + numero
                + ", solde : " + solde + "";
    }

 
    
}
