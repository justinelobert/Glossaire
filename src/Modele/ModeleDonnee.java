package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agnes
 */

public class ModeleDonnee extends Modele {
	
	public static String afficheDescrib(int id) {
        Connection connexion = startConnection();
        Statement declaration;
        String def = "pas  enregistré";
        
        try {
            declaration = connexion.createStatement();

            String query= "SELECT definition FROM saisie WHERE id = "+id;
            ResultSet resultat = declaration.executeQuery(query);
            while (resultat.next()) {
            def= resultat.getString("definition");

            }
        } catch (SQLException e) {
         
            e.printStackTrace();
        }

        return def; 
    }
	
    public static ArrayList<Object[]> afficheLexiques() {
    	
    	ArrayList< Object[]> tabdynamique= new ArrayList<>();
    	
        try {

            /* Création de la connexion*/
            Connection connexion = startConnection();

            /* Création de l'objet gérant les requêtes */
            Statement declaration = connexion.createStatement();

            /* Requete */
            String query = "SELECT id, mot FROM saisie;";

            /* Exécution d'une requête de lecture */
            ResultSet resultat = declaration.executeQuery(query);

            /* Récupération des données */ 
            while (resultat.next()) {
                Object[] row = new Object[]{
                    resultat.getInt("id"), 
                    resultat.getString("mot")
                };
                
                tabdynamique.add(row);
                System.out.println(Arrays.toString(row));

            }

            /* fermeture du resultatSet */
            resultat.close();
            /* fermeture de la connexion */ 
            closeConnection(connexion);

        } catch (SQLException e) {
            System.err.println(
                    "Erreur d'affichage des catégories: " + e.getMessage()
            );
        }
		return tabdynamique;
    }

    /**
     * Ajout d'un catégorie
     *
     * @param nom
     */
    public static boolean ajouteLexique(String mot, String def) {
    	
    	boolean succes= false;
    	
        try {
        	
            /* Création de la connexion */
            Connection co = startConnection();

            /* Création de l'objet gérant les requetes */
            Statement declaration = co.createStatement();

            /* Requete */
            String query = "INSERT INTO saisie ( mot ,definition) VALUES ('" + mot + "', '" + def + "')";
            System.out.println(query);

            /* Execution d'une requete en écriture */
            int executeUpdate = declaration.executeUpdate(query);

            /* Traitement de l'insertion */
            if (executeUpdate == 1) {
                System.out.println("Insertion du mot et de la définition effectuée ! ");
                succes=true;
                
            } else {
                System.out.println("Insertion du mot et de la définition non effectuée.");
            }

            /* Fermeture de la connexion */
            closeConnection(co);
        } catch (SQLException e) {
            System.err.println("Erreur d'insertion du mot et de la définition " + e.getMessage());
        }
		return succes;
    }

    /**
     * Supression d'une categorie en précisant l'id
     *
     * @param id
     */
    public static void supprimerCategorie(int id) {
        try {
            /* Création de la connexion */
            Connection co = startConnection();
            /* Création de l'objet gérant les requetes */
            Statement declaration = co.createStatement();

            /* Requete */
            String query = "DELETE FROM categorie WHERE id = " + id;

            /* Execution d'une requete en écriture */
            int executeUpdate = declaration.executeUpdate(query);

            /* Traitement de l'insertion */
            if (executeUpdate == 1) {
                System.out.println("Suppression de la categorie effectuée ! ");
            } else {
                System.out.println("Suppression de la catégorie non effectuée.");
            }

            /* Fermeture de la connexion */
            closeConnection(co);
        } catch (SQLException e) {
            System.err.println("Erreur de suppression d'une catégorie " + e.getMessage());
        }
    }

	public static ArrayList<Object[]> afficheLexiquesLike(String text) {

		ArrayList< Object[]> tabdynamique= new ArrayList<>();
    	
        try {
		
		   Connection connexion = startConnection();

           /* Création de l'objet gérant les requêtes */
           Statement declaration = connexion.createStatement();

           /* Requete */
           String query = "SELECT id, mot FROM saisie WHERE mot LIKE '" + text + "%'" ;
           System.out.println(query);

           /* Exécution d'une requête de lecture */
           ResultSet resultat = declaration.executeQuery(query);

           /* Récupération des données */ 
           while (resultat.next()) {
               Object[] row = new Object[]{
                   resultat.getInt("id"), 
                   resultat.getString("mot")
               };
               
               tabdynamique.add(row);
               System.out.println(Arrays.toString(row));

           }

           /* fermeture du resultatSet */
           resultat.close();
           /* fermeture de la connexion */ 
           closeConnection(connexion);

       } catch (SQLException e) {
           System.err.println(
                   "Erreur d'affichage des catégories: " + e.getMessage()
           );
       }
		return tabdynamique;
   
		
	}

}
