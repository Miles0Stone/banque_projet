package banqueProjet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Requetes {

//PAUL ******************************************** COMPTE **********************************************************

     public static Compte getCompteById(int numero) throws SQLException {
 
 
        String req = "SELECT * FROM COMPTE WHERE numero = " + numero; 
        ResultSet resultat = AccesBD.executerQuery(req);
        resultat.next();
 
        Compte compte = new Compte();
    
        compte.setNumero(resultat.getInt("numero"));
        compte.setCodeTypeCompte(Requetes.getTypedecompteById(resultat.getInt("codeTypeCompte")));
        compte.setCodeTitulaire(Requetes.getTitulaireById(resultat.getInt("codeTitulaire")));
        compte.setSolde(resultat.getFloat("solde"));

        return compte;

    }


//CREATE COMPTE

    public static void createCompte(Compte compte) throws SQLException {
    
        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("INSERT INTO compte VALUES (?,?,?,?)");
        PreparedStatement.setInt(1, compte.getNumero());
        PreparedStatement.setInt(2,compte.getCodeTypeCompte().getId());
        PreparedStatement.setInt(3, compte.getCodeTitulaire().getCode());
        PreparedStatement.setFloat(4, compte.getSolde());
        PreparedStatement.executeUpdate();
        
       } 

// DELETE COMPTE

   public static void deleteCompte (int numero) throws SQLException {
        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("DELETE FROM compte WHERE numero= ?");

        PreparedStatement.setInt(1, numero);
        PreparedStatement.executeUpdate();
   }
 
//MODIFS COMPTE
   
    public static void modifCompte (int numero, float solde) throws SQLException{
   
        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("UPDATE compte SET solde = ? WHERE numero = ?");

        PreparedStatement.setFloat(1, solde);
        PreparedStatement.setInt(2, numero);
        PreparedStatement.executeUpdate();
   }

//MOHAMMED *************************************** TITULAIRE ******************************************************


   public static Titulaire getTitulaireById (int id) throws SQLException{
        String requete = "select * from titulaire where code = " + id;
        ResultSet resultat = AccesBD.executerQuery(requete);

        Titulaire titulaire = new Titulaire();

        resultat.next();
        titulaire.setCode(resultat.getInt("code"));
        titulaire.setPrenom(resultat.getString("prenom"));
        titulaire.setNom(resultat.getString("nom"));
        titulaire.setAdresse(resultat.getString("adresse"));
        titulaire.setCodePostal(resultat.getInt("codePostal"));

        return titulaire;
}

// CREATE TITULAIRE
    
    public static void creerTitulaire(Titulaire titulaire) throws SQLException {
        String requete = "INSERT INTO TITULAIRE VALUES (?, ?, ?, ?, ?)";
        PreparedStatement PS = AccesBD.getConnection().prepareStatement(requete);
        PS.setInt(1, titulaire.getCode());
        PS.setString(2, titulaire.getPrenom());
        PS.setString(3, titulaire.getNom());
        PS.setString(4, titulaire.getAdresse());
        PS.setInt(5, titulaire.getCodePostal());
        PS.executeUpdate();
   }

// MODIF TITULAIRE

    public static void modifierTutilaire (Titulaire titulaire) throws SQLException {
        String requete = "UPDATE titulaire SET prenom = ?,nom = ?,adresse = ?, codePostal = ? WHERE code = ?";
        PreparedStatement PS = AccesBD.getConnection().prepareStatement(requete);
        PS.setString(1, titulaire.getPrenom());
        PS.setString(2, titulaire.getNom());
        PS.setString(3, titulaire.getAdresse());
        PS.setInt(4, titulaire.getCodePostal());
        PS.setInt(5, titulaire.getCode());
        PS.executeUpdate();
    }

// DELETE TITULAIRE

    public static void deleteTitulaire(int id) throws SQLException {
        String requete = "DELETE FROM titulaire WHERE code = ?";
        PreparedStatement PS = AccesBD.getConnection().prepareStatement(requete);
        PS.setInt(1, id);
        PS.executeUpdate();
    }

//THOMAS ******************************************* TYPE DE COMPTE **********************************************************

    public static Typedecompte getTypedecompteById(int i) throws SQLException {
        String req = "SELECT * FROM typecompte WHERE code =" + i;
        ResultSet resultat = AccesBD.executerQuery(req);

        Typedecompte typedecompte = new Typedecompte();

        while(resultat.next()) {
            typedecompte.setId(resultat.getInt("code"));
            typedecompte.setType(resultat.getString("intitule"));
        }

        return typedecompte;
        }
   

// AJOUTER UN TYPE DE COMPTE
//Pour l'auto-incrémenter préciser 0 dans values
    
    public static void  ajouterTypedecompte(String typeDeCompte) throws SQLException{
        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("INSERT INTO typecompte VALUES (0 , ?)");


        PreparedStatement.setString(1, typeDeCompte);
        PreparedStatement.executeUpdate();
    }

//DELETE UN TYPE DE COMPTE

    public static void  supprimerTypedecompteById(int id) throws SQLException{

        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("DELETE FROM typecompte WHERE code = ?");

        PreparedStatement.setInt(1, id);
        PreparedStatement.executeUpdate();
    }

//MODIFS TYPE DE COMPTE
    public static void  modifierTypedecompte(Typedecompte typedecompte) throws SQLException{


        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("UPDATE typecompte SET intitule = ? WHERE code = ?");

        PreparedStatement.setString(1, typedecompte.getType());
        PreparedStatement.setInt(2, typedecompte.getId());
        PreparedStatement.executeUpdate();

    }

//********************************** Lister les comptes pour un Titulaire *******************************************

    public static ArrayList<Compte> getCompteTitulaire(int id) throws SQLException {
        
        String requete =  "select * from compte where codeTitulaire = " + id ;
        ArrayList<Compte> comptes = new ArrayList<>();

        ResultSet resultat = AccesBD.executerQuery(requete);

        while (resultat.next()) {
            Compte compte = new Compte();
            compte.setNumero(resultat.getInt("numero"));
            compte.setCodeTypeCompte(Requetes.getTypedecompteById(resultat.getInt("codeTypeCompte")));
            compte.setCodeTitulaire(Requetes.getTitulaireById(resultat.getInt("codeTitulaire")));
            compte.setSolde(resultat.getFloat("solde"));
            comptes.add(compte);
        }

        return comptes;
    }
    
//***************************** Lister des opérations pour un compte sélectionné. **************************************

    public static ArrayList<Operations> getOperationsByNumCompte(int numCompte) throws SQLException {

        ArrayList<Operations> operations = new ArrayList<>();

        String req = "SELECT * FROM operations WHERE numeroCompte =" + numCompte;
        ResultSet resultat = AccesBD.executerQuery(req);

        while(resultat.next()) {

            Operations operation = new Operations();
            operation.setNumero(resultat.getInt("numero"));
            operation.setNumCompte(Requetes.getCompteById(resultat.getInt("numeroCompte")));
            operation.setDate(resultat.getDate("date"));
            operation.setLibelle(resultat.getString("libelle"));
            operation.setMontant(resultat.getFloat("montant"));
            operation.setTypeOf(resultat.getString("typeop"));
            operations.add(operation);
        }

        return operations;

        }
        
//********************************** Enregistrer une opération sur un compte ******************************************
       
        public static void operationSurCompte( int idOp) throws SQLException {
           
            String requete = "select typeop from operations where numero =" + idOp;
    
            ResultSet typeop = AccesBD.executerQuery(requete);
            typeop.next();
            String symbol = typeop.getString("typeop");
    
            String req = "update compte lol join operations, compte set compte.solde = compte.solde"  + symbol + " operations.montant where operations.numeroCompte = compte.numero and operations.numero = ?" ;
    
            PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement(req);
    
            PreparedStatement.setInt(1, idOp);
            PreparedStatement.executeUpdate();
    
        }
       
public static void main(String[] args) throws SQLException {

//deleteCompte (10012);
    
//  Compte compte = new Compte(10012, getTypedecompteById(2), getTitulaireById(1004), 200);
// createCompte(compte);

//modifCompte (10009, 400);
  
   
 }




}
